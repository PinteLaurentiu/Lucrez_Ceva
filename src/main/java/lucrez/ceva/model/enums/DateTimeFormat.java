package lucrez.ceva.model.enums;

import java.text.SimpleDateFormat;

public enum DateTimeFormat {
    IsoDate(new SimpleDateFormat("yyyy-MM-dd"));

    private SimpleDateFormat formatter;

    DateTimeFormat(SimpleDateFormat formatter) {
        this.formatter = formatter;
    }

    public SimpleDateFormat getFormatter() {
        return formatter;
    }
}
