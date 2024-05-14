package com.example.testing;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegistrationActivity extends AppCompatActivity {

    EditText editTextRegUsername, editTextRegPassword;
    Button buttonRegister;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        editTextRegUsername = findViewById(R.id.editTextUsername);
        editTextRegPassword = findViewById(R.id.editTextPassword);
        buttonRegister = findViewById(R.id.buttonRegister);

        // Получение доступа к базе данных
        db = openOrCreateDatabase("UserData", MODE_PRIVATE, null);

        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = editTextRegUsername.getText().toString();
                String password = editTextRegPassword.getText().toString();

                if (username.isEmpty() || password.isEmpty()) {
                    Toast.makeText(RegistrationActivity.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                } else {
                    // Вставка данных в базу данных
                    db.execSQL("INSERT INTO users (login, password) VALUES (?, ?)", new String[]{username, password});
                    Toast.makeText(RegistrationActivity.this, "Registration successful", Toast.LENGTH_SHORT).show();
                    finish(); // Закрытие формы регистрации
                }
            }
        });
    }
}