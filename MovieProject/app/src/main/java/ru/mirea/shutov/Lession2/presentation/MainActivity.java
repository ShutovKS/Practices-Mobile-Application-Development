package ru.mirea.shutov.Lession2.presentation;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.time.LocalDate;

import ru.mirea.shutov.Lession2.R;
import ru.mirea.shutov.data.repository.MovieRepositoryImpl;
import ru.mirea.shutov.data.storage.MovieStorage;
import ru.mirea.shutov.data.storage.sharedpref.SharedPrefMovieStorage;
import ru.mirea.shutov.domain.data.Movie;
import ru.mirea.shutov.domain.repository.MovieRepository;
import ru.mirea.shutov.domain.usecases.GetFavoriteFilmUseCase;
import ru.mirea.shutov.domain.usecases.SaveMovieToFavoriteUseCase;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText text = findViewById(R.id.editTextMovie);
        TextView textView = findViewById(R.id.textViewMovie);

        MovieStorage movieStorage = new SharedPrefMovieStorage(this);
        MovieRepository movieRepository = new MovieRepositoryImpl(movieStorage);

        findViewById(R.id.buttonSaveMovie).setOnClickListener(view -> {
            boolean result = new SaveMovieToFavoriteUseCase(movieRepository)
                    .execute(new Movie(
                            1,
                            text.getText().toString(),
                            String.valueOf(LocalDate.now())
                    ));
            textView.setText(String.format("Save result: %s", result));
        });
        findViewById(R.id.buttonGetMovie).setOnClickListener(view -> {
            Movie movie = new GetFavoriteFilmUseCase(movieRepository).execute();
            if (movie != null) {
                textView.setText(String.format("Favorite movie: %s", movie.getName()));
            } else {
                textView.setText("No favorite movie found!");
            }
        });
    }
}
