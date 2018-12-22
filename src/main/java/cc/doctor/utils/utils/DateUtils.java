package cc.doctor.utils.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by doctor on 2017/3/8.
 */
public class DateUtils {

    public static final long SECOND_IN_TIMESTAMP = 1000L;
    public static final long MINUTE_IN_TIMESTAMP = 60 * SECOND_IN_TIMESTAMP;
    public static final long HOUR_IN_TIMESTAMP = 60 * MINUTE_IN_TIMESTAMP;
    public static final long DAY_IN_TIMESTAMP = 24 * HOUR_IN_TIMESTAMP;

    public static final String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";
    public static final String YYYY_MM_DD = "yyyy-MM-dd";
    public static final String YYYY_MM = "yyyy-MM";
    public static final String Y_M_D = "yyyyMMdd";
    public static final String Y_M = "yyyyMM";
    public static final String Y_M_D_H_M_S = "yyyyMMddHHmmss";

    private DateUtils() {
    }

    private static ThreadLocal<SimpleDateFormat> simpleDateFormatThreadLocal = new ThreadLocal<SimpleDateFormat>() {
        @Override
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat();
        }
    };

    public static String format(Date date, String pattern) {
        SimpleDateFormat simpleDateFormat = simpleDateFormatThreadLocal.get();
        simpleDateFormat.applyPattern(pattern);
        return simpleDateFormat.format(date);
    }

    public static Date parse(String dateStr, String pattern) {
        SimpleDateFormat simpleDateFormat = simpleDateFormatThreadLocal.get();
        simpleDateFormat.applyPattern(pattern);
        try {
            return simpleDateFormat.parse(dateStr);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public static String toYMdHms(Date date) {
        return format(date, Y_M_D_H_M_S);
    }

    public static String toYMdHms(long timestamp) {
        return toYMdHms(new Date(timestamp));
    }

    public static String toYYYYMMddHHmmss(Date date) {
        return format(date, YYYY_MM_DD_HH_MM_SS);
    }

    public static String toYYYYMMddHHmmss(long timestamp) {
        return toYYYYMMddHHmmss(new Date(timestamp));
    }

    public static String toYYMMdd(Date date) {
        return format(date, YYYY_MM_DD);
    }

    public static String toYYMMdd(long timestamp) {
        return toYYMMdd(new Date(timestamp));
    }

    public static String toYYYYMM(Date date) {
        return format(date, YYYY_MM);
    }

    public static String toYYYYMM(long timestamp) {
        return toYYYYMM(new Date(timestamp));
    }

    public static String toYMd(long timestamp) {
        return toYMd(new Date(timestamp));
    }

    public static String toYMd(Date date) {
        return format(date, Y_M_D);
    }

    public static String toYM(Date date) {
        return format(date, Y_M);
    }

    public static String toYM(long timestamp) {
        return toYM(new Date(timestamp));
    }

    public static String year(Date date) {
        return String.valueOf(yearInt(date));
    }

    public static String month(Date date) {
        int monthInt = monthInt(date);
        return monthInt >= 10 ? String.valueOf(monthInt) : "0" + monthInt;
    }

    public static String day(Date date) {
        int dayInt = dayInt(date);
        return dayInt >= 10 ? String.valueOf(dayInt) : "0" + dayInt;
    }

    public static String hour(Date date) {
        int hourInt = hourInt(date);
        return hourInt >= 10 ? String.valueOf(hourInt) : "0" + hourInt;
    }

    public static String minute(Date date) {
        int minuteInt = minuteInt(date);
        return minuteInt >= 10 ? String.valueOf(minuteInt) : "0" + minuteInt;
    }

    public static String second(Date date) {
        int secondInt = secondInt(date);
        return secondInt >= 10 ? String.valueOf(secondInt) : "0" + secondInt;
    }

    public static int yearInt(Date date) {
        Calendar instance = Calendar.getInstance();
        instance.setTime(date);
        return instance.get(Calendar.YEAR);
    }

    public static int monthInt(Date date) {
        Calendar instance = Calendar.getInstance();
        instance.setTime(date);
        return instance.get(Calendar.MONTH);
    }

    public static int dayInt(Date date) {
        Calendar instance = Calendar.getInstance();
        instance.setTime(date);
        return instance.get(Calendar.DAY_OF_MONTH);
    }

    public static int hourInt(Date date) {
        Calendar instance = Calendar.getInstance();
        instance.setTime(date);
        return instance.get(Calendar.HOUR_OF_DAY);
    }

    public static int minuteInt(Date date) {
        Calendar instance = Calendar.getInstance();
        instance.setTime(date);
        return instance.get(Calendar.MINUTE);
    }

    public static int secondInt(Date date) {
        Calendar instance = Calendar.getInstance();
        instance.setTime(date);
        return instance.get(Calendar.SECOND);
    }

    public static Date minuteTime(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        setMinute(calendar);
        return calendar.getTime();
    }

    public static Date hourTime(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        setHour(calendar);
        return calendar.getTime();
    }

    public static Date dayTime(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        setDay(calendar);
        return calendar.getTime();
    }

    public static Date monthTime(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        setMonth(calendar);
        return calendar.getTime();
    }

    public static Date yearTime(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        setYear(calendar);
        return calendar.getTime();
    }

    public static void setYear(Calendar calendar) {
        calendar.set(Calendar.MONTH, 1);
        calendar.set(Calendar.DATE, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MINUTE, 0);
    }

    public static void setMonth(Calendar calendar) {
        calendar.set(Calendar.DATE, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MINUTE, 0);
    }

    public static void setDay(Calendar calendar) {
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MINUTE, 0);
    }

    public static void setHour(Calendar calendar) {
        calendar.set(Calendar.MILLISECOND, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MINUTE, 0);
    }

    public static void setMinute(Calendar calendar) {
        calendar.set(Calendar.MILLISECOND, 0);
        calendar.set(Calendar.SECOND, 0);
    }

    public static Date prevHour(Date date) {
        return nextHours(date, -1);
    }

    public static Date nextHour(Date date) {
        return nextHours(date, 1);
    }

    public static Date nextHours(Date date, int n) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.HOUR_OF_DAY, n);
        return calendar.getTime();
    }

    public static Date prevDay(Date date) {
        return nextDays(date, -1);
    }

    public static Date nextDay(Date date) {
        return nextDays(date, 1);
    }

    public static Date nextDays(Date date, int n) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, n);
        return calendar.getTime();
    }

    /**
     * 当月的最后一天
     *
     * @param date 当前月
     */
    public static Date lastDayOfMonth(Date date) {
        Date monthTime = monthTime(date);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(monthTime);
        calendar.add(Calendar.MONTH, 1);
        calendar.add(Calendar.DATE, -1);
        return calendar.getTime();
    }

    /**
     * 当年最后一天
     *
     * @param date 当前年
     */
    public static Date lastDayOfYear(Date date) {
        Date yearTime = yearTime(date);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(yearTime);
        calendar.add(Calendar.YEAR, 1);
        calendar.add(Calendar.DATE, -1);
        return calendar.getTime();
    }

    /**
     * unix 时间戳
     */
    public static long unixTimetamp(Date date) {
        return date.getTime() / 1000;
    }

    /**
     * 纳秒时间戳
     */
    public static long nanoTimestamp(Date date) {
        return date.getTime() * 1000000;
    }

    /**
     * 是否闰年，四年一闰，百年不闰，四百年再闰
     */
    public static boolean isLeapYear(Date date) {
        int yearInt = yearInt(date);
        return (yearInt % 4 == 0 && yearInt % 100 != 0) || yearInt % 400 == 0;
    }

}
