package com.example.utils;

import java.math.BigDecimal;
import java.time.*;
import java.time.temporal.ChronoUnit;
import java.util.Date;

/**
 * @author Leron
 * @version 1.0
 * @date 2021/5/22 14:54
 */
public class IDateTimeUtil {

    public static LocalDate dateToLocalDate(Date date) {
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }

    public static Date localDateToDate(LocalDate localDate) {
        ZoneId zoneId = ZoneId.systemDefault();
        ZonedDateTime zdt = localDate.atStartOfDay(zoneId);
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
     * 获取当前半年低的时间
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


    public static LocalDate getNextHalfYearStart(LocalDate currentDate) {
        if (currentDate.getMonthValue() <= 6) {
            return LocalDate.of(currentDate.getYear(),7,1);
        }else {
            return LocalDate.of(currentDate.getYear() + 1,1,1);
        }
    }

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



    public static BigDecimal getMonthResidueDays(LocalDate date) {
        return new BigDecimal(date.lengthOfMonth() - date.getDayOfMonth());
    }

    /**
     * 获取当前季度的天数
     */
    public static int getSeasonDayNum() {
        LocalDate currentDate = LocalDate.now();
        Month month = currentDate.getMonth();
        Month firstMonthOfQuarter = month.firstMonthOfQuarter();
        Month endMonthOfQuarter = Month.of(firstMonthOfQuarter.getValue() + 2);
        LocalDate start = LocalDate.of(currentDate.getYear(), firstMonthOfQuarter, 1);
        // 季度底
        LocalDate end = LocalDate.of(currentDate.getYear(), endMonthOfQuarter, endMonthOfQuarter.length(currentDate.isLeapYear()));
        long until = start.until(end, ChronoUnit.DAYS);
        return (int) until;
    }

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
     * 下个季度开始时间
     */
    public static LocalDate getNextSeasonStart(LocalDate currentDate) {
        Month month = currentDate.getMonth();
        Month firstMonthOfQuarter = month.firstMonthOfQuarter();
        Month endMonthOfQuarter = null;
        try {
            endMonthOfQuarter = Month.of(firstMonthOfQuarter.getValue() + 3);
        }catch (DateTimeException e) {
            endMonthOfQuarter = Month.of(1);
            currentDate = LocalDate.of(currentDate.getYear() + 1,1,1);
        }
        return LocalDate.of(currentDate.getYear(), endMonthOfQuarter, 1);
    }

    public static LocalDate getSeasonStart() {
        LocalDate currentDate = LocalDate.now();
        return getSeasonStart(currentDate);
    }

    public static LocalDate getSeasonStart(LocalDate currentDate) {
        Month month = currentDate.getMonth();
        Month firstMonthOfQuarter = month.firstMonthOfQuarter();
        return LocalDate.of(currentDate.getYear(), firstMonthOfQuarter, 1);
    }


    /**
     * 获取当前天再当前季度的第几天
     */
    public static int getDayOfSeason(LocalDate currentDate) {
        int f = 3;
        int s = 6;
        int t = 9;
        int dayNum = 0;
        // 第一季度
        if (currentDate.getMonthValue() <= f) {
            LocalDate m1 = LocalDate.of(currentDate.getYear(),1,1);
            LocalDate m2 = LocalDate.of(currentDate.getYear(),2,1);
            if (currentDate.getMonthValue() == 1) {
                dayNum = currentDate.getDayOfMonth();
            }
            if (currentDate.getMonthValue() == 2) {
                dayNum = m1.lengthOfMonth() +  currentDate.getDayOfMonth();
            }
            if (currentDate.getMonthValue() == 3) {
                dayNum = m1.lengthOfMonth() + m2.lengthOfMonth() + currentDate.getDayOfMonth();
            }
        }

        if (currentDate.getMonthValue() > f && currentDate.getMonthValue() <= s){
            LocalDate m1 = LocalDate.of(currentDate.getYear(),4,1);
            LocalDate m2 = LocalDate.of(currentDate.getYear(),5,1);
            if (currentDate.getMonthValue() == 4) {
                dayNum = currentDate.getDayOfMonth();
            }
            if (currentDate.getMonthValue() == 5) {
                dayNum = m1.lengthOfMonth() +  currentDate.getDayOfMonth();
            }
            if (currentDate.getMonthValue() == 6) {
                dayNum = m1.lengthOfMonth() + m2.lengthOfMonth() + currentDate.getDayOfMonth();
            }
        }

        if (currentDate.getMonthValue() > s && currentDate.getMonthValue() <= t){
            LocalDate m1 = LocalDate.of(currentDate.getYear(),7,1);
            LocalDate m2 = LocalDate.of(currentDate.getYear(),8,1);
            if (currentDate.getMonthValue() == 7) {
                dayNum = currentDate.getDayOfMonth();
            }
            if (currentDate.getMonthValue() == 8) {
                dayNum = m1.lengthOfMonth() +  currentDate.getDayOfMonth();
            }
            if (currentDate.getMonthValue() == 9) {
                dayNum = m1.lengthOfMonth() + m2.lengthOfMonth() + currentDate.getDayOfMonth();
            }
        }

        if (currentDate.getMonthValue() > t){
            LocalDate m1 = LocalDate.of(currentDate.getYear(),10,1);
            LocalDate m2 = LocalDate.of(currentDate.getYear(),11,1);
            if (currentDate.getMonthValue() == 10) {
                dayNum = currentDate.getDayOfMonth();
            }
            if (currentDate.getMonthValue() == 11) {
                dayNum = m1.lengthOfMonth() +  currentDate.getDayOfMonth();
            }
            if (currentDate.getMonthValue() == 12) {
                dayNum = m1.lengthOfMonth() + m2.lengthOfMonth() + currentDate.getDayOfMonth();
            }
        }
        return dayNum;
    }


    /**
     * 获取半年总天数
     */
    public static int getHalfYearDayNum() {
        LocalDate localDate = LocalDate.now();
        return getHalfYearDayNum(localDate);
    }

    public static int getHalfYearDayNum(LocalDate localDate) {
        int num = 182;
        if (localDate.getMonthValue() <= 6) {
            num = 31 * 3 +
                    LocalDate.of(localDate.getYear(),2,1).lengthOfMonth()
                    + 30 * 2;
        }
        if (localDate.getMonthValue() > 6) {
            num = 31 * 4 +
                    + 30 * 2;
        }
        return num;
    }

    public static int getSeasonOfYear(LocalDate targetDate) {
        int monthValue = targetDate.getMonthValue();
        if (monthValue <= 3) {
            return 1;
        }
        if (monthValue <= 6) {
            return 2;
        }
        if (monthValue <=9) {
            return 3;
        }
        return 4;
    }

    /**
     * 获取当前天再当前半年的第几天
     */
    public static int getDayOfHalfYear(LocalDate currentDate) {
        int num = 1;
        if (currentDate.getMonthValue() <= 6) {
            // 下半年
            LocalDate m1 = LocalDate.of(currentDate.getYear(),1,1);
            LocalDate m2 = LocalDate.of(currentDate.getYear(),2,1);
            LocalDate m3 = LocalDate.of(currentDate.getYear(),3,1);
            LocalDate m4 = LocalDate.of(currentDate.getYear(),4,1);
            LocalDate m5 = LocalDate.of(currentDate.getYear(),5,1);
            if (currentDate.getMonthValue() == 1) {
                num = currentDate.getDayOfMonth();
            }
            if (currentDate.getMonthValue() == 2) {
                num = m1.lengthOfMonth() +  currentDate.getDayOfMonth();
            }
            if (currentDate.getMonthValue() == 3) {
                num = m1.lengthOfMonth() + m2.lengthOfMonth() + currentDate.getDayOfMonth();
            }
            if (currentDate.getMonthValue() == 4) {
                num = m1.lengthOfMonth() + m2.lengthOfMonth()  + m3.lengthOfMonth() + currentDate.getDayOfMonth();
            }
            if (currentDate.getMonthValue() == 5) {
                num = m1.lengthOfMonth() + m2.lengthOfMonth() + m3.lengthOfMonth() + m4.lengthOfMonth() + currentDate.getDayOfMonth();
            }
            if (currentDate.getMonthValue() == 6) {
                num = m1.lengthOfMonth() + m2.lengthOfMonth() + m3.lengthOfMonth() + m4.lengthOfMonth() + m5.lengthOfMonth() + currentDate.getDayOfMonth();
            }
        }else {
            LocalDate m1 = LocalDate.of(currentDate.getYear(),7,1);
            LocalDate m2 = LocalDate.of(currentDate.getYear(),8,1);
            LocalDate m3 = LocalDate.of(currentDate.getYear(),9,1);
            LocalDate m4 = LocalDate.of(currentDate.getYear(),10,1);
            LocalDate m5 = LocalDate.of(currentDate.getYear(),11,1);
            if (currentDate.getMonthValue() == 7) {
                num = currentDate.getDayOfMonth();
            }
            if (currentDate.getMonthValue() == 8) {
                num = m1.lengthOfMonth() +  currentDate.getDayOfMonth();
            }
            if (currentDate.getMonthValue() == 9) {
                num = m1.lengthOfMonth() + m2.lengthOfMonth() + currentDate.getDayOfMonth();
            }
            if (currentDate.getMonthValue() == 10) {
                num = m1.lengthOfMonth() + m2.lengthOfMonth()  + m3.lengthOfMonth() + currentDate.getDayOfMonth();
            }
            if (currentDate.getMonthValue() == 11) {
                num = m1.lengthOfMonth() + m2.lengthOfMonth() + m3.lengthOfMonth() + m4.lengthOfMonth() + currentDate.getDayOfMonth();
            }
            if (currentDate.getMonthValue() == 12) {
                num = m1.lengthOfMonth() + m2.lengthOfMonth() + m3.lengthOfMonth() + m4.lengthOfMonth() + m5.lengthOfMonth() + currentDate.getDayOfMonth();
            }
        }
        return num;
    }

    public static LocalDate getYearEnd(LocalDate currentDate) {
        return LocalDate.of(currentDate.getYear(),12,31);
    }

    public static LocalDate getMonthEnd(LocalDate currentDate) {
        return LocalDate.of(currentDate.getYear(),currentDate.getMonthValue(),currentDate.lengthOfMonth());
    }

    public static LocalDate getYearStart(LocalDate currentDate) {
        return LocalDate.of(currentDate.getYear(),1,1);
    }

    public static LocalDate getYearStart() {
        return getYearStart(LocalDate.now());
    }

    public static LocalDate getMonthStart() {
        LocalDate now = LocalDate.now();
        return LocalDate.of(now.getYear(),now.getMonth(),1);
    }

    public static LocalDate getMonthEnd() {
        return getMonthEnd(LocalDate.now());
    }
}
