package com.example.mid;

public interface OnMessageClickListener {
    void onMessageClick(MessageModel message);
    void onMessageLongClick(int position, MessageModel message);
    void onMessageDelete(int position);
}