package org.esaip.cp12017.mloison.bonneboite;

import android.os.AsyncTask;
import android.util.Log;

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
import java.util.AbstractMap;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

/**
 * Created by Darkadok on 18/01/2018.
 */

public class APIRequestAsyncConnectionActivity extends AsyncTask<String , Void ,String> {

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


    private final String CLIENT_ID = "PAR_esaipandroidbonneboite_b6eb85f8edc50ff4e773779bf2dddb83907f0d28723e9aab60abfb6964f73502";
    private final String SCOPE = "application_PAR_esaipandroidbonneboite_b6eb85f8edc50ff4e773779bf2dddb83907f0d28723e9aab60abfb6964f73502%20api_labonneboitev1";
    private final String CLIENT_SECRET = "f2a0a060b911bf5e76b3d4ec5f89ad7f5cb83e9278aee1d2600b294dca89dc07";
    private final String GRANT_TYPE = "client_credentials";
    private final String ADDR = "https://entreprise.pole-emploi.fr/connexion/oauth2/access_token?realm=%2Fpartenaire";

    private String server_response;
    private int server_response_code;


    @Override
    protected String doInBackground(String[] strings) {
        URL url;
        HttpURLConnection urlConnection = null;

        try {
            url = new URL(ADDR);
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("POST");
            urlConnection.setReadTimeout(10000);
            urlConnection.setConnectTimeout(15000);
            urlConnection.setDoInput(true);
            urlConnection.setDoOutput(true);

            OutputStream os = urlConnection.getOutputStream();
            BufferedWriter writer = new BufferedWriter(
                    new OutputStreamWriter(os, "UTF-8"));

            writer.write(buildRequest());
            writer.flush();
            writer.close();
            os.close();

            urlConnection.connect();
            server_response_code = urlConnection.getResponseCode();
            server_response = readStream(urlConnection.getInputStream());

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);

        Log.e("Response", "" + server_response);


    }

// Converting InputStream to String

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

    private String buildRequest() {
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

    public String getServer_response() {
        return server_response;
    }

    public int getServer_response_code() {
        return server_response_code;
    }
}