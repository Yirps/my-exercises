package io.codeforall.bootcamp;

import java.io.IOException;
import java.util.Iterator;

public class Main {

    private static final String FILE_PATH = "scratch_1.txt";

    public static void main(String[] args) {

        try {
            WordReader wordReader = new WordReader(FILE_PATH);
        } catch (IOException e) {
            System.out.println("Due porcaria");
        }

        for (String word: wordReader) {
            System.out.println(word);
        }

        Iterator<String> iterator = wordReader.iterator();

        while (iterator.hasNext()){
            System.out.println(iterator.next());

    }
}
