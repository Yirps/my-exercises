package io.codeforall.fanstatics;

import java.util.PriorityQueue;

public class TodoList {


    private PriorityQueue priorityQueue;

    public TodoList() {
        priorityQueue = new PriorityQueue<>();
    }


    public boolean add(Importance importance, int priority, String item) {

        Task task = new Task(importance, priority, item);
        priorityQueue.add(task);
        return true;
    }

    public boolean isEmpty() {
        return priorityQueue.isEmpty();
    }

    public String remove() {
        priorityQueue.remove();
        return "Task: " + importance + " + " + priority + " + " + item + ".";
    }


    public enum Importance {
        LOW,
        MEDIUM,
        HIGH;
    }
}



