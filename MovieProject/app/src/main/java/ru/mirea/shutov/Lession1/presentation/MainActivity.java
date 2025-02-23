package ru.mirea.shutov.Lession1.presentation;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import ru.mirea.shutov.Lession1.R;
import ru.mirea.shutov.Lession1.data.repository.MovieRepositoryImpl;
import ru.mirea.shutov.Lession1.domain.models.Movie;
import ru.mirea.shutov.Lession1.domain.repository.MovieRepository;
import ru.mirea.shutov.Lession1.domain.usecases.GetFavoriteFilmUseCase;
import ru.mirea.shutov.Lession1.domain.usecases.SaveMovieToFavoriteUseCase;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText text = findViewById(R.id.editTextMovie);
        TextView textView = findViewById(R.id.textViewMovie);

        MovieRepository movieRepository = new MovieRepositoryImpl(this);

        findViewById(R.id.buttonSaveMovie).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean result = new SaveMovieToFavoriteUseCase(movieRepository)
                        .execute(new Movie(1, text.getText().toString()));
                textView.setText(String.format("Save result: %s", result));
            }
        });
        findViewById(R.id.buttonGetMovie).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Movie movie = new GetFavoriteFilmUseCase(movieRepository).execute();
                if (movie != null) {
                    textView.setText(String.format("Favorite movie: %s", movie.getName()));
                } else {
                    textView.setText("No favorite movie found!");
                }
            }
        });
    }
}
