package com.example.mid;

import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class AllCategoriesActivity extends AppCompatActivity {

    private RecyclerView rvAllCategories;
    private CategoryAdapter adapter;
    private List<CategoryModel> categoryList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_categories);

        initViews();
        loadCategories();

        ImageButton btnBack = findViewById(R.id.btn_back_all_categories);
        btnBack.setOnClickListener(v -> {
            finish();
        });
    }

    private void initViews() {
        rvAllCategories = findViewById(R.id.rv_all_categories);
        rvAllCategories.setLayoutManager(new GridLayoutManager(this, 2));
    }

    private void loadCategories() {
        categoryList = new ArrayList<>();
        categoryList.add(new CategoryModel(1, "Criminal", 15));
        categoryList.add(new CategoryModel(2, "Family Law", 10));
        categoryList.add(new CategoryModel(3, "Civil", 20));
        categoryList.add(new CategoryModel(4, "Labor", 5));
        categoryList.add(new CategoryModel(5, "Commercial", 12));
        categoryList.add(new CategoryModel(6, "Administrative", 7));

        adapter = new CategoryAdapter(categoryList, new CategoryAdapter.OnCategoryActionListener() {
            @Override
            public void onEdit(CategoryModel category, int position) {
            }

            @Override
            public void onDelete(CategoryModel category, int position) {
            }

            @Override
            public void onItemClick(CategoryModel category) {
                Toast.makeText(AllCategoriesActivity.this, "Viewing: " + category.getName(), Toast.LENGTH_SHORT).show();
            }
        });

        rvAllCategories.setAdapter(adapter);
    }
}