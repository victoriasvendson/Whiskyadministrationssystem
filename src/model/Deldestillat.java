package model;

public class Deldestillat extends Destillat implements Væske {
    private double delMængde;
    private Lagring lagring;
    private Destillat destillat;

    public Deldestillat(
            int destillatId,
            double destillatMængde,
            Destillering destillering,
            double delMængde,
            double alkoholProcent,
            Destillat destillat
    ) {
        super(destillatId, destillatMængde, destillering, alkoholProcent);
        this.delMængde = delMængde;
        this.destillat = destillat;
    }

    @Override
    public double getVolumen() {
        return delMængde;
    }


    public double getAlkoholProcent() {
        return super.getAlkoholProcent();
    }


}
