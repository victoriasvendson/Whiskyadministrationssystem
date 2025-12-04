package model;


import java.io.Serializable;

public class TidligereIndhold implements Serializable {
    private String væske;

    public TidligereIndhold (String væske) {
        this.væske = væske;
    }

    @Override
    public String toString() {
        return væske;
    }
}
