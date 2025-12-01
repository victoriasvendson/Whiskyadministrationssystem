package model;

import java.util.ArrayList;
import java.util.List;

public class DestillatComposite implements DestilleringsComponent {
    private List<DestilleringsComponent> komponenter = new ArrayList<>();

    public void add(DestilleringsComponent komponent) {
        komponenter.add(komponent);
    }

    @Override
    public double getMængde() {
        double total = 0;
        for (DestilleringsComponent c : komponenter) {
            total += c.getMængde();
        }
        return total;
    }

    @Override
    public double getAlkoholprocent() {
        double totalMængde = 0;
        double samletAlkohol = 0;

        for (DestilleringsComponent c : komponenter) {
            double mængde = c.getMængde();
            double alkohol = c.getAlkoholprocent();
            totalMængde += mængde;
            samletAlkohol += mængde * alkohol;
        }
        if (totalMængde == 0) {
            return 0;
        } else {
            return samletAlkohol / totalMængde;
        }
    }
}