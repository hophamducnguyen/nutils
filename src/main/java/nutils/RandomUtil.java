package nutils;


import nutils.constant.DateTimeConst;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.TimeZone;
import java.util.UUID;

/**
 * Created by nguyenho on 4/14/2016.
 */
public class RandomUtil {
    public static int getRandomIntBetween(int min, int max) {
        Random random = new Random();
        return random.nextInt(max - min) + min;
    }

    public static int getRandomIntLength(int length) {
        Random random = new Random();
        int num = (int) (Math.pow(10, length) - 1);
        int min = (int) (Math.pow(10, length - 1) - 1);
        int result = random.nextInt(num);
        while (min > result) {
            result = random.nextInt(num);
        }
        return result;
    }

    public static String getUniqueKeyWithDateTimeFormat(String formatString, String timeZone) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(formatString);
        dateFormat.setTimeZone(TimeZone.getTimeZone(timeZone));
        return dateFormat.format(new Date());
    }

    public static String getUniqueKeyWithDateTimeFormat(String formatString) {
        return getUniqueKeyWithDateTimeFormat(formatString, DateTimeConst.PACIFIC_TIME_ZONE);
    }

    public static String getUniqueKeyWithESTDateTimeFormat(String formatString) {
        return getUniqueKeyWithDateTimeFormat(formatString, DateTimeConst.EST_TIME_ZONE);
    }

    public static String getUniqueKeyWithDateTimeFormat() {
        return getUniqueKeyWithDateTimeFormat("yyyyMMddhhmmssSS");
    }

    public static String getRandomUID() {
        return UUID.randomUUID().toString();
    }
}
