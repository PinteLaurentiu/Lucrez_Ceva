package lucrez.ceva.service.staticService;

import lucrez.ceva.model.enums.DateTimeFormat;

import java.time.LocalDate;

public class DateTimeService {
    public static LocalDate dateFromString(String dateAsString) {
        return dateFromString(dateAsString, DateTimeFormat.IsoDate);
    }

    @SuppressWarnings("WeakerAccess")
    public static LocalDate dateFromString(String dateAsString, DateTimeFormat dateTimeFormat) {
        return LocalDate.parse(dateAsString, dateTimeFormat.getFormatter());
    }

    @SuppressWarnings("unused")
    public static String toString(LocalDate date) {
        return toString(date, DateTimeFormat.IsoDate);
    }

    @SuppressWarnings("WeakerAccess")
    public static String toString(LocalDate date, DateTimeFormat dateTimeFormat) {
        return dateTimeFormat.getFormatter().format(date);
    }
}
