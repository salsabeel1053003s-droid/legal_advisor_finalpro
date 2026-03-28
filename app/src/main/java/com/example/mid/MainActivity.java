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
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private View menuOverlay;
    private View menuContainer;
    private ImageButton btnMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // إعداد التاريخ الحالي في الواجهة
        TextView tvDate = findViewById(R.id.tvDate);
        String currentDate = new SimpleDateFormat("EEEE, dd MMMM", new Locale("ar"))
                .format(new Date());
        tvDate.setText(currentDate);

        // تعريف عناصر القائمة الجانبية
        btnMenu = findViewById(R.id.btnMenu);
        menuOverlay = findViewById(R.id.menuOverlay);
        menuContainer = findViewById(R.id.menuContainer);

        btnMenu.setOnClickListener(v -> toggleMenu());
        menuOverlay.setOnClickListener(v -> toggleMenu());

        // تعريف كروت الشبكة
        CardView cardAI = findViewById(R.id.cardAI);
        CardView cardHuman = findViewById(R.id.cardHuman);
        CardView cardLaws = findViewById(R.id.cardLaws);
        CardView cardConsult = findViewById(R.id.cardConsult);
        CardView cardContact = findViewById(R.id.cardContact);

        // ربط كروت الشبكة بالصفحات
        cardAI.setOnClickListener(v -> openActivity(AIActivity.class));
        cardHuman.setOnClickListener(v -> openActivity(HumanActivity.class));
        cardLaws.setOnClickListener(v -> openActivity(LawsActivity.class));
        cardConsult.setOnClickListener(v -> openActivity(ConsultationsActivity.class));
        cardContact.setOnClickListener(v -> openActivity(ContactActivity.class));

        // ربط عناصر القائمة الجانبية بالصفحات
        findViewById(R.id.menuFavoritesItem).setOnClickListener(v -> {
            toggleMenu();
            openActivity(FavoritesActivity.class); // مثال: ربط Favorites بـ Laws
        });

        findViewById(R.id.menuNotificationsItem).setOnClickListener(v -> {
            toggleMenu();
            openActivity(NotificationsActivity.class); // ربط Notifications بـ Consultations
        });

        findViewById(R.id.menuSettingsItem).setOnClickListener(v -> {
            toggleMenu();
            openActivity(SettingsActivity.class); // Settings
        });
    }

    // دالة مساعدة لفتح أي صفحة جديدة
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