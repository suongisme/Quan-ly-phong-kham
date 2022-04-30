package com.clinic.utils;

import java.io.*;
import java.net.URL;
import java.util.List;
import java.util.Objects;
import java.util.Properties;
import java.util.stream.Collectors;

public class SystemUtils {

    private static InputStream inputStream;
    private static Properties configs;

    private static List<String> countries;

    static {
        try {
            configs = new Properties();
            inputStream = SystemUtils.class.getResourceAsStream("/application.properties");
            configs.load(inputStream);

            // load countries file
            URL resource = SystemUtils.class.getResource("/countries.txt");
            String path = resource.getPath();
            Reader reader = new FileReader(path);
            BufferedReader bufferedReader = new BufferedReader(reader);
            countries = bufferedReader.lines().collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getValueConfig(String key) {
        if (Objects.isNull(key)) return "";
        return (String) configs.get(key);
    }

    public static List<String> getCountries() {
        return countries;
    }
}