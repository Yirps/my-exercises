package io.codeforall.fanstatics;

import java.util.Iterator;

public class PlayGround {

    public static void main(String[] args) {
        Range<Integer> r = new Range<>(-5, 5);

        Iterator<Integer> it = r.iterator();


        for (Integer i : r) {
            System.out.println("iterated: " + i);
        }
 //  while (it.hasNext()) {
       // Integer i = it.next();

        //if (i == 1 || i == 2 || i == 3){
          //  iterator().remove();
        //}
    }


    }


