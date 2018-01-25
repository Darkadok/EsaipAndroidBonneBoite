package org.esaip.cp12017.mloison.bonneboite.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import org.esaip.cp12017.mloison.bonneboite.R;
import org.esaip.cp12017.mloison.bonneboite.metier.APIRequest;
import org.esaip.cp12017.mloison.bonneboite.metier.SingletonToken;
import org.esaip.cp12017.mloison.bonneboite.metier.inseeVille;
import org.esaip.cp12017.mloison.bonneboite.activity.AndroidAutoCompleteTextView;
import org.json.JSONException;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    private APIRequest Auth;
    private ProgressBar spinner;
    private List<inseeVille> villes = new ArrayList<inseeVille>();


        /** Called when the activity is first created. */
        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            if (isNetworkAvailable()){
                spinner = (ProgressBar)findViewById(R.id.progressBar1);
                spinner.setVisibility(View.VISIBLE);
                AuthtentificationAPI();
                chargerVilles();
                spinner.setVisibility(View.GONE);
            }

        } // end onCreate()

    private void AuthtentificationAPI(){
        Date timeBeforeRequest = Calendar.getInstance().getTime();
        SingletonToken token = SingletonToken.getInstance();
        Auth = new APIRequest();
        Auth.execute();
            try {
                while (Auth.getStatus() == AsyncTask.Status.RUNNING && TimeUnit.MILLISECONDS.toSeconds(Calendar.getInstance().getTime().getTime() - timeBeforeRequest.getTime()) < 5){
                    Thread.sleep(500);
                }
                if (Auth.getServer_response_code() == 200){
                    token.setValue(Auth.getServer_response().getString("access_token"));
                    Log.i("AccesToken",token.getValue());
                    Intent intent = new Intent(MainActivity.this, RechercheActivity.class);
                    startActivity(intent);
                }else{
                    Log.w("Service inaccessible", (String.valueOf(Auth.getServer_response_code())));
                    //popup erreur ?
                }
            } catch (JSONException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
    }

    private void chargerVilles(){
        InputStream is = getResources().openRawResource(R.raw.laposte_hexasmal);
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        String line;
        try {
            while ((line = reader.readLine()) != null) {
                String[] RowData = line.split(";");
                String GPS = RowData.length == 6 ? RowData[5] : "";
                villes.add(new inseeVille(RowData[0], RowData[1], RowData[2], GPS));
            }
            AndroidAutoCompleteTextView.loadCP(villes);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

}