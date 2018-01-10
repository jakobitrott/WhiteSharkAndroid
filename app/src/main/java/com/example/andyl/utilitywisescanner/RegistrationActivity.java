package com.example.andyl.utilitywisescanner;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Iterator;

public class RegistrationActivity extends AppCompatActivity {
    private FirebaseDatabase db = FirebaseDatabase.getInstance();
    private DatabaseReference dbRef = db.getReferenceFromUrl("https://utilitywisescanner.firebaseio.com/");
    private TextView qrID, smartplugID;
    private EditText dID;

    SharedPref sharedPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        sharedPref = new SharedPref(this);

        if (sharedPref.loadDarkModeState() == true){
            setTheme(R.style.DarkTheme);
        }
        else setTheme(R.style.AppTheme);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        qrID = (TextView) findViewById(R.id.qrID);
        smartplugID = (TextView) findViewById(R.id.smartplugID);
        dID = (EditText) findViewById(R.id.dID);
        qrID.setText(getIntent().getStringExtra("DEVICE_ID"));

        dbRef.child("Items").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Iterator<DataSnapshot> item = dataSnapshot.getChildren().iterator();
                String qID;
                Boolean isFound = false;
                int c = 1;
                while (!isFound) {
                    if (item.hasNext()) {
                        DataSnapshot i = item.next();
                        qID = i.child("ID").getValue().toString();
                        if (qrID.getText().equals(qID)) {
                            isFound = true;
                            dID.setText(i.child("Name").getValue().toString());
                            smartplugID.setText(i.child("SmartPlugID").getValue().toString());
                            dbRef = db.getReferenceFromUrl("https://utilitywisescanner.firebaseio.com/Items/" + c + "/Name");
                        }
                        c++;
                    }
                    else {
                        isFound = true;
                        Toast.makeText(RegistrationActivity.this,
                                "Device could not be associated.",
                                Toast.LENGTH_LONG).show();
                        finish();
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                smartplugID.setText("No entry found.");
            }
        });
    }

    @Override
    public void onBackPressed() {
        finish();
    }

    public void register(View v) {
        dbRef.setValue(dID.getText().toString());
        Toast.makeText(RegistrationActivity.this,
                "Device successfully registered.",
                Toast.LENGTH_LONG).show();
        finish();
    }
}
