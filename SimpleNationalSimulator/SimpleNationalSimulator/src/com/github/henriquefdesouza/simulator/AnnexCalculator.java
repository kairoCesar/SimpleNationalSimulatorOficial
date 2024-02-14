package com.github.henriquefdesouza.simulator;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public record AnnexCalculator(String rbt12, String revenueValue, int track, double[] aliquot, double[] deductionValue) {

    private static final NumberFormat numberFormat = new DecimalFormat("#,##0.000");

    public String calculatorAliquot() throws ParseException {
        double valueRbt12 = numberFormat.parse(rbt12).doubleValue();
        double calc = ( valueRbt12 * aliquot[track - 1] - deductionValue[track - 1]) / valueRbt12 * 100;
        return numberFormat.format(calc);
    }

    public String calculatorValueGuide() throws ParseException {
        double convertedAliquot = numberFormat.parse(calculatorAliquot()).doubleValue();
        double convertedRevenue = numberFormat.parse(revenueValue).doubleValue();
        return NumberFormat.getCurrencyInstance().format(convertedAliquot / 100 * convertedRevenue);
    }

    public ArrayList<String> calculationTaxes() throws ParseException {
        NumberFormat numberFormat = new DecimalFormat("#,##0.00");
        ArrayList<String> list = new ArrayList<>();
        double convertedAliquot = numberFormat.parse(calculatorAliquot()).doubleValue();
        for (int i = 0; i < DataAnnexTaxesTrack.taxes(track).length; i++) {
            double calc = convertedAliquot * DataAnnexTaxesTrack.taxes(track)[i] / 100 * numberFormat.parse(revenueValue).doubleValue();
            list.add(NumberFormat.getCurrencyInstance().format(calc));
        }
        return list;
    }

    public void printTaxes() throws ParseException {
        System.out.println("Valor da guia: " + calculatorValueGuide());
        System.out.printf("Porcentagem aliquota: %s %%%n", calculatorAliquot());
        for (int i = 0; i < DataAnnexTaxesTrack.namesTaxes().size(); i++) {
            System.out.println(DataAnnexTaxesTrack.namesTaxes().get(i) + calculationTaxes().get(i));
        }
    }
}