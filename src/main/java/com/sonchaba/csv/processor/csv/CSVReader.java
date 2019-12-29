package com.sonchaba.csv.processor.csv;

import com.sonchaba.csv.processor.utils.CsvTypeConverter;
import com.sonchaba.csv.processor.utils.CsvVariables;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class CSVReader<T> {
    private static final Logger LOGGER = LogManager.getLogger();
    private Class csvClass;
    private List<CSVInfo> fields;
    private boolean continueOnError = false;

    public CSVReader(Class csvClass) {
        this.csvClass = csvClass;
        fields = CSVInfo.getAnnotatedFieldInfo(csvClass);
        this.continueOnError = Boolean.parseBoolean(System.getProperty(CsvVariables.CSV_ERROR_CONTINUE));
    }


    @SuppressWarnings("unchecked")
    private T getObjectFromCSV(String line, long lineNumber) {
        try {
            String[] split = CSVParser.CSVSplit(line);
            Object instance;
            try {
                instance = csvClass.newInstance();
            } catch (InstantiationException e) {
                LOGGER.error(e.getMessage());
                return null;
            }
            for (CSVInfo each : fields) {
                if (each.index < 0 || each.index >= split.length) {
                    LOGGER.error("Incorrect CSV entry for line: " + lineNumber);
                    if (!continueOnError) {
                        System.exit(-1);
                    }
                    return null;
                }
                String temp = split[each.index];
                if (!temp.isEmpty()) {
                    CsvVariables.FORMAT = each.format;
                    Object parsedObject = CsvTypeConverter.parseType(temp, each.cType);
                    each.field.set(instance, parsedObject);
                }

            }

            ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
            Validator validator = factory.getValidator();

            Set<ConstraintViolation<Object>> violations = validator.validate(instance);
            for (ConstraintViolation<Object> violation : violations) {
                LOGGER.info(violation.getMessage() + " for Line: " + lineNumber);
            }
            return (T) instance;
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            if (!continueOnError) {
                System.exit(-1);
            }
            return null;
        }


    }

    public List<T> readListFromCSV(String filename) throws Exception {
        BufferedReader br = new BufferedReader(new FileReader(filename));
        List<T> results = new ArrayList<>();
        long lineNumber = 0;
        String input;
        if (csvClass.isAnnotationPresent(CSVHeader.class)) {
            CSVHeader header = (CSVHeader) csvClass.getAnnotation(CSVHeader.class);
            if (header.has_header()) {
                lineNumber++;
                br.readLine();
            }
        }

        while ((input = br.readLine()) != null) {
            T t;
            t = getObjectFromCSV(input, ++lineNumber);
            if (t != null) {
                results.add(t);
            }
        }

        return results;
    }

}
