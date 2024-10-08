package io.codeforall.fanstatics;

public class NotEnoughSpaceException extends FileException {
    public NotEnoughSpaceException(){
    }

    public NotEnoughSpaceException(String s){
       super(s);
    }
}
