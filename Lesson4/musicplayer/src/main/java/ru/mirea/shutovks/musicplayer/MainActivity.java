package ru.mirea.shutovks.musicplayer;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import ru.mirea.shutovks.musicplayer.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private Handler handler;
    private boolean isPlaying = false;

    private int currentDuration;
    private int currentPosition;


    private final List<String> titles = Arrays.asList(
            "Electric Dreams", "Moonlight Sonata", "Retro Vibes",
            "Funky Town", "Starlight", "Rainy Afternoon", "Midnight Jazz"
    );
    private final List<String> artists = Arrays.asList(
            "DJ Alpha", "Luna Stars", "Groove Masters",
            "Neon Beats", "Smooth Operators", "Jazz Collective"
    );
    private final Random rnd = new Random();


    private final Runnable updateRunnable = new Runnable() {
        @Override
        public void run() {
            if (!isPlaying) return;
            currentPosition = Math.min(currentPosition + 500, currentDuration);
            binding.seekBar.setProgress(currentPosition);
            binding.tvCurrentTime.setText(formatTime(currentPosition));
            if (currentPosition < currentDuration) {
                handler.postDelayed(this, 500);
            } else {
                loadRandomTrack();
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        handler = new Handler(Looper.getMainLooper());

        binding.btnPlayPause.setOnClickListener(v -> togglePlayPause());
        binding.btnNext.setOnClickListener(v -> loadRandomTrack());
        binding.btnPrev.setOnClickListener(v -> loadRandomTrack());

        // SeekBar
        binding.seekBar.setOnSeekBarChangeListener(new android.widget.SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(android.widget.SeekBar seekBar, int progress, boolean fromUser) {
                if (fromUser) currentPosition = progress;
            }

            @Override
            public void onStartTrackingTouch(android.widget.SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(android.widget.SeekBar seekBar) {
            }
        });

        loadRandomTrack();
    }

    private void loadRandomTrack() {
        // Новый “трекик”
        String currentTitle = titles.get(rnd.nextInt(titles.size()));
        String currentArtist = artists.get(rnd.nextInt(artists.size()));
        currentDuration = rnd.nextInt(240_000) + 60_000;
        currentPosition = 0;

        // Апдейт UI
        binding.tvTitle.setText(currentTitle);
        binding.tvArtist.setText(currentArtist);
        binding.tvTotalTime.setText(formatTime(currentDuration));
        binding.tvCurrentTime.setText(formatTime(0));
        binding.seekBar.setMax(currentDuration);
        binding.seekBar.setProgress(0);
    }

    private void togglePlayPause() {
        if (isPlaying) {
            // Пауза
            isPlaying = false;
            handler.removeCallbacks(updateRunnable);
        } else {
            // Старт
            isPlaying = true;
            handler.post(updateRunnable);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeCallbacksAndMessages(null);
    }

    private String formatTime(int ms) {
        int secs = ms / 1000;
        int min = secs / 60;
        int sec = secs % 60;
        return String.format("%d:%02d", min, sec);
    }
}
