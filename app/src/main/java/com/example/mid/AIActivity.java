package com.example.mid;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class AIActivity extends AppCompatActivity {

    // تعريف العناصر
    private EditText userInquiryInput;
    private ImageButton btnSendInquiry;
    private TextView aiResponseText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ai_activity);

        // 1. ربط العناصر بالـ IDs الموجودة في ملف الـ XML
        userInquiryInput = findViewById(R.id.userInquiryInput);
        btnSendInquiry = findViewById(R.id.btnSendInquiry);
        aiResponseText = findViewById(R.id.aiResponseText);

        // 2. إعداد حدث النقر للزر
        btnSendInquiry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                performSendMessage();
            }
        });
    }

    private void performSendMessage() {
        // الحصول على النص من حقل الإدخال
        String userText = userInquiryInput.getText().toString().trim();

        if (!userText.isEmpty()) {
            // تحديث واجهة المحادثة بنص المستخدم
            aiResponseText.setText("أنت: " + userText + "\n\nجاري معالجة استشارتك... ⚖️");

            // مسح حقل الإدخال بعد الإرسال
            userInquiryInput.setText("");

            // (اختياري) محاكاة رد من النظام بعد ثانية واحدة
            new android.os.Handler().postDelayed(() -> {
                aiResponseText.append("\n\nالمستشار: شكراً لتواصلك. بناءً على الأنظمة القانونية، ننصحك بـ...");
            }, 1500);

        } else {
            Toast.makeText(this, "يرجى كتابة سؤالك أولاً", Toast.LENGTH_SHORT).show();
        }
    }
}