package com.example.mid;

public class Expert {
    private String name;
    private String specialty;
    private String phoneNumber;

    // Constructor يجمع البيانات الثلاثة
    public Expert(String name, String specialty, String phoneNumber) {
        this.name = name;
        this.specialty = specialty;
        this.phoneNumber = phoneNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpecialty() {
        return specialty;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}