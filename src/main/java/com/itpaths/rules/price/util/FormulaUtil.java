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

    public double roundOff(double num, int place) {
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
        double a = (param[1] + (min(max(param[2], param[3]), param[4]) * param[5]));
        double b = (param[7] * param[8] + param[9] * param[5]);
        double c = param[7] + param[10];
        double d = param[11] * param[12];
        double e = (param[13] * param[8]);
        double f = roundOff(param[14], 3);
        double g = roundOff(param[15], 0);
        double h = param[16] * param[17];

        return ((int)((int) ((max((( a * ( 1 - param[6] ) + b * c) * d ), e )) / f) + g ) * param[14] ) * h;

       /* return ((int) ((int) ((max((((param[1] + (min(max(param[2], param[3]), param[4]) * param[5]))
                * (1 - param[6]) + (param[7] * param[8] + param[9] * param[5]) * param[7] + param[10])
                * param[11] * param[12]), (param[13] * param[8]))) / roundOff(param[14]
                , 3)) + roundOff(param[15], 0)) * param[14]) * param[16] * param[17];*/

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

    public double RT_ALTRNV_PRICE() {
        return 0;
    }

    public double RT_VIA_PRICE() {
        try {
            double a = (roundOff(param[1], 4)
                    + (min(RNDFRM(DS[param[31].intValue()]), 150) * roundOff(param[5], 4)));
            double b = (1 - param[6]);
            double c = (roundOff(param[7], 4) * param[8] + param[9] * roundOff(param[5], 4));
            double d = param[6] + param[10];
            double e = ((RT_1STCLASS_DSTNC())) * param[12];
            double f = (param[13] * param[8]);
            double g = ((11.4 + abs((5.9 * (1 - ((RT_1STCLASS_DSTNC())))
                    / 0.54))) * ((abs(param[6] - 0.5) * 100000) + 1));
            double h = (roundOff(param[1], 4)
                    + (min(RNDFRM(DS[param[32].intValue()]), 150) * roundOff(param[5], 4)));
            double i = (1 - param[6]);
            double j = (roundOff(param[7], 4) * param[8] + param[9] * roundOff(param[5], 4));
            double k = param[6] + param[10];
            double l = ((RT_1STCLASS_DSTNC()));
            double m = (param[13] * param[8]);
            double n = ((11.4 + abs((5.9 * (1 - ((RT_1STCLASS_DSTNC()))) / 0.54)))
                    * ((abs(param[6] - 0.5) * 100000) + 1));

            return (min(min(RNDFRM(E1[(int) max(((a * b + c * d) * e), f)]),g),param[20])
                    + min(min(RNDFRM(E1[(int) max(((h * i + j * k ) * l * param[12]), m)]), n)
                    , param[20])) * min(param[32], 1);

           /* return (min(min(RNDFRM(E1[(int) max((((roundOff(param[1], 4)
                            + (min(RNDFRM(DS[param[31].intValue()]), 150) * roundOff(param[5], 4)))
                            * (1 - param[6]) + (roundOff(param[7], 4) * param[8] + param[9]
                            * roundOff(param[5], 4)) * param[6] + param[10])
                            * ((RT_1STCLASS_DSTNC())) * param[12]), (param[13] * param[8]))]),
                    ((11.4 + abs((5.9 * (1 - ((RT_1STCLASS_DSTNC())))
                            / 0.54))) * ((abs(param[6] - 0.5) * 100000) + 1))), param[20])
                    + min(min(RNDFRM(E1[(int) max((((roundOff(param[1], 4)
                    + (min(RNDFRM(DS[param[32].intValue()]), 150) * roundOff(param[5], 4)))
                    * (1 - param[6]) + (roundOff(param[7], 4) * param[8] + param[9]
                    * roundOff(param[5], 4)) * param[6] + param[10]) * ((RT_1STCLASS_DSTNC()))
                    * param[12]), (param[13] * param[8]))]), ((11.4 + abs((5.9
                    * (1 - ((RT_1STCLASS_DSTNC()))) / 0.54)))
                    * ((abs(param[6] - 0.5) * 100000) + 1))), param[20])) * min(param[32], 1);*/
        }
        catch(ArrayIndexOutOfBoundsException e){
            e.printStackTrace();
            return 0;
        }
    }

    public Integer RT_NORMAL_PRICE() {
        try {
            double a = ((min(RNDFRM(DS[param[30].intValue()]), 150)) * roundOff(param[5], 4));
            double b = (1 - param[6]);
            double c = (roundOff(param[7], 4) * param[8] + param[9] * roundOff(param[5], 4));
            double d = param[6] + param[10];
            double e = ((RT_1STCLASS_DSTNC()));
            double f = (param[13] * param[8]);
            double g = (1 - min(param[32], 1));
            double h = (1 - min(param[33], 1));
            double i = (RT_TRIPS());

            return (int) ((RNDFRM(E1[(int) max((((roundOff(param[1], 4) +
                    a )* b + c * d) * e * param[12]) , f ) ] ) * i ) * g * h );

            /*return (int) ((RNDFRM(E1[(int) max((((roundOff(param[1], 4)
                    + ((min(RNDFRM(DS[param[30].intValue()]), 150)) * roundOff(param[5], 4)))
                    * (1 - param[6]) + (roundOff(param[7], 4) * param[8] + param[9]
                    * roundOff(param[5], 4)) * param[6] + param[10])
                    * ((RT_1STCLASS_DSTNC())) * param[12]), (param[13] * param[8]))])
                    * (RT_TRIPS())) * (1 - min(param[32], 1)) * (1 - min(param[33], 1)));*/
        } catch (ArrayIndexOutOfBoundsException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int RNDFRM(double d) {
        return (int) d;
    }

    public double RT_TRIPS() {
        return param[17] * RT_ITNRY();
    }

    public double RT_1STCLASS_DSTNC() {
        double a = (0.10 * min(max((37 - param[30]), 0), 1));
        double b = (0.14 * min(max((52 - param[30]), 0), 1));
        return max(param[11] - a - b , 1);
       /* return max(param[11]
                - (0.10 * min(max((37 - param[30]), 0), 1))
                - (0.14 * min(max((52 - param[30]), 0), 1)), 1);*/
    }

    public double RT_MAX() {
        return roundOff(param[20] * (RT_ITNRY()) + param[15], 1);
    }

    public double RT_ITNRY() {
        double a = (param[16] * param[18]);
        double b = (2 * min(param[32], 1));
        double c = (2 * min(param[33], 1));
        return max(max(a , b), c);
        //return max(max((param[16] * param[18]), (2 * min(param[32], 1))), (2 * min(param[33], 1)));
    }
}
