package org.esaip.cp12017.mloison.bonneboite;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONException;

import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    private String token;
    private APIRequestConnectionActivity Auth;

        /** Called when the activity is first created. */
        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            Auth = new APIRequestConnectionActivity();
            Auth.execute();
            if (Auth.getServer_response_code() == 200){
                try {
                    token = Auth.getServer_response().getString("access_token");
                    Log.v("AccesToken", token);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        } // end onCreate()
    }