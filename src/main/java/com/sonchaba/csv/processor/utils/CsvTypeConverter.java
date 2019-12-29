package com.sonchaba.csv.processor.utils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class CsvTypeConverter {

    public static Object parseType(String value, Class type) throws Exception {
        if (type == String.class) {
            return value;
        } else if (type == int.class) {
            return value == null ? 0 : Integer.parseInt(value);
        } else if (type == float.class) {
            return value == null ? 0f : Float.parseFloat(value);
        } else if (type == long.class) {
            return value == null ? 0L : Long.parseLong(value);
        } else if (type == double.class) {
            return value == null ? 0D : Double.parseDouble(value);
        } else if (type == boolean.class) {
            return value != null && Boolean.parseBoolean(value);
        } else if (type == Boolean.class) {
            return Boolean.valueOf(value);
        } else if (type == List.class) {
            return new ArrayList<>(Arrays.asList(value.split(System.getProperty(CsvVariables.CSV_FILE_SEPARATOR))));
        } else if (type == Date.class) {
            return new SimpleDateFormat(CsvVariables.FORMAT).parse(value);
        }
        return null;
    }

    public static String parseString(Object value) {
        if (value instanceof List) {
            return "\"" + String.join(System.getProperty(CsvVariables.CSV_FILE_SEPARATOR), (List<String>) value) + "\"";
        } else if (value instanceof Date) {
            return new SimpleDateFormat(CsvVariables.FORMAT).format(value);
        }
        return String.valueOf(value);
    }

}
