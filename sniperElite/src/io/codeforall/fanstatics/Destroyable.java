package io.codeforall.fanstatics;

public interface Destroyable {

    public abstract void hit(int damage);

    public abstract boolean isDestroyed();
    public abstract String getMessage();


}
