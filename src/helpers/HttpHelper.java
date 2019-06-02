package helpers;


import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class HttpHelper {

    public String requestPost(String url) {

        HttpClient client = HttpClientBuilder.create().build();
        HttpPost request = new HttpPost(url);

        try {

            HttpResponse response = client.execute(request);


            System.out.println(response.getStatusLine());

            BufferedReader rd = new BufferedReader(
                    new InputStreamReader(response.getEntity().getContent()));
            StringBuffer result = new StringBuffer();
            String line = "";
            while ((line = rd.readLine()) != null) {
                result.append(line);
            }

            return result.toString();

        } catch (Exception e) {

            System.out.println(e.getMessage());

            return "ERROR";

        }


    }

    public String requestGet(String url) {

        HttpClient client = HttpClientBuilder.create().build();
        HttpGet request = new HttpGet(url);

        try {

            HttpResponse response = client.execute(request);


            System.out.println(response.getStatusLine());

            BufferedReader rd = new BufferedReader(
                    new InputStreamReader(response.getEntity().getContent()));
            StringBuffer result = new StringBuffer();
            String line = "";
            while ((line = rd.readLine()) != null) {
                result.append(line);
            }

            return result.toString();

        } catch (Exception e) {

            System.out.println(e.getMessage());

            return "ERROR";

        }

    }

}
