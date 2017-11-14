package ru.vyukov.time;

import org.thymeleaf.util.Validate;

import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class InstantFormatter {


    private Locale locale;
    private ZoneId zone;

    public InstantFormatter(Locale locale) {
        this(locale, ZoneId.systemDefault());
    }

    public InstantFormatter(Locale locale, ZoneId zone) {
        this.locale = locale;
        this.zone = zone;
    }


    public String formatInstant(final java.time.Instant target, final String pattern) {
        Validate.notNull(target, "Cannot apply format on null");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern, locale).withZone(zone);
        return formatter.format(target);
    }
}
