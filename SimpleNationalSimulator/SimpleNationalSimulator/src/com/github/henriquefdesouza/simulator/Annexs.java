package com.github.henriquefdesouza.simulator;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Arrays;
import java.util.List;

public interface Annexs {


    double[] taxes(int track);

    default List<String> namesTaxes(){
        return Arrays.asList("CPP: ", "CSLL: ", "ICMS: ", "IRPJ: ", "COFINS: ", "PIS: ");
    }

    default int checkTrack(String value) throws ParseException {
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
}
