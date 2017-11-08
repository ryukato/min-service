package app.util;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;

public class LocalDateTimeUtil {
    private LocalDateTimeUtil() {}

    /**
     * If the given dateTime is null or empty, then returns LocalDateTime.now(), otherwise returns LocalDateTime object for given dateTime in the given format.
     * But there is exception during the parsing the given dateTime, then returns LocalDateTime.now().
     * @param dateTime
     * @param format
     * @return
     */
    public static LocalDateTime from(String dateTime, String format) {
        LocalDateTime now = LocalDateTime.now();
        if (dateTime == null || dateTime.isEmpty()) {
            return now;
        }
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
            DateTimeFormatter formatterWithDefault = new DateTimeFormatterBuilder().append(formatter)
                    .parseDefaulting(ChronoField.HOUR_OF_DAY, 0)
                    .parseDefaulting(ChronoField.MINUTE_OF_HOUR, 0)
                    .parseDefaulting(ChronoField.SECOND_OF_MINUTE, 0)
                    .toFormatter();
            return LocalDateTime.parse(dateTime, formatterWithDefault);
        }catch (RuntimeException e) {
            return now;
        }

    }
}
