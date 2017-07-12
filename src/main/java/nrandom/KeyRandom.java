package nrandom;

import nrandom.constant.DateTimeConst;
import org.apache.commons.lang3.StringUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;
import java.util.UUID;

/**
 * Created by nguyenho on 7/5/2017.
 */
public class KeyRandom {
    public static String getWithDateTimeFormat(String formatString, String timeZone) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(formatString);
        if (StringUtils.isNotBlank(timeZone))
            dateFormat.setTimeZone(TimeZone.getTimeZone(timeZone));
        return dateFormat.format(new Date());
    }

    public static String getDateTimeKey() {
        return getWithDateTimeFormat("yyyyMMddhhmmssSS", null);
    }

    public static String getKeyWithDateTimeFormat(String formatString) {
        return getWithDateTimeFormat(formatString, DateTimeConst.PACIFIC_TIME_ZONE);
    }

    public static String getKeyWithESTDateTimeFormat(String formatString) {
        return getWithDateTimeFormat(formatString, DateTimeConst.EST_TIME_ZONE);
    }

    public static String getUUID() {
        return UUID.randomUUID().toString();
    }
}
