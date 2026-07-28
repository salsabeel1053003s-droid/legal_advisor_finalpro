package com.example.mid; // قم بتغيير اسم الحزمة ليتوافق مع مشروعك

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.appbar.MaterialToolbar;
import java.util.ArrayList;
import java.util.List;

public class MessagesActivity extends AppCompatActivity {

    private MaterialToolbar toolbarMessages;
    private RecyclerView rvContactMessages;
    private LinearLayout layoutEmptyMessages;

    private List<MessageModel> messageList;
    private MessagesAdapter messagesAdapter; // الـ Adapter الخاص بك لعرض عناصر الرسائل

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messages); // تأكد من اسم ملف XML الخاص بالواجهة

        // 1. ربط عناصر الواجهة بنفس IDs المحددة في الـ XML
        toolbarMessages = findViewById(R.id.toolbar_messages);
        rvContactMessages = findViewById(R.id.rv_contact_messages);
        layoutEmptyMessages = findViewById(R.id.layout_empty_messages);

        // 2. إعداد الـ Toolbar وزر الرجوع
        toolbarMessages.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); // إغلاق الشاشة عند الضغط على سهم الرجوع
            }
        });

        // 3. تهيئة القائمة والـ Adapter
        messageList = new ArrayList<>();
        messagesAdapter = new MessagesAdapter(messageList);

        // 4. إعداد الـ RecyclerView
        rvContactMessages.setLayoutManager(new LinearLayoutManager(this));
        rvContactMessages.setAdapter(messagesAdapter);

        // 5. تحميل البيانات (يمكنك ربطها مع Firebase أو API)
        loadMessages();
    }

    private void loadMessages() {
        // مثال: جلب البيانات (أضف أسلوب الجلب الخاص بك هنا)
        // messageList.add(new MessageModel("1", "Hello, I need help", System.currentTimeMillis()));

        // التتحكم في إظهار أو إخفاء واجهة "لا توجد رسائل"
        checkEmptyState();
    }

    // دالة لتحديث الواجهة في حال كانت القائمة فارغة أو تحتوي على بيانات
    private void checkEmptyState() {
        if (messageList.isEmpty()) {
            layoutEmptyMessages.setVisibility(View.VISIBLE);
            rvContactMessages.setVisibility(View.GONE);
        } else {
            layoutEmptyMessages.setVisibility(View.GONE);
            rvContactMessages.setVisibility(View.VISIBLE);
            messagesAdapter.notifyDataSetChanged();
        }
    }
}