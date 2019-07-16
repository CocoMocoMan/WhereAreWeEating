package util;

import org.json.JSONObject;

public class JsonUtil {

    public JSONObject parseJson (String jsonString) {
        JSONObject obj = new JSONObject(jsonString);
        return obj;
    }

}
