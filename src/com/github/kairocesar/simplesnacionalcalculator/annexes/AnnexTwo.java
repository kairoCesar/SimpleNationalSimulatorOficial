package com.github.kairocesar.simplesnacionalcalculator.annexes;

import java.util.LinkedHashMap;
import java.util.Map;

public class AnnexTwo implements AbstractAnnex {

    private final Map<String, Double[]> taxes = new LinkedHashMap<>();

    public double getAliquots(int range) {
        double[] aliquots = {0.0450, 0.0780, 0.10, 0.1120, 0.1470, 0.30};
        return aliquots[range - 1];
    }

    public double getDeductionValues(int range) {
        double[] deductionValues = {0, 5940.00, 13860.00, 22500.00, 85500.00, 720000.00};
        return deductionValues[range - 1];
    }

    public Map<String, Double[]> getTaxDistribution(int range, double generalAliquot) {
        taxes.put("CPP", new Double[]{0.3750, 0.3750, 0.3750, 0.3750, 0.3750, 0.2350});
        taxes.put("CSLL", new Double[]{0.0350, 0.0350, 0.0350, 0.0350, 0.0350, 0.0750});
        taxes.put("IRPJ", new Double[]{0.0550, 0.0550, 0.0550, 0.0550, 0.0550, 0.0850});
        taxes.put("PIS", new Double[]{0.0249, 0.0249, 0.0249, 0.0249, 0.0249, 0.0454});
        taxes.put("COFINS", new Double[]{0.1151, 0.1151, 0.1151, 0.1151, 0.1151, 0.2096});
        taxes.put("ICMS", new Double[]{0.32, 0.32, 0.32, 0.32, 0.32, 0.32});
        return taxes;
    };

}

