package com.sonchaba.csv.processor.csv;

import com.sonchaba.csv.processor.utils.CsvVariables;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class CSVParserTest {
    @BeforeAll
    static void initialize() {
        System.getProperties().setProperty(CsvVariables.CSV_FILE_SEPARATOR, ",");
    }

    @Test
    void CSVSplit() {
        String csvLine = "27415975,2/7/2017,10/6/2019,\"2857,0,222,0\",readingg 895 - Edit,MATHEW MUTHINI,kpl14634,true";
        String[] splitObject = CSVParser.CSVSplit(csvLine);
        assertNotNull(splitObject);
        assertEquals(splitObject.length, 8);
        assertEquals(splitObject[0], "27415975");
    }
}