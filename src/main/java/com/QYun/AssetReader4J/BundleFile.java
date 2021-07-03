package com.QYun.AssetReader4J;

import com.QYun.AssetReader4J.Entities.Struct.Header;
import com.QYun.AssetReader4J.Entities.Struct.Node;
import com.QYun.AssetReader4J.Entities.Struct.StorageBlock;
import com.QYun.AssetReader4J.Entities.Struct.StreamFile;
import com.QYun.AssetReader4J.Helpers.SevenZipHelper;
import com.QYun.util.Stream.UnityStream;
import net.jpountz.lz4.LZ4Factory;

import java.io.File;
import java.io.IOException;

public class BundleFile {
    public final Header m_Header = new Header();
    public StreamFile[] fileList;
    private StorageBlock[] m_BlocksInfo;
    private Node[] m_DirectoryInfo;

    public BundleFile(UnityStream reader, File file) throws IOException {
        m_Header.signature = reader.ReadStringToNull();
        m_Header.version = reader.readInt();
        m_Header.unityVersion = reader.ReadStringToNull();
        m_Header.unityRevision = reader.ReadStringToNull();

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
                case 1 -> SevenZipHelper.streamDecompress(reader, blocksStream, blockInfo.uncompressedSize);
                case 2, 3 -> {
                    byte[] uncompressedBytes = new byte[blockInfo.uncompressedSize];
                    LZ4Factory.fastestInstance().fastDecompressor().decompress(
                            reader.readBytes(blockInfo.compressedSize), 0,
                            uncompressedBytes, 0, blockInfo.uncompressedSize);
                    blocksStream.write(uncompressedBytes);
                }
                default -> blocksStream.write(reader.readBytes(blockInfo.compressedSize));
            }
        }
    }

    private void readFiles(UnityStream blocksStream, File file) {
        fileList = new StreamFile[m_DirectoryInfo.length];
        for (int i = 0; i < fileList.length; i++) {
            var node = m_DirectoryInfo[i];
            var streamFile = new StreamFile();

            streamFile.file = file;
            streamFile.fileName = node.path;
            streamFile.stream = new UnityStream(Math.toIntExact(node.size));
            fileList[i] = streamFile;

            blocksStream.setPos(Math.toIntExact(node.offset));
            blocksStream.copyTo(streamFile.stream, node.size);
            streamFile.stream.rewind();
        }
    }

    private UnityStream createBlocksStream() {
        int uncompressedSizeSum = 0;
        for (var blocksInfo : m_BlocksInfo)
            uncompressedSizeSum += blocksInfo.uncompressedSize;
        return new UnityStream(uncompressedSizeSum);
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
            reader.AlignStream(16);

        if ((m_Header.flags & 0x80) != 0) {
            reader.mark();
            reader.setPos(Math.toIntExact((reader.trueLen - m_Header.compressedBlocksInfoSize)));
            blocksInfoBytes = reader.readBytes(m_Header.compressedBlocksInfoSize);
            reader.reset();
        } else blocksInfoBytes = reader.readBytes(m_Header.compressedBlocksInfoSize);

        UnityStream blocksInfoCompressedStream = new UnityStream(blocksInfoBytes);
        UnityStream blocksInfoUncompressedStream;

        switch (m_Header.flags & 0x3F) {
            case 1 -> {
                blocksInfoUncompressedStream = new UnityStream(m_Header.uncompressedBlocksInfoSize);
                SevenZipHelper.streamDecompress(blocksInfoCompressedStream, blocksInfoUncompressedStream, m_Header.uncompressedBlocksInfoSize);
            }
            case 2, 3 -> {
                byte[] uncompressedBytes = new byte[m_Header.uncompressedBlocksInfoSize];
                LZ4Factory.fastestInstance().fastDecompressor().decompress(
                        blocksInfoBytes, 0, uncompressedBytes, 0, m_Header.uncompressedBlocksInfoSize);
                blocksInfoUncompressedStream = new UnityStream(uncompressedBytes);
            }
            default -> blocksInfoUncompressedStream = blocksInfoCompressedStream;
        }

        UnityStream blocksInfoReader = blocksInfoUncompressedStream.setToReadOnly();
        blocksInfoReader.rewind();
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
            tmp.path = blocksInfoReader.ReadStringToNull();
            m_DirectoryInfo[i] = tmp;
        }
    }
}
