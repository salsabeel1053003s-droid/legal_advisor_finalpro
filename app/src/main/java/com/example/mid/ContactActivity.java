package com.example.mid;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import androidx.appcompat.app.AppCompatActivity;

public class ContactActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contact_activity);

        findViewById(R.id.btnBackContact).setOnClickListener(v -> finish());

        Button btnCall = findViewById(R.id.btnCall);
        btnCall.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_DIAL);
            intent.setData(Uri.parse("tel:" + getString(R.string.contact_phone_number)));
            startActivity(intent);
        });

        Button btnEmail = findViewById(R.id.btnEmail);
        btnEmail.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_SENDTO);
            intent.setData(Uri.parse("mailto:" + getString(R.string.contact_email_address)));
            intent.putExtra(Intent.EXTRA_SUBJECT, "App Support Request");
            startActivity(intent);
        });

        // Social Media Listeners
        findViewById(R.id.btnFacebook).setOnClickListener(v -> openLink("https://facebook.com"));
        findViewById(R.id.btnTwitter).setOnClickListener(v -> openLink("https://twitter.com"));
        findViewById(R.id.btnInstagram).setOnClickListener(v -> openLink("https://instagram.com"));
        findViewById(R.id.btnWhatsApp).setOnClickListener(v -> openLink("https://wa.me/18005550199"));
    }

    private void openLink(String url) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        startActivity(intent);
    }
}