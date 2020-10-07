package com.itpaths.rules.price.util;

import java.math.RoundingMode;
import java.text.DecimalFormat;

import static java.lang.Math.*;

public class FormulaUtil {
    private Double param[];
    private Double E1[];
    private Double DS[];

    public FormulaUtil(Double param[], Double[] E1, Double[] DS) {
        this.param = param;
        this.E1 = E1;
        this.DS = DS;
    }

    private double roundOff(double num, int place) {
        DecimalFormat df = null;
        if (place == 2)
            df = new DecimalFormat("#.##");
        else if (place == 1)
            df = new DecimalFormat("#.#");
        else if (place == 4)
            df = new DecimalFormat("#.####");
        df.setRoundingMode(RoundingMode.CEILING);
        return Double.parseDouble(df.format(num));
    }

    public double RT_CLASSIC() {
        return ((int) ((int) ((max((((param[1] + (min(max(param[2], param[3]), param[4]) * param[5]))
                * (1 - param[6]) + (param[7] * param[8] + param[9] * param[5]) * param[7] + param[10])
                * param[11] * param[12]), (param[13] * param[8]))) / roundOff(param[14]
                , 3)) + roundOff(param[15], 0)) * param[14]) * param[16] * param[17];

    }

    public double RT_CLASSIC_DEBET() {
        return roundOff(((RT_CLASSIC_FULL() - RT_SUPLMNT()) * param[23]), 2);
    }

    public double RT_CLASSIC_FULL() {
        return min((((RT_CLASSIC_BASE()) + param[15]) + (RT_SUPLMNT())), (RT_MAX()));
    }

    public double RT_SUPLMNT() {
        return param[21] * param[16];
    }

    public double RT_CLASSIC_BASE() {
        return max(max((RT_NORMAL_PRICE()), (RT_VIA_PRICE())), (RT_ALTRNV_PRICE()));
    }

    private double RT_ALTRNV_PRICE() {
        return 0;
    }

    private double RT_VIA_PRICE() {
        return (min(min(RNDFRM(E1[(int) max((((roundOff(param[1], 4)
                + (min(RNDFRM(DS[Integer.parseInt(param[31].toString())]), 150) * roundOff(param[5], 4)))
                * (1 - param[6]) + (roundOff(param[7], 4) * param[8] + param[9]
                * roundOff(param[5], 4)) * param[6] + param[10])
                * ((RT_1STCLASS_DSTNC())) * param[12]), (param[13] * param[8]))]),
                ((11.4 + abs((5.9 * (1 - ((RT_1STCLASS_DSTNC())))
                / 0.54))) * ((abs(param[6] - 0.5) * 100000) + 1))), param[20])
                + min(min(RNDFRM(E1[(int) max((((roundOff(param[1], 4)
                + (min(RNDFRM(DS[Integer.parseInt(param[32].toString())]), 150) * roundOff(param[5], 4)))
                * (1 - param[6]) + (roundOff(param[7], 4) * param[8] + param[9]
                * roundOff(param[5], 4)) * param[6] + param[10]) * ((RT_1STCLASS_DSTNC()))
                * param[12]), (param[13] * param[8]))]), ((11.4 + abs((5.9
                * (1 - ((RT_1STCLASS_DSTNC()))) / 0.54)))
                * ((abs(param[6] - 0.5) * 100000) + 1))), param[20])) * min(param[32], 1);
    }

    private int RT_NORMAL_PRICE() {
        return (int) ((RNDFRM(E1[(int) max((((roundOff(param[1], 4)
                        + ((min(RNDFRM(DS[Integer.parseInt(param[30].toString())]), 150)) * roundOff(param[5], 4)))
                        * (1 - param[6]) + (roundOff(param[7], 4) * param[8] + param[9]
                        * roundOff(param[5], 4)) * param[6] + param[10])
                        * ((RT_1STCLASS_DSTNC())) * param[12]), (param[13] * param[8]))])
                        * (RT_TRIPS())) * (1 - min(param[32], 1)) * (1 - min(param[33], 1)));
    }

    private int RNDFRM(double d) {
        return (int)d;
    }

    private double RT_TRIPS() {
        return param[17] * RT_ITNRY();
    }

    private double RT_1STCLASS_DSTNC() {
        return max(param[11]
                - (0.10 * min(max((37 - param[30]), 0), 1))
                - (0.14 * min(max((52 - param[30]), 0), 1)), 1);
    }

    public double RT_MAX() {
        return roundOff(param[20] * (RT_ITNRY()) + param[15], 1);
    }

    public double RT_ITNRY() {
        return max(max((param[16] * param[18]), (2 * min(param[32], 1))), (2 * min(param[33], 1)));
    }
}
