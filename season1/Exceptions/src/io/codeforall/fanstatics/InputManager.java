package io.codeforall.fanstatics;

import javax.swing.plaf.IconUIResource;
import java.util.Scanner;
public class InputManager {

    public static void start() throws FileException {

        Scanner scan = new Scanner(System.in);

        System.out.println("File manager size: ");
        int filesSize = scan.nextInt();
        FileManager manager = new FileManager(filesSize);

        while (true) {

            boolean leave = false;

            int opt = 0;
            while (true) {
                System.out.println("1. LogIn 2. Create file 3. Get file 4. LogOut 5. Exit");
                System.out.println("Option: ");
                try {
                    opt = Integer.parseInt(scan.nextLine());
                }catch (NumberFormatException e){
                    System.out.println("Not valid input");
                }
                if (opt > 0 && opt < 6) {
                    break;
                }
                System.out.println("Input not valid");
            }

            switch (opt) {
                case 1:
                    manager.login();
                    System.out.println("Logging in...");
                    break;
                case 2:
                    System.out.println("Insert new file name: ");
                    try {
                        String newFileName = String.format(scan.nextLine());
                        manager.createFile(newFileName);
                    } catch (NotEnoughPermissionsException e){
                        System.out.println("Not enough permissions.");
                    } catch (NotEnoughSpaceException e){
                        System.out.println("Not enough space to create a new file.");
                    }
                    break;
                case 3:
                    System.out.println("Search file name: ");
                    try {
                        String fileName = scan.nextLine();
                        manager.getFile(fileName);
                    } catch (NotEnoughPermissionsException e){
                        System.out.println("Not enough permissions");
                    } catch (FileNotFoundException e){
                        System.out.println("That file doesnt exist.");
                    }
                    break;
                case 4:
                    manager.logout();
                    System.out.println("Logging out...");
                    break;
                default:
                    leave = true;
                    break;
            }

            if (leave) {
                break;
            }
        }
    }
}
