package com.did.core.service;

import com.did.core.dto.Entry;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Emrah Onder on 3/15/2019
 * @project moteo
 */
public class WordpressCallService {
    boolean siteChecker = false;
    private String targetURL;
    private String selectType;
    private int itemCount;

    public WordpressCallService(String targetURL, String selectType, int itemCount) {
        this.targetURL = targetURL;
        this.selectType = selectType;
        this.itemCount = itemCount;
    }

    public List<Entry> getDataBack() {
        String endpoint = "";
        List<Entry> entryList = null;
        if (this.targetURL != null) {
            switch (this.selectType) {
                case "posts":
                    endpoint = this.targetURL + "/wp-json/wp/v2/posts/?per_page=" + this.itemCount;
                    break;
                case "pages":
                    endpoint = this.targetURL + "/wp-json/wp/v2/pages/?per_page=" + this.itemCount;
                    break;
            }
            String returnData = "";
            try {
                entryList = this.getData(endpoint);
            } catch (JSONException e) {
                System.out.println(e.getMessage());
            } catch (ParseException e) {
                System.out.println(e.getMessage());
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }

        return entryList;
    }

    private List<Entry> getData(String callUri) throws JSONException, ParseException, IOException {
        List<Entry> entryList = new ArrayList<Entry>();
        String USER_AGENT = "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/53.0.2785.143 Safari/537.36";
        String returnData = "";
        System.out.print("Output from Server .... ");
        HttpGet httpGet = new HttpGet(callUri + "&_embed");
        httpGet.addHeader("User-Agent", USER_AGENT);
        String output = "";

        CloseableHttpClient closeableHttpClient = HttpClientBuilder.create().build();
        HttpResponse response = null;


        response = closeableHttpClient.execute(httpGet);

        if (response.getStatusLine().getStatusCode() != 200) {
            this.siteChecker = false;

        } else {
            this.siteChecker = true;
        }

        BufferedReader br = new BufferedReader(new InputStreamReader((response.getEntity().getContent())));
        output = br.readLine();


        try {
            closeableHttpClient.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


        JSONArray resultArray = null;
        resultArray = new JSONArray(output);


        for (int i = 0; i < resultArray.length(); i++) {
            JSONObject row = resultArray.getJSONObject(i);

            int id = row.getInt("id");
            String date = row.getString("date");
            Date date1 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").parse(date);
            JSONObject titleObject = row.getJSONObject("title");
            String title = titleObject.getString("rendered");


            String link = row.getString("link");

            JSONObject excerptObject = row.getJSONObject("excerpt");
            String excerpt = excerptObject.getString("rendered");

            Entry e = new Entry(id, date1, title, excerpt, link);
            entryList.add(e);
        }


        return entryList;
    }
}
