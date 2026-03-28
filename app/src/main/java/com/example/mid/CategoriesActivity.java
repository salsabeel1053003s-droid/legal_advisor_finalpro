package com.example.mid;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.util.ArrayList;
import java.util.List;

public class CategoriesActivity extends AppCompatActivity {

    private RecyclerView rvCategories;
    private FloatingActionButton fabAddCategory;
    private List<CategoryModel> categoryList;
    private CategoryAdapter adapter;

    private final ActivityResultLauncher<Intent> addCategoryLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                    String categoryName = result.getData().getStringExtra("cat_name");

                    if (categoryList != null) {
                        categoryList.add(0, new CategoryModel(0, categoryName, 0));
                        adapter.notifyItemInserted(0);
                        rvCategories.scrollToPosition(0);
                        Toast.makeText(this, "Category added successfully ✅", Toast.LENGTH_SHORT).show();
                    }
                }
            }
    );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_law_categories);

        initViews();
        loadCategoriesData();
        MaterialToolbar toolbar = findViewById(R.id.toolbar_categories);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    private void initViews() {
        rvCategories = findViewById(R.id.rv_categories);
        fabAddCategory = findViewById(R.id.fab_add_category);

        rvCategories.setLayoutManager(new LinearLayoutManager(this));

        fabAddCategory.setOnClickListener(view -> {
            Intent intent = new Intent(CategoriesActivity.this, AddCategoryActivity.class);
            addCategoryLauncher.launch(intent);
        });
    }

    private void loadCategoriesData() {
        categoryList = new ArrayList<>();
        categoryList.add(new CategoryModel(1, "Criminal Law", 12));
        categoryList.add(new CategoryModel(2, "Personal Status", 8));
        categoryList.add(new CategoryModel(3, "Civil Law", 15));
        categoryList.add(new CategoryModel(4, "Labor Law", 5));

        adapter = new CategoryAdapter(categoryList, new CategoryAdapter.OnCategoryActionListener() {
            @Override
            public void onEdit(CategoryModel category, int position) {
                Toast.makeText(CategoriesActivity.this, "Edit: " + category.getName(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onDelete(CategoryModel category, int position) {
                if (position != RecyclerView.NO_POSITION) {
                    categoryList.remove(position);
                    adapter.notifyItemRemoved(position);
                    adapter.notifyItemRangeChanged(position, categoryList.size());
                    Toast.makeText(CategoriesActivity.this, category.getName() + " deleted", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onItemClick(CategoryModel category) {
                Toast.makeText(CategoriesActivity.this, "Viewing: " + category.getName(), Toast.LENGTH_SHORT).show();
            }
        });

        rvCategories.setAdapter(adapter);
    }
}