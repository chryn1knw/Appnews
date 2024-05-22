package com.project.uts;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class ResetPasswordActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private EditText etResetEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);

        // Inisialisasi FirebaseAuth
        mAuth = FirebaseAuth.getInstance();

        etResetEmail = findViewById(R.id.et_reset_email);
        Button buttonResetPassword = findViewById(R.id.button_reset_password);

        buttonResetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = etResetEmail.getText().toString().trim();

                if (TextUtils.isEmpty(email)) {
                    etResetEmail.setError("Please enter your email");
                    etResetEmail.requestFocus();
                    return;
                }
                mAuth.sendPasswordResetEmail(email)
                        .addOnCompleteListener(task -> {
                            if (task.isSuccessful()) {
                                Toast.makeText(ResetPasswordActivity.this, "Reset password email sent", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(ResetPasswordActivity.this, "Email is not registered ", Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });
    }
}
