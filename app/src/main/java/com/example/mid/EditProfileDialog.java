package com.example.mid;

import android.app.Dialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

public class EditProfileDialog extends DialogFragment {

    private EditText etName, etEmail;
    private Button btnSave, btnCancel;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(requireActivity());

        View view = LayoutInflater.from(getActivity())
                .inflate(R.layout.dialog_edit_profile, null);

        builder.setView(view);

        etName = view.findViewById(R.id.etName);
        etEmail = view.findViewById(R.id.etEmail);

        btnSave = view.findViewById(R.id.btnSave);
        btnCancel = view.findViewById(R.id.btnCancel);

        AlertDialog dialog = builder.create();

        // جعل الخلفية شفافة ليظهر التصميم
        if (dialog.getWindow() != null) {
            dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        }

        // تحميل البيانات المحفوظة إن وجدت
        SharedPreferences prefs = requireActivity().getSharedPreferences("UserPrefs", 0);
        String savedName = prefs.getString("name", "");
        String savedEmail = prefs.getString("email", "");

        etName.setText(savedName);
        etEmail.setText(savedEmail);

        btnSave.setOnClickListener(v -> {

            String name = etName.getText().toString().trim();
            String email = etEmail.getText().toString().trim();

            if (TextUtils.isEmpty(name)) {
                etName.setError("Enter your name");
                return;
            }

            if (TextUtils.isEmpty(email)) {
                etEmail.setError("Enter your email");
                return;
            }

            // حفظ البيانات
            SharedPreferences.Editor editor = prefs.edit();
            editor.putString("name", name);
            editor.putString("email", email);
            editor.apply();

            Toast.makeText(getActivity(), "Profile Updated Successfully", Toast.LENGTH_SHORT).show();

            dismiss();
        });

        btnCancel.setOnClickListener(v -> dismiss());

        return dialog;
    }
}