package com.nguyenmauhuy.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TimeUtil {
    private static  final String PATTERN_DATE = "yyyy-MM-dd";
    private static  final String PATTERN_DATE_TIME = "yyyy-MM-dd hh:mm:ss";

    public static LocalDate convertToLocalDate(String date){
        return LocalDate.parse(date, DateTimeFormatter.ofPattern(PATTERN_DATE));
    }

    public static LocalDateTime convertToLocalDateTime(String date){
        return LocalDateTime.parse(date, DateTimeFormatter.ofPattern(PATTERN_DATE_TIME));
    }

}
