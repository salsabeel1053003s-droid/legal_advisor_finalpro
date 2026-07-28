package com.example.mid;

public interface OnExpertActionListener {
    void OnCallClick(Expert expert);
    void OnDeleteClick(int position);
    void OnUpdateClick(int position, Expert expert);
}
