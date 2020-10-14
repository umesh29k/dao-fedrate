package com.itpaths.rules.price.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Common {
    public static double invokeForumla(String frml_id) throws InvocationTargetException {
        double result = 0;
        Method sumInstanceMethod;
        try {
            Double param[] = new Double[35];
            Double[] E1 = new Double[2];
            Double[] DS = new Double[2];
            sumInstanceMethod = FormulaUtil.class.getMethod(frml_id);
            FormulaUtil operationsInstance = new FormulaUtil(param, E1, DS);
            result = (Double) sumInstanceMethod.invoke(operationsInstance);
        } catch (InvocationTargetException | IllegalAccessException | NoSuchMethodException e) {
            e.printStackTrace();
        }
        return result;
    }

}
