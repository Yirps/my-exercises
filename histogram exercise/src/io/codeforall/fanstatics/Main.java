package io.codeforall.fanstatics;

public class Main {
    public static final String STRING = "test word words test 1 10 1";

    public static void main(String[] args) {

        WordHistogramv2 wordHistogramv2 = new WordHistogramv2(STRING);
        System.out.println("MAP has " + wordHistogramv2.size() + " distinct words");

        for (String word : wordHistogramv2){
            System.out.println(word + " : " + wordHistogramv2.get(word));
        }
    }
}
