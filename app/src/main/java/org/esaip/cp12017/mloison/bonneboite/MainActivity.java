package org.esaip.cp12017.mloison.bonneboite;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import org.json.JSONException;

public class MainActivity extends AppCompatActivity {

    private String token;
    private APIRequestConnection Auth;

        /** Called when the activity is first created. */
        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            Auth = new APIRequestConnection();
            Auth.execute();
            if (Auth.getServer_response_code() == 200){
                try {
                    token = Auth.getServer_response().getString("access_token");
                    Log.v("AccesToken", token);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }else{
                Log.w("Service inaccessible", (String.valueOf(Auth.getServer_response_code())));
                //popup erreur.
            }
        } // end onCreate()
    }