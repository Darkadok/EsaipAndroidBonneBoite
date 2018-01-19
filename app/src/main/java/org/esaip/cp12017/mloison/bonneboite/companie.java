package org.esaip.cp12017.mloison.bonneboite;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Darkadok on 19/01/2018.
 */

public class companie {
    private String siret;
    private String name;
    private String adresse;
    private String city;
    private String contact_mode;
    private int distance;
    private String headcount_text;
    private String naf;
    private String naf_text;
    private long longitude;
    private long latitude;
    private int stars;
    private String url;
    private boolean alternance;


    public companie(JSONObject json){
        try {
            siret = json.getString("siret");
            name = json.getString("name");
            adresse = json.getString("adresse");
            city = json.getString("city");
            contact_mode = json.getString("contact_mode");
            distance = json.getInt("distance");
            headcount_text = json.getString("headcount_text");
            naf = json.getString("naf");
            naf_text = json.getString("naf_text");
            longitude = json.getLong("lon");
            latitude = json.getLong("lat");
            stars = json.getInt("stars");
            url = json.getString("url");
            alternance = json.getBoolean("alternance");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
