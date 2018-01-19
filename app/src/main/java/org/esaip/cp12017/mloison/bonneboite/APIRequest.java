package org.esaip.cp12017.mloison.bonneboite;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Darkadok on 19/01/2018.
 */

public abstract class APIRequest extends AsyncTask<String , Void ,String> {
    protected final String CLIENT_ID = "PAR_esaipandroidbonneboite_b6eb85f8edc50ff4e773779bf2dddb83907f0d28723e9aab60abfb6964f73502";
    protected final String SCOPE = "application_PAR_esaipandroidbonneboite_b6eb85f8edc50ff4e773779bf2dddb83907f0d28723e9aab60abfb6964f73502%20api_labonneboitev1";
    protected final String CLIENT_SECRET = "f2a0a060b911bf5e76b3d4ec5f89ad7f5cb83e9278aee1d2600b294dca89dc07";
    protected final String GRANT_TYPE = "client_credentials";
    protected String ADDR;
    protected String METHODE;

    protected JSONObject server_response;
    protected int server_response_code;


    public JSONObject getServer_response() {
        return server_response;
    }

    public int getServer_response_code() {
        return server_response_code;
    }

    protected abstract String buildRequest();

    protected String readStream(InputStream in) {
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


    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        Log.e("Response", "" + server_response);

    }

    @Override
    protected String doInBackground(String[] strings) {
        URL url;
        HttpURLConnection urlConnection = null;

        try {
            url = new URL(ADDR);
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod(METHODE);
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
}
