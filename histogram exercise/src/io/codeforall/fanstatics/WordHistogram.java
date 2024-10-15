package io.codeforall.fanstatics;

import java.util.HashMap;
import java.util.Iterator;
public class WordHistogram implements Iterable<String> {

    private String string;

    HashMap<String, Integer> hm;

    public WordHistogram(String string) {
        this.string = string;
        hm = new HashMap<>();

        wordCounter(split());
    }

    public int size() {
        return hm.size();
    }

    public Integer get(String string) {
        return hm.get(string);
    }

    public String[] split() {
        String[] strings = this.string.split("\\s+");
        return strings;
    }


    public void wordCounter(String[] strings) {

        for (int i = 0; i < strings.length; i++) {
            if(hm.containsKey(strings[i])) {
                hm.put(strings[i], hm.get(strings[i]) +1 ) ;
            } else {
                hm.put(strings[i], 1);
            }

        }
    }


    @Override
    public Iterator iterator() {
        return hm.keySet().iterator();
    }
}
