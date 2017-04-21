package com.example.yeong.market2u;

import android.content.Context;
import android.os.AsyncTask;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/*
*   This class handle all the ingoing and outgoing HTTP Request
*   Created by Sharifuddin Omar on 22/04/17
*
*
*/

public class Connection extends AsyncTask<String, Void, JSONObject> {

    // global function
    public interface Listener {
        void onResult(JSONObject result);
    }

    private Listener mListener;
    private Context context;
    private final String localhost = "http://192.168.2.103/market2u/";

    public Connection(Context context) {
        this.context = context;
    }
    public void setListener(Listener listener) {
        mListener = listener;
    }

    @Override
    protected JSONObject doInBackground(String... params) {

        JSONObject json = null;
        HttpURLConnection connection = null;
        URL url = null;

        try {
            if(params[0] == "POST"){
                url = new URL(localhost+"test.php"); // params separated
                connection = post_request(url);
            }else{
                url = new URL(localhost+"test.php?"+params[1]);// params on url
                connection = get_request(url);
            }

            if(params[0] == "POST"){
                String post_params = params[1];
                DataOutputStream dStream = new DataOutputStream(connection.getOutputStream());
                dStream.writeBytes(post_params);
                dStream.flush();
                dStream.close();
            }

            // Server Respond OK
            if(connection.getResponseCode() == 200){
                // Get Output
                BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));

                // Convert JSON output to String
                String line = "";
                StringBuilder responseOutput = new StringBuilder();
                while((line = br.readLine()) != null ) {
                    responseOutput.append(line);
                }

                // Convert String to JSONObject
                json = new JSONObject(responseOutput.toString());

                // close BufferedReader
                br.close();
            }


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return json;
    }

    protected void onPostExecute(JSONObject result) {
        // something...
        if (mListener != null) {
            mListener.onResult(result);
        }
    }

    //
    public HttpURLConnection get_request(URL url){

        HttpURLConnection connection = null;

        try {
            connection = (HttpURLConnection)url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("USER-AGENT", "Mozilla/5.0");
            connection.setRequestProperty("ACCEPT-LANGUAGE", "en-US,en;0.5");

        } catch (IOException e) {
            e.printStackTrace();
        }

        return connection;

    }

    //
    public HttpURLConnection post_request(URL url){

        HttpURLConnection connection = null;

        try {
            connection = (HttpURLConnection)url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("USER-AGENT", "Mozilla/5.0");
            connection.setRequestProperty("ACCEPT-LANGUAGE", "en-US,en;0.5");
            connection.setDoOutput(true);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return connection;

    }
}
