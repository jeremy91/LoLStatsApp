package com.example.jeremylee.lolstats.asynctask;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;

import com.example.jeremylee.lolstats.MainActivity;
import com.example.jeremylee.lolstats.StatsActivity;
import com.example.jeremylee.lolstats.helper.HttpConnection;
import com.example.jeremylee.lolstats.helper.UrlList;
import com.example.jeremylee.lolstats.models.summonerGames;
import com.example.jeremylee.lolstats.models.summonerLogin;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Created by jeremylee on 20/04/16.
 */
public class fetchGames extends AsyncTask<String, Void, summonerGames> {

    private final String TAG = getClass().getSimpleName();

    private Context context;
    private Activity activity;
    private boolean networkAvailable = true;
    private boolean validateLoginSuccess = false;


    public fetchGames(Context context, Activity activity) {
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
    protected summonerGames doInBackground(String... args) {
        JSONObject response = null;
        String region = args[0];
        String id = args[1];
        summonerGames summonerGamesHolder = null;
        Log.d(TAG, "We are loggin in..");
        // Save login credentials
        //LoginCredentials.setPreference(context, context.getResources().getString(R.string.entered_username), enteredUsername);
        //LoginCredentials.setPreference(context, context.getResources().getString(R.string.password), password);

        try {
            // 1. Http online, else networkAvailable = false
            if (HttpConnection.isOnline(context)) {
                response = HttpConnection.httpConnect(UrlList.fetchGames(region, id));

                // 2. Json response retrieved, else return null
                if (response != null) {
                    Log.d(TAG, "We logged in");
                    validateLoginSuccess = true;
                    String championId;
                    String goldEarned;
                    String numDeaths;
                    String minionsKilled;
                    String championsKilled;
                    String totalDamageDealt;
                    String win;
                    String assists;
                    JSONArray returnData = response.getJSONArray("games");
                    for(int i = 0; i < 10; i++) {
                        returnData[i];
                    }
                    //String username = returnData.getString("name");
                    //String id = returnData.getString("id");
                    //summonerGamesHolder = new summonerGames(region, id);


                }
            } else {
                networkAvailable = false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return summonerGamesHolder;
    }

    @Override
    protected void onPostExecute(summonerGames summonerGamesHolder) {
        // 1, 2, 3. Http online, json retrieved and no error
        if (networkAvailable && validateLoginSuccess == true) {
            // On success, go to home
            Log.d(TAG, "We got to onPostExecute");
            Intent goToStats = new Intent(activity, StatsActivity.class);
            goToStats.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            goToStats.putExtra("summonerGamesHolder", summonerGamesHolder);
            activity.startActivity(goToStats);
            activity.finish();  // finish Login
        } else {
            // On fail, display Login elements again
            MainActivity.getLoginButton().setVisibility(View.VISIBLE);
            if (!networkAvailable) {
                // If offline, show toast
                HttpConnection.networkUnavailableToast(context);
            } else if (summonerGamesHolder != null ) {
                // Show error message from server

            }
        }
    }

}
