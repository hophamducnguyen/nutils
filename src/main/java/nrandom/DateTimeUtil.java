package nrandom;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by nguyenho on 4/14/2016.
 */
public class DateTimeUtil {

    public static final Pattern ALPHABETIC_CURRENT_DATE = Pattern.compile("NOW|CURRENT_DATE");
    public static final Pattern ALPHABETIC_END_DATE_OF_CURRENT_MONTH = Pattern.compile("END_CURRENT_MONTH");
    public static final Pattern ALPHABETIC_DATE_PATTERN = Pattern.compile("^(LAST|NEXT)_([0-9]+)_(DAY|MONTH|YEAR)(S)?$");

    public static final String DATE_FORMAT = "yyyy-MM-dd";

    private enum CalendarUnit {
        DAY("DAY"),
        MONTH("MONTH"),
        YEAR("YEAR");

        private String name;

        private CalendarUnit(final String name) {
            this.name = name;
        }
    }

    public static String changeDateFormat(String inputDate, String inputFormat, String outputFormat) {
        SimpleDateFormat sdf = new SimpleDateFormat(inputFormat);
        Date date = null;
        try {
            date = sdf.parse(inputDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        sdf.applyPattern(outputFormat);
        return sdf.format(date);
    }

    public static String getCurrentDataTimeString(String format) {
        DateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(new Date());
    }

    public static String addTime(String inputDate, String format, int addTime, int calendarTime) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        Date date = null;
        try {
            date = sdf.parse(inputDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(calendarTime, addTime);
        return sdf.format(calendar.getTime());
    }

    public static String addHours(String inputDate, String format, int addTime) {
        return addTime(inputDate, format, addTime, Calendar.HOUR);
    }

    public static String addMinutes(String inputDate, String format, int addTime) {
        return addTime(inputDate, format, addTime, Calendar.MINUTE);
    }

    public static boolean checkDateTimeFormat(String inputDate, String format) {
        boolean flag = false;
        DateFormat df = new SimpleDateFormat(format);
        df.setLenient(false);
        try {
            Date date = df.parse(inputDate);
            flag = true;
        } catch (ParseException e) {
            flag = false;
        }
        return flag;
    }


    /**
     * Parse date from a word string
     * If param round=true will round up the NEXT date to the last day of the next x month(s)
     * or round down the LAST date to the first day of the last x month(s).
     * For example: the current date is 7/27/2015
     * round = false: LAST_12_MONTHS=7/27/2014; NEXT_1_MONTHS=8/27/2015; NOW=7/27/2015; END_CURRENT_MONTH=7/31/2015
     * round = true: LAST_12_MONTHS=7/01/2014; NEXT_1_MONTHS=8/31/2015; NOW=7/27/2015; END_CURRENT_MONTH=7/31/2015
     *
     * @param dateString
     * @param round      a boolean
     * @return
     */
    public static Date getDateFromWordParam(String dateString, boolean round) {
        Calendar now = Calendar.getInstance();

        // Matching current date pattern
        Matcher m = ALPHABETIC_CURRENT_DATE.matcher(dateString.toUpperCase());
        if (m.find()) {
            return now.getTime();
        }

        // matching alphabetic end date of current month pattern
        m = ALPHABETIC_END_DATE_OF_CURRENT_MONTH.matcher(dateString.toUpperCase());
        if (m.find()) {
            now.set(Calendar.DAY_OF_MONTH, now.getActualMaximum(Calendar.DAY_OF_MONTH));
            return now.getTime();
        }

        // Matching alphabetic date pattern
        m = ALPHABETIC_DATE_PATTERN.matcher(dateString.toUpperCase());
        if (!m.find()) {
            // If not matching with alphabetic date pattern, try to parse as a regular date
            try {
                return new SimpleDateFormat(DATE_FORMAT).parse(dateString);
            } catch (ParseException pe) {
                return null;
            }
        }
        int numericValue = Integer.parseInt(m.group(2));
        boolean isLast = "LAST".equals(m.group(1));
        numericValue = isLast ? numericValue * -1 : numericValue;
        CalendarUnit calUnit = CalendarUnit.valueOf(m.group(3));
        switch (calUnit) {
            case DAY:
                now.add(Calendar.DATE, numericValue);
                return now.getTime();

            case MONTH:
                now.add(Calendar.MONTH, numericValue);
                break;

            case YEAR:
                now.add(Calendar.YEAR, numericValue);
                break;
            default:
                break;
        }

        if (round) {
            if (isLast) {
                // get the last day of the month
                now.set(Calendar.DAY_OF_MONTH, now.getActualMinimum(Calendar.DAY_OF_MONTH));
            } else {
                // get the first day of the month
                now.set(Calendar.DAY_OF_MONTH, now.getActualMaximum(Calendar.DAY_OF_MONTH));
            }
        }
        return now.getTime();
    }

    /**
     * Format date into a string as format "yyyy-MM-dd"
     *
     * @param date
     * @return
     */
    public static String formatDate(Date date) {
        if (date == null) {
            return "";
        }
        return new SimpleDateFormat(DATE_FORMAT).format(date);
    }
}