/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

//THIS CLASS IS NOT NEEDED.

package com.myapp.util;

import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

/**
 *
 * @author palwal
 */
public class FileReader {

    private static int count = 1010;//241024; //10 MB

    public static void main(String[] args) throws Exception {
        RandomAccessFile memoryMappedFile = new RandomAccessFile("HomeStuff.alwal", "rw");

        //Mapping a file into memory
        MappedByteBuffer out = memoryMappedFile.getChannel().map(FileChannel.MapMode.READ_WRITE, 0, count);

        //Writing into Memory Mapped File
        for (int i = 0; i < count; i++) {
            out.put((byte) 'A');
        }
        System.out.println("Writing to Memory Mapped File is completed" + memoryMappedFile.toString());

        //reading from memory file in Java
        for (int i = 0; i < 10; i++) {
            System.out.print((char) out.get(i));
        }
        System.out.println("Reading from Memory Mapped File is completed");
    }
}


