package io.codeforall.fanstatics;

import org.academiadecodigo.bootcamp.Prompt;
import org.academiadecodigo.bootcamp.scanners.menu.MenuInputScanner;
import org.academiadecodigo.bootcamp.scanners.string.StringInputScanner;

public class Sandbox {
    public static void main(String[] args) {

        String UsernameQ = new String("Username:");
        String PasswordQ = new String("Password:");
        Prompt prompt = new Prompt(System.in, System.out);

        StringInputScanner var3 = new StringInputScanner();

        var3.setMessage("Login");
        System.out.println(UsernameQ.);
    }
}
