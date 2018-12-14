package lucrez.ceva.model.enums;

import java.time.format.DateTimeFormatter;

public enum DateTimeFormat {
    IsoDate(DateTimeFormatter.ISO_LOCAL_DATE);

    private DateTimeFormatter formatter;

    DateTimeFormat(DateTimeFormatter formatter) {
        this.formatter = formatter;
    }

    public DateTimeFormatter getFormatter() {
        return formatter;
    }
}
