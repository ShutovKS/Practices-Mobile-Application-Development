package ru.mirea.shutov.data.storage.sharedpref;

import android.content.Context;
import android.content.SharedPreferences;

import java.time.LocalDate;

import ru.mirea.shutov.data.storage.MovieStorage;
import ru.mirea.shutov.data.storage.models.MovieData;

public class SharedPrefMovieStorage implements MovieStorage {
    private static final String PREFS_NAME = "favorite_movie_prefs";
    private static final String KEY_MOVIE_ID = "movie_id";
    private static final String KEY_MOVIE_NAME = "movie_name";
    private static final String KEY_MOVIE_DATE = "movie_date";

    private Context context;
    private SharedPreferences sharedPreferences;

    public SharedPrefMovieStorage(Context context) {
        this.context = context;
        this.sharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
    }

    public boolean save(MovieData movie) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(KEY_MOVIE_ID, 1);
        editor.putString(KEY_MOVIE_NAME, movie.getName());
        editor.putString(KEY_MOVIE_DATE, String.valueOf(LocalDate.now()));
        return editor.commit();
    }

    public MovieData get() {
        int id = sharedPreferences.getInt(KEY_MOVIE_ID, -1);
        String name = sharedPreferences.getString(KEY_MOVIE_NAME, "unknown");
        String date = sharedPreferences.getString(KEY_MOVIE_DATE, String.valueOf(LocalDate.now()));
        return new MovieData(id, name, date);
    }
}
