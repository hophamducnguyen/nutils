package nassert;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonschema.core.tree.JsonTree;
import com.google.common.collect.Maps;
import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.MalformedJsonException;
import nassert.exception.JsonAssertException;

import java.io.IOException;
import java.io.StringReader;
import java.lang.reflect.Type;
import java.util.Map;
import static org.assertj.core.api.Assertions.*;

/**
 * Created by nguyenho on 7/12/2017.
 */
public class JsonAssert {
    public static void compareJson(String strJson1, String strJson2) {
        assertThat(strJson1).isNotNull();
        assertThat(strJson2).isNotNull();
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
        assertThat(json1).isNotNull();
        assertThat(json2).isNotNull();

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

    }

    public static void compareSchema(String schema){
        ObjectMapper mapper = new ObjectMapper();
        try {
            JsonNode tree1 = mapper.readTree(schema);
            System.out.println(tree1);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
