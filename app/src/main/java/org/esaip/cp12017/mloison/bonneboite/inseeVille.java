package org.esaip.cp12017.mloison.bonneboite;

/**
 * Created by Darkadok on 24/01/2018.
 */

public class inseeVille {

    private String Code_commune_INSEE;
    private String Nom_Commune;
    private String CodePostal;
    private long GPS_Latitude;
    private long GPS_Longitude;

    public inseeVille(String code_commune_INSEE, String nom_Commune, String codePostal, String GPS) {
        Code_commune_INSEE = code_commune_INSEE;
        Nom_Commune = nom_Commune;
        CodePostal = codePostal;

        //handle GPS
        this.GPS_Latitude = GPS_Latitude;
        this.GPS_Longitude = GPS_Longitude;
    }

    public String getCode_commune_INSEE() {
        return Code_commune_INSEE;
    }

    public String getNom_Commune() {
        return Nom_Commune;
    }

    public long getGPS_Latitude() {
        return GPS_Latitude;
    }

    public long getGPS_Longitude() {
        return GPS_Longitude;
    }

    public String getCodePostal() {
        return CodePostal;
    }
}
