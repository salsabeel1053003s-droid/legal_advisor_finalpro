package com.example.mid;

import android.os.Bundle;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class ConsultationsActivity extends AppCompatActivity implements OnConsultationClickListener {

    private RecyclerView rvConsultations;
    private ConsultationsAdapter adapter;
    private List<ConsultationModel> consultationList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consulation);

        rvConsultations = findViewById(R.id.rv_consultations); // تأكدي من ID الـ RecyclerView في activity_consultation.xml
        if (rvConsultations != null) {
            rvConsultations.setLayoutManager(new LinearLayoutManager(this));
        }

        // إعداد القائمة الديناميكية
        consultationList = new ArrayList<>();
        loadDummyData();

        adapter = new ConsultationsAdapter(consultationList, this);
        if (rvConsultations != null) {
            rvConsultations.setAdapter(adapter);
        }
    }

    private void loadDummyData() {
        consultationList.add(new ConsultationModel("محمد أحمد", "2026-05-20", "كيف يمكنني تقديم طلب استشارة هندسية؟", "قيد الانتظار"));
        consultationList.add(new ConsultationModel("سارة الخالد", "2026-05-22", "استفسار بخصوص موعد الجلسة القادمة.", "تم الرد"));
    }

    @Override
    public void onViewClick(ConsultationModel consultation) {
        // عرض تفاصيل الاستشارة والحالة الحالية
        new AlertDialog.Builder(this)
                .setTitle("تفاصيل الاستشارة")
                .setMessage("من: " + consultation.getName() +
                        "\nالحالة: " + consultation.getStatus() +
                        "\n\nالسؤال:\n" + consultation.getContent())
                .setPositiveButton("إغلاق", null)
                .show();
    }

    @Override
    public void onDeleteClick(int position) {
        // تأكيد الحذف قبل التنفيذ
        new AlertDialog.Builder(this)
                .setTitle("حذف الاستشارة")
                .setMessage("هل أنت تأكد من رغبتك في حذف هذه الاستشارة؟")
                .setPositiveButton("حذف", (dialog, which) -> {
                    adapter.removeItem(position);
                    Toast.makeText(this, "تم حذف الاستشارة بنجاح", Toast.LENGTH_SHORT).show();
                })
                .setNegativeButton("إلغاء", null)
                .show();
    }

    @Override
    public void onStatusUpdateClick(int position, ConsultationModel consultation) {
        // خيارات تحديث حالة الاستشارة
        String[] options = {"قيد الانتظار", "تم الرد", "إلغاء الاستشارة"};

        new AlertDialog.Builder(this)
                .setTitle("تحديث حالة الاستشارة")
                .setItems(options, (dialog, which) -> {
                    consultation.setStatus(options[which]);
                    adapter.updateItem(position, consultation);
                    Toast.makeText(this, "تم تغيير الحالة إلى: " + options[which], Toast.LENGTH_SHORT).show();
                })
                .show();
    }
}
