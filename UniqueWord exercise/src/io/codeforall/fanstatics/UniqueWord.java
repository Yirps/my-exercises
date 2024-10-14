package io.codeforall.fanstatics;

import java.util.HashSet;
import java.util.Iterator;

public class UniqueWord implements Iterable<String>{

    HashSet<String> hs;
    private String string;
    private String[] strings;

    public UniqueWord(String string){
        this.string = string;
        hs = new HashSet<>();
        strings = split();
        modifier();
    }

    public String[] split() {
        String[] strings = string.split("\\s+");
        return strings;
    }

    public void modifier() {
        for (int i = 0; i < strings.length; i++) {
            hs.add(this.strings[i]);
        }
    }




    @Override
    public Iterator<String> iterator() {
        return hs.iterator();
    }
}
