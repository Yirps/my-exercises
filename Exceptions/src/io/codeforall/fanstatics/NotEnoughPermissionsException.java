package io.codeforall.fanstatics;

public class NotEnoughPermissionsException extends FileException {
    public NotEnoughPermissionsException(){

    }

    public NotEnoughPermissionsException(String s){
        super(s);
    }

}
