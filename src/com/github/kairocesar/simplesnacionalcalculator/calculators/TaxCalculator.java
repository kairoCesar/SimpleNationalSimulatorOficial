package com.github.kairocesar.simplesnacionalcalculator.calculators;

import com.github.kairocesar.simplesnacionalcalculator.annexes.AbstractAnnex;

import java.util.Map;

public record TaxCalculator (AbstractAnnex annex, double rbt12, double salesValue, String... replacedTaxes) {

    public double getGeneralAliquot() {
        int range = annex.getRange(rbt12());
        double deductionValue = annex.getDeductionValues(range);
        return (((rbt12 * annex.getAliquots(range)) - deductionValue) / rbt12);
    }

    public Map<String, Double[]> getTaxes() {
        return annex().getTaxDistribution(annex.getRange(rbt12()), getGeneralAliquot());
    }

    public int getRange() {
        return annex.getRange(rbt12) - 1;
    }
}
