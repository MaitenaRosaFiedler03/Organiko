package com.example.organiko1.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.organiko1.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class SplashScreen extends Activity {

    Handler handler;
    FirebaseUser firebaseUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                firebaseUser=  FirebaseAuth.getInstance().getCurrentUser();
                Intent intent;
                if(firebaseUser != null){
                     intent = new Intent(SplashScreen.this, MainActivity.class);
                    startActivity(intent);
                }else{
                    intent = new Intent( SplashScreen.this, LoginActivity.class);
                }

                startActivity(intent);
                finish();
            }
        }, 3000);

    }
}