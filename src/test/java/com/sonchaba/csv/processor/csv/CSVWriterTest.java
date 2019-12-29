package com.sonchaba.csv.processor.csv;

import com.sonchaba.csv.processor.CsvRecord;
import com.sonchaba.csv.processor.utils.CsvVariables;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static com.sonchaba.csv.processor.utils.CsvVariables.CSV_FILE;
import static org.junit.jupiter.api.Assertions.*;

class CSVWriterTest {
    private List<Object> recordList;

    @BeforeEach
    void setUp() {
        System.getProperties().setProperty(CsvVariables.CSV_FILE_WRITE, System.getProperty("csv.file.test.read.write"));
        System.getProperties().setProperty(CsvVariables.CSV_FILE, System.getProperty("csv.file.test.read.write"));

        System.getProperties().setProperty(CsvVariables.CSV_FILE_SEPARATOR, ",");
        loadData();
    }

    @Test
    @Order(1)
    void writeFile() {
        CSVWriter csvWriter = new CSVWriter(CsvRecord.class);
        boolean b = csvWriter.writeFile(recordList);
        assertTrue(b);
    }

    @Test
    @Order(2)
    void readFile() {
        List list = null;
        try {
            list = new CSVReader(CsvRecord.class).readListFromCSV(System.getProperty(CSV_FILE));
        } catch (Exception e) {
            e.printStackTrace();
        }
        assertNotNull(list);
        assertEquals(list.size(), recordList.size());
        CsvRecord record = (CsvRecord) list.get(0);
        CsvRecord record2 = (CsvRecord) recordList.get(0);
        assertEquals(record.getAccount(), record2.getAccount());
        assertTrue(record.getRemarks().equals(record2.getRemarks()));

    }

    private void loadData() {
        recordList = new ArrayList<>();
        CsvRecord csvRecord = new CsvRecord();
        csvRecord.setAccount(12565);
        csvRecord.setFromDate(new Date());
        csvRecord.setToDate(new Date());
        csvRecord.setRemarks("debit units");
        csvRecord.setStaffNumber("kpl3345456");
        csvRecord.setUser("Shadrack Onchaba");
        csvRecord.setValid(true);
        csvRecord.setUnits(Arrays.asList("500,5,0,0".split(",")));
        recordList.add(csvRecord);

        csvRecord = new CsvRecord();
        csvRecord.setAccount(12565);
        csvRecord.setFromDate(new Date());
        csvRecord.setToDate(new Date());
        csvRecord.setRemarks("debit units");
        csvRecord.setStaffNumber("kpl3345456");
        csvRecord.setUser("Shadrack Onchaba");
        csvRecord.setValid(true);
        csvRecord.setUnits(Arrays.asList("500,5,0,0".split(",")));
        recordList.add(csvRecord);

        csvRecord = new CsvRecord();
        csvRecord.setAccount(58945215);
        csvRecord.setFromDate(new Date());
        csvRecord.setToDate(new Date());
        csvRecord.setRemarks("Error in charged units");
        csvRecord.setStaffNumber("kpl586126");
        csvRecord.setUser("Edmund James");
        csvRecord.setValid(false);
        csvRecord.setUnits(Arrays.asList("1000,50,0,0".split(",")));
        recordList.add(csvRecord);

        csvRecord = new CsvRecord();
        csvRecord.setAccount(98962632);
        csvRecord.setFromDate(new Date());
        csvRecord.setToDate(new Date());
        csvRecord.setRemarks("debit units");
        csvRecord.setStaffNumber("kpl4562456");
        csvRecord.setUser("Ian Wright");
        csvRecord.setValid(true);
        csvRecord.setUnits(Arrays.asList("500,5,0,0".split(",")));
        recordList.add(csvRecord);

        csvRecord = new CsvRecord();
        csvRecord.setAccount(965235323);
        csvRecord.setFromDate(new Date());
        csvRecord.setToDate(new Date());
        csvRecord.setRemarks("debit units");
        csvRecord.setStaffNumber("kpl3345456");
        csvRecord.setUser("Shadrack Onchaba");
        csvRecord.setValid(true);
        csvRecord.setUnits(Arrays.asList("6000,400,0,0".split(",")));
        recordList.add(csvRecord);
    }
}