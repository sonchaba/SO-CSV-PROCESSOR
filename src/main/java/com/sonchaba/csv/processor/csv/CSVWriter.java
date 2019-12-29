package com.sonchaba.csv.processor.csv;

import com.sonchaba.csv.processor.utils.CsvTypeConverter;
import com.sonchaba.csv.processor.utils.CsvVariables;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import static com.sonchaba.csv.processor.utils.CsvVariables.CSV_FILE_SEPARATOR;
import static com.sonchaba.csv.processor.utils.CsvVariables.CSV_FILE_WRITE;

public class CSVWriter {
    private List<CSVInfo> fields;
    private FileWriter csvWriter;

    public CSVWriter(Class csvClass) {
        fields = CSVInfo.getAnnotatedFieldInfo(csvClass);
        try {
            csvWriter = new FileWriter(System.getProperty(CSV_FILE_WRITE));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void writeHeader() throws IOException {
        int count = 0;

        for (CSVInfo csvInfo : fields) {
            if (count++ > 0) {
                csvWriter.append(System.getProperty(CSV_FILE_SEPARATOR));
            }
            csvWriter.append(csvInfo.field.getName());
        }
        csvWriter.append(System.lineSeparator());

    }

    private void writeBody(Object object) throws IOException, IllegalAccessException {
        int counter = 0;
        for (CSVInfo csvInfo : fields) {
            csvInfo.field.setAccessible(true);
            if (counter++ > 0) {
                csvWriter.append(System.getProperty(CSV_FILE_SEPARATOR));
            }
            Object value = csvInfo.field.get(object);
            CsvVariables.FORMAT = csvInfo.format;
            String val = CsvTypeConverter.parseString(value);
            csvWriter.append(val);
        }
        csvWriter.append(System.lineSeparator());
    }

    public boolean writeFile(List<Object> list) {
        try {
            writeHeader();
            list.stream().forEach(rowData -> {
                try {
                    writeBody(rowData);
                } catch (IOException | IllegalAccessException e) {
                    e.printStackTrace();

                }
            });
            csvWriter.flush();
            csvWriter.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}
