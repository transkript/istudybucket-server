package com.elroykanye.istudybucket.business.util;

import java.time.Instant;

public class InstantUtil {
    public static String extractDateFromInstant(Instant instant) {
        return splitInstant(instant)[0];
    }

    public static String extractTimeFromInstant(Instant instant) {
        return splitInstant(instant)[1];
    }

    private static String[] splitInstant(Instant instant) {
        return instant.toString().split("T");
    }
}
