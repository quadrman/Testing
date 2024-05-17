package com.example.testing;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class DataActivity extends AppCompatActivity {

    TextView textViewData, textViewUsername;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data);
        ImageView imageView = findViewById(R.id.imageView);
        imageView.setImageResource(R.drawable.avatar);
        Button logoutButton = findViewById(R.id.buttonLogout);
        logoutButton.setOnClickListener(view -> {
                    finish(); // Закрытие текущей Activity
                });
        textViewData = findViewById(R.id.textViewEmail);
        textViewUsername = findViewById(R.id.textViewUsername);

        // Получение имени пользователя из интента
        String username = getIntent().getStringExtra("username");
        textViewUsername.setText("Приветствую, " + username);

        // Получение данных из БД
        db = openOrCreateDatabase("UserData", MODE_PRIVATE, null);
        String data = loadDataFromDatabase(username);
        textViewData.setText(data);
        TextView textKolClick = findViewById(R.id.textKolClick);
        String clicks = loadClicksFromDatabase(username);
        textKolClick.setText(clicks);

        Button buttonClick = findViewById(R.id.buttonClick);
        buttonClick.setOnClickListener((view -> {

            String text = textKolClick.getText().toString();
            int colonIndex = text.indexOf(":");
            String numberString = text.substring(colonIndex + 1).trim();
            int number = Integer.parseInt(numberString);
            // Увеличиваем число на 1
            number++;
            // Записываем новое число обратно в TextView
            String clicker = loadClicksFromDatabase(username);
            textKolClick.setText(clicker);
            db.execSQL("UPDATE users SET click = ? WHERE login = ?", new Object[]{number, username});

        }));
    }

    private String loadDataFromDatabase(String username) {
        Cursor cursor = db.rawQuery("SELECT email FROM users WHERE login=?", new String[]{username});
        StringBuilder sb = new StringBuilder();
        if (cursor.moveToFirst()) {
            String email = cursor.getString(cursor.getColumnIndexOrThrow("email"));
            sb.append("Ваша почта: ").append(email);
        }
        cursor.close();
        return sb.toString();
    }
    private String loadClicksFromDatabase(String username) {
        Cursor cursor = db.rawQuery("SELECT click FROM users WHERE login=?", new String[]{username});
        StringBuilder sb = new StringBuilder();
        if (cursor.moveToFirst()) {
            String click = cursor.getString(cursor.getColumnIndexOrThrow("click"));
            sb.append("Кол-во кликов: ").append(click);
        }
        cursor.close();
        return sb.toString();
    }
}