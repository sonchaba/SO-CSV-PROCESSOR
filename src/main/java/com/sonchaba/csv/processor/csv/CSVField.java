package com.sonchaba.csv.processor.csv;

import java.lang.annotation.*;


@Inherited
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface CSVField {
    int index();

    Class classType() default String.class;

    boolean quotes() default false;

    String format() default "";

    String name() default "";
}
