package com.example.jeremylee.lolstats.asynctask;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.view.View;
import android.widget.Toast;
import android.util.Log;
import com.example.jeremylee.lolstats.R;
import com.example.jeremylee.lolstats.MainActivity;
import com.example.jeremylee.lolstats.StatsActivity;
import com.example.jeremylee.lolstats.helper.HttpConnection;
import com.example.jeremylee.lolstats.helper.UrlList;
import com.example.jeremylee.lolstats.models.summonerLogin;


import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by jeremylee on 18/04/16.
 */
public class LoginConnect extends AsyncTask<String, Void, summonerLogin> {

    private final String TAG = getClass().getSimpleName();

    private Context context;
    private Activity activity;
    private boolean networkAvailable = true;
    private boolean validateLoginSuccess = false;


    public LoginConnect(Context context, Activity activity) {
        this.context = context;
        this.activity = activity;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        // Hide elements, show progressview
        MainActivity.getLoginButton().setVisibility(View.GONE);
    }

    @Override
    protected summonerLogin doInBackground(String... args) {
        JSONObject response = null;
        String enteredUsername = args[0];
        String region = args[1];
        summonerLogin summonerLoginHolder = null;
        Log.d(TAG, "We are loggin in..");
        // Save login credentials
        //LoginCredentials.setPreference(context, context.getResources().getString(R.string.entered_username), enteredUsername);
        //LoginCredentials.setPreference(context, context.getResources().getString(R.string.password), password);

        try {
            // 1. Http online, else networkAvailable = false
            if (HttpConnection.isOnline(context)) {
                response = HttpConnection.httpConnect(UrlList.ValidateLogin(enteredUsername, region));
                // 2. Json response retrieved, else return null
                if (response != null) {
                    Log.d(TAG, "We logged in");
                    validateLoginSuccess = true;
                    JSONObject returnData = response.getJSONObject(enteredUsername);

                    String username = returnData.getString("name");
                    String id = returnData.getString("id");
                    Log.d(TAG, username);
                    Log.d(TAG, id);
                    Log.d(TAG, region);

                    summonerLoginHolder = new summonerLogin(username, region, id);

                }
            } else {
                networkAvailable = false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return summonerLoginHolder;
    }

    @Override
    protected void onPostExecute(summonerLogin summonerLoginHolder) {
        // 1, 2, 3. Http online, json retrieved and no error
        if (networkAvailable && validateLoginSuccess == true) {
            // On success, go to home
            Log.d(TAG, "We got to onPostExecute");
            Intent goToStats = new Intent(activity, StatsActivity.class);
            goToStats.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            goToStats.putExtra("summonerLoginHolder", summonerLoginHolder);
            activity.startActivity(goToStats);
            activity.finish();  // finish Login
        } else {
            // On fail, display Login elements again
            MainActivity.getLoginButton().setVisibility(View.VISIBLE);
            if (!networkAvailable) {
                // If offline, show toast
                HttpConnection.networkUnavailableToast(context);
            } else if (summonerLoginHolder != null ) {
                // Show error message from server

            }
        }
    }
}
