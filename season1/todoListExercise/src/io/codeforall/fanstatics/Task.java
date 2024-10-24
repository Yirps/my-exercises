package io.codeforall.fanstatics;

public class Task implements Comparable<Task>{
    private TodoList.Importance importance;
    private int priority;
    private String item;

    /*public Task(Importance importance, int priority, String item){
        this.importance = importance;
        this.priority = priority;
        this.item = item;
    }

     */

    public Task(TodoList.Importance importance, int priority, String item) {
        this.importance = importance;
        this.priority = priority;
        this.item = item;
    }

    public String getItem() {
        return item;
    }

    @Override
    public int compareTo(Task t) {
        if (this.) {

        }return 0;

    }
}
