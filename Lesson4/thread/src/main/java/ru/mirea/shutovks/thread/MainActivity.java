package ru.mirea.shutovks.thread;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;

import ru.mirea.shutovks.thread.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private Handler handler = new Handler(Looper.getMainLooper());

    private final AtomicInteger counter = new AtomicInteger(1);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Thread mainThread = Thread.currentThread();

        binding.textView.setText("Имя текущего потока: " + mainThread.getName());

        mainThread.setName("МОЙ НОМЕР ГРУППЫ: БСБО-09-22, НОМЕР ПО СПИСКУ: 27, МОЙ ЛЮБИИМЫЙ ФИЛЬМ: Мстители: Война бесконечности");

        binding.textView.append("\nНовое имя потока: " + mainThread.getName());

        Log.d(MainActivity.class.getSimpleName(), "Stack: " + Arrays.toString(mainThread.getStackTrace()));
        Log.d(MainActivity.class.getSimpleName(), "Group: " + mainThread.getThreadGroup());

        binding.buttonMirea.setOnClickListener(v -> startWorkerThread());
    }

    private void startWorkerThread() {
        String pairsStr = binding.etTotalPairs.getText().toString();
        String daysStr = binding.etStudyDays.getText().toString();
        if (pairsStr.isEmpty() || daysStr.isEmpty()) {
            binding.textViewResult.setText("Введите данные");
            return;
        }

        Runnable worker = new Runnable() {
            @Override
            public void run() {
                int threadNum = counter.getAndIncrement();
                Log.d("ThreadProject", String.format(
                        "Запущен поток №%d студентом группы БСБО-09-22 номер по списку 27",
                        threadNum
                ));

                long endTime = System.currentTimeMillis() + 20 * 1000;
                while (System.currentTimeMillis() < endTime) {
                    synchronized (this) {
                        try {
                            wait(endTime - System.currentTimeMillis());
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                            return;
                        }
                    }
                }
                Log.d("ThreadProject", "Выполнен поток №" + threadNum);

                int totalPairs = Integer.parseInt(pairsStr);
                int days = Integer.parseInt(daysStr);
                double avg = days != 0 ? (double) totalPairs / days : 0.0;

                handler.post(() -> binding.textViewResult.setText(String.format("Среднее пар в день: %.2f", avg)));
            }
        };

        new Thread(worker).start();
    }
}
