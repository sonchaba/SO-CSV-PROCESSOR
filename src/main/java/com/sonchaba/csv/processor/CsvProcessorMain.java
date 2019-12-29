package com.sonchaba.csv.processor;

import com.sonchaba.csv.processor.csv.CSVReader;
import com.sonchaba.csv.processor.csv.CSVWriter;

import java.io.IOException;
import java.util.List;

import static com.sonchaba.csv.processor.utils.CsvVariables.CSV_FILE;

public class CsvProcessorMain {
    public static void main(String[] args) {
        CsvProcessorMain csvProcessorMain = new CsvProcessorMain();
        csvProcessorMain.readProperties();
        csvProcessorMain.readCSV();
    }

    private void readProperties() {
        try {
            System.getProperties().load(getClass().getClassLoader().getResourceAsStream("application.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void readCSV() {
        try {
            List list = new CSVReader(CsvRecord.class).readListFromCSV(System.getProperty(CSV_FILE));
            CSVWriter csvWriter = new CSVWriter(CsvRecord.class);
            csvWriter.writeFile(list);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

        }
    }
}
