package io.codeforall.fanstatics;
public class Main {

    public static void main(String[] args) {
        Bank montepio = new Bank();
        Person yirps = new Person("yirps", "yirps","montepio");
        Person yarp = new Person("Yarp","yarp","montepio");

        System.out.println(yirps.getBalance());
        System.out.println(yirps.getId());
        System.out.println(yirps.deposit(100));
        System.out.println(yirps.take(50));
        System.out.println(yirps.getId());
        System.out.println("yarp is " + yarp.getId());
        System.out.println("yarp is " + yarp.getId());





        System.out.println(yirps.getBalance());

        System.out.println("Yarp has: " + yarp.getBalance());


    }
}
