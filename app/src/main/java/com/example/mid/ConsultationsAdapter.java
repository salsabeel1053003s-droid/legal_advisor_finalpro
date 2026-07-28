package com.example.mid;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class ConsultationsAdapter extends RecyclerView.Adapter<ConsultationsAdapter.ConsViewHolder> {

    private List<ConsultationModel> consList;
    private OnConsultationClickListener listener;

    public ConsultationsAdapter(List<ConsultationModel> consList, OnConsultationClickListener listener) {
        this.consList = consList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ConsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_consulation, parent, false);
        return new ConsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ConsViewHolder holder, int position) {
        ConsultationModel model = consList.get(position);

        if (model != null) {
            holder.tvName.setText(model.getName());
            holder.tvDate.setText(model.getDate());
            holder.tvQuestion.setText(model.getContent());
        }

        // عرض التفاصيل
        if (holder.btnView != null) {
            holder.btnView.setOnClickListener(v -> {
                if (listener != null && model != null) {
                    listener.onViewClick(model);
                }
            });
        }

        // حذف الاستشارة
        if (holder.btnDelete != null) {
            holder.btnDelete.setOnClickListener(v -> {
                int currentPos = holder.getAdapterPosition();
                if (currentPos != RecyclerView.NO_POSITION && listener != null) {
                    listener.onDeleteClick(currentPos);
                }
            });
        }

        // الضغط المطول أو النقر لتحديث حالة الاستشارة
        holder.itemView.setOnLongClickListener(v -> {
            int currentPos = holder.getAdapterPosition();
            if (currentPos != RecyclerView.NO_POSITION && listener != null && model != null) {
                listener.onStatusUpdateClick(currentPos, model);
                return true;
            }
            return false;
        });
    }

    @Override
    public int getItemCount() {
        return (consList != null) ? consList.size() : 0;
    }

    // دالة حذف عنصر من القائمة
    public void removeItem(int position) {
        if (position >= 0 && position < consList.size()) {
            consList.remove(position);
            notifyItemRemoved(position);
            notifyItemRangeChanged(position, consList.size());
        }
    }

    // دالة تحديث عنصر في القائمة
    public void updateItem(int position, ConsultationModel updatedModel) {
        if (position >= 0 && position < consList.size()) {
            consList.set(position, updatedModel);
            notifyItemChanged(position);
        }
    }

    public static class ConsViewHolder extends RecyclerView.ViewHolder {
        TextView tvName, tvDate, tvQuestion;
        ImageButton btnDelete, btnView;

        public ConsViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tv_user_name);
            tvDate = itemView.findViewById(R.id.tv_date);
            tvQuestion = itemView.findViewById(R.id.tv_question_preview);
            btnDelete = itemView.findViewById(R.id.btn_delete);
            btnView = itemView.findViewById(R.id.btn_view);
        }
    }

    public void filterList(List<ConsultationModel> filteredList) {
        this.consList = filteredList;
        notifyDataSetChanged();
    }
}