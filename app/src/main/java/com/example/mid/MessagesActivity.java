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

    private RecyclerView rvMessages;
    private LinearLayout layoutEmpty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messages);

        initViews();

        // Setup RecyclerView
        rvMessages.setLayoutManager(new LinearLayoutManager(this));

        // Load Data
        loadMessages();
    }

    private void initViews() {
        rvMessages = findViewById(R.id.rv_contact_messages);
        layoutEmpty = findViewById(R.id.layout_empty_messages);
        MaterialToolbar toolbar = findViewById(R.id.toolbar_messages);

        // Setup Toolbar
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Contact Messages");
        }
        toolbar.setNavigationOnClickListener(v -> finish());
    }

    private void loadMessages() {
        List<MessageModel> list = new ArrayList<>();

        list.add(new MessageModel("Ahmed Mahmoud", "2026-02-27", "ahmed@mail.com", "0599123456", "I would like to inquire about the official working hours."));
        list.add(new MessageModel("Laila Hassan", "2026-02-26", "laila@mail.com", "0599888777", "Thank you for the excellent service and legal assistance."));
        list.add(new MessageModel("John Doe", "2026-03-01", "john.d@mail.com", "0599000111", "Is there a consultation available for labor law?"));

        if (list.isEmpty()) {
            layoutEmpty.setVisibility(View.VISIBLE);
            rvMessages.setVisibility(View.GONE);
        } else {
            layoutEmpty.setVisibility(View.GONE);
            rvMessages.setVisibility(View.VISIBLE);

            MessagesAdapter adapter = new MessagesAdapter(list);
            rvMessages.setAdapter(adapter);
        }
    }
}

class MessageModel {
    String name, date, email, phone, content;

    public MessageModel(String name, String date, String email, String phone, String content) {
        this.name = name;
        this.date = date;
        this.email = email;
        this.phone = phone;
        this.content = content;
    }
}