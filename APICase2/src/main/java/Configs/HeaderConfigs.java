package Configs;

import java.util.HashMap;
import java.util.Map;

import Utilities.FileandEnv;

public class HeaderConfigs {

    private static final String KEY = FileandEnv.loadFileAndEnv().get("key");
    private static final String TOKEN = FileandEnv.loadFileAndEnv().get("token");

    public Map<String, String> headersWithToken(String name) {
        Map<String, String> headers = new HashMap<>();
        headers.put("key", KEY);
        headers.put("token", TOKEN);
        headers.put("name", name);
        return headers;
    }

    public Map<String, String> headerForCard(String id) {
        Map<String, String> headers = new HashMap<>();
        headers.put("key", KEY);
        headers.put("token", TOKEN);
        headers.put("idList", id);
        return headers;
    }
}
