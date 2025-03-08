package ru.mirea.shutov.domain.repository;

import ru.mirea.shutov.domain.data.Movie;

public interface MovieRepository {
    boolean saveMovie(Movie movie);

    Movie getMovie();
}

