package org.esaip.cp12017.mloison.bonneboite;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
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


/**
 * Created by Darkadok on 18/01/2018.
 */

public class APIRequestConnection extends APIRequest {

    /*POST https://entreprise.pole-emploi.fr/connexion/oauth2/access_token


    POST /connexion/oauth2/access_token?realm=%2Fpartenaire
    Content-Type: application/x-www-form-urlencoded

            grant_type=client_credentials
            &client_id=[identifiant client]
            &client_secret=[clé secrète]
            &scope=application_[identifiant client]%20api_labonneboitev1

    {
  "scope": "api_labonneboitev1 application_[Identifiant client]",
  "expires_in": 1500,
  "token_type": "Bearer",
  "access_token": "[Valeur du jeton généré]"


GET /partenaire/labonneboite/v1/company/?distance=30&latitude=49.119146&longitude=6.17602&rome_codes=M1607

Authorization: Bearer [Access token]

}*/

    protected String ADDR = "https://entreprise.pole-emploi.fr/connexion/oauth2/access_token?realm=%2Fpartenaire";
    protected String METHODE = "POST";

    protected String buildRequest() {
        StringBuilder result = new StringBuilder();
        try {
            result.append(URLEncoder.encode("grant_type", "UTF-8"));
            result.append("=");
            result.append(GRANT_TYPE);
            result.append("&");
            result.append(URLEncoder.encode("client_id", "UTF-8"));
            result.append("=");
            result.append(CLIENT_ID);
            result.append("&");
            result.append(URLEncoder.encode("client_secret", "UTF-8"));
            result.append("=");
            result.append(CLIENT_SECRET);
            result.append("&");
            result.append(URLEncoder.encode("scope", "UTF-8"));
            result.append("=");
            result.append(SCOPE);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        Log.v("Requete: ", result.toString());
        return result.toString();

    }

}