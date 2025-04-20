package com.example.nasapto.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Patterns;

import com.example.nasapto.R;


public class ActualLogin extends AppCompatActivity {

    // Declare variables for the UI components
    EditText email, password;
    Button loginButton;
    TextView registerLink; // Declare a TextView for the registration link
    ImageButton btnShowHidePassword, btnShowHideConfirmPassword;

    boolean isPasswordVisible = false;
    boolean isConfirmPasswordVisible = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actual_login);  // Set the content to the login layout

        // Link the XML components to Java variables using findViewById
        email = findViewById(R.id.inputemail);
        password = findViewById(R.id.inputpassword);
        loginButton = findViewById(R.id.btnLogin);
        btnShowHidePassword = findViewById(R.id.btnShowHidePassword);
        registerLink = findViewById(R.id.registerLink); // Initialize the registration link

        // Set an onClickListener on the login button to handle login
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get the input values from EditTexts
                String emailInput = email.getText().toString().trim();
                String passwordInput = password.getText().toString().trim();

                // Validate the inputs
                if (validateInputs(emailInput, passwordInput)) {
                    // If validation passes, proceed with login logic
                    loginUser(emailInput, passwordInput);
                }
            }
        });

        // Set onClick listener for the register link to navigate to registration activity
        registerLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to Registration Activity (replace RegistrationActivity with your actual class name)
                startActivity(new Intent(ActualLogin.this, LoginActivity.class));
            }
        });

        btnShowHidePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isPasswordVisible = !isPasswordVisible;
                updatePasswordVisibility(password, btnShowHidePassword, isPasswordVisible);
            }
        });


    }

    // Method to validate user input
    private boolean validateInputs(String email, String password) {
        if (email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            Toast.makeText(this, "Please enter a valid email", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (password.isEmpty()) {
            Toast.makeText(this, "Please enter a password", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }

    // Method to handle user login (You can replace this with your backend logic)
    private void loginUser(String email, String password) {
        // Placeholder for actual login logic (e.g., checking credentials)
        Toast.makeText(this, "Login successful!", Toast.LENGTH_SHORT).show();

        startActivity(new Intent(ActualLogin.this, LauncherActivity.class));
        finish();
    }


    private void updatePasswordVisibility(EditText passwordField, ImageButton toggleButton, boolean isVisible) {
        if (isVisible) {
            passwordField.setInputType(1); // 1 for visible password
            toggleButton.setImageResource(R.drawable.ic_visibility); // Change to visible icon
        } else {
            passwordField.setInputType(129); // 129 for hidden password
            toggleButton.setImageResource(R.drawable.ic_visibility_off); // Change to hidden icon
        }
        passwordField.setSelection(passwordField.length()); // Move cursor to the end
    }
}





