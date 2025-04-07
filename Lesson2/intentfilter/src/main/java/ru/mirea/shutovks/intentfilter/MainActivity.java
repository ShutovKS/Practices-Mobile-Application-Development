package ru.mirea.shutovks.intentfilter;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    private Button openLinkButton;
    private Button sendData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        openLinkButton = findViewById(R.id.openLinkButton);
        openLinkButton.setOnClickListener(v -> {
            Uri address = Uri.parse("https://www.mirea.ru/");
            Intent openLinkIntent = new Intent(Intent.ACTION_VIEW, address);
            startActivity(openLinkIntent);
        });
        
        sendData = findViewById(R.id.sendDataButton);
        sendData.setOnClickListener(v -> {
            Intent shareIntent = new Intent(Intent.ACTION_SEND);
            shareIntent.setType("text/plain");
            shareIntent.putExtra(Intent.EXTRA_SUBJECT, "MIREA");
            shareIntent.putExtra(Intent.EXTRA_TEXT, "Шутов Кирилл Сергеевич");
            startActivity(Intent.createChooser(shareIntent, "МОИ ФИО"));
        });
        
    }
}