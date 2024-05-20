package com.example.testing;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegistrationActivity extends AppCompatActivity {

    EditText editTextRegUsername, editTextRegPassword,edittextRegemail;
    Button buttonRegister, buttonAlready;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        editTextRegUsername = findViewById(R.id.editTextUsername);
        editTextRegPassword = findViewById(R.id.editTextPassword);
        buttonRegister = findViewById(R.id.buttonRegister);
        edittextRegemail = findViewById(R.id.editTextEmail);
        buttonAlready = findViewById(R.id.buttonalready);

        // Получение доступа к базе данных
        db = openOrCreateDatabase("UserData", MODE_PRIVATE, null);
        buttonAlready.setOnClickListener(v -> finish());

        buttonRegister.setOnClickListener(view -> {
            String username = editTextRegUsername.getText().toString();
            String password = editTextRegPassword.getText().toString();
            String email = edittextRegemail.getText().toString();
            if (username.isEmpty() || password.isEmpty()) {
                Toast.makeText(RegistrationActivity.this, "Пожалуйста заполните все поля.", Toast.LENGTH_SHORT).show();
            } else {
                // Вставка данных в базу данных

                Cursor cursor = db.rawQuery("SELECT 1 FROM users WHERE login = ?", new String[]{username});
                if (cursor.moveToFirst()) {
                    // Пользователь с таким логином уже существует
                    Toast.makeText(RegistrationActivity.this, "Данный логин уже зарегистрирован!", Toast.LENGTH_SHORT).show();
                    cursor.close();
                } else {
                    // Пользователя с таким логином нет, можно регистрировать
                    db.execSQL("INSERT INTO users (login, password, email, click) VALUES (?, ?, ?, ?)", new String[]{username, password, email, "0"});
                    Toast.makeText(RegistrationActivity.this, "Регистрация успешна", Toast.LENGTH_SHORT).show();
                    finish(); // Закрытие формы регистрации
                    db.close();
                }

            }
        });
    }
}