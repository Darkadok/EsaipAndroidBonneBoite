package org.esaip.cp12017.mloison.bonneboite;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import java.util.HashMap;

/**
 * Created by Darkadok on 23/01/2018.
 */

public class RechercheActivity extends AppCompatActivity {
    private String token;

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
    }

    private HashMap<String, String> construireParametres(){
        HashMap<String, String> toReturn = new HashMap<>();
        toReturn.put("commune_id", "00000");
        return toReturn;
    }
    //From zipcode to INSEE code by choosing a city
}
