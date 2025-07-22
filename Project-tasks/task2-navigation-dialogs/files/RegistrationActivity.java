package com.example.epicverse;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class RegistrationActivity extends AppCompatActivity {

    EditText etName, etMobileNo, etEmailId, etUsername, etPassword;
    Button btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_registration);




        setTitle("Registration Activity");

        etName = findViewById(R.id.etRegisterName);
        etMobileNo = findViewById(R.id.etRegisterMobileNumber);
        etEmailId = findViewById(R.id.etRegisterEmailId);
        etUsername = findViewById(R.id.etRegisterUsername);
        etPassword = findViewById(R.id.etRegisterPassword);
        btnRegister = findViewById(R.id.btnRegisterRegister);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (etName.getText().toString().isEmpty()) {
                    etName.setError("Please Enter Your Name");
                } else if (etMobileNo.getText().toString().isEmpty()) {
                    etMobileNo.setError("Please Enter Your Mobile Number");
                } else if (etMobileNo.getText().toString().length() != 10) {
                    etMobileNo.setError("Please 10 digit Mobile Number");
                } else if (etEmailId.getText().toString().isEmpty()) {
                    etEmailId.setError("Please Enter your Email ID");
                } else if (!etEmailId.getText().toString().contains("@") || !etEmailId.getText().toString().contains(".com")) {
                    etEmailId.setError("Please Enter Valid Email Id");
                } else if (etUsername.getText().toString().isEmpty()) {
                    etUsername.setError("Please Enter Your Username");
                } else if (etUsername.getText().toString().length() < 8) {
                    etUsername.setError("Please Enter 8 Character Long Username");
                } else if (!etUsername.getText().toString().matches(".*[A-Z].*")) {
                    etUsername.setError("Please Enter at least One Uppercase letter");
                } else if (!etUsername.getText().toString().matches(".*[a-z].*")) {
                    etUsername.setError("Please Enter at least One Lowercase letter");
                } else if (!etUsername.getText().toString().matches(".*[0-9].*")) {
                    etUsername.setError("Please Enter at least One digit");
                } else if (!etUsername.getText().toString().matches(".*[@,#,$,%,&,!].*")) {
                    etUsername.setError("Please Enter at least one Special Character");
                } else if (etPassword.getText().toString().isEmpty()) {
                    etPassword.setError("Please Enter Your Password");
                } else if (etPassword.getText().toString().length() < 8) {
                    etPassword.setError("Please Enter 8 Character Long Password");
                } else {
                    Toast.makeText(RegistrationActivity.this, "Registration Succesfully Done", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }
}