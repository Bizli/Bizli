package com.test.parser;

import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class creatingMultipleXmls {

	public static void main(String[] args) throws IOException {
		File f1 = new File("G:\\Downloads Chrome\\Code\\Bijli\\260414013508S217146HPS47756    26042014001353101.xml");
		for(int i=0;i<=1000;i++){
				File f2 = new File("G:\\Downloads Chrome\\Code\\Bijli\\multi xmls\\260414013508S217146HPS47756    26042014001353101--"+i+".xml");
				copy(f1, f2);
		}
		
	}
    public static  void copy(File source, File destination) throws IOException {
        if (source == null) {
            throw new NullPointerException("Null Source");
        }
        if (destination == null) {
            throw new NullPointerException("Null Destination");
        }
        if (source.isDirectory()) {
            copyDirectory(source, destination);
        } else {
            copyFile(source, destination);
        }
    }

    public static  void copyDirectory(File source, File destination) throws IOException {
        copyDirectory(source, destination, null);
    }

    public static  void copyDirectory(File source, File destination, FileFilter filter) throws IOException {
        File nextDirectory = new File(destination, source.getName());
        if (!nextDirectory.exists() && !nextDirectory.mkdirs()) {// create the directory if necessary...
            Object[] filler = {nextDirectory.getAbsolutePath()};
            String message = "Dir Copy Failed";
            throw new IOException(message);
        }
        File[] files = source.listFiles();
        for (int n = 0; n < files.length; ++n) {// and then all the items below the directory...
            if (filter == null || filter.accept(files[n])) {
                if (files[n].isDirectory()) {
                    copyDirectory(files[n], nextDirectory, filter);
                } else {
                    copyFile(files[n], nextDirectory);
                }
            }
        }
    }

    public static  void copyFile(File source, File destination) throws IOException {
        // what we really want to do is create a file with the same name in that dir
        if (destination.isDirectory()) {
            destination = new File(destination, source.getName());
        }
        FileInputStream input = new FileInputStream(source);
        copyFile(input, destination);
    }

    public static  void copyFile(InputStream input, File destination) throws IOException {
        OutputStream output = null;
        try {
            output = new FileOutputStream(destination);
            byte[] buffer = new byte[1024];
            int bytesRead = input.read(buffer);
            while (bytesRead >= 0) {
                output.write(buffer, 0, bytesRead);
                bytesRead = input.read(buffer);
            }
        } catch (Exception e) {
            //
        } finally {
            input.close();
            output.close();
        }
        input = null;
        output = null;
    }

    private creatingMultipleXmls() {
    }
}