package model;

public class Deldestillat extends Destillat implements Væske {
    private double delMængde;
    private Lagring lagring;

    public Deldestillat(
            int destillatId,
            double mængde,
            String rygemateriale,
            Destillering destillering,
            double delMængde,
            double alkoholProcent
    ) {
        super(destillatId, mængde, rygemateriale, destillering, alkoholProcent);
        this.delMængde = delMængde;
    }

    @Override
    public double getVolumen() {
        return delMængde;
    }


    public double getAlkoholProcent() {
        return super.getAlkoholProcent();
    }


}
