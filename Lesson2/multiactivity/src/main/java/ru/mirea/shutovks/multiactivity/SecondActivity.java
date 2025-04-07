
package ru.mirea.shutovks.multiactivity;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SecondActivity extends AppCompatActivity {
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        textView = findViewById(R.id.textViewSecond);
        String text = getIntent().getStringExtra("key");
        if (text != null) {
            textView.setText(text);
        }
    }
}
