package nutils;


import org.apache.commons.lang3.RandomStringUtils;

/**
 * Created by nguyenho on 6/20/2017.
 */
public class NUtils {
    public static String getRandomString(int length) {
        return RandomStringUtils.random(length);
    }
}
