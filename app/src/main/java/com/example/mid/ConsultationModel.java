package com.example.mid;

public class ConsultationModel {
    private String name;
    private String date;
    private String content;
    private String status; // مثال: "قيد الانتظار"، "تم الرد"، "ملغية"

    public ConsultationModel(String name, String date, String content, String status) {
        this.name = name;
        this.date = date;
        this.content = content;
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}