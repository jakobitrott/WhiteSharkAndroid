package com.example.andyl.utilitywisescanner;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //Opens the Scanner on the launch of the app
        Intent intent = new Intent(MainActivity.this, Scanner.class);
        startActivity(intent);

        /*SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        boolean b = sharedPref.getBoolean("dark_mode_preference", false);
        if (b = true) {
            TextView activityMainTextView = (TextView) findViewById(R.id.drawer_layout);
            activityMainTextView.setBackgroundColor(0xffffff);
        }
        else {
            TextView activityMainTextView = (TextView) findViewById(R.id.drawer_layout);
            activityMainTextView.setBackgroundColor(0x000000);
        }*/
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_device) {

        }
        else if (id == R.id.nav_settings) {
            Intent i = new Intent(MainActivity.this, SettingsActivity.class);
            startActivity(i);
        }
        else if (id == R.id.nav_webview) {
            Intent i = new Intent(MainActivity.this, WebActivity.class);
            startActivity(i);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
