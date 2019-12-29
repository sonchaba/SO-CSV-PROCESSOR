package com.sonchaba.csv.processor.utils;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class CsvTypeConverterTest {
    private String fromDate = "02/07/2017";

    @BeforeEach
    void setUp() {
        CsvVariables.FORMAT = "MM/dd/yyyy";
    }

    @Test
    void parseType() throws Exception {
        Date date = (Date) CsvTypeConverter.parseType(fromDate, Date.class);
        assertNotNull(date);
        assertEquals(date.getClass(), Date.class);
    }

    @Test
    void parseString() throws Exception {
        Date date = (Date) CsvTypeConverter.parseType(fromDate, Date.class);
        String dateString = CsvTypeConverter.parseString(date);
        assertNotNull(dateString);
        assertTrue(dateString.equals(fromDate));
    }
}