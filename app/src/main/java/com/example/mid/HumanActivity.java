package com.example.mid;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;
import com.example.mid.R;

public class HumanActivity extends AppCompatActivity implements OnExpertActionListener {

    private RecyclerView recyclerView;
    private ExpertsAdapter adapter;
    private List<Expert> expertList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.human_activity);

        recyclerView = findViewById(R.id.rv_experts);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        expertList = new ArrayList<>();

        adapter = new ExpertsAdapter(this, expertList, this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void OnCallClick(Expert expert) {
        if (expert != null && expert.getPhoneNumber() != null) {
            Intent intent = new Intent(Intent.ACTION_DIAL);
            intent.setData(Uri.parse("tel:" + expert.getPhoneNumber()));
            startActivity(intent);
        }
    }

    @Override
    public void OnDeleteClick(int position) {
        adapter.removeItem(position);
        Toast.makeText(this, "تم الحذف بنجاح", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void OnUpdateClick(int position, Expert expert) {
        Toast.makeText(this, "تعديل العنصر في الموقع: " + position, Toast.LENGTH_SHORT).show();
    }
}