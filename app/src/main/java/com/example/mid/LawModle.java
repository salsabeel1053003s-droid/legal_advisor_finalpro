package com.example.mid;

public class LawModle {
    private String title;
    private String category;
    private String content;

    public LawModle(String title, String category, String content) {
        this.title = title;
        this.category = category;
        this.content = content;
    }

    public String getTitle() { return title; }
    public String getCategory() { return category; }
    public String getContent() { return content; }
}