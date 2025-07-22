package com.example.mysplashscr;

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

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class HomeActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    boolean doubletap;
    
    BottomNavigationView bottomNavigationView;

    SharedPreferences preferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_home);
        setTitle("My AirCooler");

        preferences= PreferenceManager.getDefaultSharedPreferences(HomeActivity.this);
        editor = preferences.edit();

        boolean firsttime = preferences.getBoolean("isFirstTime",true);

        if(firsttime)
        {
            welcome();
        }
        
        bottomNavigationView = findViewById(R.id.homeBottomNavigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        
        bottomNavigationView.setSelectedItemId(R.id.menuBottomNavHome);

    }

    private void welcome()
    {
        AlertDialog.Builder ad = new AlertDialog.Builder(HomeActivity.this);
        ad.setTitle("My AirCooler");
        ad.setMessage("Welcome to AirCooler Services");
        ad.setPositiveButton("Thank You", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        }).create().show();

        editor.putBoolean("isFirstTime",false).commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_home, menu);
        return true;
    }
    
    

    @Override
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
        ed.setTitle("My AirCooler");
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
                Intent i = new Intent(HomeActivity.this, LoginActivity.class);
                editor.putBoolean("isLogin",false).commit();
                startActivity(i);
            }
        });

        ed.create().show();
    }

    @Override
    public void onBackPressed() {
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
    
    HomeFragment homeFragment = new HomeFragment();
    ExploreFragment exploreFragment = new ExploreFragment();
    CategoriesFragment categoriesFragment = new CategoriesFragment();
    MyCartFragment myCartFragment = new MyCartFragment();
    MyProfileFragment myProfileFragment = new MyProfileFragment();
    
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()== R.id.menuBottomNavHome) {
            getSupportFragmentManager().beginTransaction().replace(R.id.homeFrameLayout,homeFragment).commit();
        } else if (item.getItemId() == R.id.menuBottomNavExplore) {
            getSupportFragmentManager().beginTransaction().replace(R.id.homeFrameLayout,exploreFragment).commit();
        } else if (item.getItemId() == R.id.menuBottomNavCategories) {
            getSupportFragmentManager().beginTransaction().replace(R.id.homeFrameLayout,categoriesFragment).commit();
        } else if (item.getItemId() == R.id.menuBottomNavMyCart) {
            getSupportFragmentManager().beginTransaction().replace(R.id.homeFrameLayout,myCartFragment).commit();
        }else if (item.getItemId() == R.id.menuBottomNavMyProfile) {
            getSupportFragmentManager().beginTransaction().replace(R.id.homeFrameLayout,myProfileFragment).commit();
        }
        return true;
    }
}
