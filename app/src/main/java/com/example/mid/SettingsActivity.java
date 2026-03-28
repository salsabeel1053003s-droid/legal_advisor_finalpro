package com.example.mid;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.SwitchCompat;
import androidx.cardview.widget.CardView;

public class SettingsActivity extends AppCompatActivity {

    private boolean isAccountVisible = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        // --- ربط عناصر كرت التعريف وتحديث البيانات ---
        TextView tvUserName = findViewById(R.id.tvUserName);
        TextView tvUserEmail = findViewById(R.id.tvUserEmail);
        ImageButton btnEditProfileTop = findViewById(R.id.btnEditProfileTop);

        // جلب البيانات المخزنة من SharedPreferences التي تم إنشاؤها في صفحة التسجيل
        SharedPreferences prefs = getSharedPreferences("UserPrefs", MODE_PRIVATE);
        String savedUsername = prefs.getString("username", "Anas Al-Ahmad");
        String savedEmail = prefs.getString("email", "anas@example.com");

        tvUserName.setText(savedUsername);
        tvUserEmail.setText(savedEmail);

        // --- ربط العناصر الأخرى ---
        RelativeLayout layoutAccountHeader = findViewById(R.id.layoutAccountHeader);
        CardView cardAccountDetails = findViewById(R.id.cardAccountDetails);
        ImageView imgAccountArrow = findViewById(R.id.imgAccountArrow);
        RelativeLayout btnNotificationPage = findViewById(R.id.btnNotificationPage);
        SwitchCompat switchDarkMode = findViewById(R.id.switchDarkMode);
        TextView btnLogout = findViewById(R.id.btnLogout);
        TextView settingChangePhoto = findViewById(R.id.settingChangePhoto);
        TextView settingEditName = findViewById(R.id.settingEditName);

        // منطق القائمة المنسدلة (Account)
        layoutAccountHeader.setOnClickListener(v -> {
            if (!isAccountVisible) {
                cardAccountDetails.setVisibility(View.VISIBLE);
                imgAccountArrow.animate().rotation(180).setDuration(300).start();
                isAccountVisible = true;
            } else {
                cardAccountDetails.setVisibility(View.GONE);
                imgAccountArrow.animate().rotation(0).setDuration(300).start();
                isAccountVisible = false;
            }
        });

        // زر التعديل في الكرت العلوي
        btnEditProfileTop.setOnClickListener(v -> {
            EditProfileDialog dialog = new EditProfileDialog();
            dialog.show(getSupportFragmentManager(), "EditProfileDialog");
        });
        btnNotificationPage.setOnClickListener(v -> {
            Intent intent = new Intent(SettingsActivity.this, NotificationsActivity.class);
            startActivity(intent);
        });

        switchDarkMode.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
            }
        });

        btnLogout.setOnClickListener(v -> {
            Intent intent = new Intent(SettingsActivity.this, LoginActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finish();
        });

        settingChangePhoto.setOnClickListener(v -> Toast.makeText(this, "Opening Gallery...", Toast.LENGTH_SHORT).show());
        settingEditName.setOnClickListener(v -> Toast.makeText(this, "Edit Nickname...", Toast.LENGTH_SHORT).show());
    }
}