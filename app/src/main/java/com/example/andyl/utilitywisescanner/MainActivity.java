package com.example.andyl.utilitywisescanner;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    public static MainActivity m;
    SharedPref sharedPref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        sharedPref = new SharedPref(this);

        m = this;
        if (sharedPref.loadDarkModeState() == true){
            setTheme(R.style.DarkTheme);
        }
        else setTheme(R.style.AppTheme);

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
