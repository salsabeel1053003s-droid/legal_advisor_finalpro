package com.example.mid;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.appbar.MaterialToolbar;

import java.util.ArrayList;
import java.util.List;

public class AllLawsViewActivity extends AppCompatActivity {
    private RecyclerView rvAllLaws;
    private LawAdapter adapter; // تم تصحيح الاسم لـ LawAdapter
    private List<LawModle> lawList; // تم تصحيح الاسم لـ LawModel

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_laws);

        rvAllLaws = findViewById(R.id.rv_laws);
        rvAllLaws.setLayoutManager(new LinearLayoutManager(this));
        MaterialToolbar toolbar = findViewById(R.id.toolbar_laws);

        toolbar.setNavigationIconTint(getResources().getColor(R.color.gold));
        toolbar.setNavigationOnClickListener(v -> {
            finish();
        });

        loadData();

    }

    private void loadData() {
        lawList = new ArrayList<>();
        lawList.add(new LawModle("Labor Law", "Civil", "Article 1: Employment contracts must be in writing..."));
        lawList.add(new LawModle("Commercial Law", "Business", "Article 10: All traders must maintain accounting records..."));

        adapter = new LawAdapter(lawList, new LawAdapter.OnLawActionListener() {
            @Override
            public void onEdit(LawModle law, int position) {
            }

            @Override
            public void onDelete(LawModle law, int position) {
            }

            @Override
            public void onItemClick(LawModle law) {
                Intent intent = new Intent(AllLawsViewActivity.this, LawDetailsActivity.class);
                intent.putExtra("law_title", law.getTitle());
                intent.putExtra("law_category", law.getCategory());
                intent.putExtra("law_content", law.getContent());
                startActivity(intent);
            }
        });
        rvAllLaws.setAdapter(adapter);
    }
}