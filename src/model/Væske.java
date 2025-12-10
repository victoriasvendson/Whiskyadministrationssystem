package model;

public interface Væske {
    double getVolumen();
    double getAlkoholProcent();

    default Malt findMalt() {
        return null;
    }
}

