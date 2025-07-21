package com.example.epicverse;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    Animation fadeInanim;
    ImageView ivMainlogo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        fadeInanim = AnimationUtils.loadAnimation(MainActivity.this,R.anim.fade_in);

        ivMainlogo = findViewById(R.id.ivMainlogo);

        ivMainlogo.setAnimation(fadeInanim);


        Handler h = new Handler();
        h.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(MainActivity.this,LoginActivity.class);
                startActivity(i);
            }
        },3000);

        setTitle("Login Activity");


    }
}