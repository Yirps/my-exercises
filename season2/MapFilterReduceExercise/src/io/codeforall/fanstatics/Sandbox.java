package io.codeforall.fanstatics;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Sandbox {
    public static void main(String[] args) {

        String message = "I'll send an SOS to the garbage world, " +
                "I hope that someone garbage gets my message in a garbage bottle.";

        String[] result = message.split(" ");

        System.out.println(Stream.of(result)
                .filter(name -> !name.equals("garbage"))
                .map(name -> name.toUpperCase())
                .reduce("", (acc, elem) -> acc + " " + elem));

    }
}
