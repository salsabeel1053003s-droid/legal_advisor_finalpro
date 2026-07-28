package com.example.mid;

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

    private RecyclerView rvContactMessages;
    private LinearLayout layoutEmptyMessages;
    private MessagesAdapter adapter;
    private List<MessageModel> messageList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messages);

        // 1. ربط العناصر بنفس الـ IDs الموجودة في activity_messages.xml
        MaterialToolbar toolbar = findViewById(R.id.toolbar_messages);
        rvContactMessages = findViewById(R.id.rv_contact_messages);
        layoutEmptyMessages = findViewById(R.id.layout_empty_messages);

        // زر العودة للخلف في Toolbar
        toolbar.setNavigationOnClickListener(v -> finish());

        // 2. إعداد الـ RecyclerView
        rvContactMessages.setLayoutManager(new LinearLayoutManager(this));
        messageList = new ArrayList<>();

        // 3. إضافة بيانات تجريبية (يمكنك حذفها أو استبدالها ببيانات من قاعدة البيانات/Firebase)
        loadSampleData();

        // 4. إعداد الـ Adapter وربطه
        adapter = new MessagesAdapter(messageList);
        rvContactMessages.setAdapter(adapter);

        // 5. التحقق من القائمة لإظهار layout_empty_messages إن كانت فارغة
        checkEmptyState();
    }

    private void checkEmptyState() {
        if (messageList.isEmpty()) {
            layoutEmptyMessages.setVisibility(View.VISIBLE);
            rvContactMessages.setVisibility(View.GONE);
        } else {
            layoutEmptyMessages.setVisibility(View.GONE);
            rvContactMessages.setVisibility(View.VISIBLE);
        }
    }

    private void loadSampleData() {
        messageList.add(new MessageModel("سارة أحمد", "مرحباً، أحتاج إلى استشارة قانونية.", System.currentTimeMillis(), "sara@example.com", "0590000000"));
        messageList.add(new MessageModel("محمد علي", "استفسار بخصوص موعد الجلسة القادمة.", System.currentTimeMillis(), "mohamed@example.com", "0560000000"));
    }
}