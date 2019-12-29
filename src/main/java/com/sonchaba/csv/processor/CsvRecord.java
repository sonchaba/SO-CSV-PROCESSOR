package com.sonchaba.csv.processor;

import com.sonchaba.csv.processor.csv.CSVField;
import com.sonchaba.csv.processor.csv.CSVHeader;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

@CSVHeader(has_header = true, header = "account,FROM_DATE,TO_DATE,UNITS,REMARKS,USER_REQ,STAFF_NUM,valid")
@Getter
@Setter
public class CsvRecord {
    @CSVField(index = 0, classType = long.class)
    private long account;
    @Past(message = "From date must be in the past")
    @CSVField(index = 1, classType = Date.class, format = "MM/dd/yyyy")
    private Date fromDate;
    @Past(message = "End date must be in the past")
    @CSVField(index = 2, classType = Date.class, format = "MM/dd/yyyy")
    private Date toDate;
    @CSVField(index = 3, classType = List.class, quotes = true)
    private List<String> units;
    @NotNull(message = "Remarks cannot be null")
    @CSVField(index = 4, classType = String.class)
    private String remarks;
    @CSVField(index = 5, classType = String.class)
    private String user;
    @Pattern(regexp = "^[kK][pP][lL][0-9]*", message = "Staff Number must start with KPL followed by digits: ${validatedValue}")
    @Size(min = 6, max = 10, message
            = "Staff number length must be between 6 and 10 characters: ${validatedValue}")
    @CSVField(index = 6, classType = String.class)
    private String staffNumber;
    @CSVField(index = 7, classType = Boolean.class, quotes = true)
    private Boolean valid;

    @Override
    public String toString() {
        return "CsvRecord{" +
                "account=" + account +
                ", fromDate=" + fromDate +
                ", toDate=" + toDate +
                ", units=" + units +
                ", remarks='" + remarks + '\'' +
                ", user='" + user + '\'' +
                ", staffNumber='" + staffNumber + '\'' +
                ", valid=" + valid +
                '}';
    }
}
