package io.codeforall.fanstatics;

import javax.swing.plaf.synth.SynthOptionPaneUI;
import java.util.ArrayList;

public class FileManager {
    private boolean logged;
    public File[] files;

    public FileManager(int numFiles){
        this.logged = false;
        this.files = createFiles(numFiles);
    }

    private File[] createFiles(int numFiles){
        File[] files = new File[numFiles];
        for (int i = 0; i < numFiles; i++) {
           files[i] = new File("");
        }
        return files;
    }

    public void login(){
        this.logged = true;
    }

    public void logout(){
        this.logged = false;
    }

    public File getFile(String name) throws FileException{
        if(!this.logged){
            throw new NotEnoughPermissionsException("Not enough permissions");
        }
        for(File f : this.files){
            if(f.getName().equals(name)){
                return f;
            }
        }
        throw new FileNotFoundException("File not found.");
    }

    public void createFile(String name) throws FileException{
        if(!this.logged){
            throw new NotEnoughPermissionsException("Not enough permissions");
        }
        for (int i = 0; i < this.files.length; i++) {
            if(this.files[i].getName().equals("")){
                this.files[i].setName(name);
                return;
            }
        }
        throw new NotEnoughSpaceException("Not enough space.");
    }
}