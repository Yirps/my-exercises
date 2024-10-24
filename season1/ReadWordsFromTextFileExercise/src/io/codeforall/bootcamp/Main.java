package io.codeforall.bootcamp;

public class Main {

    private static final String FILE_PATH = "scratch_1.txt";

    public static void main(String[] args) {

        WordReader wordReader = new WordReader(FILE_PATH);

        for (String word : wordReader) {
            System.out.println(word);
        }
    }
}
