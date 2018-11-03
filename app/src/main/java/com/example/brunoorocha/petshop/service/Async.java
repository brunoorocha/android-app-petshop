package com.example.brunoorocha.petshop.service;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.example.brunoorocha.petshop.R;
import com.example.brunoorocha.petshop.model.Foods;
import com.example.brunoorocha.petshop.view.MainActivity;
import com.example.brunoorocha.petshop.util.UrlUtils;
import com.example.brunoorocha.petshop.model.Pets;
import com.google.gson.JsonObject;

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

    private int[] catsImageResources = {
            R.drawable.cat1,
            R.drawable.cat2,
            R.drawable.cat3
    };

    private int[] dogsImageResources = {
            R.drawable.dog1,
            R.drawable.dog2,
            R.drawable.dog3
    };

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
            int catResourceCounter = 0, dogResourceCounter = 0;

            for(int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObj = new JSONObject(jsonArray.getString(i));
                JSONObject foods = jsonObj.getJSONObject("foods");
                Pets pets = new Pets();

                pets.setName(jsonObj.getString("name"));
                pets.setSpecies(jsonObj.getString("species"));
                pets.setPrice(jsonObj.getString("price"));

                JSONArray jsonLikes = foods.getJSONArray("likes");
                JSONArray jsonDislikes = foods.getJSONArray("dislikes");
                String[] likes = new String[jsonLikes.length()];
                String[] dislikes = new String[jsonDislikes.length()];

                for (int j = 0; j < likes.length; j++) {
                    likes[j] = jsonLikes.getString(j);
                }

                for (int j = 0; j < dislikes.length; j++) {
                    dislikes[j] = jsonDislikes.getString(j);
                }


                if (pets.getSpecies().contains("cat")) {
                    if (catResourceCounter > 2) catResourceCounter = 0;
                    pets.setImageResourceId(this.catsImageResources[catResourceCounter]);
                    catResourceCounter++;
                }
                else if (pets.getSpecies().contains("dog")) {
                    if (dogResourceCounter> 2) dogResourceCounter= 0;
                    pets.setImageResourceId(this.dogsImageResources[dogResourceCounter]);
                    dogResourceCounter++;
                }

                pets.setFoods(new Foods(likes, dislikes));
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
