package api;

import helpers.HttpHelper;

public class ZomataApi {
    final static String API_KEY = "b3daf72e1bdd4670f27c1bc0036b52b2";
    final static String API_URL = "https://developers.zomato.com/api/v2.1";

    final static String SEARCH_API_URL = "/search";


    public String search (double lat, double lon, double radius) {

        HttpHelper http = new HttpHelper();
        String response = http.requestGet(API_URL + SEARCH_API_URL);

        return response;
    }
}
