package org.esaip.cp12017.mloison.bonneboite;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import org.json.JSONException;

import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    private String token;
    private APIRequest Auth;
    private ProgressBar spinner;


        /** Called when the activity is first created. */
        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            spinner = (ProgressBar)findViewById(R.id.progressBar1);
            spinner.setVisibility(View.VISIBLE);
            Date timeBeforeRequest = Calendar.getInstance().getTime();
            Auth = new APIRequest();
            Auth.execute();
            do{
                try {
                    if (Auth.getServer_response_code() == 200){
                        token = Auth.getServer_response().getString("access_token");
                        Log.v("AccesToken", token);
                        spinner.setVisibility(View.GONE);
                    }else{
                        Thread.sleep(500);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            while (Auth.getServer_response() != null && TimeUnit.MILLISECONDS.toSeconds(Calendar.getInstance().getTime().getTime() - timeBeforeRequest.getTime()) < 5);
                Log.w("Service inaccessible", (String.valueOf(Auth.getServer_response_code())));
                //popup erreur.
        } // end onCreate()
    }