package com.github.kairocesar.simplesnacionalcalculator.panel;

import com.github.kairocesar.simplesnacionalcalculator.annexes.*;
import com.github.kairocesar.simplesnacionalcalculator.calculators.AnnexCalculator;
import com.github.kairocesar.simplesnacionalcalculator.calculators.TaxCalculator;

import java.util.Map;
import java.util.Scanner;

public class UserPanel {

    private static final AbstractAnnex[] annexes = {new AnnexOne(), new AnnexTwo(), new AnnexThree(), new AnnexFour()};

    private static AnnexCalculator annexCalculator;
    private static AbstractAnnex annex = getAnnex();
    private static double rbt12 = getRbt12();
    private static double salesValue = getSalesValue();
    private static double salesValueWithIcmsSt;

    public static void main(String[] args) {
        double totalvalue = 0;
        for (Map.Entry<String, Double> taxDetails : buildTaxCalculator().entrySet()) {
            System.out.printf("%s aliquot: %.4f, value: R$ %.2f%n", taxDetails.getKey(),
                    ((taxDetails.getValue() / salesValue) * 100), taxDetails.getValue());
            totalvalue += taxDetails.getValue();
        }

        System.out.printf("Total Value: R$ %.2f%n", totalvalue);
    }

    public static Map<String, Double> buildTaxCalculator() {
        annexCalculator = new AnnexCalculator(new TaxCalculator(annex, rbt12, salesValue,
                checkIcmsSt(), checkMonophasicPisAndCofins()));

        return annexCalculator.calculateTaxes();
    }

    private static AbstractAnnex getAnnex() {
        Scanner inputAnnex = new Scanner(System.in);
        System.out.print("Annex: ");
        return annexes[inputAnnex.nextInt() - 1];
    }

    private static double getRbt12() {
        Scanner inputRbt = new Scanner(System.in);
        System.out.print("RBT12: ");
        return inputRbt.nextDouble();
    }

    private static double getSalesValue() {
        Scanner inputSales = new Scanner(System.in);
        System.out.print("Sales value: ");
        return inputSales.nextDouble();
    }

    public static double getSalesValueWithReplace(String taxName) {
        Scanner inputInit = new Scanner(System.in);
        System.out.print("Insert the sales value regarding replacement of" + taxName + ":");
        return inputInit.nextDouble();
    }


    private static String checkIcmsSt() {
        Scanner inputSt = new Scanner(System.in);
        System.out.print("Did your company sell products subject to ICMS ST(yes/no?)? ");
        if (inputSt.next().equalsIgnoreCase("yes")) {
          return "ICMS";
        }

        return null;
    }

    private static String checkMonophasicPisAndCofins() {
        Scanner inputMono = new Scanner(System.in);
        System.out.print("Did your company sell products subject to monophasic taxation of Pis/Cofins (yes/no?)? ");
        if (inputMono.next().equalsIgnoreCase("yes")) {
            return "PIS COFINS";
        }

        return null;
    }
}
