package com.example.utils;

import java.text.DateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

/**
 * @Description:
 * @Author: chenchong
 * @Date: 2022/1/5 10:18
 */
public class LocalDateUtil {

    public static LocalDate dateToLocalDate(Date date) {
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }

    public static Date localDateToDate(LocalDate localDate) {
        ZoneId zoneId = ZoneId.systemDefault();
        ZonedDateTime zdt = localDate.atStartOfDay(zoneId);
        return Date.from(zdt.toInstant());
    }

    public static LocalDateTime dateToLocalDateTime(Date date) {
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
    }

    public static Date localDateToDateTime(LocalDateTime localDateTime) {
        ZoneId zoneId = ZoneId.systemDefault();
        ZonedDateTime zdt = localDateTime.atZone(zoneId);
        return Date.from(zdt.toInstant());
    }



    /**
     * 获取当前季度低的时间
     */
    public static LocalDate getSeasonEndTime(LocalDate currentDate) {
        Month month = currentDate.getMonth();
        Month firstMonthOfQuarter = month.firstMonthOfQuarter();
        Month endMonthOfQuarter = Month.of(firstMonthOfQuarter.getValue() + 2);
        return LocalDate.of(currentDate.getYear(),endMonthOfQuarter,endMonthOfQuarter.length(currentDate.isLeapYear()));
    }

    public static LocalDate getSeasonEndTime() {
        LocalDate currentDate = LocalDate.now();
        return getSeasonEndTime(currentDate);
    }

    /**
     * 获取季度初的时间
     * @param currentDate
     * @return
     */

    public static LocalDate getSeasonStart(LocalDate currentDate) {
        Month month = currentDate.getMonth();
        Month firstMonthOfQuarter = month.firstMonthOfQuarter();
        return LocalDate.of(currentDate.getYear(), firstMonthOfQuarter, 1);
    }

    /**
     * 获取季度的天数
     * @param currentDate
     * @return
     */
    public static int getSeasonDayNum(LocalDate currentDate) {
        Month month = currentDate.getMonth();
        Month firstMonthOfQuarter = month.firstMonthOfQuarter();
        Month endMonthOfQuarter = Month.of(firstMonthOfQuarter.getValue() + 2);
        LocalDate start = LocalDate.of(currentDate.getYear(), firstMonthOfQuarter, 1);
        // 季度底
        LocalDate end = LocalDate.of(currentDate.getYear(), endMonthOfQuarter, endMonthOfQuarter.length(currentDate.isLeapYear()));
        long until = start.until(end, ChronoUnit.DAYS);
        // 非当前季度会少一天
        return LocalDate.now().getMonth().getValue() == currentDate.getMonth().getValue()
                ?(int) until:
                (int) until + 1;
    }


    /**
     * 下个季度末
     * @param currentDate
     * @return
     */
    public static LocalDate getNextSeasonEnd(LocalDate currentDate) {
        Month month = currentDate.getMonth();
        Month firstMonthOfQuarter = month.firstMonthOfQuarter();
        Month endMonthOfQuarter = null;
        endMonthOfQuarter = Month.of(firstMonthOfQuarter.getValue() + 5);
        return LocalDate.of(currentDate.getYear(), endMonthOfQuarter, endMonthOfQuarter.length(currentDate.isLeapYear()));
    }


    /**
     * 获取当前半年初的时间
     */
    public static LocalDate getHalfYearStart(LocalDate currentDate) {
        if (currentDate.getMonthValue() <= 6) {
            return LocalDate.of(currentDate.getYear(),1,1);
        }else {
            return LocalDate.of(currentDate.getYear(),7,1);
        }
    }

    public static LocalDate getHalfYearStart() {
        LocalDate currentDate = LocalDate.now();
        return getHalfYearStart(currentDate);
    }
    /**
     * 获取当前半年末的时间
     */
    public static LocalDate getHalfYearEnd() {
        LocalDate currentDate = LocalDate.now();
        return getHalfYearEnd(currentDate);
    }

    public static LocalDate getHalfYearEnd(LocalDate currentDate) {
        LocalDate halfYearEnd = LocalDate.of(currentDate.getYear(),6,30);
        if (currentDate.getMonthValue() > 6) {
            // 下半年
            halfYearEnd = LocalDate.of(currentDate.getYear(),12,31);
        }
        return halfYearEnd;
    }

    /**
     * 下半年开始的时间
     */
    public static LocalDate getNextHalfYearStart() {
        LocalDate currentDate = LocalDate.now();
        return getNextHalfYearStart(currentDate);
    }

    /**
     * 下半年结束的时间
     */
    public static LocalDate getNextHalfYearEnd() {
        LocalDate currentDate = LocalDate.now();
        return getNextHalfYearEnd(currentDate);
    }


    public static LocalDate getNextHalfYearStart(LocalDate currentDate) {
        if (currentDate.getMonthValue() <= 6) {
            return LocalDate.of(currentDate.getYear(),7,1);
        }else {
            return LocalDate.of(currentDate.getYear() + 1,1,1);
        }
    }

    public static LocalDate getNextHalfYearEnd(LocalDate currentDate) {
        if (currentDate.getMonthValue() <= 6) {
            return LocalDate.of(currentDate.getYear(),12,31);
        }else {
            return LocalDate.of(currentDate.getYear() + 1,6,30);
        }
    }



    public static void main(String[] args) {
//        LocalDate start = LocalDate.of(2022, 1,29);
//        LocalDate end = LocalDate.of(2023, 4,30);
//        System.out.println(getHalfYearStart(start));
//        System.out.println(getHalfYearEnd(start));
//
//        System.out.println(getNextHalfYearStart(start));
//        System.out.println(getNextHalfYearEnd(start));

//        LocalDate localDate = start.plusMonths(1);
//        System.out.println(localDate);
//
//        long diff = start.until(end,ChronoUnit.MONTHS);
//        System.out.println(diff);

//        System.out.println("===本季度末日期===");
//        System.out.println(getSeasonEndTime(start));
//
//        System.out.println("===本季度初日期===");
//        System.out.println(getSeasonStart(start));
//
//
//        System.out.println("===本季度天数===");
//        System.out.println(getSeasonDayNum(start));
//
//        System.out.println(start);
//        LocalDate end = LocalDate.of(2022, 3, 31);
//
//        long until = start.until(end, ChronoUnit.DAYS);
//        System.out.println(until);
//
//        Period period = Period.between(start, end);
//        int days = period.getDays();
//        System.out.println(days);
//
//        System.out.println("=====================");
//        System.out.println(start.toEpochDay()-end.toEpochDay());
//
//
//        System.out.println("===下个季度末===");
//        System.out.println(getNextSeasonEnd(start));

        Random random = new Random();
        String i = random.nextInt(999999999)+ "";
        System.out.println(i);


    }
}
