package org.esaip.cp12017.mloison.bonneboite.metier;

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
            adresse = json.getString("address");
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

    public String getSiret() {
        return siret;
    }

    public void setSiret(String siret) {
        this.siret = siret;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getContact_mode() {
        return contact_mode;
    }

    public void setContact_mode(String contact_mode) {
        this.contact_mode = contact_mode;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public String getHeadcount_text() {
        return headcount_text;
    }

    public void setHeadcount_text(String headcount_text) {
        this.headcount_text = headcount_text;
    }

    public String getNaf() {
        return naf;
    }

    public void setNaf(String naf) {
        this.naf = naf;
    }

    public String getNaf_text() {
        return naf_text;
    }

    public void setNaf_text(String naf_text) {
        this.naf_text = naf_text;
    }

    public long getLongitude() {
        return longitude;
    }

    public void setLongitude(long longitude) {
        this.longitude = longitude;
    }

    public long getLatitude() {
        return latitude;
    }

    public void setLatitude(long latitude) {
        this.latitude = latitude;
    }

    public int getStars() {
        return stars;
    }

    public void setStars(int stars) {
        this.stars = stars;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public boolean isAlternance() {
        return alternance;
    }

    public void setAlternance(boolean alternance) {
        this.alternance = alternance;
    }

    @Override
    public String toString() {
        return name + "\n" + adresse + "\n "+stars + "/5";
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
