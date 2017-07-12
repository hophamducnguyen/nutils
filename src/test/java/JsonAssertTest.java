import nassert.JsonAssert;
import org.testng.annotations.Test;

/**
 * Created by nguyenho on 7/12/2017.
 */
public class JsonAssertTest {
    @Test
    public void testJsonEqual() {
        String jsonArr1 = "  [{\"reportKey\":\"POLICYSCORES\",\"perm\":\"ROLE_PERM_POLICYSCORES\",\"filters\":[{\"required\":true,\"key\":\"policyNumber\",\"isRequired\":true,\"type\":\"string\",\"options\":1},{\"required\":true,\"key\":\"startDate\",\"isRequired\":true,\"type\":\"date\",\"options\":null}],\"headers\":[{\"headerKey\":\"policyNumber\",\"type\":\"string\"},{\"headerKey\":\"licencePlateNumber\",\"type\":\"string\"}]},{\"reportKey\":\"POLICYSCORES2\",\"perm\":\"ROLE_PERM_POLICYSCORES\",\"filters\":[{\"required\":true,\"key\":\"policyNumber\",\"isRequired\":true,\"type\":\"string\",\"options\":null},{\"required\":true,\"key\":\"startDate\",\"isRequired\":true,\"type\":\"date\",\"options\":null}],\"headers\":[{\"headerKey\":\"policyNumber\",\"type\":\"string\"},{\"headerKey\":\"licencePlateNumber\",\"type\":\"string\"}]}]";
        String jsonArr2 = "[{\"reportKey\":\"POLICYSCORES\",\"perm\":\"ROLE_PERM_POLICYSCORES\",\"filters\":[{\"required\":true,\"key\":\"policyNumber\",\"isRequired\":true,\"type\":\"string\",\"options\":1},{\"required\":true,\"key\":\"startDate\",\"isRequired\":true,\"type\":\"date\",\"options\":null}],\"headers\":[{\"headerKey\":\"policyNumber\",\"type\":\"string\"},{\"headerKey\":\"licencePlateNumber\",\"type\":\"string\"}]},{\"reportKey\":\"POLICYSCORES2\",\"perm\":\"ROLE_PERM_POLICYSCORES\",\"filters\":[{\"required\":true,\"key\":\"policyNumber\",\"isRequired\":true,\"type\":\"string\",\"options\":null},{\"required\":true,\"key\":\"startDate\",\"isRequired\":true,\"type\":\"date\",\"options\":null}],\"headers\":[{\"headerKey\":\"policyNumber\",\"type\":\"string\"},{\"headerKey\":\"licencePlateNumber\",\"type\":\"string\"}]}]\n";
        String json1 = "  {\"reportKey\":\"POLICYSCORES\",\"perm\":\"ROLE_PERM_POLICYSCORES\",\"filters\":[{\"required\":true,\"key\":\"policyNumber\",\"isRequired\":true,\"type\":\"string\",\"options\":1},{\"required\":true,\"key\":\"startDate\",\"isRequired\":true,\"type\":\"date\",\"options\":null}],\"headers\":[{\"headerKey\":\"policyNumber\",\"type\":\"string\"},{\"headerKey\":\"licencePlateNumber\",\"type\":\"string\"}]}";
        String json2 = "\n\n{\n\"reportKey\":\"POLICYSCORES\",\"perm\":\"ROLE_PERM_POLICYSCORES\",\"filters\":[{\"required\":true,\"key\":\"policyNumber\",\"isRequired\":true,\"type\":\"string\",\"options\":1},{\"required\":true,\"key\":\"startDate\",\"isRequired\":true,\"type\":\"date\",\"options\":null}],\"headers\":[{\"headerKey\":\"policyNumber\",\"type\":\"string\"},{\"headerKey\":\"licencePlateNumber\",\"type\":\"string\"}]}";
        JsonAssert.compareJson(jsonArr1, jsonArr2);
        JsonAssert.compareJson(json1, json2);
    }
}
