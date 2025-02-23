package ru.mirea.shutov.Lession1.domain.usecases;

import ru.mirea.shutov.Lession1.domain.models.Movie;
import ru.mirea.shutov.Lession1.domain.repository.MovieRepository;

public class SaveMovieToFavoriteUseCase {
    private MovieRepository movieRepository;

    public SaveMovieToFavoriteUseCase(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public boolean execute(Movie movie) {
        return movieRepository.saveMovie(movie);
    }
}