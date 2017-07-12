package nrandom;

import org.apache.commons.lang3.Validate;

import java.util.Random;

/**
 * Created by nguyenho on 7/5/2017.
 */
public class NumberRandom {
    private static final Random RAMDOM = new Random();

    public static boolean getBoolean() {
        return RAMDOM.nextBoolean();
    }


    public static byte[] getBytes(int count) {
        Validate.isTrue(count >= 0, "Count cannot be negative.", new Object[0]);
        byte[] result = new byte[count];
        RAMDOM.nextBytes(result);
        return result;
    }

    public static int getIntWithLength(int length) {
        int num = (int) (Math.pow(10, length) - 1);
        int min = (int) (Math.pow(10, length - 1) - 1);
        int result = RAMDOM.nextInt(num);
        while (min > result) {
            result = RAMDOM.nextInt(num);
        }
        return result;
    }


    public static int getIntBetween(int min, int max) {
        Validate.isTrue(max >= min, "Start value must be smaller or equal to end value.", new Object[0]);
        Validate.isTrue(min >= 0, "Both range values must be non-negative.", new Object[0]);
        return min == max ? min : min + RAMDOM.nextInt(max - min);
    }

    public static int getInt() {
        return getIntBetween(0, 2147483647);
    }

    public static long getLongBetween(long min, long max) {
        Validate.isTrue(max >= min, "Start value must be smaller or equal to end value.", new Object[0]);
        Validate.isTrue(min >= 0L, "Both range values must be non-negative.", new Object[0]);
        return min == max ? min : (long) getDoubleBetween((double) min, (double) max);
    }

    public static long getLong() {
        return getLongBetween(0L, 9223372036854775807L);
    }

    public static double getDoubleBetween(double min, double endInclusive) {
        Validate.isTrue(endInclusive >= min, "Start value must be smaller or equal to end value.", new Object[0]);
        Validate.isTrue(min >= 0.0D, "Both range values must be non-negative.", new Object[0]);
        return min == endInclusive ? min : min + (endInclusive - min) * RAMDOM.nextDouble();
    }

    public static double getDouble() {
        return getDoubleBetween(0.0D, 1.7976931348623157E308D);
    }

    public static float getFloatBetween(float min, float endInclusive) {
        Validate.isTrue(endInclusive >= min, "Start value must be smaller or equal to end value.", new Object[0]);
        Validate.isTrue(min >= 0.0F, "Both range values must be non-negative.", new Object[0]);
        return min == endInclusive ? min : min + (endInclusive - min) * RAMDOM.nextFloat();
    }

    public static float getFloat() {
        return getFloatBetween(0.0F, 3.4028235E38F);
    }
}
