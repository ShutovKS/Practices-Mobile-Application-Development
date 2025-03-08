package ru.mirea.shutov.data.storage;

import ru.mirea.shutov.data.storage.models.MovieData;

public interface MovieStorage {
    MovieData get();

    boolean save(MovieData movie);
}