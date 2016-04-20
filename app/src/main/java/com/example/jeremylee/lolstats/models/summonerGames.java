package com.example.jeremylee.lolstats.models;

/**
 * Created by jeremylee on 20/04/16.
 */
public class summonerGames {
    String championId;
    String goldEarned;
    String numDeaths;
    String minionsKilled;
    String championsKilled;
    String totalDamageDealt;
    String win;
    String assists;

    public summonerGames(String championId, String goldEarned, String numDeaths, String minionsKilled, String championsKilled, String totalDamageDealt, String win, String assists) {
        this.championId = championId;
        this.goldEarned = goldEarned;
        this.numDeaths = numDeaths;
        this.minionsKilled = minionsKilled;
        this.championsKilled = championsKilled;
        this.totalDamageDealt = totalDamageDealt;
        this.win = win;
        this.assists = assists;
    }

    public String returnChampionId() {
        return championId;
    }

    public String returnGoldEarned() {
        return goldEarned;
    }

    public String returnNumDeaths() {
        return numDeaths;
    }

    public String returnMinionsKilled() {
        return minionsKilled;
    }

    public String returnChampionsKilled() {
        return championsKilled;
    }

    public String returnTotalDamageDealt() {
        return totalDamageDealt;
    }

    public String returnWin() {
        return win;
    }

    public String returnAssists() {
        return assists;
    }
}
