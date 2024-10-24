package io.codeforall.fanstatics;

import java.util.ArrayList;
public class Main {

    public static void main(String[] args) throws FileException {

        /*
        FileManager manager = new FileManager(3);

        try {
            System.out.println("Trying to getFile without login in.");
            manager.getFile("My file");
        } catch (NotEnoughPermissionsException ex) {
            System.out.println(ex.getMessage());
        } catch (FileNotFoundException ex) {
            System.out.println(ex.getMessage());
        } catch (FileException e) {
            System.out.println(e.getMessage());
        }

        manager.login();

        try {
            System.out.println("Trying to get file without creating it.");
            manager.getFile("My file");
        } catch (NotEnoughPermissionsException ex) {
            System.out.println(ex.getMessage());
        } catch (FileNotFoundException ex) {
            System.out.println(ex.getMessage());
        } catch (FileException e) {
            System.out.println(e.getMessage());
        }

        for (int i = 0; i < 5; i++) {
            try {
                System.out.println("Trying to create file MyFile" + i);
                manager.createFile("MyFile" + i);
            } catch (NotEnoughPermissionsException ex) {
                System.out.println(ex.getMessage());
            } catch (FileNotFoundException ex) {
                System.out.println(ex.getMessage());
            } catch (FileException e) {
                System.out.println(e.getMessage());
            }
        }

        System.out.println("List of files");
        for(File f : manager.files){
            System.out.println(f.getName());
        }

        manager.logout();
         */

        InputManager.start();


    }
}
