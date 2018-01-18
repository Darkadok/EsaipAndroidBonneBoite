package org.esaip.cp12017.mloison.bonneboite;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    private APIRequestAsyncConnectionActivity Auth;
        /** Called when the activity is first created. */
        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            Auth = new APIRequestAsyncConnectionActivity();
            Auth.execute();
            String test = Auth.getServer_response();
        } // end onCreate()

    protected void onResultButtonClick(View v){
        TextView result = (TextView)findViewById(R.id.textViewResult);
        if (Auth.getServer_response_code() == 200) {
            result.setText(Auth.getServer_response());
        }
    }
    }