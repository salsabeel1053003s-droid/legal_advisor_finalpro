package com.example.mid;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AIActivity extends AppCompatActivity {

    private EditText inputEditText;
    private ImageButton sendButton;
    private TextView aiTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ai_activity);

        aiTitle = findViewById(R.id.aiTitle);
        inputEditText = findViewById(R.id.inputArea).findViewById(android.R.id.edit); // إذا لم يكن له ID مباشر
        sendButton = findViewById(R.id.inputArea).findViewWithTag("send_button"); // أو يفضل إضافة ID للـ EditText والـ Button في الـ XML

        ImageButton sendBtn = findImageButtonInLayout();
        if (sendBtn != null) {
            sendBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    sendMessage();
                }
            });
        }
    }

    private void sendMessage() {
        Toast.makeText(this, "جاري معالجة استشارتك القانونية...", Toast.LENGTH_SHORT).show();
    }

    private ImageButton findImageButtonInLayout() {
        View inputArea = findViewById(R.id.inputArea);
        if (inputArea instanceof android.widget.LinearLayout) {
            android.widget.LinearLayout layout = (android.widget.LinearLayout) inputArea;
            for (int i = 0; i < layout.getChildCount(); i++) {
                if (layout.getChildAt(i) instanceof ImageButton) {
                    return (ImageButton) layout.getChildAt(i);
                }
            }
        }
        return null;
    }
}