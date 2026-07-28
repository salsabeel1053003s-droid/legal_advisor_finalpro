package com.example.mid;

import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class NotificationsActivity extends AppCompatActivity implements OnNotificationClickListener {

    private ImageButton btnBack, btnSettings;
    private RecyclerView rvNotifications;
    private NotificationsAdapter adapter;
    private List<NotificationModel> notificationsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notifications_activity);

        btnBack = findViewById(R.id.btnBackNotify);
        btnSettings = findViewById(R.id.btnSettingsNotify);
        rvNotifications = findViewById(R.id.rv_notifications);

        btnBack.setOnClickListener(v -> finish());

        btnSettings.setOnClickListener(v -> {
            Toast.makeText(this, getString(R.string.desc_notification_settings), Toast.LENGTH_SHORT).show();
        });

        rvNotifications.setLayoutManager(new LinearLayoutManager(this));

        // إعداد البيانات الديناميكية
        notificationsList = new ArrayList<>();
        notificationsList.add(new NotificationModel("تحديث جديد", "تم إضافة ميزات جديدة في التطبيق.", "2026-05-20"));
        notificationsList.add(new NotificationModel("تذكير موعد", "لديك استشارة قائمة اليوم الساعة 4 مساءً.", "2026-05-21"));

        // تمرير this لتنفيذ الـ Listener
        adapter = new NotificationsAdapter(notificationsList, this);
        rvNotifications.setAdapter(adapter);
    }

    @Override
    public void onNotificationClick(NotificationModel notification) {
        // عرض التفاصيل في AlertDialog
        new AlertDialog.Builder(this)
                .setTitle(notification.getTitle())
                .setMessage(notification.getMessage())
                .setPositiveButton("إغلاق", null)
                .show();
    }

    @Override
    public void onNotificationDelete(int position) {
        // تنفيذ الحذف عبر دالة הـ Adapter
        adapter.removeItem(position);
        Toast.makeText(this, "تم حذف الإشعار بنجاح", Toast.LENGTH_SHORT).show();
    }
}