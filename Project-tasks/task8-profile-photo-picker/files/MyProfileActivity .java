
package com.example.mysplashscr;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.speech.RecognizerIntent;
import android.speech.tts.TextToSpeech;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.OnBackPressedCallback;
import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.FirebaseMessaging;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Locale;

public class MyProfileActivity extends AppCompatActivity {

    boolean doubletap;

    ImageView profileImage;
    Button btnChangPic,btnSaveChanges,btnLogOut;
    TextView profileUserName;
    EditText y_name,password,phone,email;

    SharedPreferences preferences;//temp data store
    SharedPreferences.Editor editor;//put or edit
    private static final String PROFILE_IMAGE_URI_KEY = "PROFILE_IMAGE_URI";


    Uri imagePath;
    Bitmap bitmap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_my_profile_activity);


        preferences = PreferenceManager.getDefaultSharedPreferences(MyProfileActivity.this);
        editor = preferences.edit();


        ImageView back = findViewById(R.id.ivBack);

        profileImage = findViewById(R.id.profile_image);
        btnChangPic = findViewById(R.id.btnChangePicture);
        btnSaveChanges = findViewById(R.id.btnSaveCanges);
        btnLogOut = findViewById(R.id.btnLogOut);
        profileUserName = findViewById(R.id.profile_name);
        y_name = findViewById(R.id.name);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        phone = findViewById(R.id.phone);

        String strName = preferences.getString("NAME","");
        String strMobileNo = preferences.getString("PHONE","");
        String strEmail = preferences.getString("EMAIL","");
        String strPassword = preferences.getString("PASSWORD","");
        String strUsername = preferences.getString("USERNAME","");
        String profileImageUri = preferences.getString(PROFILE_IMAGE_URI_KEY, "");

        profileUserName.setText(strUsername);
        y_name.setText(strName);
        email.setText(strEmail);
        password.setText(strPassword);
        phone.setText(strMobileNo);

        File imageFile = new File(getFilesDir(),"profile.png");
        if(imageFile.exists())
        {
            Bitmap savedBitmap = BitmapFactory.decodeFile(imageFile.getAbsolutePath());
            profileImage.setImageBitmap(savedBitmap);
        }

        btnLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logOut();
            }
        });

        btnChangPic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openImageChooser();

            }

        });



        btnSaveChanges.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveChanges();
            }


        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MyProfileActivity.this,HomeActivity.class);
                startActivity(i);
            }
        });



    }

    private void saveChanges() {
        String newName = y_name.getText().toString();
        String newEmail = email.getText().toString();
        String newPassword = password.getText().toString();
        String newPhone = phone.getText().toString();

        editor.putString("NAME", newName);
        editor.putString("EMAIL", newEmail);
        editor.putString("PASSWORD", newPassword);
        editor.putString("PHONE", newPhone);
        editor.apply();

        Toast.makeText(MyProfileActivity.this, "Changes Saved", Toast.LENGTH_SHORT).show();
    }

    private void openImageChooser() {
        Intent i = new Intent();
        i.setType("image/*");
        i.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(i, "SELECT PROFILE PHOTO"), 999);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 999 && resultCode == RESULT_OK && data != null) {
            imagePath = data.getData();
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(),imagePath);
                profileImage.setImageBitmap(bitmap);

               //Save the image to the app
                File imageFile = new File(getFilesDir(),"profile.png");
                FileOutputStream outputStream = openFileOutput(imageFile.getName(), Context.MODE_PRIVATE);
                bitmap.compress(Bitmap.CompressFormat.JPEG,100,outputStream);
                outputStream.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

    }

    private void logOut() {
        AlertDialog.Builder ed = new AlertDialog.Builder(MyProfileActivity.this);
        ed.setTitle("EPIC VERSE");
        ed.setMessage("Are You Sure You Want To Log Out?");
        ed.setPositiveButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        ed.setNegativeButton("LogOut", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                editor.clear();
                editor.apply();
                Toast.makeText(MyProfileActivity.this, "Logged out", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(MyProfileActivity.this, LoginActivity.class);
                startActivity(i);
                finish();
            }
        });
        ed.show();
    }
}

