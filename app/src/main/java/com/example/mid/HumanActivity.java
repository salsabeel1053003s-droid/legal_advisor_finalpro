package com.example.mid;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import androidx.appcompat.app.AppCompatActivity;

public class HumanActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.human_activity);

        ImageButton btnBack = findViewById(R.id.btnBackHuman);
        btnBack.setOnClickListener(v -> finish());

        setupCallButton(R.id.btnCallExpert1, "111222333");
        setupCallButton(R.id.btnCallExpert2, "444555666");
        setupCallButton(R.id.btnCallExpert3, "777888999");
    }

    private void setupCallButton(int buttonId, String phoneNumber) {
        Button button = findViewById(buttonId);
        button.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_DIAL);
            intent.setData(Uri.parse("tel:" + phoneNumber));
            startActivity(intent);
        });
    }
}