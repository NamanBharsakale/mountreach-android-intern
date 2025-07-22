package com.example.epicverse;

import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ContactUsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_contact_us);
        setTitle("Contact Us");
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Toast.makeText(ContactUsActivity.this, "Press Again to Exit", Toast.LENGTH_SHORT).show();
    }

}