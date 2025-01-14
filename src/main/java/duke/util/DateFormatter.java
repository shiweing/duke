package duke.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormatter {
    /**
     * Date formatter for date and time related to task.
     */
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HHmm");

    /**
     * Returns Date from date input string.
     * @param dateString date input satring.
     * @param errorMsg error message for exception if parsing fails.
     * @return Date.
     * @throws DukeException if unable to parse date input string to Date.
     */
    public static Date parse(String dateString, String errorMsg) throws DukeException {
        try {
            return dateFormat.parse(dateString);
        } catch (ParseException e) {
            throw new DukeException(errorMsg);
        }
    }

    /**
     * Returns formatted String from Date.
     * @param date Date to format.
     * @return formatted date string.
     */
    public static String format(Date date) {
        return dateFormat.format(date);
    }
}
