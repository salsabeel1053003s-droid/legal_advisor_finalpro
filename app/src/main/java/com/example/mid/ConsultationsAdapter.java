package com.example.mid;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class ConsultationsAdapter extends RecyclerView.Adapter<ConsultationsAdapter.ConsViewHolder> {

    private List<ConsultationModel> consList;

    public ConsultationsAdapter(List<ConsultationModel> consList) {
        this.consList = consList;
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

        // Binding Data
        holder.tvName.setText(model.name);
        holder.tvDate.setText(model.date);
        holder.tvQuestion.setText(model.content);

        holder.btnView.setOnClickListener(v -> {
            new AlertDialog.Builder(v.getContext())
                    .setTitle("Consultation Details")
                    .setMessage("From: " + model.name + "\n\n" + "Question:\n" + model.content)
                    .setPositiveButton("Close", null)
                    .show();
        });

        // Delete Action
        holder.btnDelete.setOnClickListener(v -> {
            int currentPos = holder.getAdapterPosition();
            if (currentPos != RecyclerView.NO_POSITION) {
                consList.remove(currentPos);
                notifyItemRemoved(currentPos);
                notifyItemRangeChanged(currentPos, consList.size());
                Toast.makeText(v.getContext(), "Consultation deleted successfully", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return (consList != null) ? consList.size() : 0;
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