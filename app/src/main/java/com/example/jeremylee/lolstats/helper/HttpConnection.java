

/**
 * Created by jeremylee on 18/04/16.
 */
package com.example.jeremylee.lolstats.helper;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import android.widget.Toast;

import com.example.jeremylee.lolstats.R;

import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpConnection {

    private final static String TAG = "HttpConnection";

    /**
     * Try http connection to url
     * Returns string converted into json response
     * Returns null if response code is not 200
     */
    public static JSONObject httpConnect(String connectionUrl) {
        //Boolean httpOk = false;
        JSONObject jsonResponse = null;
        try {
            // Http connection
            URL url = new URL(connectionUrl);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            // Optional request header
            urlConnection.setRequestProperty("Content-Type", "application/json");
            urlConnection.setRequestProperty("Accept", "application/json");
            // Get request
            urlConnection.setRequestMethod("GET");
            Log.d(TAG, "Sending 'GET' request to URL : " + url);
            int statusCode = urlConnection.getResponseCode();
            // 200 represents HTTP OK
            if (statusCode ==  200) {
                // Get server response
                InputStream inputStream = new BufferedInputStream(urlConnection.getInputStream());
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                String line = "";
                String response = "";
                while ((line = bufferedReader.readLine()) != null) {
                    response += line;
                }
                // Close stream
                if (inputStream != null) {
                    inputStream.close();
                }
                jsonResponse = new JSONObject(response);
                //httpOk = true; // Successful
            } else {
                //httpOk = false; //"Failed to fetch data!";
            }
        } catch (Exception e) {
            Log.d(TAG, e.getLocalizedMessage());
        }
        return jsonResponse;
    }

    /**
     * Try http connection to url
     */
    /*public static JSONObject httpConnect(String connectionUrl) {
        JSONObject jsonResponse = null;
        BufferedReader serverResponse = null;
        try {
            // HTTP connection
            URL url = new URL(connectionUrl);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("GET");
            Log.d(TAG, "Sending 'GET' request to URL : " + url);
//            int responseCode = httpURLConnection.getResponseCode();
//            System.out.println("Response Code : " + responseCode);

            // Get server response
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = bufferedReader.readLine()) != null) {
                response.append(inputLine);
            }
            bufferedReader.close();
            jsonResponse = new JSONObject(response.toString());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (serverResponse != null) {
                try {
                    serverResponse.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return jsonResponse;
    }*/

    /**
     * Check if network is online
     */
    public static boolean isOnline(Context context) {
        ConnectivityManager connMgr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        return (networkInfo != null && networkInfo.isConnected());
    }

    /**
     * Show network unavailable toast
     */
    public static void networkUnavailableToast(Context context) {
        Toast.makeText(context, context.getString(R.string.network_unavailable), Toast.LENGTH_SHORT).show();
    }

}

