package io.codeforall.bootcamp;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Integer range iterator
 */
public class Range implements Iterable<Integer> {

    private int min;
    private int max;

    //some state that can be changed

    private boolean[] removed;
    private boolean useFwdIterator;

    public Range(int min, int max, boolean useFwdIterator) {
        this.min = min;
        this.max = max;
        this.useFwdIterator = useFwdIterator;

        //The size of array is +1 to be inclusive of both min and max
        removed = new boolean[max - min + 1];
    }

    public void setUseFwdIterator(){
        useFwdIterator = !useFwdIterator;
    }

    @Override
    public Iterator<Integer> iterator() {
        if (useFwdIterator) {
            return new Iterator<Integer>() {
                private int current = min - 1;

                @Override
                public boolean hasNext() {
                    // check if the next number is removed.
                    // if it is.... keep checking if the following numbers are removed.

                    while (removedContains(current + 1)) {
                        current++;
                    }

                    return current < max;

                }

                private boolean removedContains(int current) {
                    // First we need to check if the current number above the max value
                    return current <= max && removed[current - min];
                }

                @Override
                public Integer next() {
                    if (!hasNext()) {
                        throw new NoSuchElementException();
                    }

                    current++;
                    return current;

                }

                @Override
                public void remove() {
                    // if you try to remove an element before calling next(), you will get a runtime exception (see Iterable:remove() description)
                    if (current < min) {
                        throw new IllegalStateException("You need to call next() at least once to remove an element.");
                    }

                    // if you try to call remove() consecutively you will get a runtime exception (see Iterable:remove() description)
                    if (removedContains(current)) {
                        throw new IllegalStateException("You cannot call remove() repeatedly");
                    }

                    removed[current - min] = true;

                }
            };
        } else {
            return new Iterator<Integer>() {

                private int current = max + 1;
                @Override
                public boolean hasNext() {

                    // check if the next number is removed.
                    // if it is.... keep checking if the following numbers are removed.
                    while (removedContains(current - 1)) {
                        current--;
                    }

                    return current > min;
                }

                private boolean removedContains(int current) {
                    return current >= min && removed[current + max];
                }

                @Override
                public Integer next() {

                    if (!hasNext()) {
                        throw new NoSuchElementException();
                    }

                    current--;
                    return current;
                }

                @Override
                public void remove() {
                    // if you try to remove an element before calling next(), you will get a runtime exception (see Iterable:remove() description)
                    if (current > max) {
                        throw new IllegalStateException("You need to call next() at least once to remove an element.");
                    }

                    // if you try to call remove() consecutively you will get a runtime exception (see Iterable:remove() description)
                    if (removedContains(current)) {
                        throw new IllegalStateException("You cannot call remove() repeatedly");
                    }

                    removed[current + max] = true;
                }

            };
        }
    }



    /*private class BwdRangeIterator implements Iterator<Integer> {


        private int current;

        public BwdRangeIterator() {
            // start this iterator with the "current number" just after the max value.
            // You need to call next() to get the first element.

        }

        @Override
        public boolean hasNext() {

            // check if the next number is removed.
            // if it is.... keep checking if the following numbers are removed.
            while (removedContains(current - 1)) {
                current--;
            }

            return current > min;
        }

        private boolean removedContains(int current) {
            // First we need to check if the current number above the max value
            return current >= min && removed[current + max];
        }

        @Override
        public Integer next() {

            if (!hasNext()) {
                throw new NoSuchElementException();
            }

            current--;
            return current;
        }

        @Override
        public void remove() {

            // if you try to remove an element before calling next(), you will get a runtime exception (see Iterable:remove() description)
            if (current > max) {
                throw new IllegalStateException("You need to call next() at least once to remove an element.");
            }

            // if you try to call remove() consecutively you will get a runtime exception (see Iterable:remove() description)
            if (removedContains(current)) {
                throw new IllegalStateException("You cannot call remove() repeatedly");
            }

            removed[current + max] = true;
        }

    }


    private class FwdRangeIterator implements Iterator<Integer> {

        private int current;

        public FwdRangeIterator() {
            // start this iterator with the "current number" just before the min value.
            // You need to call next() to get the first element.
            current = min - 1;
        }

        @Override
        public boolean hasNext() {

            // check if the next number is removed.
            // if it is.... keep checking if the following numbers are removed.
            while (removedContains(current + 1)) {
                current++;
            }

            return current < max;
        }

        private boolean removedContains(int current) {
            // First we need to check if the current number above the max value
            return current <= max && removed[current - min];
        }

        @Override
        public Integer next() {

            if (!hasNext()) {
                throw new NoSuchElementException();
            }

            current++;
            return current;
        }

        @Override
        public void remove() {

            // if you try to remove an element before calling next(), you will get a runtime exception (see Iterable:remove() description)
            if (current < min) {
                throw new IllegalStateException("You need to call next() at least once to remove an element.");
            }

            // if you try to call remove() consecutively you will get a runtime exception (see Iterable:remove() description)
            if (removedContains(current)) {
                throw new IllegalStateException("You cannot call remove() repeatedly");
            }

            removed[current - min] = true;
        }

    }*/

}
