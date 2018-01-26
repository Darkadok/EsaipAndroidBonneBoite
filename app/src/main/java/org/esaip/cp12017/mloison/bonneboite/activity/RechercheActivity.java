package org.esaip.cp12017.mloison.bonneboite.activity;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;

import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import org.esaip.cp12017.mloison.bonneboite.R;
import org.esaip.cp12017.mloison.bonneboite.metier.APIRequest;
import org.esaip.cp12017.mloison.bonneboite.metier.companie;
import org.esaip.cp12017.mloison.bonneboite.metier.inseeVille;
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

public class RechercheActivity extends AppCompatActivity implements TextWatcher {
    public static List<companie> companies;
    AutoCompleteTextView myAutoComplete;
    private String _inseeCodeSelected;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recherche);

        myAutoComplete = (AutoCompleteTextView)findViewById(R.id.autoCompleteCP);

        myAutoComplete.addTextChangedListener(this);
        myAutoComplete.setAdapter(new ArrayAdapter<inseeVille>(this, android.R.layout.simple_dropdown_item_1line, MainActivity.villes));

        myAutoComplete.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long rowId) {
                inseeVille selection = (inseeVille) parent.getItemAtPosition(position);
                _inseeCodeSelected = selection.getCode_commune_INSEE();
            }
        });
    }


    public void onClick_LancerRecherche(View v){
        Date timeBeforeRequest = Calendar.getInstance().getTime();
        APIRequest request = new APIRequest(construireParametres());
        request.execute();
        while (request.getServer_response_code() == -1 && TimeUnit.MILLISECONDS.toSeconds(Calendar.getInstance().getTime().getTime() - timeBeforeRequest.getTime()) < 5){
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
                        Intent intent = new Intent(RechercheActivity.this, ResultatActivity.class);
                        startActivityForResult(intent,1);
                    } else{
                        afficherSnackBar("Aucune résultat");
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                //lancer activité affichage avec RecyclerView. Nombre de pages = response.getInt(companies_count)/10
            }else{
                Log.e("Erreur requete" , String.valueOf(request.getServer_response_code()));
                if (request.getServer_response_code() >= 500 && request.getServer_response_code() < 600){
                    afficherSnackBar("Service temporairement indisponible");
                }
            }

    }

    private HashMap<String, String> construireParametres(){
        HashMap<String, String> toReturn = new HashMap<>();
        toReturn.put("commune_id", _inseeCodeSelected);
        toReturn.put("rome_codes","A1202");
        return toReturn;
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
    }
    //From zipcode to INSEE code by choosing a city

    private void afficherSnackBar(String message){
        Snackbar.make(findViewById(android.R.id.content), message, Snackbar.LENGTH_LONG)
                .setActionTextColor(Color.RED)
                .show();
    }
}
