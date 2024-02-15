package com.github.kairocesar.simplesnacionalcalculator.calculators;

import com.github.kairocesar.simplesnacionalcalculator.panel.UserPanel;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.LinkedHashMap;
import java.util.Map;

public class AnnexCalculator {

    private final NumberFormat currencyFormat = NumberFormat.getCurrencyInstance();
    private final NumberFormat percentFormat = new DecimalFormat("#.###%");
    private Map<String, Double> taxes = new LinkedHashMap<>();
    private Map<String, Double> taxesWithReplace = new LinkedHashMap<>();
    private Map<String, Double> taxesReturn = new LinkedHashMap<>();


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

        if (taxCalc.replacedTaxes().length > 0) {
            for (String tax : taxCalc.replacedTaxes()) {
                taxesWithReplace = calculateTaxWithReplace(UserPanel.getSalesValueWithReplace(tax), tax);
                //erro
                taxesReturn.put(tax, taxes.get(tax) + taxesWithReplace.get(tax));
            }
        }
        return taxesReturn;
    }

    private Map<String, Double> calculateTaxWithReplace(double salesValue, String... taxName) {
        String pis;
        String cofins;

        for (Map.Entry<String, Double[]> tax : taxCalc.getTaxes().entrySet()) {
            taxesWithReplace.put(tax.getKey(), salesValue * (tax.getValue()[taxCalc.getRange()] *
                    taxCalc.getGeneralAliquot()));
        }
        for (String tax : taxName) {
            if (tax.equalsIgnoreCase("PIS COFINS")) {
                pis = tax.substring(0, tax.indexOf(" "));
                cofins = tax.substring(tax.indexOf(" ") + 1, tax.length());
                taxesWithReplace.put(pis, 0d);
                taxesWithReplace.put(cofins, 0d);
            } else {
                taxesWithReplace.put(tax, 0d);
            }
        }
        return taxesWithReplace;
    }

}

