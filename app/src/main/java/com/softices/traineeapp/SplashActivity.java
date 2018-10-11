package com.softices.traineeapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (isUserLogin()) {
                    Intent i = new Intent(SplashActivity.this, DashboardActivity.class);
                    startActivity(i);
                    finish();
                    Toast.makeText(getApplicationContext(),"success",Toast.LENGTH_LONG).show();
                }else{
                    Intent i = new Intent(SplashActivity.this, SignInActivity.class);
                    startActivity(i);
                    finish();
                }
            }
        }, 4 * 1000);

    }

    private Boolean isUserLogin() {
        android.content.SharedPreferences sharedPreferences = android.preference.PreferenceManager
                .getDefaultSharedPreferences(this);
        return sharedPreferences.getBoolean("is_user_login", false);
    }
}