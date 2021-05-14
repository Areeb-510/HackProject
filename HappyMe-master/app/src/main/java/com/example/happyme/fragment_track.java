package com.example.happyme;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.leo.simplearcloader.SimpleArcLoader;

import org.eazegraph.lib.charts.PieChart;
import org.eazegraph.lib.models.PieModel;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.StringReader;

import info.hoang8f.widget.FButton;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link fragment_track#newInstance} factory method to
 * create an instance of this fragment.
 */
public class fragment_track extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public fragment_track() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment fragment_track.
     */
    // TODO: Rename and change types and number of parameters
    public static fragment_track newInstance(String param1, String param2) {
        fragment_track fragment = new fragment_track();
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
    TextView tvCases,tvRecovered,tvCritical,tvActive,tvTodayCases,tvTodayDeaths,tvTodayRec,tvTotalDeath,tvAffectedCountries;
    SimpleArcLoader simpleArcLoader;
    PieChart pieChart;
    ScrollView scrollView;
    FButton trackaffect;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_track, container, false);
        tvCases = v.findViewById(R.id.tvCases);
        tvRecovered = v.findViewById(R.id.tvRecovered);
        tvCritical = v.findViewById(R.id.tvCritical);
        tvActive = v.findViewById(R.id.tvActive);
        tvTodayRec = v.findViewById(R.id.tvTodayRec);
        tvTodayCases = v.findViewById(R.id.tvTodayCases);
        tvTodayDeaths = v.findViewById(R.id.tvTodayDeaths);
        tvTotalDeath = v.findViewById(R.id.tvTotalDeaths);
        tvAffectedCountries = v.findViewById(R.id.tvAffectedCountries);
        trackaffect = v.findViewById(R.id.trackingco);
        

        simpleArcLoader = v.findViewById(R.id.loader);
        scrollView = v.findViewById(R.id.scroll);
        pieChart = v.findViewById(R.id.piechart);

        trackaffect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),AffectedCountries.class);
                startActivity(intent);
            }
        });


        fetchData();
        return v;

    }

    private void fetchData() {

        String url = "https://corona.lmao.ninja/v2/all/";
        simpleArcLoader.start();
        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONObject jsonObject = new JSONObject(response.toString());
                    tvCases.setText(jsonObject.getString("cases"));
                    tvRecovered.setText(jsonObject.getString("recovered"));
                    tvCritical.setText(jsonObject.getString("critical"));
                    tvActive.setText(jsonObject.getString("active"));
                    tvTotalDeath.setText(jsonObject.getString("deaths"));
                    tvTodayCases.setText(jsonObject.getString("todayCases"));
                    tvTodayDeaths.setText(jsonObject.getString("todayDeaths"));
                    tvTodayRec.setText(jsonObject.getString("todayRecovered"));
                    tvAffectedCountries.setText(jsonObject.getString("affectedCountries"));

                    pieChart.addPieSlice(new PieModel("Cases",Integer.parseInt(tvCases.getText().toString()), Color.parseColor("#FFFF00")));
                    pieChart.addPieSlice(new PieModel("Recovered",Integer.parseInt(tvRecovered.getText().toString()), Color.parseColor("#00FF7F")));
                    pieChart.addPieSlice(new PieModel("Deaths",Integer.parseInt(tvTotalDeath.getText().toString()), Color.parseColor("#FF0000")));
                    pieChart.addPieSlice(new PieModel("Active",Integer.parseInt(tvActive.getText().toString()), Color.parseColor("#FF8C00")));

                    pieChart.startAnimation();
                    simpleArcLoader.stop();
                    simpleArcLoader.setVisibility(View.GONE);
                    scrollView.setVisibility(View.VISIBLE);





                } catch (JSONException e) {
                    e.printStackTrace();
                    simpleArcLoader.stop();
                    simpleArcLoader.setVisibility(View.GONE);
                    scrollView.setVisibility(View.VISIBLE);
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