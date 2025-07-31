package ru.nesthcher.utils.time;

import lombok.experimental.UtilityClass;
import org.jetbrains.annotations.NotNull;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.TimeZone;

@UtilityClass
public class TimeUtil {
    public String FORMAT_DATETIME = "dd-MM-yyyy HH:mm:ss";
    public ZoneId TIME_ZONE = ZoneId.of("Europe/Moscow");

    public String getFormat() {
        Date date = new Date(); //
        return getDateFormat().format(date);
    }

    public DateFormat getDateFormat() {
        DateFormat df = new SimpleDateFormat(FORMAT_DATETIME);
        df.setTimeZone(TimeZone.getTimeZone(TIME_ZONE));
        return df;
    }

    public Long getMillis() {
        Date date = new Date(); //
        date.toInstant().atZone(TIME_ZONE);
        return date.getTime();
    }

    public Long toMillis(
            long time
    ) {
        if (time < 0L) return 0L;
        return getMillis() + (time * 60000);
    }

    public @NotNull String toString(
            long time
    ) {
        if (time <= 0L) {
            return "inf";
        } else {
            Instant instant = Instant.ofEpochMilli(time);
            ZonedDateTime zdt = ZonedDateTime.ofInstant(instant, TIME_ZONE);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(FORMAT_DATETIME);
            String output = formatter.format(zdt);
            return output + " (MSK)";
        }
    }

    public @NotNull String toString(
            Object time
    ) {
        if (time == null) return toString(0L);
        String sTime = String.valueOf(time);
        long lTime = Long.parseLong(sTime);
        return toString(lTime);
    }

    public @NotNull String getCompleteTime(
            int time
    ) {// 1000:59
        long longVal = new BigDecimal(time).longValue();
        int hours = (int) longVal / 3600;
        int remainder = (int) longVal - hours * 3600;
        int min = remainder / 60;
        remainder = remainder - min * 60;
        int sec = remainder;
        return String.format("%02d:%02d", min, sec);
    }

    /**
     * @param duration 10s, 5m, 12h, 3d, 1w
     * @return 10 * 1000, 5 * 60 * 1000, 12 * 60 * 60 * 1000, 3 * 24 * 60 * 60 * 1000, 1 * 7 * 24 * 60 * 60 * 1000
     */
    public long durationToMillis(
            String duration
    ) {
        if (duration == null || duration.isEmpty()) return 0L;
        char flag = duration.charAt(duration.length() - 1);
        if (flag >= '0' && flag <= '9') return Long.parseLong(duration) * 60_000;
        long multiplier = switch (flag) {
            case 'S', 's' -> 1000;
            case 'h', 'H' -> 1000 * 60 * 60;
            case 'd', 'D' -> 1000 * 60 * 60 * 24;
            case 'w', 'W' -> 1000 * 60 * 60 * 24 * 7;
            case 'i' -> 1;
            default -> 1000 * 60;
        };
        if (duration.length() == 1) return multiplier;
        String digits = duration.substring(0, duration.length() - 1);
        try {
            return (long) (Double.parseDouble(digits) * multiplier);
        } catch (NumberFormatException ex) {
            return multiplier;
        }
    }
}