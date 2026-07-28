package com.example.mid;

public interface OnConsultationClickListener {
    void onViewClick(ConsultationModel consultation);
    void onDeleteClick(int position);
    void onStatusUpdateClick(int position, ConsultationModel consultation); // ميزة تحديث حالة الاستشارة
}