package com.example.mid;

public class MessageModel {
    private String senderId;
    private String messageText;
    private long timestamp;
    private String email;
    private String phone;

    // Empty constructor (ضروري عند التعامل مع Firebase)
    public MessageModel() {
    }

    // Full constructor
    public MessageModel(String senderId, String messageText, long timestamp, String email, String phone) {
        this.senderId = senderId;
        this.messageText = messageText;
        this.timestamp = timestamp;
        this.email = email;
        this.phone = phone;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}