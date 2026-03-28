package com.example.mid;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class LawDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_law_details);

        TextView tvTitle = findViewById(R.id.tv_detail_title);
        TextView tvCategory = findViewById(R.id.tv_detail_category);
        TextView tvContent = findViewById(R.id.tv_detail_content);

        String title = getIntent().getStringExtra("law_title");
        String category = getIntent().getStringExtra("law_category");
        String content = getIntent().getStringExtra("law_content");

        tvTitle.setText(title != null ? title : "No Title Available");
        tvCategory.setText(category != null ? category : "General");
        tvContent.setText(content != null ? content : "No content provided for this law.");
    }
}