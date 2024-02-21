package com.github.kairocesar.simplesnacionalcalculator.panel;

import com.github.kairocesar.simplesnacionalcalculator.annexes.*;
import com.github.kairocesar.simplesnacionalcalculator.calculators.AnnexCalculator;

import java.util.Map;
import java.util.Scanner;

public class UserPanel {

    private static final AbstractAnnex[] annexes = {new AnnexOne(), new AnnexTwo(), new AnnexThree(), new AnnexFour()};

    private static Scanner input = new Scanner(System.in);
    private static AnnexCalculator annexCalculator;
    private static AbstractAnnex annex = getAnnex();
    private static double rbt12 = getRbt12();
    private static double salesValue = getSalesValue();

    public static void calculateTax() {
        double totalvalue = 0;
        double totalAliquot = 0;
        for (Map.Entry<String, Double> taxDetails : buildTaxCalculator().entrySet()) {
            System.out.printf("%s alíquota: %.4f, valor: R$ %.2f%n", taxDetails.getKey(),
                    ((taxDetails.getValue() / salesValue) * 100), taxDetails.getValue());
            totalvalue += taxDetails.getValue();
            totalAliquot += (taxDetails.getValue() / salesValue) * 100;
        }

        System.out.printf("Valor total dos impostos: R$ %.2f%nAlíquota efetiva: %.2f", totalvalue, totalAliquot);
    }

    private static Map<String, Double> buildTaxCalculator() {
        if (annex instanceof AnnexOne || annex instanceof AnnexTwo) {
            annexCalculator = new AnnexCalculator(new AnnexCalculator.TaxCalculator(annex, rbt12, salesValue,
                    checkIcmsSt(), checkMonophasicPisAndCofins()));
        } else {
            annexCalculator = new AnnexCalculator(new AnnexCalculator.TaxCalculator(annex, rbt12, salesValue, checkIssRetented()));
        }
        return annexCalculator.calculateTaxes();
    }


    private static AbstractAnnex getAnnex() {
        System.out.print("Anexo: ");
        return annexes[input.nextInt() - 1];
    }

//    private static void printPanel() {
//        System.out.println("""
//                Selecione a atividade de acordo com os códigos fornecidos abaixo:
//                1. Revenda de mercadorias (Anexo 1 - Comércio) - Selecione 1
//                2. Venda de produção (Anexo 2 - Indústria) - Selecione 2
//                3. Prestação de serviços (Anexo 3) - Selecione 3
//                4. Prestação de serviços (Anexo 4) - Selecione 4
//                5. Prestação de serviços (Anexo 5) - Selecione 5
//                """);
//    }

    private static double getRbt12() {
        System.out.print("RBT12: ");
        return input.nextDouble();
    }

    private static double getSalesValue() {
        System.out.print("Valor das vendas: ");
        return input.nextDouble();
    }

    public static double checkReplacementTaxation(String taxName) {
        System.out.print("Insira o valor sujeito a substituição de " + taxName + ": ");
        return input.nextDouble();
    }

    public static String checkIcmsSt() {
        System.out.print("Sua empresa vendeu produtos sujeitos à ICMS ST?(sim/não?) ");
        if (input.next().equalsIgnoreCase("sim")) {
            return "ICMS";
        }
        return null;
    }

    public static String checkMonophasicPisAndCofins() {
        System.out.print("Sua empresa vendeu produtos sujeitos à tributação monofásoica de PIS e COFINS?(sim/não) ");
        if (input.next().equalsIgnoreCase("sim")) {
            return "PIS COFINS";
        }
        return null;
    }

    public static String checkIssRetented() {
        System.out.print("Sua empresa prestou serviços sujeitos com retenção de ISS?(sim/não?) ");
        if (input.next().equalsIgnoreCase("sim")) {
            return "ISS";
        }
        return null;
    }
}
