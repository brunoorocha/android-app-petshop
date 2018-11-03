package com.example.brunoorocha.petshop.service;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import com.example.brunoorocha.petshop.view.MainActivity;
import com.example.brunoorocha.petshop.util.UrlUtils;
import com.example.brunoorocha.petshop.model.Pets;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class Async extends AsyncTask<String, Void, List<Pets>> {

    OnLoadEventListener mListener = null;
    Context mContext = null;
    ProgressDialog progressDialog;

    public Async(OnLoadEventListener mListener, Context mContext) {
        this.mListener = mListener;
        this.mContext = mContext;
    }

    @Override
    public void onPreExecute() {
        if (this.mContext != null) {
            progressDialog = new ProgressDialog(mContext);
            progressDialog.setTitle("Pets inventory");
            progressDialog.setMessage("Searching for pets list...");
            progressDialog.show();
        }
    }

    @Override
    protected List<Pets> doInBackground(String... params) {

        String urlString = params[0];

        try {
            URL url = new URL(urlString);
            HttpURLConnection connection =
                    (HttpURLConnection) url.openConnection();

            if(connection != null) {

                InputStream is = (InputStream) connection.getContent();
                String json = UrlUtils.toString(is);

                List<Pets> allPets = getAllPets(json);

                return allPets;

            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private List<Pets> getAllPets(String json) {
        List<Pets> myPets = new ArrayList<>();

        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONArray jsonArray = jsonObject.getJSONArray("pets");


            for(int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObj = new JSONObject(jsonArray.getString(i));

                Pets pets = new Pets();
                pets.setName(jsonObj.getString("name"));
                pets.setSpecies(jsonObj.getString("species"));
                pets.setPrice(jsonObj.getString("price"));

                /*
                    TO DO - Foods
                 */

                myPets.add(pets);
            }


        } catch (JSONException e) {
            e.printStackTrace();
        }
        return myPets;
    }

    @Override
    public void onPostExecute(List<Pets> result) {

        progressDialog.dismiss();

        if(result.size() > 0) {
            this.mListener.onLoadEvent(result);
        } else {
            AlertDialog.Builder alert =
                    new AlertDialog.Builder(this.mContext);
            alert.setTitle("Pets inventory");
            alert.setMessage("Sorry! Info ont available");
            alert.setPositiveButton("OK", null);
            alert.create().show();
        }

    }

    public interface OnLoadEventListener {
        void onLoadEvent(List<Pets> pets);
    }
}
