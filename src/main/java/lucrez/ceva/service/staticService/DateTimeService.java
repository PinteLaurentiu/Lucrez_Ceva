package lucrez.ceva.service.staticService;

import lucrez.ceva.model.enums.DateTimeFormat;

import java.text.ParseException;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import static java.time.temporal.ChronoUnit.DAYS;

public class DateTimeService {
    public static Date dateFromString(String dateAsString) {
        return dateFromString(dateAsString, DateTimeFormat.IsoDate);
    }

    @SuppressWarnings("WeakerAccess")
    public static Date dateFromString(String dateAsString, DateTimeFormat dateTimeFormat) {
        try {
            return dateTimeFormat.getFormatter().parse(dateAsString);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    @SuppressWarnings("unused")
    public static String toString(LocalDate date) {
        return toString(date, DateTimeFormat.IsoDate);
    }

    @SuppressWarnings("WeakerAccess")
    public static String toString(LocalDate date, DateTimeFormat dateTimeFormat) {
        return dateTimeFormat.getFormatter().format(date);
    }

    public static Integer daysDifference(Date date, Date now) {
        long millis = Math.abs(now.getTime() - date.getTime());
        long days = TimeUnit.DAYS.convert(millis, TimeUnit.MILLISECONDS);
        return (int)days;
    }

    public static Date addDays(Date date, int days){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, days);
        return calendar.getTime();
    }
}
