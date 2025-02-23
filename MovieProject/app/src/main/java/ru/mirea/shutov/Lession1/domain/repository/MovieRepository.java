package ru.mirea.shutov.Lession1.domain.repository;

import ru.mirea.shutov.Lession1.domain.models.Movie;

public interface MovieRepository {
    public boolean saveMovie(Movie movie);

    public Movie getMovie();
}

