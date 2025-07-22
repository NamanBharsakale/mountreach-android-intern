package com.example.epicverse;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class HomeActivity extends AppCompatActivity {

    boolean doubletap;

    SharedPreferences preferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_home);
        setTitle("Home");

        preferences= PreferenceManager.getDefaultSharedPreferences(HomeActivity.this);
        editor = preferences.edit();

        boolean firsttime = preferences.getBoolean("isFirstTime",true);

        if(firsttime)
        {
            welcome();
        }

    }

    private void welcome()
    {
        AlertDialog.Builder ad = new AlertDialog.Builder(HomeActivity.this);
        ad.setTitle("Epic Verse");
        ad.setMessage("Welcome to AirCooler Services");
        ad.setPositiveButton("Thank You", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        }).create().show();

        editor.putBoolean("isFirstTime",false).commit();
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_home,menu);
        return true;
    }
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.menuHomeMyProfile) {
            Intent i = new Intent(HomeActivity.this, MyProfileActivity.class);
            startActivity(i);
        } else if (item.getItemId() == R.id.menuHomeSettings) {
            Intent i = new Intent(HomeActivity.this, SettingsActivity.class);
            startActivity(i);
        } else if (item.getItemId() == R.id.menuHomeContactUs) {
            Intent i = new Intent(HomeActivity.this, ContactUsActivity.class);
            startActivity(i);
        } else if (item.getItemId() == R.id.menuHomeAboutUs) {
            Intent i = new Intent(HomeActivity.this, AboutUsActivity.class);
            startActivity(i);
        } else if (item.getItemId() == R.id.menuHomeLogOut) {
            logout();
        }
        return true;
    }
    private void logout() {
        AlertDialog.Builder ed = new AlertDialog.Builder(HomeActivity.this);
        ed.setTitle("Epic Verse");
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
                Intent i = new Intent(HomeActivity.this,LoginActivity.class);
                startActivity(i);
            }
        });
        ed.create().show();
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (doubletap) {
            finishAffinity();
        } else {
            Toast.makeText(HomeActivity.this, "Press Again to Exit", Toast.LENGTH_SHORT).show();
            doubletap = true;

            Handler h = new Handler();
            h.postDelayed(new Runnable() {
                @Override
                public void run() {
                    doubletap = false;
                }
            }, 2000);
        }
    }
}
