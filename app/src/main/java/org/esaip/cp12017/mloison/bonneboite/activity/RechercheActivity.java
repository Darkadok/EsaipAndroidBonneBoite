package org.esaip.cp12017.mloison.bonneboite.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import org.esaip.cp12017.mloison.bonneboite.R;
import org.esaip.cp12017.mloison.bonneboite.metier.APIRequest;
import org.esaip.cp12017.mloison.bonneboite.metier.companie;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Darkadok on 23/01/2018.
 */

public class RechercheActivity extends AppCompatActivity {
    private String token;
    private List<companie> companies;

    public RechercheActivity(String token) {
        this.token = token;
    }

    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recherche);
    }

    public void onClick_LancerRecherche(View v){
        APIRequest request = new APIRequest(token, construireParametres());
        request.execute();
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
        }
    }

    private HashMap<String, String> construireParametres(){
        HashMap<String, String> toReturn = new HashMap<>();
        toReturn.put("commune_id", "00000");
        return toReturn;
    }
    //From zipcode to INSEE code by choosing a city
}
