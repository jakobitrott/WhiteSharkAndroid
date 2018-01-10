package com.example.andyl.utilitywisescanner;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatDelegate;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import org.w3c.dom.Text;

public class SettingsActivity extends AppCompatActivity {

    private Switch myswitch;
    private TextView manage;
    SharedPref sharedPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        sharedPref = new SharedPref(this);

        if (sharedPref.loadDarkModeState() == true){
            setTheme(R.style.DarkTheme);
        }
        else setTheme(R.style.AppTheme);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        myswitch = (Switch)findViewById(R.id.myswitch);
        if(sharedPref.loadDarkModeState() == true){
            myswitch.setChecked(true);
        }
        myswitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    sharedPref.setDarkModeState(true);
                    restartApp();
                }
                else{
                    sharedPref.setDarkModeState(false);
                    restartApp();
                }
            }
        });

        //manage = (TextView)findViewById(R.id.manage);
        /*manage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Open the activity here
            }
        });
*/
    }
    public void manage(View v)
    {
        Intent i = new Intent(this, ManageActivity.class);
        startActivity(i);
    }

    public void restartApp()
    {
        MainActivity.m.finish();
        Intent io = new Intent(this,MainActivity.class);
        Intent i = new Intent(getApplicationContext(),SettingsActivity.class);
        startActivity(io);
        startActivity(i);
        finish();
    }
}
