package org.esaip.cp12017.mloison.bonneboite.metier;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Rolexel on 26/01/2018.
 */

public class rome {

    private String Libelle;
    private String RomeId;


    public rome(String libelle, String romeId){
        Libelle = libelle;
        RomeId = romeId;
    }
    public String getLibelle() { return Libelle; }

    public void setLibelle(String libelle) { this.Libelle = libelle; }

    public String getromeId() { return RomeId; }

    public void setromeId(String id) { this.RomeId = id; }

    @Override
    public String toString() {
        return RomeId +" - "+ Libelle ;
    }
}
