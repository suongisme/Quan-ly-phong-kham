package com.clinic.utils;

import java.util.Objects;

public class DataUtils {

    public static String resolveKeySearch(String keyword) {
        if (Objects.isNull(keyword)) {
            return "%%";
        }
        return "%" + keyword + "%";
    }
}
