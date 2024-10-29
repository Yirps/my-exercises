package io.codeforall.bootcamp;

import io.codeforall.bootcamp.bqueue.Pizza;
import io.codeforall.bootcamp.bqueue.BQueue;

/**
 * Consumer of integers from a blocking queue
 */
public class Consumer implements Runnable {

    private final BQueue<Pizza> queue;
    private int elementNum;

    /**
     * @param queue the blocking queue to consume elements from
     * @param elementNum the number of elements to consume
     */
    public Consumer(BQueue queue, int elementNum) {
        this.queue = queue;
        this.elementNum = elementNum;
    }

    @Override
    public void run() {
       // synchronized (queue) {
            try {
                for (int i = 0; i < elementNum; i++) {
                        Pizza pizza = queue.poll(); // Blocking call if the queue is empty
                        Thread.sleep((long) (Math.random() * 2500));

                    if(queue.getSize() == 0){
                        synchronized (queue){
                            System.out.println("Hurry up.");
                        }
                    }


                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println(Thread.currentThread().getName() + " interrupted");
            }

    }
}