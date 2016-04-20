package com.example.jeremylee.lolstats.helper;

import android.util.Log;

/**
 * Created by jeremylee on 18/04/16.
 */
public class UrlList {

    private static final String apiKey = "20532a40-5c51-4e07-bcae-f32b7daeea26";

    // Login
    public static String ValidateLogin (String username, String region) {
        String lowerRegion = region.toLowerCase();
        String lowerUsername = username.toLowerCase();
        return "https://" + lowerRegion + ".api.pvp.net/api/lol/" + lowerRegion + "/v1.4/summoner/by-name/" + lowerUsername + "?api_key=" + apiKey;
    }

    //Get game data
    public static String fetchGames(String region, String summonerId) {
        String lowerRegion = region.toLowerCase();
        return "https://" + lowerRegion + ".api.pvp.net/api/lol/" + lowerRegion + "/v1.3/game/by-summoner/" + summonerId + "/recent?api_key=" + apiKey;
    }
}
