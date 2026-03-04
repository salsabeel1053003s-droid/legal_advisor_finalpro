package com.example.mid;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class FavoritesActivity extends AppCompatActivity {

    private ImageButton btnBack, btnClearAll, btnRemoveFav;
    private CardView cardExample;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.favorites_activity);

        initViews();

        setupClickListeners();
    }

    private void initViews() {
        btnBack = findViewById(R.id.btnBack);
        btnClearAll = findViewById(R.id.btnClearAll);
        btnRemoveFav = findViewById(R.id.btnRemoveFav);
        cardExample = findViewById(R.id.cardAI);
    }

    private void setupClickListeners() {

        btnBack.setOnClickListener(v -> {
            finish();
        });

        btnClearAll.setOnClickListener(v -> {
            Toast.makeText(this, "تم مسح جميع المفضلات", Toast.LENGTH_SHORT).show();
        });

        if (btnRemoveFav != null) {
            btnRemoveFav.setOnClickListener(v -> {
                Toast.makeText(this, "تمت الإزالة من المفضلات", Toast.LENGTH_SHORT).show();
                // cardExample.setVisibility(View.GONE);
            });
        }
    }
}

