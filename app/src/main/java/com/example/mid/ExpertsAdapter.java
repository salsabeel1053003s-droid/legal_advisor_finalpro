package com.example.mid;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class ExpertsAdapter extends RecyclerView.Adapter<ExpertsAdapter.ViewHolder> {

    private Context context;
    private List<Expert> mList; // تم التعديل إلى List<Expert>
    private OnExpertActionListener listener; // إضافة الـ Listener

    // Constructor يستقبل الـ 3 معاملات بشكل صحيح
    public ExpertsAdapter(Context context, List<Expert> mList, OnExpertActionListener listener) {
        this.context = context;
        this.mList = mList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_expert, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Expert expert = mList.get(position);

        // ربط البيانات
        if (expert != null) {
            holder.tvExpertName.setText(expert.getName());
            holder.tvExpertSpecialty.setText(expert.getSpecialty());
        }

        // زر الاتصال بالخبير
        holder.btnCallExpert.setOnClickListener(v -> {
            if (listener != null && expert != null) {
                listener.OnCallClick(expert);
            }
        });

        // زر حذف الخبير
        holder.btnDeleteExpert.setOnClickListener(v -> {
            int currentPosition = holder.getAdapterPosition();
            if (currentPosition != RecyclerView.NO_POSITION && listener != null) {
                listener.OnDeleteClick(currentPosition);
            }
        });

        // إمكانية النقر على العنصر للتعديل (اختياري)
        holder.itemView.setOnClickListener(v -> {
            int currentPosition = holder.getAdapterPosition();
            if (currentPosition != RecyclerView.NO_POSITION && listener != null && expert != null) {
                listener.OnUpdateClick(currentPosition, expert);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList != null ? mList.size() : 0;
    }

    /**
     * دالة حذف عنصر من القائمة وتحديث الـ RecyclerView
     */
    public void removeItem(int position) {
        if (position >= 0 && position < mList.size()) {
            mList.remove(position);
            notifyItemRemoved(position);
            notifyItemRangeChanged(position, mList.size());
        }
    }

    // ViewHolder Class
    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvExpertName, tvExpertSpecialty;
        Button btnCallExpert;
        ImageButton btnDeleteExpert;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvExpertName = itemView.findViewById(R.id.tv_expert_name);
            tvExpertSpecialty = itemView.findViewById(R.id.tv_expert_specialty);
            btnCallExpert = itemView.findViewById(R.id.btn_call_expert);
            btnDeleteExpert = itemView.findViewById(R.id.btn_delete_expert);
        }
    }
}