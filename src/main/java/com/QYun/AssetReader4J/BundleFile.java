package com.QYun.AssetReader4J;

import com.QYun.AssetReader4J.Entities.Struct.Header;
import com.QYun.AssetReader4J.Entities.Struct.Node;
import com.QYun.AssetReader4J.Entities.Struct.StorageBlock;
import com.QYun.AssetReader4J.Entities.Struct.StreamFile;
import com.QYun.AssetReader4J.Helpers.SevenZipHelper;
import com.QYun.AssetReader4J.Helpers.StreamCopyHelper;
import com.QYun.Stream.UnityStream;
import net.jpountz.lz4.LZ4Factory;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

public class BundleFile {
    private Header m_Header = new Header();
    private StorageBlock[] m_BlocksInfo;
    private Node[] m_DirectoryInfo;
    private StreamFile[] fileList;

    public BundleFile(UnityStream reader, File file) throws IOException {
        m_Header.signature = reader.readStringToNull();
        m_Header.version = reader.readInt();
        m_Header.unityVersion = reader.readStringToNull();
        m_Header.unityRevision = reader.readStringToNull();

        switch (m_Header.signature) {
            case "UnityArchive" -> {
            }
            case "UnityWeb", "UnityRaw" -> {
                if (m_Header.version == 6)
                    handleFS(reader, file);
            }
            case "UnityFS" -> handleFS(reader, file);
        }
    }

    private void handleFS(UnityStream reader, File file) throws IOException {
        readHeader(reader);
        readBlocksInfoAndDirectory(reader);
        var blocksStream = createBlocksStream();
        readBlocks(reader, blocksStream);
        readFiles(blocksStream, file);
    }

    private void readBlocks(UnityStream reader, UnityStream blocksStream) throws IOException {
        for (var blockInfo : m_BlocksInfo) {
            switch (blockInfo.flags & 0x3F) {
                case 1 -> {
                    var tmp = new ByteArrayOutputStream();
                    SevenZipHelper.streamDecompress(reader.toInputStream(), tmp, blockInfo.uncompressedSize);
                }
                case 2, 3 -> {
                    byte[] uncompressedBytes = new byte[blockInfo.uncompressedSize];
                    LZ4Factory.fastestInstance().fastDecompressor().decompress(
                            reader.readBytes(blockInfo.compressedSize), 0,
                            uncompressedBytes, 0, blockInfo.uncompressedSize);
                    blocksStream = new UnityStream(new ByteArrayInputStream(uncompressedBytes));
                }
                default -> blocksStream = new UnityStream(new ByteArrayInputStream(reader.readBytes(blockInfo.compressedSize)));
            }
        }
    }

    private void readFiles(UnityStream blocksStream, File file) throws IOException {
        fileList = new StreamFile[m_DirectoryInfo.length];
        for (int i = 0; i < fileList.length; i++) {
            var node = m_DirectoryInfo[i];
            var streamFile = new StreamFile();

            streamFile.file = file;
            streamFile.fileName = node.path;
            fileList[i] = streamFile;

            blocksStream.setPos(Math.toIntExact(node.offset));
            StreamCopyHelper.copyTo(blocksStream.toInputStream(), streamFile.stream, node.size);
        }
    }

    private UnityStream createBlocksStream() throws IOException {
        int uncompressedSizeSum = 0;
        for (var blocksInfo : m_BlocksInfo)
            uncompressedSizeSum += blocksInfo.uncompressedSize;
        return new UnityStream(new ByteArrayInputStream(new byte[uncompressedSizeSum]));
    }

    private void readHeader(UnityStream reader) {
        m_Header.size = reader.readLong();
        m_Header.compressedBlocksInfoSize = reader.readInt();
        m_Header.uncompressedBlocksInfoSize = reader.readInt();
        m_Header.flags = reader.readInt();
        if (!m_Header.signature.equals("UnityFS"))
            reader.readByte();
    }

    private void readBlocksInfoAndDirectory(UnityStream reader) throws IOException {
        byte[] blocksInfoBytes;
        if (m_Header.version >= 7)
            reader.alignStream(16);

        if ((m_Header.flags & 0x80) != 0) {
            int position = reader.getPos();
            reader.setPos((int) (reader.length - m_Header.compressedBlocksInfoSize));
            blocksInfoBytes = reader.readBytes(m_Header.compressedBlocksInfoSize);
            reader.setPos(position);
        } else blocksInfoBytes = reader.readBytes(m_Header.compressedBlocksInfoSize);

        ByteArrayInputStream blocksInfoCompressedStream = new ByteArrayInputStream(blocksInfoBytes);
        ByteArrayInputStream blocksInfoUncompressedStream;

        switch (m_Header.flags & 0x3F) {
            case 1 -> {
                var outputStream = new ByteArrayOutputStream(m_Header.uncompressedBlocksInfoSize);
                SevenZipHelper.streamDecompress(blocksInfoCompressedStream, outputStream, m_Header.uncompressedBlocksInfoSize);
                blocksInfoUncompressedStream = new ByteArrayInputStream(outputStream.toByteArray());
            }
            case 2, 3 -> {
                byte[] uncompressedBytes = new byte[m_Header.uncompressedBlocksInfoSize];
                LZ4Factory.fastestInstance().fastDecompressor().decompress(
                        blocksInfoBytes, 0, uncompressedBytes, 0, m_Header.uncompressedBlocksInfoSize);
                blocksInfoUncompressedStream = new ByteArrayInputStream(uncompressedBytes);
            }
            default -> blocksInfoUncompressedStream = blocksInfoCompressedStream;
        }

        var blocksInfoReader = new UnityStream(blocksInfoUncompressedStream);
        byte[] uncompressedDataHash = blocksInfoReader.readBytes(16);

        int blocksInfoCount = blocksInfoReader.readInt();
        m_BlocksInfo = new StorageBlock[blocksInfoCount];
        for (int i = 0; i < blocksInfoCount; i++) {
            var tmp = new StorageBlock();
            tmp.uncompressedSize = blocksInfoReader.readInt();
            tmp.compressedSize = blocksInfoReader.readInt();
            tmp.flags = blocksInfoReader.readShort();
            m_BlocksInfo[i] = tmp;
        }

        var nodesCount = blocksInfoReader.readInt();
        m_DirectoryInfo = new Node[nodesCount];
        for (int i = 0; i < nodesCount; i++) {
            var tmp = new Node();
            tmp.offset = blocksInfoReader.readLong();
            tmp.size = blocksInfoReader.readLong();
            tmp.flags = blocksInfoReader.readInt();
            tmp.path = blocksInfoReader.readStringToNull();
            m_DirectoryInfo[i] = tmp;
        }
    }

}
