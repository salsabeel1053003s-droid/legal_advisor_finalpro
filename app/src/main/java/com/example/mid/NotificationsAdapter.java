package com.example.mid;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class NotificationsAdapter extends RecyclerView.Adapter<NotificationsAdapter.NotificationViewHolder> {

    private List<NotificationModel> notificationList;
    private OnNotificationClickListener listener;

    // الـ Constructor المحدث يستقبل الـ Listener
    public NotificationsAdapter(List<NotificationModel> notificationList, OnNotificationClickListener listener) {
        this.notificationList = notificationList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public NotificationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_notification, parent, false);
        return new NotificationViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NotificationViewHolder holder, int position) {
        NotificationModel model = notificationList.get(position);

        if (model != null) {
            holder.tvTitle.setText(model.getTitle());
            holder.tvMessage.setText(model.getMessage());
            holder.tvDate.setText(model.getDate());
        }

        // حدث عرض تفاصيل الإشعار
        holder.itemView.setOnClickListener(v -> {
            if (listener != null && model != null) {
                listener.onNotificationClick(model);
            }
        });

        // حدث حذف الإشعار
        if (holder.btnDelete != null) {
            holder.btnDelete.setOnClickListener(v -> {
                int currentPos = holder.getAdapterPosition();
                if (currentPos != RecyclerView.NO_POSITION && listener != null) {
                    listener.onNotificationDelete(currentPos);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return (notificationList != null) ? notificationList.size() : 0;
    }

    // دالة حذف عنصر من القائمة وتحديث الـ UI
    public void removeItem(int position) {
        if (position >= 0 && position < notificationList.size()) {
            notificationList.remove(position);
            notifyItemRemoved(position);
            notifyItemRangeChanged(position, notificationList.size());
        }
    }

    public static class NotificationViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle, tvMessage, tvDate;
        View btnDelete;

        public NotificationViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tv_notify_title);
            tvMessage = itemView.findViewById(R.id.tv_notify_message);
            tvDate = itemView.findViewById(R.id.tv_notify_date);
            btnDelete = itemView.findViewById(R.id.btn_delete_notify);
        }
    }

    public void filterList(List<NotificationModel> filteredList) {
        this.notificationList = filteredList;
        notifyDataSetChanged();
    }
}