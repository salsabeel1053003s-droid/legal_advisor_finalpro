package com.example.mid;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class LawAdapter extends RecyclerView.Adapter<LawAdapter.LawViewHolder> {

    private List<LawModle> lawList;
    private OnLawActionListener listener;

    public interface OnLawActionListener {
        void onEdit(LawModle law, int position);
        void onDelete(LawModle law, int position);
        void onItemClick(LawModle law);
    }

    public LawAdapter(List<LawModle> lawList, OnLawActionListener listener) {
        this.lawList = lawList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public LawViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_law, parent, false);
        return new LawViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LawViewHolder holder, int position) {
        LawModle law = lawList.get(position);

        holder.tvTitle.setText(law.getTitle());
        holder.tvCategory.setText(law.getCategory());
        holder.tvContent.setText(law.getContent());

        holder.btnEdit.setVisibility(View.VISIBLE);
        holder.btnDelete.setVisibility(View.VISIBLE);

        holder.btnEdit.setOnClickListener(v -> {
            int currentPos = holder.getAdapterPosition();
            if (listener != null && currentPos != RecyclerView.NO_POSITION) {
                listener.onEdit(lawList.get(currentPos), currentPos);
            }
        });

        holder.btnDelete.setOnClickListener(v -> {
            int currentPos = holder.getAdapterPosition();
            if (listener != null && currentPos != RecyclerView.NO_POSITION) {
                listener.onDelete(lawList.get(currentPos), currentPos);
            }
        });

        holder.itemView.setOnClickListener(v -> {
            if (listener != null) listener.onItemClick(law);
        });
    }

    @Override
    public int getItemCount() {
        return (lawList != null) ? lawList.size() : 0;
    }

    public static class LawViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle, tvCategory, tvContent;
        ImageButton btnEdit, btnDelete;

        public LawViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tv_law_title);
            tvCategory = itemView.findViewById(R.id.tv_law_category);
            tvContent = itemView.findViewById(R.id.tv_law_content_preview);
            btnEdit = itemView.findViewById(R.id.btn_edit_law);
            btnDelete = itemView.findViewById(R.id.btn_delete_law);
        }
    }
}
