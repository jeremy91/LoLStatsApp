package com.example.jeremylee.lolstats;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.content.Intent;
import com.example.jeremylee.lolstats.models.summonerLogin;
import android.widget.TextView;

public class StatsActivity extends ActionBarActivity {



    String summonerName;
    String summonerId;
    TextView summonerNameView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stats);

        if (savedInstanceState == null) {
            // During initial setup, plug in the details fragment.
            Bundle data = getIntent().getExtras();
            summonerLogin summonerLoginHolder = data.getParcelable("summonerLoginHolder");
            summonerName = summonerLoginHolder.returnSummonerName();
            summonerId = summonerLoginHolder.returnId();
            Log.d("StatsActivity" , summonerName);
            Log.d("StatsActivity", summonerId);
            summonerNameView=(TextView)findViewById(R.id.summonerName);
            summonerNameView.setText(summonerId);
        }


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_stats, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /*public void setAdapter(summonerLogin summonerLoginHolder) {
        if (summonerLoginHolder != null) {
            summonerName = summonerLoginHolder.returnSummonerName();
            summonerId = summonerLoginHolder.returnId();
        }
    }*/
}
