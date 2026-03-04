package com.example.mid;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class RegistrationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        if (getSupportActionBar() != null) getSupportActionBar().hide();

        Button btnSignUp = findViewById(R.id.btnSignUp);
        TextView tvBack = findViewById(R.id.tvBackToLogin);

        btnSignUp.setOnClickListener(v -> {
            Toast.makeText(this, "Account Created!", Toast.LENGTH_SHORT).show();
            finish(); // يعود لصفحة الدخول
        });

        tvBack.setOnClickListener(v -> finish());
    }
}