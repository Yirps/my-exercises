package io.codeforall.fanstatics;

import java.util.Iterator;

public class MyLittleIterator<Integer> implements Iterator<Integer> {


    @Override
    public boolean hasNext() {
        return true;
    }

    @Override
    public Integer next() {
        return next();
    }
}
