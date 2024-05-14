package com.example.testing;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class DataActivity extends AppCompatActivity {

    TextView textViewData, textViewUsername;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data);
        Button logoutButton = findViewById(R.id.buttonLogout);
        logoutButton.setOnClickListener(view -> {
                    // Действия по выходу
                    // 1. Очистка данных пользователя, например, SharedPreferences
                    // 2. Переход на экран входа
                    finish(); // Закрытие текущей Activity
                });
        textViewData = findViewById(R.id.textViewEmail);
        textViewUsername = findViewById(R.id.textViewUsername);

        // Получение имени пользователя из интента
        String username = getIntent().getStringExtra("username");
        textViewUsername.setText("Welcome, " + username);

        // Получение данных из БД
        db = openOrCreateDatabase("UserData", MODE_PRIVATE, null);
        String data = loadDataFromDatabase(username);
        textViewData.setText(data);

    }

    private String loadDataFromDatabase(String username) {
        Cursor cursor = db.rawQuery("SELECT password FROM users WHERE login=?", new String[]{username});
        StringBuilder sb = new StringBuilder();
        if (cursor.moveToFirst()) {
            String password = cursor.getString(cursor.getColumnIndexOrThrow("password"));
            sb.append("Your password: ").append(password);
        }
        cursor.close();
        db.close();
        return sb.toString();
    }
}