import nrandom.DateTimeUtil;
import org.testng.annotations.Test;

/**
 * Created by nguyenho on 7/13/2017.
 */
public class DateTimeTest {
    @Test
    public void testGetTime() {
        System.out.println(DateTimeUtil.getDateFromWordParam("NEXT_1_DAY", true));

    }
}
