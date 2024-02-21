package com.github.kairocesar.simplesnacionalcalculator.panel;

import com.github.kairocesar.simplesnacionalcalculator.annexes.*;
import com.github.kairocesar.simplesnacionalcalculator.calculators.AnnexCalculator;

import java.util.Map;
import java.util.Scanner;

public class UserPanel {

    private static final AbstractAnnex[] annexes = {new AnnexOne(), new AnnexTwo(), new AnnexThree(), new AnnexFour()};

    private static AnnexCalculator annexCalculator;
    private static AbstractAnnex annex = getAnnex();
    private static double rbt12 = getRbt12();
    private static double salesValue = getSalesValue();

    public static void main(String[] args) {
        double totalvalue = 0;
        double totalAliquot = 0;
        for (Map.Entry<String, Double> taxDetails : buildTaxCalculator().entrySet()) {
            System.out.printf("%s alíquota: %.4f, valor: R$ %.2f%n", taxDetails.getKey(),
                    ((taxDetails.getValue() / salesValue) * 100), taxDetails.getValue());
            totalvalue += taxDetails.getValue();
            totalAliquot += (taxDetails.getValue() / salesValue) * 100;
        }

        System.out.printf("Valor total dos impostos: R$ %.2f%n", totalvalue);
    }

    public static Map<String, Double> buildTaxCalculator() {
        if (annex instanceof AnnexOne || annex instanceof AnnexTwo) {
            annexCalculator = new AnnexCalculator(new AnnexCalculator.TaxCalculator(annex, rbt12, salesValue,
                    checkIcmsSt(), checkMonophasicPisAndCofins()));
        } else {
            annexCalculator = new AnnexCalculator(new AnnexCalculator.TaxCalculator(annex, rbt12, salesValue, checkIssRetented()));
        }
        return annexCalculator.calculateTaxes();
    }


    private static AbstractAnnex getAnnex() {
        Scanner inputAnnex = new Scanner(System.in);
        System.out.print("Anexo: ");
        return annexes[inputAnnex.nextInt() - 1];
    }

    private static double getRbt12() {
        Scanner inputRbt = new Scanner(System.in);
        System.out.print("RBT12: ");
        return inputRbt.nextDouble();
    }

    private static double getSalesValue() {
        Scanner inputSales = new Scanner(System.in);
        System.out.print("Valor das vendas: ");
        return inputSales.nextDouble();
    }

    public static double checkReplacementTaxation(String taxName) {
        Scanner inputSt = new Scanner(System.in);
        System.out.print("Insira o valor sujeito a substituição de " + taxName + ": ");
        return inputSt.nextDouble();
    }

    public static String checkIcmsSt() {
        Scanner inputSt = new Scanner(System.in);
        System.out.print("Sua empresa vendeu produtos sujeitos à ICMS ST?(sim/não?) ");
        if (inputSt.next().equalsIgnoreCase("sim")) {
            return "ICMS";
        }
        return null;
    }

    public static String checkMonophasicPisAndCofins() {
        Scanner inputSt = new Scanner(System.in);
        System.out.print("Sua empresa vendeu produtos sujeitos à tributação monofásoica de PIS e COFINS?(sim/não) ");
        if (inputSt.next().equalsIgnoreCase("sim")) {
            return "PIS COFINS";
        }
        return null;
    }

    public static String checkIssRetented() {
        Scanner inputIss = new Scanner(System.in);
        System.out.print("Sua empresa prestou serviços sujeitos com retenção de ISS?(sim/não?) ");
        if (inputIss.next().equalsIgnoreCase("sim")) {
            return "ISS";
        }
        return null;
    }
}
