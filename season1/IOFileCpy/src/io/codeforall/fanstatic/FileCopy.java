package io.codeforall.fanstatic;

import java.io.*;

public class FileCopy {

    private FileInputStream inputStream;
    private FileOutputStream outputStream;
    private byte[] buffer;
    private int bytesRead;


    public FileCopy(String sourceFilePath, String destinationFilePath) throws IOException {
        inputStream = new FileInputStream(sourceFilePath);
        outputStream = new FileOutputStream(destinationFilePath);
        buffer = new byte[1024];
        bytesRead = 0;
    }


    public void copyFile() throws IOException {
        int bytesReadNow;


        while ((bytesReadNow = inputStream.read(buffer)) != -1) {
            outputStream.write(buffer, 0, bytesReadNow);
            bytesRead += bytesReadNow;
        }
    }


    public void closeStreams() throws IOException {
        if (inputStream != null) {
            inputStream.close();
        }
        if (outputStream != null) {
            outputStream.close();
        }
    }

    public int getNumberOfBytesCopied() {
        return bytesRead;
    }

    public static void main(String[] args) {
        try {

            FileCopy fileCopy = new FileCopy("/Users/codecadet/Downloads/car.png", "/Users/codecadet/Documents/car.png");


            fileCopy.copyFile();


            System.out.println("Number of bytes copied: " + fileCopy.getNumberOfBytesCopied());

            fileCopy.closeStreams();

        } catch (IOException e) {
            System.out.println("Deu merda meu puto.");
        }
    }
}
