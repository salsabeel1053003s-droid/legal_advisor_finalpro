package com.example.mid;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.button.MaterialButton;

public class AdminDashboard extends AppCompatActivity {

    private TextView txtAdminName, txtUserCount, txtLawCount;
    private MaterialButton btnManageLaws, btnManageCats, btnMessages, btnAllLaws, btnAllCats, btnConsultations;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        initViews();
        setupDashboardData();
        setupClickListeners();
        ImageButton btn_menu = findViewById(R.id.btn_menu);
        btn_menu.setOnClickListener(v -> {
            PopupMenu popue = new PopupMenu(AdminDashboard.this,v);
            popue.getMenu().add("settings");
            popue.getMenu().add("help");
            popue.getMenu().add("logout");
            popue.setOnMenuItemClickListener(item -> {
                Toast.makeText(this, "choices", Toast.LENGTH_SHORT).show();
                return true;
            });
            popue.show();

        });
        ImageButton btnProfile = findViewById(R.id.btn_profile);
        btnProfile.setOnClickListener(v -> { new AlertDialog.Builder(this)
                .setTitle("my account")
                .setMessage("Name:Ahmed Najem\nMembership ID:#5502")
                .setPositiveButton("ok",null)
                .show();
        });
    }

    private void initViews() {
        txtAdminName = findViewById(R.id.txt_admin_name);
        txtUserCount = findViewById(R.id.count_users);
        txtLawCount = findViewById(R.id.count_laws);

        btnManageLaws = findViewById(R.id.btn_manage_laws);
        btnManageCats = findViewById(R.id.btn_manage_cats);

        btnMessages = findViewById(R.id.btn_messages);
        btnAllLaws = findViewById(R.id.btn_all_laws);
        btnAllCats = findViewById(R.id.btn_all_cats);
        btnConsultations = findViewById(R.id.btn_consultations);
    }

    private void setupDashboardData() {
        txtAdminName.setText("Welcome back, Admin 👋");
        txtUserCount.setText("25");
        txtLawCount.setText("142");
    }

    private void setupClickListeners() {
        btnManageLaws.setOnClickListener(v ->
                startActivity(new Intent(AdminDashboard.this, LawsActivity.class)));

        btnManageCats.setOnClickListener(v ->
                startActivity(new Intent(AdminDashboard.this, CategoriesActivity.class)));

        btnAllLaws.setOnClickListener(v ->
                startActivity(new Intent(AdminDashboard.this, AllLawsViewActivity.class)));

        btnAllCats.setOnClickListener(v ->
                startActivity(new Intent(AdminDashboard.this, AllCategoriesActivity.class)));

        btnConsultations.setOnClickListener(v ->
                startActivity(new Intent(AdminDashboard.this, ConsultationsActivity.class)));

        btnMessages.setOnClickListener(v ->
                startActivity(new Intent(AdminDashboard.this, MessagesActivity.class)));
    }

}