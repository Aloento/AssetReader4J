package com.QYun.AssetReader4J.Readers;

import java.io.*;

public class BinaryReader implements Closeable {
    private DataInputStream dStream;

    public BinaryReader(File file){
        try {
            dStream = new DataInputStream(new FileInputStream(file));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public int read(){
        int b = 0;
        try {
            b = dStream.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return b;
    }

    public byte[] read(int length){
        byte[] bs = new byte[length];
        byte[] rs = new byte[length];
        try {
            dStream.read(bs,0,length);
            System.arraycopy(bs, 0, rs, 0, length);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return rs;
    }

    public int readInt16(){
        byte[] bs = new byte[2];
        int temp = 0;
        try {
            dStream.read(bs,0,2);
            temp = HexUtil.byte2int(bs);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return temp;
    }

    public int readInt32(){
        byte[] bs = new byte[4];
        int temp = 0;
        try {
            dStream.read(bs,0,4);
            temp = HexUtil.byteArrayToInt(bs, 0);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return temp;
    }

    public int ToInt16(byte[] b,int start){
        byte[] bs = new byte[2];
        int temp = 0;
        System.arraycopy(b, start, bs, 0, 2);
        temp = HexUtil.byte2int(bs);
        return temp;
    }

    public int ToInt32(byte[] b,int start){
        byte[] bs = new byte[4];
        int temp = 0;
        System.arraycopy(b, start, bs, 0, 4);
        temp = HexUtil.byteArrayToInt(bs, 0);
        return temp;
    }

    public double readDouble(){
        byte[] bs = new byte[8];
        double b = 0;
        try {
            dStream.read(bs,0,8);
            b = HexUtil.byteArrayToDouble(bs, 0);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return b;
    }

    public double ToDouble(byte[] b,int start){
        byte[] bs = new byte[8];
        System.arraycopy(b, start, bs, 0, 8);
        return HexUtil.byteArrayToDouble(bs, 0);
    }

    public double[] readDoubles(int length){
        byte[] bs = new byte[length*8];
        double[] d = new double[length];
        try {
            dStream.read(bs,0,bs.length);
            d = HexUtil.getData(bs, 0);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return d;
    }

    public float readFloat(){
        byte[] bs = new byte[4];
        float f = 0;
        try {
            dStream.read(bs,0,4);
            f = HexUtil.byteArrayToFloat(bs, 0, 0);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return f;
    }

    public float ToFloat(byte[] b,int start){
        byte[] bs = new byte[4];
        System.arraycopy(b, start, bs, 0, 4);
        float f = HexUtil.byteArrayToFloat(bs, 0, 0);
        return f;
    }

    public float[] readFloats(int length){
        byte[] bs = new byte[length*4];
        float[] fs = new float[length];
        try {
            dStream.read(bs,0,bs.length);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String s = new String(bs);
        String[] ssr = s.split(",");
        for (int i = 0; i < fs.length; i++) {
            fs[i] = Float.parseFloat(ssr[i]);
        }
        return fs;
    }

    public char readChar(){
        byte[] bs = new byte[1];
        char ch = 0;
        try {
            dStream.read(bs,0,1);
            ch = HexUtil.byteArrayToChar(bs);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ch;
    }

    public char[] readChars(int length){
        byte[] bs = new byte[length];
        char[] ch = new char[length];
        try {
            dStream.read(bs,0,bs.length);
            ch = HexUtil.getChars(bs);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ch;
    }

    public String readString(int length){
        byte[] bs = new byte[length];
        String string = "";
        try {
            dStream.read(bs,0,bs.length);
            string = new String(bs);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return string;
    }

    public String readDate(String format){
        byte[] bs = new byte[8];
        String time = "";
        double time_d = 0;
        try {
            dStream.read(bs,0,8);
            time_d = HexUtil.byteArrayToDouble(bs, 0);
            time = HexUtil.doubleToDate(time_d, format);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return time;
    }

    public boolean ToBoolean(byte[] b,int start){
        byte[] bs = new byte[4];
        System.arraycopy(b, start, bs, 0, 4);
        return HexUtil.byteArrayToBoolean(bs, 0);
    }

    public void close(){
        try {
            dStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
