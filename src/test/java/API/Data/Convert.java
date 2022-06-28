package API.Data;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class Convert {

    public static JsonObject getJsonO(String jsonString){
        if (jsonString != null) {
            return JsonParser.parseString(jsonString).getAsJsonObject();
        }else {
            JsonObject jsonO = new JsonObject();
            jsonO.addProperty("Body is empty",0);
            return jsonO;
        }
    }

    public static JsonArray getJsonA(String jsonString){
        if (jsonString != null) {
            return JsonParser.parseString(jsonString).getAsJsonArray();
        }else {
            JsonArray jsonA = new JsonArray();
            jsonA.add("Body is empty");
            return jsonA;
        }
    }

}
