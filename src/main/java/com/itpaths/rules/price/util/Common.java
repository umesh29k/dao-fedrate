package com.itpaths.rules.price.util;

import com.itpaths.rules.price.exception.ApiException;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Common {
    public static double invokeForumla(String frml_id, Double[] E1, Double[] DS, Double[] param) throws InvocationTargetException, ApiException {
        double result = 0;
        //Method sumInstanceMethod;
        //try {
        //  sumInstanceMethod = FormulaUtil.class.getMethod(frml_id);
        FormulaUtil formula = new FormulaUtil(param, E1, DS);
        if(frml_id != null) {
            if (frml_id.equalsIgnoreCase("RT_CLASSIC")) {
                result = (Double) formula.RT_CLASSIC();
            } else if (frml_id.equalsIgnoreCase("RT_CLASSIC_DEBET")) {
                result = (Double) formula.RT_CLASSIC_DEBET();
            } else if (frml_id.equalsIgnoreCase("RT_CLASSIC_FULL")) {
                result = (Double) formula.RT_CLASSIC_FULL();
            } else if (frml_id.equalsIgnoreCase("RT_SUPLMNT")) {
                result = (Double) formula.RT_SUPLMNT();
            } else if (frml_id.equalsIgnoreCase("RT_CLASSIC_BASE")) {
                result = (Double) formula.RT_CLASSIC_BASE();
            } else if (frml_id.equalsIgnoreCase("RT_ALTRNV_PRICE")) {
                result = (Double) formula.RT_ALTRNV_PRICE();
            } else if (frml_id.equalsIgnoreCase("RT_VIA_PRICE")) {
                result = (Double) formula.RT_VIA_PRICE();
            } else if (frml_id.equalsIgnoreCase("RT_NORMAL_PRICE")) {
                result = formula.RT_NORMAL_PRICE().doubleValue();
            } else if (frml_id.equalsIgnoreCase("RT_TRIPS")) {
                result = (Double) formula.RT_TRIPS();
            } else if (frml_id.equalsIgnoreCase("RT_1STCLASS_DSTNC")) {
                result = (Double) formula.RT_1STCLASS_DSTNC();
            } else if (frml_id.equalsIgnoreCase("RT_MAX")) {
                result = (Double) formula.RT_MAX();
            } else if (frml_id.equalsIgnoreCase("RT_ITNRY")) {
                result = (Double) formula.RT_ITNRY();
            }
            else
                throw new ApiException("No formula id found");

        }
        else{
            throw new ApiException("No formula id found");
        }
        //result = (Double) sumInstanceMethod.invoke(formula);
        /*} catch (InvocationTargetException | IllegalAccessException | NoSuchMethodException e) {
            e.printStackTrace();
        }*/
        return result;
    }

}
