package com.example.mid;

public class CategoryModel {
    private int id;
    private String name;
    private int lawsCount;

    public CategoryModel(int id, String name, int lawsCount) {
        this.id = id;
        this.name = name;
        this.lawsCount = lawsCount;
    }

    public String getName() { return name; }
    public int getLawsCount() { return lawsCount; }
}
