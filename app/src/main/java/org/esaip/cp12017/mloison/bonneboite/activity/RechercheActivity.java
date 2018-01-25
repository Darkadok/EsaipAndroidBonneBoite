package org.esaip.cp12017.mloison.bonneboite.activity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import org.esaip.cp12017.mloison.bonneboite.R;
import org.esaip.cp12017.mloison.bonneboite.metier.APIRequest;
import org.esaip.cp12017.mloison.bonneboite.metier.companie;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by Darkadok on 23/01/2018.
 */

public class RechercheActivity extends AppCompatActivity {
    private List<companie> companies;

    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recherche);
    }

    public void onClick_LancerRecherche(View v){
        Date timeBeforeRequest = Calendar.getInstance().getTime();
        APIRequest request = new APIRequest(construireParametres());
        request.execute();
        while (request.getStatus() == AsyncTask.Status.RUNNING && TimeUnit.MILLISECONDS.toSeconds(Calendar.getInstance().getTime().getTime() - timeBeforeRequest.getTime()) < 5){
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
            if (request.getServer_response_code() == 200){
                JSONObject response = request.getServer_response();
                try {
                    JSONArray jsonCompanies = response.getJSONArray("companies");
                    if (jsonCompanies.length() > 0){
                        companies = new ArrayList<companie>();
                        for (int i=0; i<jsonCompanies.length(); i++){
                            companie c = new companie(jsonCompanies.getJSONObject(i));
                            companies.add(c);
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                //lancer activité affichage avec RecyclerView. Nombre de pages = response.getInt(companies_count)/10
            }else{
                Log.e("Erreur requete" , String.valueOf(request.getServer_response_code()));
            }

    }

    private HashMap<String, String> construireParametres(){
        HashMap<String, String> toReturn = new HashMap<>();
        toReturn.put("commune_id", "10227");//a récuperer du choix utilisateur
        toReturn.put("rome_codes","A1202");
        return toReturn;
    }
    //From zipcode to INSEE code by choosing a city
}
