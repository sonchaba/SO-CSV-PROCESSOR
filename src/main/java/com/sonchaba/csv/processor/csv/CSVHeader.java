package com.sonchaba.csv.processor.csv;

import java.lang.annotation.*;

@Inherited
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface CSVHeader {
    boolean has_header() default false;

    String header() default "";
}
