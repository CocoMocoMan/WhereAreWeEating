package api;

import main.java.util.HttpUtil;

public class GeocodeApi {
    final static String API_KEY = "AIzaSyA0rtcMsUZxxoQioLCYdINLnXWlriOrbZk";
    final static String API_URL = "https://maps.googleapis.com/maps/api/geocode/json";

    public String zipCode (String zipCode) {

        String zipCodeLookUp = "?address=" + zipCode;

        HttpUtil httpUtil = new HttpUtil();
        String response = httpUtil.requestGet(API_URL + zipCodeLookUp + "&key=" + API_KEY);

        return response;

    }

}
