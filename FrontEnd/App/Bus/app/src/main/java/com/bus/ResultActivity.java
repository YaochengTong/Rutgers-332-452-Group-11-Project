package com.bus;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Random;

public class ResultActivity extends Activity {
    private static final String TAG = "ResultActivity";
    String minutes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        final TextView tv = (TextView) findViewById(R.id.text);
        final String start = getIntent().getStringExtra("start").split(" ")[0].toLowerCase();
        String end = getIntent().getStringExtra("end");
        final String route = getIntent().getStringExtra("route").toLowerCase();
        tv.setText("Your current bus stop is \n" + start + "\n\n\nYou current wait time is \n ... mins");
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    String content = getContent(route, start);
                    Log.e(TAG, "run: " + content);
                    final JSONObject jo = new JSONObject(content);
                    if (jo.has("Error")) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                tv.setText(jo.optJSONObject("Error").optString("content"));
                            }
                        });
                        return;
                    }
                    JSONObject jsonObject = jo.optJSONObject("predictions");
                    if (jsonObject.has("direction")) {
                        jsonObject = jsonObject.optJSONObject("direction");
                    } else {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                tv.setText("Your current bus stop is \n" + start + "\n\n\n Nothing found");
                            }
                        });
                        return;
                    }

                    Object prediction = jsonObject.opt("prediction");
                    if (prediction instanceof JSONObject) {
                        minutes = ((JSONObject) prediction).optString("minutes");
                    } else {
                        minutes = ((JSONArray) prediction).optJSONObject(0).optString("minutes");
                    }
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            tv.setText("Your current bus stop is \n" + start + "\n\n\nYou current wait time is \n " + minutes + " mins");
                        }
                    });

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }).start();
    }

    public static String getContent(String route, String start) {
        String u = "http://webservices.nextbus.com/service/publicJSONFeed?command=predictionsForMultiStops&a=rutgers&stops=" + route + "|null|" + start;
        Log.e(TAG, "getContent: " + u);
        URL url;
        int code = 0;
        InputStream stream = null;
        HttpURLConnection conn = null;
        try {
            url = new URL(u);
            conn = (HttpURLConnection) url.openConnection();
            conn.setConnectTimeout(60000);
            conn.setReadTimeout(60000);
            conn.connect();
            code = conn.getResponseCode();
            stream = conn.getInputStream();
            StringBuilder sb = new StringBuilder();
            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    stream, "UTF-8"));
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }

            return sb.toString();

        } catch (Exception e) {
        } finally {
            try {
                if (stream != null)
                    stream.close();
                if (conn != null)
                    conn.disconnect();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return "";
    }

}
