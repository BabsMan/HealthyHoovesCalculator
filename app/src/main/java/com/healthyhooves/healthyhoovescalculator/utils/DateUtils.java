package com.healthyhooves.healthyhoovescalculator.utils;

import android.annotation.SuppressLint;
import android.support.annotation.NonNull;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Time Utilities for formatting and calculating differences.
 */
public class DateUtils {

    @SuppressLint({ "SimpleDateFormat" }) private static final SimpleDateFormat
            FORMAT_SESSION_INSTANCE = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");

    /**
     * This constructor has been made private to prevent instantiation of this class.
     */
    private DateUtils() {
        // No implementation.
    }

    /**
     * Parse a SessionInstance date string in the format as received from the api into a
     * {@link Date}.
     *
     * @param sessionInstanceDateString The date string to parse.
     * @return An {@link Date}.
     * @throws {@link ParseException} when unable to parse string to {@link Date}.
     */
    public static Date getSessionInstanceDate(@NonNull final String sessionInstanceDateString)
            throws ParseException {
        return FORMAT_SESSION_INSTANCE.parse(sessionInstanceDateString);
    }
}
