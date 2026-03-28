package com.example.mid;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class LoginActivity extends AppCompatActivity {

    EditText etUsername, etPassword;
    Button btnLogin;
    TextView tvRegister, tvForgot;
    CheckBox cbRemember;

    SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // إخفاء شريط العنوان (ActionBar)
        if (getSupportActionBar() != null) getSupportActionBar().hide();

        // ربط العناصر بالواجهة
        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.btnLogin);
        tvRegister = findViewById(R.id.tvRegister);
        tvForgot = findViewById(R.id.tvForgot);
        cbRemember = findViewById(R.id.cbRemember);

        prefs = getSharedPreferences("UserPrefs", MODE_PRIVATE);

        // التحقق من حالة "تذكرني" (Remember Me)
        boolean remember = prefs.getBoolean("remember_me", false);
        if (remember) {
            String savedUser = prefs.getString("username", "");
            etUsername.setText(savedUser);
            // نترك كلمة المرور فارغة للأمان أو يمكن تعبئتها حسب رغبتك
            cbRemember.setChecked(true);
        }

        // حدث الضغط على زر تسجيل الدخول
        btnLogin.setOnClickListener(v -> loginUser());

        // التوجه لصفحة التسجيل
        tvRegister.setOnClickListener(v ->
                startActivity(new Intent(LoginActivity.this, RegistrationActivity.class)));

        // التوجه لصفحة نسيت كلمة المرور
        tvForgot.setOnClickListener(v -> {
            Intent intent = new Intent(LoginActivity.this, ForgotPasswordActivity.class);
            startActivity(intent);
        });
    }

    private void loginUser() {
        String username = etUsername.getText().toString().trim();
        String password = etPassword.getText().toString().trim();

        // التحقق من الحقول الفارغة
        if (TextUtils.isEmpty(username)) {
            etUsername.setError("Enter username");
            return;
        }

        if (TextUtils.isEmpty(password)) {
            etPassword.setError("Enter password");
            return;
        }

        // جلب البيانات المخزنة (التي تم حفظها أثناء التسجيل)
        String savedUser = prefs.getString("username", null);
        String savedPass = prefs.getString("password", null);

        if (savedUser == null || savedPass == null) {
            Toast.makeText(this, "No account found, please register first", Toast.LENGTH_LONG).show();
            return;
        }

        // تشفير كلمة المرور المدخلة لمقارنتها بالمخزنة
        String hashedInput = hashPassword(password);

        // التحقق من صحة البيانات
        if (!username.equals(savedUser)) {
            etUsername.setError("Username incorrect");
            return;
        }

        if (hashedInput == null || !hashedInput.equals(savedPass)) {
            etPassword.setError("Password incorrect");
            return;
        }

        // حفظ حالة "تذكرني"
        prefs.edit().putBoolean("remember_me", cbRemember.isChecked()).apply();

        Toast.makeText(this, "Login Successful", Toast.LENGTH_SHORT).show();

        // --- التوجيه بناءً على نوع الحساب ---
        Intent nextIntent;
        if (username.equalsIgnoreCase("admin")) {
            // التوجه لواجهة الأدمن (AdminActivity)
            nextIntent = new Intent(LoginActivity.this, AdminDashboard.class);
        } else {
            // التوجه للواجهة الرئيسية (MainActivity)
            nextIntent = new Intent(LoginActivity.this, MainActivity.class);
        }

        startActivity(nextIntent);
        finish(); // إغلاق صفحة تسجيل الدخول لعدم العودة إليها عند الضغط على "خلف"
    }

    // دالة لتشفير كلمة المرور بتقنية SHA-256
    private String hashPassword(String password) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(password.getBytes());

            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }
}