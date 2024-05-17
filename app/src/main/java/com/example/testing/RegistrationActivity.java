package com.example.testing;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegistrationActivity extends AppCompatActivity {

    EditText editTextRegUsername, editTextRegPassword,edittextRegemail;
    Button buttonRegister;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        editTextRegUsername = findViewById(R.id.editTextUsername);
        editTextRegPassword = findViewById(R.id.editTextPassword);
        buttonRegister = findViewById(R.id.buttonRegister);
        edittextRegemail = findViewById(R.id.editTextEmail);

        // Получение доступа к базе данных
        db = openOrCreateDatabase("UserData", MODE_PRIVATE, null);

        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = editTextRegUsername.getText().toString();
                String password = editTextRegPassword.getText().toString();
                String email = edittextRegemail.getText().toString();
                if (username.isEmpty() || password.isEmpty()) {
                    Toast.makeText(RegistrationActivity.this, "Пожалуйста заполните все поля.", Toast.LENGTH_SHORT).show();
                } else {
                    // Вставка данных в базу данных
                    db.execSQL("INSERT INTO users (login, password,email,click) VALUES (?, ?, ?,?)", new String[]{username, password,email,"0"});
                    Toast.makeText(RegistrationActivity.this, "Регистрация успешна", Toast.LENGTH_SHORT).show();
                    finish(); // Закрытие формы регистрации
                    db.close();
                }
            }
        });
    }
}