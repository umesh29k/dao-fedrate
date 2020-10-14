package com.itpaths.rules.price.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Common {
    public static double invokeForumla(String frml_id, Double [] E1, Double [] DS, Double [] param) throws InvocationTargetException {
        double result = 0;
        Method sumInstanceMethod;
        try {
            sumInstanceMethod = FormulaUtil.class.getMethod(frml_id);
            FormulaUtil operationsInstance = new FormulaUtil(param, E1, DS);
            result = (Double) sumInstanceMethod.invoke(operationsInstance);
        } catch (InvocationTargetException | IllegalAccessException | NoSuchMethodException e) {
            e.printStackTrace();
        }
        return result;
    }

}
