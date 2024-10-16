package io.codeforall.bootcamp;

import java.io.*;
import java.util.Iterator;

public class WordReader implements Iterable{

    String sourceFilePath;
    String result;
    FileReader reader;
    BufferedReader bReader;


    public WordReader(String sourceFilePath) throws IOException {
        reader = new FileReader(sourceFilePath);
        bReader = new BufferedReader(reader);

        String line = "";


        while((line = bReader.readLine()) != null) {
            this.result += line + "\n";
        }
        System.out.println(result);
        bReader.close();

        textSep();
    }
    public String textSep(){
        result.split("[^a-zA-Z]");
        return result;
    }
    @Override
    public Iterator<String> iterator(){
        return new Iterator<String>() {
            @Override
            public boolean hasNext() {
                if(bReader.readLine() != null){
                   return true;
                }
                return false;
            }

            @Override
            public String next() {
                return bReader.readLine();
            }
        };
    }
}
