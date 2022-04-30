package com.clinic.utils;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

public class Convertor {

    public static final String SET = "set";
    public static final String GET = "get";
    private static final SimpleDateFormat dateFormater = new SimpleDateFormat("yyyy-MM-dd");

    public static <T> T requestParamToObject(HttpServletRequest req, Class<T> clazz) {
        try {
            Field[] fields = clazz.getDeclaredFields();
            Constructor<T> constructor = clazz.getConstructor();
            T t = constructor.newInstance();
            for (Field field : fields) {
                Object parameter = req.getParameter(field.getName());
                String methodName = resolveMethodName(SET, field.getName());
                Method method = clazz.getDeclaredMethod(methodName, field.getType());

                boolean isValid = Objects.nonNull(parameter) && parameter.toString().length() > 0;

                if (!isValid) {
                    parameter = null;
                }

                if (Date.class.equals(field.getType()) && isValid) {
                    parameter = dateFormater.parse(parameter.toString());
                }

                if (Double.class.equals(field.getType()) && isValid) {
                    parameter = Double.valueOf(parameter.toString());
                }

                if (Integer.class.equals(field.getType()) && isValid) {
                    parameter = Integer.valueOf(parameter.toString());
                }
                
                method.invoke(t, parameter);
            }
            return t;
        } catch(Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    private static String resolveMethodName(String prefix, String fieldName) {
        return prefix + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
    }
}