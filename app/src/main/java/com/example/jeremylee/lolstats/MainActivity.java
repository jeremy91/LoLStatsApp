package com.example.jeremylee.lolstats;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.app.Activity;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.Button;
import android.widget.Toast;
import android.content.Intent;
import com.example.jeremylee.lolstats.StatsActivity;

import com.example.jeremylee.lolstats.models.summonerLogin;
import com.example.jeremylee.lolstats.asynctask.LoginConnect;


public class MainActivity extends Activity {

    // UI references.
    private EditText summonerNameView;
    private EditText regionView;
    private static Button signIn;
    private View mProgressView;
    private View mLoginFormView;
    private summonerLogin summonerLoginHolder;
    private String region = "OCE";
    private Activity thisActivity = this;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        signIn=(Button)findViewById(R.id.sign_in_button);
        summonerNameView=(EditText)findViewById(R.id.summoner);
        regionView=(EditText)findViewById(R.id.region);

        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String summonerName = summonerNameView.getText().toString();
                String region = regionView.getText().toString();

                LoginConnect loginConnect = new LoginConnect(getApplicationContext(), thisActivity);
                loginConnect.execute(summonerName, region);




                /*if(summonerName.equals("acjd") &&

                        region.equals("admin")) {
                    Intent gotoStatsActivity = new Intent(v.getContext(), StatsActivity.class);
                    gotoStatsActivity.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    gotoStatsActivity.putExtra("summonerLoginHolder", summonerLoginHolder);
                    startActivity(gotoStatsActivity);
                    Toast.makeText(getApplicationContext(), "Redirecting...",Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(getApplicationContext(), "Wrong Credentials",Toast.LENGTH_SHORT).show();
                }*/
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

    public static Button getLoginButton() {
        return signIn;
    }


}
