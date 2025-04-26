package ru.mirea.shutovks.lesson6;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText groupNumber, listNumber, movieName;
    private SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        groupNumber = findViewById(R.id.groupNumber);
        listNumber = findViewById(R.id.listNumber);
        movieName = findViewById(R.id.movieName);
        Button saveButton = findViewById(R.id.saveButton);

        prefs = getSharedPreferences("user_info", MODE_PRIVATE);

        loadPrefs();

        saveButton.setOnClickListener(v -> savePrefs());
    }

    private void loadPrefs() {
        groupNumber.setText(prefs.getString("groupNumber", ""));
        listNumber.setText(prefs.getString("listNumber", ""));
        movieName.setText(prefs.getString("movieName", ""));
    }

    private void savePrefs() {
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("groupNumber", groupNumber.getText().toString());
        editor.putString("listNumber", listNumber.getText().toString());
        editor.putString("movieName", movieName.getText().toString());
        editor.apply();
    }
}
