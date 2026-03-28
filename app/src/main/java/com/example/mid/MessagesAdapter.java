package com.example.mid;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class MessagesAdapter extends RecyclerView.Adapter<MessagesAdapter.MessageViewHolder> {

    private List<MessageModel> messageList;

    public MessagesAdapter(List<MessageModel> messageList) {
        this.messageList = messageList;
    }

    @NonNull
    @Override
    public MessageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_message, parent, false);
        return new MessageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MessageViewHolder holder, int position) {
        MessageModel message = messageList.get(position);

        // Display Data
        holder.tvName.setText(message.name);
        holder.tvDate.setText(message.date);
        holder.tvContent.setText(message.content);
        holder.tvEmail.setText(message.email);
        holder.tvPhone.setText(message.phone);

        holder.btnDelete.setOnClickListener(v -> {
            int currentPos = holder.getAdapterPosition();
            if (currentPos != RecyclerView.NO_POSITION) {
                messageList.remove(currentPos);
                notifyItemRemoved(currentPos);
                notifyItemRangeChanged(currentPos, messageList.size());
                Toast.makeText(v.getContext(), "Message deleted successfully", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return messageList != null ? messageList.size() : 0;
    }

    public static class MessageViewHolder extends RecyclerView.ViewHolder {
        public View btnDelete;
        TextView tvName, tvDate, tvContent, tvEmail, tvPhone;

        public MessageViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tv_msg_sender_name);
            tvDate = itemView.findViewById(R.id.tv_msg_date);
            tvContent = itemView.findViewById(R.id.tv_msg_content);
            tvEmail = itemView.findViewById(R.id.tv_msg_email);
            tvPhone = itemView.findViewById(R.id.tv_msg_phone);

            btnDelete = itemView.findViewById(R.id.btn_delete_msg);
        }
    }
}
