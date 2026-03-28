package com.example.mid;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

public class AddLawActivity extends AppCompatActivity {

    private TextInputEditText etTitle, etContent;
    private MaterialButton btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_law);

        initViews();

        btnSave.setOnClickListener(v -> {
            saveLaw();
        });
    }

    private void initViews() {
        etTitle = findViewById(R.id.et_law_title);
        etContent = findViewById(R.id.et_law_content);
        btnSave = findViewById(R.id.btn_save_law);
    }

    private void saveLaw() {
        String title = etTitle.getText().toString().trim();
        String content = etContent.getText().toString().trim();

        if (title.isEmpty() || content.isEmpty()) {
            Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
        } else {
            Intent resultIntent = new Intent();
            resultIntent.putExtra("law_title", title);
            resultIntent.putExtra("law_content", content);

            setResult(RESULT_OK, resultIntent);

            Toast.makeText(this, "Law saved successfully ✅", Toast.LENGTH_SHORT).show();

            finish();
        }
    }
}
