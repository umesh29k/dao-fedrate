package com.itpaths.rules.price.util;

import java.math.RoundingMode;
import java.text.DecimalFormat;

import static java.lang.Math.max;
import static java.lang.Math.min;

public class Calculate {
    double RT_CLASSIC_FULL = 0, RT_CLASSIC_DEBET = 0, RT_SUPLMNT = 0;
    double RT_CLASSIC_BASE = 0, RT_MAX = 0, RT_ITNRY = 0;
    double RT_NORMAL_PRICE = 0, RT_VIA_PRICE = 0, RT_ALTRNV_PRICE = 0;

    public double getClassic(double P34) {
        double RT_CLASSICS = roundOff((RT_CLASSIC_FULL - RT_CLASSIC_DEBET + P34), 2);// -off upto 2 decimal digits;
        return RT_CLASSICS;
    }

    public double getClassicDebet(double P24) {
        double RT_CLASSIC_DEBET = roundOff((RT_CLASSIC_FULL - RT_SUPLMNT * P24), 2); // -off upto 2 decimal digits;)
        return RT_CLASSIC_DEBET;
    }

    public double getSupliment(double P21, double P16) {
        return RT_SUPLMNT = P21 * P16;
    }

    //Need to confirm, here we take minimum of RT_MAX and calculated data??
    public double getRT_CLASSIC_FULL(double P15) {
        return RT_CLASSIC_FULL = min((((RT_CLASSIC_BASE) + P15) + (RT_SUPLMNT)), (RT_MAX));
    }

    public double getRT_MAX(double P20, double P15) {
        return RT_MAX = roundOff((P20 * (RT_ITNRY) + P15), 1); //round of upto one digit
    }

    public double getRT_ITNRY(double P16, double P18, double P32, double P33) {
        double temp = 0;
        temp = max((P16 * P18), (2 * min(P32, 1)));
        return RT_ITNRY = max(temp, 2 * (min(P33, 1)));
    }

    public double getRT_MAX(double P15, double P16, double P18, double P20, double P32, double P33) {
        double temp = 0;
        temp = max(P16 * P18, (2 * min(P32, 1)));
        return RT_MAX = roundOff((P20 * (max(temp, (2 * min(P33, 1)))) + P15), 1);//round down by 1
    }

    //RT_CLASSIC_BASE = FORM(RT_NORMAL_PRICE) MAX FORM(RT_VIA_PRICE) MAX FORM(RT_ALTRNV_PRICE)
    public double getRT_CLASSIC_BASE() {
        double temp = max(RT_NORMAL_PRICE, RT_VIA_PRICE);
        return RT_CLASSIC_BASE = max(temp, RT_ALTRNV_PRICE);
    }

    //RT_NORMAL_PRICE = ((int)(E1[((((ROUND(P1;4)+(((int)(DS[P30]) MIN 150)*ROUND(P5;4)))*(1-P6)+(ROUND(P7;4)*P8+P9*ROUND(P5;4))*P6+P10)*(FORM(RT_1STCLASS_DSTNC))*P12) MAX (P13*P8))])*FORM(RT_TRIPS))*(1-(P32 MIN 1))*(1-(P33 MIN 1))
    public double getRT_NORMAL_PRICE(double P1, double P5, double P6, int P7, double P8, double P9, double P10,
                                     int P11, double P12, double P13, int P17, int P30, int P32, double P33) {
        //What is E1?
        double[] E1 = new double[0];
        //What is DS
        double[] DS = new double[0];
        return RT_NORMAL_PRICE =
                (int)
                        (E1[(int) max((roundOff(P1, 4) +
                                ((
                                        (int)
                                                (min(DS[P30], 150) * roundOff(P5, 4)))
                                        * (1 - P6) + (roundOff(P7, 4) *
                                        P8 + P9 * roundOff(P5, 4)) * P6 + P10) *
                                        ((getRT_1STCLASS_DSTNC(P11, P30))) * P12), (P13 * P8))])
                        * (getRT_TRIPS(P17)) * (1 - min(P32, 1)) * (1 - min(P33, 1));
    }

    public double getRT_1STCLASS_DSTNC(int P11, int P30) {
        double RT_1STCLASS_DSTNC = P11 - (0.10 * min(max(37 - P30, 0), 1)) - 0.14 * max(min(max(52 - P30, 0), 1),
                1);
        return RT_1STCLASS_DSTNC;
    }

    public double getRT_TRIPS(int P17) {
        double RT_TRIPS = P17 * RT_ITNRY;
        return RT_TRIPS;
    }

    public double getRT_VIA_PRICE(int P1, int P5, int P6, int P7, int P8, int P9, int P10,
                                  int P11, int P12, int P13, int P20, int P30, int P31, int P32) {
        int[] E1 = new int[0];
        int[] DS = new int[0];
        return 0;
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
}
