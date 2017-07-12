package nassert;

import com.google.common.collect.Maps;
import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.MalformedJsonException;
import nassert.exception.JsonAssertException;

import java.io.StringReader;
import java.lang.reflect.Type;
import java.util.Map;

/**
 * Created by nguyenho on 7/12/2017.
 */
public class JsonAssert {
    public static void compareJson(String strJson1, String strJson2) {
        JsonParser parser = new JsonParser();
        try {
            JsonElement json1 = parser.parse(new StringReader(strJson1));
            JsonElement json2 = parser.parse(new StringReader(strJson2));
            compareJson(json1, json2);
        }catch (JsonSyntaxException e){
            throw new JsonAssertException("Incorrect Json format.");
        }
    }

    public static void compareJson(JsonElement json1, JsonElement json2) {
        // Check whether both jsonElement are not null
        if (json1 != null && json2 != null) {

            // Check whether both jsonElement are objects
            if (json1.isJsonObject() && json2.isJsonObject()) {
                Gson g = new Gson();
                Type mapType = new TypeToken<Map<String, Object>>() {
                }.getType();
                Map<String, Object> firstMap = g.fromJson(json1, mapType);
                Map<String, Object> secondMap = g.fromJson(json2, mapType);
                String difference = Maps.difference(firstMap, secondMap).toString();
                if (!"equal".equals(difference))
                    throw new JsonAssertException(difference);
            }

            // Check whether both jsonElement are arrays
            else if (json1.isJsonArray() && json2.isJsonArray()) {
                JsonArray jarr1 = json1.getAsJsonArray();
                JsonArray jarr2 = json2.getAsJsonArray();
                if (jarr1.size() != jarr2.size()) {
                    throw new JsonAssertException(String.format("Json size is difference. %d vs %d", jarr1.size(), jarr2.size()));
                } else {
                    int i = 0;
                    // Iterate JSON Array to JSON Elements
                    for (JsonElement je : jarr1) {
                        compareJson(je, jarr2.get(i));
                        i++;
                    }
                }
            }

            // Check whether both jsonElement are primitives
            else if (json1.isJsonPrimitive() && json2.isJsonPrimitive()) {
                if (!json1.equals(json2)) {
                    throw new JsonAssertException("JsonPrimitive are NOT equal");
                }
            } else {
                throw new JsonAssertException("The input are NOT JSON format");
            }
        } else {
            throw new JsonAssertException("NULL Json Input");
        }
    }
}
