package Builders;

import java.util.HashMap;
import java.util.Map;

import Utilities.FileandEnv;

public class PostAPIBuilder {
    String key = FileandEnv.loadFileAndEnv().get("key");
    String token = FileandEnv.loadFileAndEnv().get("token");

    public Map<String, String> postRequestBody(String name) {

        Map<String, String> body = new HashMap<String, String>();

        body.put("key", key);
        body.put("token", token);
        body.put("name", name);

        return body;

    }

    public Map<String, String> getRequestBody() {

        Map<String, String> body = new HashMap<String, String>();

        body.put("key", key);
        body.put("token", token);

        return body;

    }

}