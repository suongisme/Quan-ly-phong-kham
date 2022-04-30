package com.clinic.utils;

import com.clinic.annotation.Required;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Validator {

    public static String validate(Object object) {

        try {
            Method[] methods = object.getClass().getDeclaredMethods();
            List<Method> getMethods = Stream.of(methods).filter(method -> method.getName().startsWith("get")).collect(Collectors.toList());
            for (Method method : getMethods) {
                boolean annotationPresent = method.isAnnotationPresent(Required.class);
                Required annotation = method.getAnnotation(Required.class);
                if (annotationPresent) {
                    Object invoke = method.invoke(object);
                    if (Objects.isNull(invoke)) {
                        return annotation.message();
                    }
                }
            }
            return null;
        } catch (Exception exception) {
            exception.printStackTrace();
            return null;
        }
    }
}