package com.example.andyl.utilitywisescanner;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void scan(View v) {

    }

    public void add(View v) {
        Intent i = new Intent(this, DetectQRActivity.class);
        startActivity(i);
    }

    public void settings(View v) {
        Intent i = new Intent(this, SettingsActivity.class);
        startActivity(i);
    }

    public void about (View v) {
        Intent i = new Intent(this, WebActivity.class);
        startActivity(i);
    }
}
