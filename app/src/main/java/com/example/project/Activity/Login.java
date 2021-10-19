package com.example.project.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.Preferences.*;
import com.example.project.R;
import com.google.android.material.button.MaterialButton;

public class Login extends AppCompatActivity {
    private EditText etUsername, etPassword;
    private MaterialButton btnLogin;
    private UserPreferences userPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        userPreferences = new UserPreferences(Login.this);

        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);


        btnLogin = findViewById(R.id.btnLogin);
        /* Apps will check the login first from shared preferences */
        checkLogin();

        /* to clear the field just set text to "" */


        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validateForm()) {
                    if (etUsername.getText().toString().trim().equals("admin")
                            && etPassword.getText().toString().trim().equals("admin")) {
                        /* Set user to sharedPreferences */
                        userPreferences.setLogin(etUsername.getText().toString().trim(), etPassword.getText().toString().trim());
                        checkLogin();
                    } else {
                        Toast.makeText(Login.this, "Username Atau Password Salah", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    private boolean validateForm() {
        /* Check username & password is empty or not */
        if (etUsername.getText().toString().trim().isEmpty() || etPassword.getText().toString().trim().isEmpty()) {
            Toast.makeText(Login.this, "Username Atau Password Kosong", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private void checkLogin() {
        if(userPreferences.checkLogin()) {
            startActivity(new Intent(Login.this, MainActivity.class));
            finish();
        }
    }
}
