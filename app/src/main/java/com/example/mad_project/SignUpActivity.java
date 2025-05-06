package com.example.mad_project;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class SignUpActivity extends AppCompatActivity {
    private EditText emailEditText;
    private EditText passwordEditText;
    private EditText confirmPasswordEditText;
    private ImageView passwordToggle;
    private ImageView confirmPasswordToggle;
    private Button createAccountButton;
    private TextView signInText;
    private boolean passwordVisible = false;
    private boolean confirmPasswordVisible = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        // Initialize views
        emailEditText = findViewById(R.id.emailEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        confirmPasswordEditText = findViewById(R.id.confirmPasswordEditText);
        passwordToggle = findViewById(R.id.passwordToggle);
        confirmPasswordToggle = findViewById(R.id.confirmPasswordToggle);
        createAccountButton = findViewById(R.id.createAccountButton);
        signInText = findViewById(R.id.signInText);

        // Password visibility toggle
        passwordToggle.setOnClickListener(v -> {
            passwordVisible = !passwordVisible;
            if (passwordVisible) {
                passwordEditText.setTransformationMethod(null);
                passwordToggle.setImageResource(R.drawable.ic_eye_off);
            } else {
                passwordEditText.setTransformationMethod(new PasswordTransformationMethod());
                passwordToggle.setImageResource(R.drawable.ic_eye);
            }
            passwordEditText.setSelection(passwordEditText.getText().length());
        });

        // Confirm Password visibility toggle
        confirmPasswordToggle.setOnClickListener(v -> {
            confirmPasswordVisible = !confirmPasswordVisible;
            if (confirmPasswordVisible) {
                confirmPasswordEditText.setTransformationMethod(null);
                confirmPasswordToggle.setImageResource(R.drawable.ic_eye_off);
            } else {
                confirmPasswordEditText.setTransformationMethod(new PasswordTransformationMethod());
                confirmPasswordToggle.setImageResource(R.drawable.ic_eye);
            }
            confirmPasswordEditText.setSelection(confirmPasswordEditText.getText().length());
        });

        // Create Account button click
        createAccountButton.setOnClickListener(v -> {
            String email = emailEditText.getText().toString().trim();
            String password = passwordEditText.getText().toString();
            String confirmPassword = confirmPasswordEditText.getText().toString();

            if (validateInputs(email, password, confirmPassword)) {
                // Start OTP verification
                Intent intent = new Intent(SignUpActivity.this, OTPVerificationActivity.class);
                intent.putExtra("email", email);
                startActivity(intent);
            }
        });

        // Sign In text click
        signInText.setOnClickListener(v -> {
            startActivity(new Intent(SignUpActivity.this, LoginActivity.class));
            finish();
        });
    }

    private boolean validateInputs(String email, String password, String confirmPassword) {
        if (email.isEmpty()) {
            emailEditText.setError("Email is required");
            return false;
        }

        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            emailEditText.setError("Please enter a valid email");
            return false;
        }

        if (password.isEmpty()) {
            passwordEditText.setError("Password is required");
            return false;
        }

        if (password.length() < 6) {
            passwordEditText.setError("Password must be at least 6 characters");
            return false;
        }

        if (!password.equals(confirmPassword)) {
            confirmPasswordEditText.setError("Passwords do not match");
            return false;
        }

        return true;
    }
} 