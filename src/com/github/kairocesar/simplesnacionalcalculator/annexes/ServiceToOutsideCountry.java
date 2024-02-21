package com.github.kairocesar.simplesnacionalcalculator.annexes;

import java.util.Map;

public class ServiceToOutsideCountry implements AbstractAnnex {

    AbstractAnnex annexThree = new AnnexThree();

    @Override
    public double getAliquots(int range) {
        return annexThree.getAliquots(range);
    }

    @Override
    public double getDeductionValues(int range) {
        return annexThree.getDeductionValues(range);
    }

    @Override
    public Map<String, Double[]> getTaxDistribution(int range, double generalAliquot) {
        Map<String, Double[]> taxes = annexThree.getTaxDistribution(range, generalAliquot);
        taxes.put("PIS", new Double[]{0d, 0d, 0d, 0d, 0d, 0d});
        taxes.put("COFINS", new Double[]{0d, 0d, 0d, 0d, 0d, 0d});
        taxes.put("ISS", new Double[]{0d, 0d, 0d, 0d, 0d, 0d});
        return taxes;
    }
}
