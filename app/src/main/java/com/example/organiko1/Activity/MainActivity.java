package com.example.organiko1.Activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.example.organiko1.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.organiko1.databinding.ActivityMain2Binding;

public class MainActivity extends AppCompatActivity {

    private ActivityMain2Binding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMain2Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Bundle intent = getIntent().getExtras();
        if (intent!=null) {
            String publisher = intent.getString( "publisherid");

            //TODO(1): Escribir en las preferencias "PREFS" el par con clave "profileid" y con valor publisher
            SharedPreferences preferences = getSharedPreferences( "PREFS", Context.MODE_PRIVATE );
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString("profileid", publisher );
            editor.apply();

        } else {

        }

        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main2);
      //  NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, navController);
    }

}