package com.example.testing; // замените 'com.example.yourapp' на ваш пакет

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class DataActivity extends AppCompatActivity {

    TextView textViewWelcome, textViewUsername, textViewEmail;
    Button buttonLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data);

        textViewWelcome = findViewById(R.id.textViewWelcome);
        textViewUsername = findViewById(R.id.textViewUsername);
        textViewEmail = findViewById(R.id.textViewEmail);
        buttonLogout = findViewById(R.id.buttonLogout);

        // Получение данных пользователя (предполагается, что они передаются через Intent)
        Intent intent = getIntent();
        String username = intent.getStringExtra("username");
        String email = intent.getStringExtra("email");

        // Отображение данных пользователя
        textViewUsername.setText("Имя пользователя: " + username);
        textViewEmail.setText("Email: " + email);

        // Обработка нажатия на кнопку "Выйти"
        buttonLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Здесь можно добавить логику выхода (например, очистку данных сессии)

                // Возврат на экран входа
                Intent loginIntent = new Intent(DataActivity.this, MainActivity.class);
                startActivity(loginIntent);
                finish(); // Закрытие ActivityData
            }
        });
    }
}