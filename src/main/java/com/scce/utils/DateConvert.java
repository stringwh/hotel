package com.scce.utils;

import org.springframework.core.convert.converter.Converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Date;

public class DateConvert implements Converter<String, Date> {
    private String pattern;

    public void setPattern(String pattern) {
        this.pattern = pattern;
    }
    @Override
    public Date convert(String source) {
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        Date d = null;
        try {
            d = format.parse(source);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return d;
    }

}
