package com.legacybridge.config;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

public class Settings {

    private final List<DateTimeFormatter> formatters = List.of(
            DateTimeFormatter.ofPattern("dd/MM/yyyy"),
            DateTimeFormatter.ofPattern("MM/dd/yyyy"),
            DateTimeFormatter.ofPattern("yyyy-MM-dd"),
            DateTimeFormatter.ofPattern("dd-MM-yyyy"),
            DateTimeFormatter.ofPattern("dd MMM yyyy", Locale.ENGLISH),
            DateTimeFormatter.ofPattern("MMM dd, yyyy", Locale.ENGLISH),
            DateTimeFormatter.ofPattern("dd 'de' MMMM 'de' yyyy", new Locale("es", "ES"))
    );

    private final List<String> words = List.of(
            "Payment", "Online", "Order", "Store", "POS", "TX",
            "REF", "Mktp", "Via", "US", "Charge", "Purchase"
    );

    private final List<String> separators = List.of(
            " ", " ", " ", "-", "*", " #", " "
    );

    public final List<DateTimeFormatter> getFormatters() {
        return formatters;
    }

    public final List<String> getWords() {
        return words;
    }

    public final List<String> getSeparators() {
        return separators;
    }

}
