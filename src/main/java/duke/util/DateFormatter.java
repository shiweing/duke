package duke.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormatter {
    /**
     * Date formatter for date and time related to task.
     */
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HHmm");

    public static Date parse(String dateString, String errorMsg) throws DukeException {
        try {
            return dateFormat.parse(dateString);
        } catch (ParseException e) {
            throw new DukeException(errorMsg);
        }
    }

    public static String format(Date date) {
        return dateFormat.format(date);
    }
}
