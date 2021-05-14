package com.example.happyme;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterViewFlipper;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

import de.hdodenhof.circleimageview.CircleImageView;
import info.hoang8f.widget.FButton;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link fragment_home#newInstance} factory method to
 * create an instance of this fragment.
 */
public class fragment_home extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;



    public fragment_home() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment fragment_home.
     */
    // TODO: Rename and change types and number of parameters
    public static fragment_home newInstance(String param1, String param2) {
        fragment_home fragment = new fragment_home();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    AdapterViewFlipper adapterViewFlipper;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);

        }
    }
    ToggleButton notify;
    TextView username;
    CircleImageView photo;
    FirebaseDatabase database;
    FirebaseAuth auth;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_home,container,false);
        CardView games = v.findViewById(R.id.gamescard);
        CardView music = v.findViewById(R.id.musiccard);
        CardView track = v.findViewById(R.id.tracker);
        CardView test = v.findViewById(R.id.test);
        username = v.findViewById(R.id.user);
        photo = v.findViewById(R.id.photouser);
        CardView profile = v.findViewById(R.id.profile);
        database = FirebaseDatabase.getInstance();
        auth = FirebaseAuth.getInstance();

        FirebaseUser u = auth.getCurrentUser();

        if(u.getDisplayName()!=null) {
            String text = "Hello," + u.getDisplayName();
            username.setText(text);
        }else
        {
            String text = "Hello,User";
            username.setText(text);
        }

        if(u.getPhotoUrl()!=null) {
            Glide.with(this).load(u.getPhotoUrl()).into(photo);
        }


        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),ProfileActivity.class);
                startActivity(intent);
            }

        });

        games.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragment_games fragment_game = new fragment_games();
                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.container,fragment_game,fragment_game.getTag()).commit();

            }

        });
        music.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragment_music fragment_music = new fragment_music();
                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.container,fragment_music,fragment_music.getTag()).commit();

            }

        });
        track.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragment_track fragment_track = new fragment_track();
                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.container,fragment_track,fragment_track.getTag()).commit();

            }

        });
        test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragment_test fragment_test = new fragment_test();
                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.container,fragment_test,fragment_test.getTag()).commit();

            }

        });





        return v;

    }




}