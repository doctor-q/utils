package cc.doctor.utils.functions;

import cc.doctor.utils.utils.DateUtils;

import java.util.Calendar;
import java.util.Date;

public class DateIterators {

    private DateIterators() {
    }

    public interface DateIterateFun {
        void fun(Date date);
    }

    public static void iterateHour(Date from, Date to, DateIterateFun dateIterateFun) {
        if (from == null || to == null || from.before(to)) {
            return;
        }

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(from);
        while (!calendar.getTime().before(to)) {
            dateIterateFun.fun(calendar.getTime());
            calendar.add(Calendar.HOUR, -1);
        }
    }

    public static void iterateDay(Date from, Date to, DateIterateFun dateIterateFun) {
        if (from == null || to == null || from.before(to)) {
            return;
        }

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(from);
        while (!calendar.getTime().before(to)) {
            dateIterateFun.fun(calendar.getTime());
            calendar.add(Calendar.DATE, -1);
        }
    }


    public static void iterateMonth(Date from, Date to, DateIterateFun dateIterateFun) {
        if (from == null || to == null || from.before(to)) {
            return;
        }

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(from);
        to = DateUtils.monthTime(to);
        while (!calendar.getTime().before(to)) {
            dateIterateFun.fun(calendar.getTime());
            calendar.add(Calendar.MONTH, -1);
        }
    }

    public static void iterateYear(Date from, Date to, DateIterateFun dateIterateFun) {
        if (from == null || to == null || from.before(to)) {
            return;
        }

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(from);
        to = DateUtils.monthTime(to);
        while (!calendar.getTime().before(to)) {
            dateIterateFun.fun(calendar.getTime());
            calendar.add(Calendar.YEAR, -1);
        }
    }
}
