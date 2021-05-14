package com.example.happyme;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class ProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
    }

    public void happydialog(View view) {
        Toast.makeText(this, "Happy", Toast.LENGTH_SHORT).show();
    }

    public void angrydialog(View view) {
        Toast.makeText(this, "Angry", Toast.LENGTH_SHORT).show();
    }

    public void saddialog(View view) {
        Toast.makeText(this, "Sad", Toast.LENGTH_SHORT).show();
    }
}