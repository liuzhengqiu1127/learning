package com.lzq.tool;

import java.io.*;
import java.nio.channels.FileChannel;

public class FileTool {

    public static void copyFileByStream(File source, File target) throws IOException {
        try(InputStream inputStream = new FileInputStream(source);
        OutputStream outputStream = new FileOutputStream(target)){
            byte[] buffer = new byte[1024];
            int length;
            while ((length = inputStream.read(buffer))>0){
                outputStream.write(buffer,0,length);
            }
        }
    }

    public static void copyFileByChannel(File source, File target) throws IOException {
        try(FileChannel sourceFileChannel = new FileInputStream(source).getChannel();
        FileChannel targetFileChannel = new FileOutputStream(target).getChannel()){
            for (long count = sourceFileChannel.size(); count > 0;){
                long transfer = sourceFileChannel.transferTo(sourceFileChannel.position(),count,targetFileChannel);
                count -= transfer;
            }
        }
    }
}
