package com.github.kairocesar.simplesnacionalcalculator.annexes;

import java.util.LinkedHashMap;
import java.util.Map;

public class AnnexFour implements AbstractAnnex {

    private final Map<String, Double[]> taxes = new LinkedHashMap<>();

    public double getAliquots(int range) {
        double[] aliquots = {0.0450, 0.09, 0.1020, 0.14, 0.22, 0.33};
        return aliquots[range - 1];
    }

    public double getDeductionValues(int range) {
        double[] deductionValues = {0, 8100.00, 12420.00, 39780.00, 183780.00, 828000.00};
        return deductionValues[range - 1];
    }

    public Map<String, Double[]> getTaxDistribution(int range, double generalAliquot) {
        taxes.put("CSLL", new Double[]{0.1520, 0.1520, 0.1520, 0.1920, 0.1920, 0.2150});
        taxes.put("IRPJ", new Double[]{0.1880, 0.1980, 0.2080, 0.1780, 0.1880, 0.5350});
        taxes.put("PIS", new Double[]{0.0383, 0.0445, 0.0427, 0.0410, 0.0392, 0.0445});
        taxes.put("COFINS", new Double[]{0.1767, 0.2055, 0.1973, 0.1890, 0.1808, 0.2055});
        taxes.put("ISS", new Double[]{0.4450, 0.40, 0.40, 0.40, 0.40, 0.40});
        return taxes;
    };
}
