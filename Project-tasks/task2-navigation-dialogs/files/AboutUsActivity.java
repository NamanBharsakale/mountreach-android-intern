package com.example.epicverse;

import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class AboutUsActivity extends AppCompatActivity {
    boolean doubletap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setTitle("About Us");
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Toast.makeText(AboutUsActivity.this, "Press Again to Exit", Toast.LENGTH_SHORT).show();
    }

}