package com.github.kairocesar.simplesnacionalcalculator.annexes;

import java.util.LinkedHashMap;
import java.util.Map;

public class AnnexThree implements AbstractAnnex{

    private final Map<String, Double[]> taxes = new LinkedHashMap<>();

    public double getAliquots(int range) {
        double[] aliquots = {0.06, 0.1120, 0.1350, 0.16, 0.21, 0.33};
        return aliquots[range - 1];
    }

    public double getDeductionValues(int range) {
        double[] deductionValues = {0, 9360.00, 17640.00, 35640.00, 125640.00, 648000.00};
        return deductionValues[range - 1];
    }

    public Map<String, Double[]> getTaxDistribution(int range, double generalAliquot) {
        taxes.put("CPP", new Double[]{0.4340, 0.4340, 0.4340, 0.4340, 0.4340, 0.3050});
        taxes.put("CSLL", new Double[]{0.0350, 0.0350, 0.0350, 0.0350, 0.0350, 0.15});
        taxes.put("IRPJ", new Double[]{0.04, 0.04, 0.04, 0.04, 0.04, 0.35});
        taxes.put("PIS", new Double[]{0.0278, 0.0305, 0.0296, 0.0296, 0.0278, 0.0347});
        taxes.put("COFINS", new Double[]{0.1282, 0.1405, 0.1364, 0.1364, 0.1282, 0.1603});
        taxes.put("ISS", new Double[]{0.3350, 0.32, 0.3250, 0.3250, 0.3350, 0.32});
        return taxes;
        };
}

