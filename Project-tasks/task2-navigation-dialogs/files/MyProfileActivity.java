package com.example.epicverse;

import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MyProfileActivity extends AppCompatActivity {

    boolean doubletap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_my_profile);
        setTitle("MyProfileActivity");
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Toast.makeText(MyProfileActivity.this, "Back to", Toast.LENGTH_SHORT).show();

    }

}