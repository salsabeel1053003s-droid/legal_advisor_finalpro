package com.example.mid;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(() -> {
            // فحص هل المستخدم مسجل دخول من قبل؟
            boolean isLoggedIn = getSharedPreferences("UserPrefs", MODE_PRIVATE)
                    .getBoolean("isLoggedIn", false);

            if (isLoggedIn) {
                startActivity(new Intent(SplashActivity.this, MainActivity.class));
            } else {
                startActivity(new Intent(SplashActivity.this, LoginActivity.class));
            }
            finish();
        }, 2000); // تأخير لمدة ثانيتين
    }
}