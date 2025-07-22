package com.example.mysplashscr;

import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;

public class QRActivity extends AppCompatActivity {

    private static final String TAG = "QRActivity";
    private static final int QRWIDTH = 300;
    private static final int QRHEIGHT = 300;

    ImageView ivQrCode;
    Bitmap bitmap;
    SharedPreferences preferences;
    String strUsername;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_qractivity);

        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        strUsername = preferences.getString("username", null);

        ivQrCode = findViewById(R.id.myQr);

        if (strUsername != null) {
            try {
                createQRcode();
            } catch (WriterException e) {
                Log.e(TAG, "Error generating QR code: " + e.getMessage());
                Toast.makeText(this, "Error generating QR code: " + e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        } else {
            Log.e(TAG, "Username is null");
            Toast.makeText(this, "Username is not available", Toast.LENGTH_SHORT).show();
        }
    }

    private void createQRcode() throws WriterException {
        bitmap = textToimageEncode(strUsername);
        ivQrCode.setImageBitmap(bitmap);
    }

    private Bitmap textToimageEncode(String strUsername) throws WriterException {
        BitMatrix bitMatrix;
        try {
            bitMatrix = new MultiFormatWriter().encode(strUsername, BarcodeFormat.QR_CODE, QRWIDTH, QRHEIGHT);
        } catch (WriterException e) {
            Log.e(TAG, "WriterException: " + e.getMessage());
            throw e;
        }

        int[] pixels = new int[bitMatrix.getWidth() * bitMatrix.getHeight()];

        for (int x = 0; x < bitMatrix.getWidth(); x++) {
            int offset = x * bitMatrix.getHeight();
            for (int y = 0; y < bitMatrix.getHeight(); y++) {
                pixels[offset + y] = bitMatrix.get(x, y) ? getResources().getColor(R.color.black) : getResources().getColor(R.color.white);
            }
        }

        bitmap = Bitmap.createBitmap(bitMatrix.getWidth(), bitMatrix.getHeight(), Bitmap.Config.ARGB_4444);
        bitmap.setPixels(pixels, 0, bitMatrix.getWidth(), 0, 0, bitMatrix.getWidth(), bitMatrix.getHeight());

        return bitmap;
    }
}
