package com.example.happyme;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.leo.simplearcloader.SimpleArcLoader;

import org.json.JSONException;
import org.json.JSONObject;

import info.hoang8f.widget.FButton;

public class MemeActivity extends AppCompatActivity {

    SimpleArcLoader arcLoader;
    ImageView imageView;
    FButton btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_meme);
        arcLoader = findViewById(R.id.arc);
        btn= findViewById(R.id.btnmeme);
        fetch();

    }

    public void fetch()
    {
        arcLoader.start();
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "https://meme-api.herokuapp.com/gimme";

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            String u = response.getString("url");
                            imageView = (ImageView) findViewById(R.id.imageView2);
                            Glide.with(MemeActivity.this).load(u).into(imageView);
                            arcLoader.stop();
                            arcLoader.setVisibility(View.GONE);
                            imageView.setVisibility(View.VISIBLE);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO: Handle error

                    }
                });

// Add the request to the RequestQueue.
        queue.add(jsonObjectRequest);

    }

    public void next(View view) {
        fetch();
    }
}