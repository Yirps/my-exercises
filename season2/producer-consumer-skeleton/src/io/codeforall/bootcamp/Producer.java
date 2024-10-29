package io.codeforall.bootcamp;

import io.codeforall.bootcamp.bqueue.BQueue;
import io.codeforall.bootcamp.bqueue.Pizza;

/**
 * Produces and stores integers into a blocking queue
 */
public class Producer implements Runnable {

    private final BQueue<Pizza> queue;
    private int elementNum;

    /**
     * @param queue the blocking queue to add elements to
     * @param elementNum the number of elements to produce
     */
    public Producer(BQueue queue, int elementNum) {
        this.queue = queue;
        this.elementNum = elementNum;
    }

    @Override
    public void run() {
       // synchronized (queue) {
            try {
                for (int i = 0; i < elementNum; i++) {
                        Pizza pizza = new Pizza();
                        queue.offer(pizza); // Blocking call if the queue is full
                        Thread.sleep((long) (Math.random() * 1000));

                        if(queue.getSize() == queue.getLimit()){
                            synchronized (queue){
                                System.out.println("Table full.");
                            }
                        }
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println(Thread.currentThread().getName() + " interrupted");
            }

    }
}
