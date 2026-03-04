package com.example.mid; // تأكد أن هذا هو اسم الباكج الخاص بمشروعك

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
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


        RelativeLayout layoutAccountHeader = findViewById(R.id.layoutAccountHeader);
        CardView cardAccountDetails = findViewById(R.id.cardAccountDetails);
        ImageView imgAccountArrow = findViewById(R.id.imgAccountArrow);

        RelativeLayout btnNotificationPage = findViewById(R.id.btnNotificationPage);
        SwitchCompat switchDarkMode = findViewById(R.id.switchDarkMode);

        TextView btnLogout = findViewById(R.id.btnLogout);
        TextView settingChangePhoto = findViewById(R.id.settingChangePhoto);
        TextView settingEditName = findViewById(R.id.settingEditName);

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

        btnNotificationPage.setOnClickListener(v -> {
            Intent intent = new Intent(SettingsActivity.this, NotificationsActivity.class);
            startActivity(intent);
        });

        switchDarkMode.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                Toast.makeText(this, "تم تفعيل الوضع الداكن", Toast.LENGTH_SHORT).show();
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                Toast.makeText(this, "تم تفعيل الوضع الفاتح", Toast.LENGTH_SHORT).show();
            }
        });

        btnLogout.setOnClickListener(v -> {
            Intent intent = new Intent(SettingsActivity.this, LoginActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finish(); // إغلاق صفحة الإعدادات
        });

        settingChangePhoto.setOnClickListener(v -> {
            Toast.makeText(this, "فتح الاستوديو لتغيير الصورة...", Toast.LENGTH_SHORT).show();
            // يمكنك هنا إضافة كود فتح معرض الصور لاحقاً
        });

        settingEditName.setOnClickListener(v -> {
            Toast.makeText(this, "تعديل الاسم المستعار...", Toast.LENGTH_SHORT).show();
        });
    }
}