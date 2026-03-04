package com.example.mid;

import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class NotificationsActivity extends AppCompatActivity {

    private ImageButton btnBack, btnSettings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notifications_activity);

        btnBack = findViewById(R.id.btnBackNotify);
        btnSettings = findViewById(R.id.btnSettingsNotify);

        btnBack.setOnClickListener(v -> {
            finish();
        });

        btnSettings.setOnClickListener(v -> {
            // Updated to use the new string resource
            Toast.makeText(this, getString(R.string.desc_notification_settings), Toast.LENGTH_SHORT).show();
        });
    }
}