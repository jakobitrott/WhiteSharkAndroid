package com.example.andyl.utilitywisescanner;

import android.content.Context;
import android.content.Intent;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.ResultPoint;
import com.google.zxing.client.android.BeepManager;
import com.journeyapps.barcodescanner.BarcodeCallback;
import com.journeyapps.barcodescanner.BarcodeResult;
import com.journeyapps.barcodescanner.DecoratedBarcodeView;
import java.util.List;

public class DetectQRActivity extends AppCompatActivity {

    private DecoratedBarcodeView barcodeView;
    private BeepManager beepManager;
    private TextView txtView;

    private BarcodeCallback callback = new BarcodeCallback() {
        @Override
        public void barcodeResult(BarcodeResult result) {

            txtView = (TextView) findViewById(R.id.txtView);
            txtView.setText(result.getText());
            txtView.setVisibility(View.VISIBLE);
            txtView.postDelayed(new Runnable() {
                public void run() {
                    txtView.setVisibility(View.INVISIBLE);
                }
            }, 3000);
            Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
            // Vibrate for 500 milliseconds
            v.vibrate(500);

            Intent i = new Intent(DetectQRActivity.this, RegistrationActivity.class);
            i.putExtra("DEVICE_ID", txtView.getText());
            startActivity(i);
            finish();

            // Beeps when the code is scanned - Can be deleted if not necessary
//            beepManager.playBeepSoundAndVibrate();

//            //This part is responsible for displaying the little preview of the scanned barcode - can be skipped if not needed
//            ImageView imageView = (ImageView) findViewById(R.id.barcodePreview);
//            imageView.setImageBitmap(result.getBitmapWithResultPoints(Color.YELLOW));
        }

        @Override
        public void possibleResultPoints(List<ResultPoint> resultPoints) {
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detectqr);

        Toast.makeText(this,
                "Please scan a QR Code to register a new device",
                Toast.LENGTH_SHORT)
                .show();

        barcodeView = (DecoratedBarcodeView) findViewById(R.id.barcode_scanner);
        barcodeView.decodeContinuous(callback);
    }

    @Override
    protected void onResume() {
        super.onResume();
        barcodeView.resume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        barcodeView.pause();
    }

    public void pause(View view) {
        barcodeView.pause();
    }

    public void resume(View view) {
        barcodeView.resume();
    }

    public void triggerScan(View view) {
        barcodeView.decodeSingle(callback);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return barcodeView.onKeyDown(keyCode, event) || super.onKeyDown(keyCode, event);
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}
