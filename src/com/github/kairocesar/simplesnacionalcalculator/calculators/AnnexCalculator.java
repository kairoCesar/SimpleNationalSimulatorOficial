package com.github.kairocesar.simplesnacionalcalculator.calculators;

import com.github.kairocesar.simplesnacionalcalculator.panel.UserPanel;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

public class AnnexCalculator {

    private Map<String, Double> taxes = new LinkedHashMap<>();
    private double valueWihReplacement;
    private TaxCalculator taxCalc;


    public AnnexCalculator(TaxCalculator taxCalc) {
        this.taxCalc = taxCalc;
    }

    public Map<String, Double> calculateTaxes() {

        for (Map.Entry<String, Double[]> tax : taxCalc.getTaxes().entrySet()) {
            double aliquotTax = taxCalc.getGeneralAliquot() * tax.getValue()[taxCalc.getRange()];
            double taxValue = taxCalc.salesValue() * aliquotTax;
            taxes.put(tax.getKey(), taxValue);
        }
        calculateTaxWithReplacement();
        return taxes;
    }

    private void calculateTaxWithReplacement() {
        for (String tax : taxCalc.replacedTaxes()) {
            if (!Objects.isNull(tax) && tax.equalsIgnoreCase("PIS COFINS")) {
                valueWihReplacement = UserPanel.checkReplacementTaxation(tax);
                removeValueOfTax(valueWihReplacement, "PIS");
                removeValueOfTax(valueWihReplacement, "COFINS");
            } else if (!Objects.isNull(tax)) {
                valueWihReplacement = UserPanel.checkReplacementTaxation(tax);
                removeValueOfTax(valueWihReplacement, tax);
            }
        }
    }

    private void removeValueOfTax(double salesValue, String tax) {
        double aliquotTax = taxCalc.getGeneralAliquot() * taxCalc.getTaxes().get(tax)[taxCalc.getRange()];
        double valueTaxToPay = taxes.get(tax);
        double valueTaxToPut = valueTaxToPay - (salesValue * aliquotTax);
        taxes.put(tax, valueTaxToPut);
    }

}

