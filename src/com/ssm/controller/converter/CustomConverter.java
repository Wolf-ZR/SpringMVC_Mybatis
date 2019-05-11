package com.ssm.controller.converter;

import org.springframework.core.convert.converter.Converter;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CustomConverter implements Converter<String, Date> {

    @Override
    public Date convert(String s) {
        DateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        try {
            return format.parse(s);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
}
