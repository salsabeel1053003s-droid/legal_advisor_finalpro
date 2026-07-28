package com.example.mid;

public class MessageModel {
    private String senderId;
    private String messageText;
    private long timestamp;

    // مشيد فارغ (ضروري جداً إذا كنت تستخدم Firebase أو مكتبات Parsing)
    public MessageModel() {
    }

    // مشيد لتمرير البيانات عند إنشاء رسالة جديدة
    public MessageModel(String senderId, String messageText, long timestamp) {
        this.senderId = senderId;
        this.messageText = messageText;
        this.timestamp = timestamp;
    }

    // Getters and Setters
    public String getSenderId() {
        return senderId;
    }

    public void setSenderId(String senderId) {
        this.senderId = senderId;
    }

    public String getMessageText() {
        return messageText;
    }

    public void setMessageText(String messageText) {
        this.messageText = messageText;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
}