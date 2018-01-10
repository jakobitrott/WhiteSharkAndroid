package com.example.andyl.utilitywisescanner;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class RegistrationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        TextView qrID = (TextView)findViewById(R.id.qrID);
        qrID.setText(getIntent().getStringExtra("DEVICE_ID"));
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}
