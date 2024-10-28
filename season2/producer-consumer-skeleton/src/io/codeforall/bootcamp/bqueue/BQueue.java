package io.codeforall.bootcamp.bqueue;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Blocking Queue
 * @param <T> the type of elements stored by this queue
 */
public class BQueue<T> {

    private final int limit;
    private Queue<T> queue = new LinkedList<>();

    /**
     * Constructs a new queue with a maximum size
     * @param limit the queue size
     */
    public BQueue(int limit) {
        this.limit = limit;
        //throw new UnsupportedOperationException();

    }

    /**
     * Inserts the specified element into the queue
     * Blocking operation if the queue is full
     * @param data the data to add to the queue
     */
    public synchronized void offer(T data) throws InterruptedException {
        while (getSize() >= getLimit()) {
            wait();
        }
        queue.add(data);
        notifyAll();
        //throw new UnsupportedOperationException();

    }

    /**
     * Retrieves and removes data from the head of the queue
     * Blocking operation if the queue is empty
     * @return the data from the head of the queue
     */
    public synchronized T poll() throws InterruptedException {
        while (queue.isEmpty()) {
            wait();
        }
        T data = queue.poll();
        notifyAll();
        return data;
        //throw new UnsupportedOperationException();

    }

    /**
     * Gets the number of elements in the queue
     * @return the number of elements
     */
    public synchronized int getSize() {
        return queue.size();
        //throw new UnsupportedOperationException();

    }

    /**
     * Gets the maximum number of elements that can be present in the queue
     * @return the maximum number of elements
     */
    public int getLimit() {
        return limit;
        //throw new UnsupportedOperationException();

    }
}
