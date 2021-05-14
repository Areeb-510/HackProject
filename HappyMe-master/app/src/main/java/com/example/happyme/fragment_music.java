package com.example.happyme;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import info.hoang8f.widget.FButton;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link fragment_music#newInstance} factory method to
 * create an instance of this fragment.
 */
public class fragment_music extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public fragment_music() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment fragment_music.
     */
    // TODO: Rename and change types and number of parameters
    public static fragment_music newInstance(String param1, String param2) {
        fragment_music fragment = new fragment_music();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    TextView movie,year,certification,genre,runtime,imdb,overview,metascore,director;
    FButton fbtn;
    CardView meme;
    CardView askmovies;
    CardView fmovies;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v=  inflater.inflate(R.layout.fragment_music, container, false);

        movie = v.findViewById(R.id.movie);
        year = v.findViewById(R.id.year);
        certification = v.findViewById(R.id.certify);
        genre = v.findViewById(R.id.genre);
        runtime = v.findViewById(R.id.runtime);
        imdb = v.findViewById(R.id.imdb);
        overview = v.findViewById(R.id.overview);
        metascore = v.findViewById(R.id.metascore);
        director = v.findViewById(R.id.director);
        fbtn = v.findViewById(R.id.fbtn);
        meme = v.findViewById(R.id.meme);
        askmovies = v.findViewById(R.id.ask4movies);
        fmovies = v.findViewById(R.id.fmovies);

        askmovies.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://ask4movie.cc/";
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }
        });

        fmovies.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://fmoviesf.co/";
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }
        });

        fbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getmovie();
            }
        });

        meme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),MemeActivity.class);
                startActivity(intent);
            }
        });




        return v;

    }

    private void getmovie() {
        String url = "https://moviehut.tech/api/random/";
        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONObject jsonObject = new JSONObject(response.toString());
                    movie.setText(jsonObject.getString("name"));
                    year.setText(jsonObject.getString("releaseYear"));
                    certification.setText(jsonObject.getString("certificate"));
                    genre.setText(jsonObject.getString("genre"));
                    runtime.setText(jsonObject.getString("runtime"));
                    imdb.setText(jsonObject.getString("imdbRating"));
                    overview.setText(jsonObject.getString("overview"));
                    metascore.setText(jsonObject.getString("metaScore"));
                    director.setText(jsonObject.getString("director"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getActivity(), error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(request);
    }
}