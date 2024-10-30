package io.codeforall.fanstatics;

public class Machine {


        MonoOperation mo = name -> name.toUpperCase();

        String UpperCase = mo.Execute("Yirps");


        BiOperation<Integer> sum = ((number1, number2) -> number1 + number2);

        BiOperation<Integer> sub = ((number1, number2) -> number1 - number2);

        BiOperation<Integer> mul = ((number1, number2) -> number1 * number2);

        BiOperation<Integer> div = ((number1, number2) -> number1 / number2);


        BiOperation<String> concat = ((string1, string2) -> string1 + string2);



}
