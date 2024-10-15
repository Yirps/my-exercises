package io.codeforall.fanstatics;

import java.util.HashMap;
import java.util.Iterator;
public class WordHistogramv2 extends HashMap<String, Integer> implements Iterable<String> {

    private String string;




    public WordHistogramv2(String string) {
        this.string = string;


        wordCounter(split());
    }

    /*
    public int size() {
        return this.size();
    }

     */

    /*
    public Integer get(String string) {
        return this.get(string);
    }
     */

    public String[] split() {
        String[] strings = this.string.split("\\s+");
        return strings;
    }


    public void wordCounter(String[] strings) {

        for (int i = 0; i < strings.length; i++) {
            if(this.containsKey(strings[i])) {

                this.put(strings[i], this.get(strings[i]) +1 ) ;

            } else {
                this.put(strings[i], 1);
            }

        }
    }


    @Override
    public Iterator iterator() {
        return this.keySet().iterator();
    }
}
