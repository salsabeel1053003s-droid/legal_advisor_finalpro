package com.example.mid;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

public class AddCategoryActivity extends AppCompatActivity {

    private TextInputEditText etCategoryName;
    private MaterialButton btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_category);

        initViews();

        btnSave.setOnClickListener(v -> {
            saveCategory();
        });
    }

    private void initViews() {
        etCategoryName = findViewById(R.id.et_category_name);
        btnSave = findViewById(R.id.btn_save_category);
    }

    private void saveCategory() {
        String name = etCategoryName.getText().toString().trim();

        if (name.isEmpty()) {
            Toast.makeText(this, "Please enter the category name", Toast.LENGTH_SHORT).show();
        } else {
            Intent resultIntent = new Intent();
            resultIntent.putExtra("cat_name", name);

            setResult(RESULT_OK, resultIntent);

            Toast.makeText(this, "Category '" + name + "' added successfully ✅", Toast.LENGTH_SHORT).show();

            finish();
        }
    }
}