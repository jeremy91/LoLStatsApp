package com.example.jeremylee.lolstats.models;

import android.os.Parcelable;
import android.os.Parcel;

/**
 * Created by jeremylee on 17/04/16.
 */
public class summonerLogin implements Parcelable{
    private String summonerName;
    private String password;
    private String id;

    public summonerLogin(String summonerName, String password, String id) {
        this.summonerName = summonerName;
        this.password = password;
        this.id = id;
    }

    public String returnSummonerName() {
        return summonerName;
    }

    public String returnPassword() {
        return password;
    }

    public String returnId() {
        return id;
    }


    // Parcelling part
    public summonerLogin(Parcel in){
        String[] data = new String[3];

        in.readStringArray(data);
        this.summonerName = data[0];
        this.password = data[1];
        this.id = data[2];
    }

    @Override
    public int describeContents(){
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeStringArray(new String[] {this.summonerName,
                this.password, this.id});
    }
    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public summonerLogin createFromParcel(Parcel in) {
            return new summonerLogin(in);
        }

        public summonerLogin[] newArray(int size) {
            return new summonerLogin[size];
        }
    };
}
