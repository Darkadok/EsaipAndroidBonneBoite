package org.esaip.cp12017.mloison.bonneboite.metier;

import android.os.AsyncTask;
import android.util.Log;

import org.esaip.cp12017.mloison.bonneboite.R;
import org.esaip.cp12017.mloison.bonneboite.activity.MainActivity;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;


/**
 * Created by Darkadok on 19/01/2018.
 */

public class APIRequest extends AsyncTask<String , Void ,String> {
    private final String CLIENT_ID = "PAR_esaipandroidbonneboite_b6eb85f8edc50ff4e773779bf2dddb83907f0d28723e9aab60abfb6964f73502";
    private final String SCOPE = "application_PAR_esaipandroidbonneboite_b6eb85f8edc50ff4e773779bf2dddb83907f0d28723e9aab60abfb6964f73502%20api_labonneboitev1";
    private final String CLIENT_SECRET = "f2a0a060b911bf5e76b3d4ec5f89ad7f5cb83e9278aee1d2600b294dca89dc07";
    private final String GRANT_TYPE = "client_credentials";
    private String _addr;
    private String _methode;
    private HashMap<String, String> _parametres;
    private JSONObject server_response;
    private int server_response_code = -1;


    public APIRequest(){
        _addr = "https://entreprise.pole-emploi.fr/connexion/oauth2/access_token?realm=%2Fpartenaire";
        _methode = "POST";
        _parametres = new HashMap<String, String>();
        _parametres.put("grant_type", GRANT_TYPE);
        _parametres.put("client_id", CLIENT_ID);
        _parametres.put("client_secret", CLIENT_SECRET);
        _parametres.put("scope", SCOPE);
    }

    public APIRequest(HashMap<String, String> parametres){
        _addr = "https://api.emploi-store.fr/partenaire/labonneboite/v1/company?";
        _methode = "GET";
        _parametres = parametres;
    }



    protected String buildRequest() {
        StringBuilder result = new StringBuilder();
        int compteur = 1;
        for (Map.Entry<String, String> entry : _parametres.entrySet()) {
            String cle = entry.getKey();
            String valeur = entry.getValue();
            try {
                result.append(URLEncoder.encode(cle, "UTF-8"));
                result.append("=");
                result.append(valeur);
                if (compteur < _parametres.entrySet().size()){
                    result.append("&");
                }
                compteur++;
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        Log.i("Requete: ", result.toString());
        return result.toString();
    }

        @Override
    protected String doInBackground(String[] strings) {
        URL url;
        HttpURLConnection urlConnection = null;
        try {
            url = new URL(_addr);
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod(_methode);
            urlConnection.setReadTimeout(10000);
            urlConnection.setConnectTimeout(15000);
            urlConnection.setDoInput(true);
            urlConnection.setDoOutput(true);
            if(!_methode.equals("POST")){//pas auth
                SingletonToken token = SingletonToken.getInstance();
                token.getValue();
                urlConnection.setRequestProperty( "Authorization", "Bearer " + token.getValue() );
            }
            OutputStream os = urlConnection.getOutputStream();
            BufferedWriter writer = new BufferedWriter(
                    new OutputStreamWriter(os, "UTF-8"));

            writer.write(buildRequest());
            writer.flush();
            writer.close();
            os.close();

            urlConnection.connect();
            server_response_code = urlConnection.getResponseCode();
            server_response = new JSONObject(readStream(urlConnection.getInputStream()));

        } catch (MalformedURLException e) {
            e.printStackTrace();
            return e.getMessage();
        } catch (IOException e) {
            e.printStackTrace();
            return e.getMessage();
        } catch (org.json.JSONException e) {
            e.printStackTrace();
            return e.getMessage();
        }
        return "OK";
    }

    private String readStream(InputStream in) {
            BufferedReader reader = null;
            StringBuffer response = new StringBuffer();
            try {
                reader = new BufferedReader(new InputStreamReader(in));
                String line = "";
                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            return response.toString();
        }

    public JSONObject getServer_response() {
        return server_response;
    }

    public int getServer_response_code() {
        return server_response_code;
    }

}
