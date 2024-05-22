package com.project.uts;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    private FirebaseAuth myAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);

        myAuth = FirebaseAuth.getInstance();
        Button buttonLogin = findViewById(R.id.button_login);
        EditText editTextUsername = findViewById(R.id.et_email);
        EditText editTextPassword = findViewById(R.id.et_password);
        TextView tvRegister = findViewById(R.id.tv_span_1);
        TextView tvForgotPassword = findViewById(R.id.tv_span_2);
        String text1 = "Don't have an account? Register";
        String text2 = "Reset Password";

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = editTextUsername.getText().toString().trim();
                String password = editTextPassword.getText().toString().trim();

                if (TextUtils.isEmpty(name)) {
                    editTextUsername.setError("Please enter your username");
                    editTextUsername.requestFocus();
                    return;
                }

                if (TextUtils.isEmpty(password)) {
                    editTextPassword.setError("Please enter your password");
                    editTextPassword.requestFocus();
                    return;
                }

                myAuth.signInWithEmailAndPassword(name, password)
                        .addOnCompleteListener(LoginActivity.this, task -> {
                            if (task.isSuccessful()) {
                                FirebaseUser user = myAuth.getCurrentUser();
                                Intent intent = new Intent(LoginActivity.this, NewsDashboardActivity.class);
                                startActivity(intent);
                                finish();
                            } else {
                                Toast.makeText(LoginActivity.this, "Wrong password or username is not registered", Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });

        //span
        SpannableString registerspan = new SpannableString(text1);
        SpannableString resetPasswordspan = new SpannableString(text2);
        int color = Color.parseColor("#FE504E");
        ForegroundColorSpan colorSpan = new ForegroundColorSpan(color);
        registerspan.setSpan(colorSpan, text1.indexOf("Register"), text1.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        resetPasswordspan.setSpan(colorSpan, text2.indexOf("Reset"), text2.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        tvRegister.setText(registerspan);
        tvForgotPassword.setText(resetPasswordspan);

        tvRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
                finish();
            }
        });

        tvForgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, ResetPasswordActivity.class);
                startActivity(intent);
            }
        });
    }
}
