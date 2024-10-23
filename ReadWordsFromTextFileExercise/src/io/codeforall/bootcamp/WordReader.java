package io.codeforall.bootcamp;

import java.io.*;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class WordReader implements Iterable<String> {
    String sourceFilePath;

    public WordReader(String sourceFilePath) {
        this.sourceFilePath = sourceFilePath;
    }

    @Override
    public Iterator<String> iterator() {
        return new Iterator<>() {
            FileReader reader;
            BufferedReader bReader;
            Iterator<String> linesIterator = null;
            Iterator<String> wordsIterator = null;

            @Override
            public boolean hasNext() {
                // We might need to check multiple times because a line might contain no words.
                while (true) {
                    // Check if there is already an active wordsIterator.
                    if (wordsIterator == null) {
                        if (linesIterator == null) {
                            try {
                                reader = new FileReader(sourceFilePath);
                                bReader = new BufferedReader(reader);
                                linesIterator = bReader.lines().iterator();
                            } catch (IOException e) {
                                // Could not open the file, so nothing to do..
                                return false;
                            }
                        }
                        // If there's no wordsIterator and no more lines in the file, we're done.
                        if (!linesIterator.hasNext()) {
                            try {
                                bReader.close();
                            } catch (IOException e) {
                                // do nothing
                            }
                            return false;
                        }
                        // Otherwise, get the next line in the file.
                        String line = linesIterator.next();
                        // Split it into words.
                        String[] wordsInLine = line.split("\\W+");
                        // Set the iterator.
                        wordsIterator = List.of(wordsInLine).iterator();
                    }
                    // If there are any words in the line, return true.
                    if (wordsIterator.hasNext()) return true;
                    // Otherwise, reset the wordsIterator to null so we try again with a new line.
                    wordsIterator = null;
                }
            }

            @Override
            public String next() throws NoSuchElementException {
                if (!hasNext()) throw new NoSuchElementException();
                return wordsIterator.next();
            }
        };
    }
}
