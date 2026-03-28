package com.example.mid;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.content.SharedPreferences;

import androidx.appcompat.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(() -> {

            SharedPreferences prefs = getSharedPreferences("UserPrefs", MODE_PRIVATE);

            // فحص هل المستخدم مسجل دخول
            boolean isLoggedIn = prefs.getBoolean("isLoggedIn", false);

            String username = prefs.getString("username", "");

            if (isLoggedIn) {

                // إذا كان الادمن
                if (username.equals("admin")) {
                    startActivity(new Intent(SplashActivity.this, AdminDashboard.class));
                }
                // المستخدم العادي
                else {
                    startActivity(new Intent(SplashActivity.this, MainActivity.class));
                }

            } else {
                startActivity(new Intent(SplashActivity.this, LoginActivity.class));
            }

            finish();

        }, 2000); // تأخير ثانيتين
    }
}