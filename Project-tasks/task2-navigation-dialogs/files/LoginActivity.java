package com.example.epicverse;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class LoginActivity extends AppCompatActivity {
    EditText etUsername,etPassword;
    CheckBox cbShowHidePassword;
    Button btnLogin;
    TextView tvSignUp;

    SharedPreferences preferences;
    SharedPreferences.Editor editor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);




        setTitle("Login Activity");

        preferences = PreferenceManager.getDefaultSharedPreferences(LoginActivity.this);
        editor = preferences.edit();
        if(preferences.getBoolean("isLogin",false))
        {
            Intent i = new Intent(LoginActivity.this,HomeActivity.class);
            startActivity(i);
        }

        etUsername = findViewById(R.id.etLoginUsername);
        etPassword = findViewById(R.id.etLoginPsw);
        cbShowHidePassword = findViewById(R.id.cbLoginShowHidePassword);
        btnLogin = findViewById(R.id.btnLoginLogin);
        tvSignUp = findViewById(R.id.tvLoginSignup);

        cbShowHidePassword.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if(isChecked)
                {
                    etPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }
                else{
                    etPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String Username = etUsername.getText().toString();
                String Psw = etPassword.getText().toString();

                if(Username.isEmpty())
                {
                    etUsername.setError("Please Enter Your Username");
                } else if (Psw.isEmpty()) {
                    etPassword.setError("Please Enter Your Password");
                } else if (Username.length() < 8) {
                    etUsername.setError("Please Enter 8 Character Username");
                } else if (Psw.length() < 8) {
                    etPassword.setError("Please Enter 8 Character Password");
                } else if (!Username.matches(".*[a-z].*")) {
                    etUsername.setError("Please include atleast One Lowercase letter");
                } else if (!Username.matches(".*[A-Z].*")) {
                    etUsername.setError("Please include atleast One Uppercase letter");
                } else if (!Username.matches(".*[0-9].*")) {
                    etUsername.setError("Please include atleast One Digit from 0-9");
                } else if (!Username.matches(".*[@,#,$,%,&,*,!].*")) {
                    etUsername.setError("Please include atleast One Special Character");
                }else{
                    Toast.makeText(LoginActivity.this,"Successfully Login",Toast.LENGTH_SHORT).show();

                    Intent i = new Intent(LoginActivity.this,HomeActivity.class);
                    startActivity(i);

                }
            }

        });

        tvSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LoginActivity.this,RegistrationActivity.class);
                startActivity(i);
            }
        });



    }
}