package com.ppf.springboot.untis.ioUntils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class IOUntil {

    public static void download(File file, InputStream inputStream, OutputStream outputStream) throws IOException {

        byte[] buff = new byte[1024];
        int length =0;
        while ((length = inputStream.read(buff))!=-1){

            outputStream.write(buff,0,length);
        }

        inputStream.close();

        outputStream.close();

    }


    public static  String getPath(final String path){
        path.replaceAll("/","\\"+File.separator);
        path.replaceAll("\\\\","\\"+File.separator);
        return path;
    }


}
