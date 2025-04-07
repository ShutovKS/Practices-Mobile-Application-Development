package ru.mirea.shutovks.toastapp;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private EditText edittext;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        edittext = findViewById(R.id.editTextText);
        button = findViewById(R.id.button);

        button.setOnClickListener(v -> {
            Toast toast = Toast.makeText(getApplicationContext(),
                    "СТУДЕНТ № 27 ГРУППА БСБО-09-22 Количество символов - " + edittext.length(),
                    Toast.LENGTH_SHORT);
            toast.show();
        });
    }
}