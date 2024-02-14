package com.github.henriquefdesouza.simulator;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class DataAnnexTaxesTrack {
    private static final Scanner sc = new Scanner(System.in);

    public static AnnexCalculator dataAnnexOne() throws ParseException {
        System.out.print("Rbt12: ");
        String rbt12 = sc.next();
        System.out.print("Receita: ");
        String revenueValue = sc.next();
        return new AnnexCalculator(rbt12,revenueValue, checkTrack(rbt12), new double[]{0.04, 0.073, 0.095, 0.107, 0.143, 0.19},
                new double[]{0, 5_940, 13_860, 22_500, 87_300, 378_000});
    }

    public static double[] taxes(int track){
        if(track <= 2) return new double[] {0.415, 0.035, 0.34, 0.055, 0.1274, 0.0276};
        if(track <= 5) return new double[]{0.42, 0.035, 0.335, 0.055, 0.1274, 0.0276};
        return new double[]{0.421, 0.10, 0.135, 0.2827, 0.0613};
    }

    private static int checkTrack(String value) throws ParseException {
        NumberFormat numberFormat = new DecimalFormat("#,##0.00");
        double convertedValue = numberFormat.parse(value).doubleValue();
        if (convertedValue <= 0) return 0;
        if (convertedValue <= 180_000) return 1;
        if (convertedValue <= 360_000) return 2;
        if (convertedValue <= 720_000) return 3;
        if (convertedValue <= 1_800_000) return 4;
        if (convertedValue <= 3_600_000) return 5;
        return 6;
    }

    public static List<String> namesTaxes(){
        return Arrays.asList("CPP: ", "CSLL: ", "ICMS: ", "IRPJ: ", "COFINS: ", "PIS: ");
    }
}
