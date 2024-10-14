package io.codeforall.fanstatics;

import java.util.Iterator;

public class Range<Integer> implements Iterable<Integer> {
    public Range(int i, int i1) {
    }



    @Override
    public Iterator<Integer> iterator() {
        return new MyLittleIterator<>();
    }
}
