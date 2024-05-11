package com.project.uts;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);

        Button buttonLogin = findViewById(R.id.button_login);
        EditText editTextUsername = findViewById(R.id.et_register_username);
        EditText editTextPassword = findViewById(R.id.et_register_password);
        TextView tvRegister = findViewById(R.id.tv_register);
        String text = "Dont have an account? Register";

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

                Intent intent = new Intent(LoginActivity.this, NewsDashboardActivity.class);
                startActivity(intent);
                finish();
            }
        });

        //span
        SpannableString spannableString = new SpannableString(text);
        int color = Color.parseColor("#FE504E");
        ForegroundColorSpan colorSpan = new ForegroundColorSpan(color);
        spannableString.setSpan(colorSpan, text.indexOf("Register"), text.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        tvRegister.setText(spannableString);

        tvRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
                finish();
            }
        });



    }
}
