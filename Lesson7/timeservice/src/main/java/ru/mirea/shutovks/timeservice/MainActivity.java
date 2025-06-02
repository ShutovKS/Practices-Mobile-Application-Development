package ru.mirea.shutovks.timeservice;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.Socket;

public class MainActivity extends AppCompatActivity {
    private final String host = "time-a.nist.gov";
    private final int port = 13;

    private TextView timeTextView;
    private Button getTimeButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        timeTextView = findViewById(R.id.timeTextView);
        getTimeButton = findViewById(R.id.getTimeButton);

        getTimeButton.setOnClickListener(v -> {
            timeTextView.setText("Запрашиваем...");
            new GetTimeTask().execute();
        });
    }

    private class GetTimeTask extends AsyncTask<Void, Void, String> {
        @Override
        protected String doInBackground(Void... params) {
            try {
                Socket socket = new Socket(host, port);
                BufferedReader reader = SocketUtils.getReader(socket);
                reader.readLine();
                String timeResult = reader.readLine();
                socket.close();
                return timeResult;
            } catch (IOException e) {
                return null;
            }
        }

        @Override
        protected void onPostExecute(String result) {
            if (result != null && !result.isEmpty()) {
                String[] parts = result.split(" ");
                if (parts.length >= 3) {
                    timeTextView.setText("Дата: " + parts[1] + "\nВремя: " + parts[2]);
                } else {
                    timeTextView.setText("Ошибка разбора строки времени.");
                }
            } else {
                timeTextView.setText("Ошибка получения времени.");
            }
        }
    }
}
