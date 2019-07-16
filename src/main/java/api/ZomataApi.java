package main.java.api;

import main.java.util.HttpUtil;
import javafx.util.Pair;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ZomataApi {
    final static String API_KEY = "b3daf72e1bdd4670f27c1bc0036b52b2";
    final static String API_URL = "https://developers.zomato.com/api/v2.1";

    final static String SEARCH_API_URL = "/search";


    public String search (String lat, String lon, String radius) {

        List<Map.Entry<String, String>> header = new ArrayList<Map.Entry<String, String>>();
        header.add(new AbstractMap.SimpleEntry<String, String>("user-key", API_KEY));
        header.add(new AbstractMap.SimpleEntry<String, String>("lat", lat));
        header.add(new AbstractMap.SimpleEntry<String, String>("lon", lon));
        header.add(new AbstractMap.SimpleEntry<String, String>("radius", radius));

        HttpUtil http = new HttpUtil();
        String response = http.requestGet(API_URL + SEARCH_API_URL, header);

        return response;
    }
}
