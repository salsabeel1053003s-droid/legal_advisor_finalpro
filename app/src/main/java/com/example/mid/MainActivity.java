package com.example.mid;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private View menuOverlay;
    private CardView menuContainer;
    private ImageButton btnMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 1. إعداد التاريخ الحالي في الواجهة
        TextView tvDate = findViewById(R.id.tvDate);
        String currentDate = new SimpleDateFormat("EEEE, dd MMMM", new Locale("ar"))
                .format(new Date());
        tvDate.setText(currentDate);

        // 2. تعريف عناصر القائمة الجانبية (التعتيم والحاوية)
        btnMenu = findViewById(R.id.btnMenu);
        menuOverlay = findViewById(R.id.menuOverlay);
        menuContainer = findViewById(R.id.menuContainer);

        btnMenu.setOnClickListener(v -> toggleMenu());
        menuOverlay.setOnClickListener(v -> toggleMenu());

        // 3. تعريف كروت الشبكة (Grid Cards) بربطها بـ IDs الموجودة في XML
        CardView cardAI = findViewById(R.id.cardAI);
        CardView cardHuman = findViewById(R.id.cardHuman);
        CardView cardFavorites = findViewById(R.id.cardFavorites);
        CardView cardNotifications = findViewById(R.id.cardNotifications);
        CardView cardContact = findViewById(R.id.cardContact);
        CardView cardSettings = findViewById(R.id.cardSettings);

        // 4. برمجة التنقل بين الصفحات (Intent) بناءً على طلبك
        // ربط AI Advisor بصفحة AIActivity
        cardAI.setOnClickListener(v -> openActivity(AIActivity.class));

        // ربط Human Expert بصفحة HumanActivity
        cardHuman.setOnClickListener(v -> openActivity(HumanActivity.class));

        // ربط Favorites بصفحة FavoritesActivity
        cardFavorites.setOnClickListener(v -> openActivity(FavoritesActivity.class));

        // ربط Notifications بصفحة NotificationsActivity
        cardNotifications.setOnClickListener(v -> openActivity(NotificationsActivity.class));

        // ربط Contact Us بصفحة ContactActivity
        cardContact.setOnClickListener(v -> openActivity(ContactActivity.class));

        // ربط Settings بصفحة SettingsActivity
        cardSettings.setOnClickListener(v -> openActivity(SettingsActivity.class));

        // 5. برمجة أزرار القائمة المنبثقة (Popup Menu Items)
        findViewById(R.id.menuFavoritesItem).setOnClickListener(v -> {
            toggleMenu();
            openActivity(FavoritesActivity.class);
        });
        findViewById(R.id.menuNotificationsItem).setOnClickListener(v -> {
            toggleMenu();
            openActivity(NotificationsActivity.class);
        });
        findViewById(R.id.menuSettingsItem).setOnClickListener(v -> {
            toggleMenu();
            openActivity(SettingsActivity.class);
        });
    }

    // دالة مساعدة لفتح أي صفحة جديدة لتقليل تكرار الكود
    private void openActivity(Class<?> targetActivity) {
        Intent intent = new Intent(MainActivity.this, targetActivity);
        startActivity(intent);
    }

    // دالة لإظهار أو إخفاء القائمة الجانبية
    private void toggleMenu() {
        if (menuContainer.getVisibility() == View.GONE) {
            menuContainer.setVisibility(View.VISIBLE);
            menuOverlay.setVisibility(View.VISIBLE);
        } else {
            menuContainer.setVisibility(View.GONE);
            menuOverlay.setVisibility(View.GONE);
        }
    }
}