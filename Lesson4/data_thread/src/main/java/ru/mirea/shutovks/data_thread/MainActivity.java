package ru.mirea.shutovks.data_thread;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import java.util.concurrent.TimeUnit;

import ru.mirea.shutovks.data_thread.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        String info = "Последовательность:\n" +
                "1) runn1 — через runOnUiThread сразу в UI‑поток\n" +
                "2) runn2 — через post сразу после предыдущих задач\n" +
                "3) runn3 — через postDelayed с задержкой 2 сек\n\n" +
                "runOnUiThread ≃ handler.post(),\n" +
                "post — тоже ставит в очередь UI‑потока,\n" +
                "postDelayed — то же с таймером.";
        binding.tvInfo.setText(info);
        
        final Runnable runn1 = () -> binding.tvInfo.setText("runn1");
        final Runnable runn2 = () -> binding.tvInfo.setText("runn2");
        final Runnable runn3 = () -> binding.tvInfo.setText("runn3");
        Thread t = new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);
                runOnUiThread(runn1);
                TimeUnit.SECONDS.sleep(1);
                binding.tvInfo.postDelayed(runn3, 2000);
                binding.tvInfo.post(runn2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        t.start();
    }
}