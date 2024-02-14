package com.github.kairocesar.simplesnacionalcalculator.annexes;

import java.util.Map;

public interface AbstractAnnex {

    default int getRange (double rbt12) {
        if (rbt12 <= 180000.00) return 1;
        if (rbt12 <= 360000.00) return  2;
        if (rbt12 <= 720000.00) return 3;
        if (rbt12 <= 1_800_000.00) return 4;
        if (rbt12 <= 3_600_000.00) return 5;
        return 6;
    }

    double getAliquots(int range);

    double getDeductionValues(int range);

    Map<String, Double[]> getTaxDistribution(int range, double generalAliquot);
}
