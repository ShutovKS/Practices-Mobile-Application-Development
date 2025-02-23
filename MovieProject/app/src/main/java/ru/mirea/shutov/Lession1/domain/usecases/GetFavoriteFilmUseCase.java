package ru.mirea.shutov.Lession1.domain.usecases;

import ru.mirea.shutov.Lession1.domain.models.Movie;
import ru.mirea.shutov.Lession1.domain.repository.MovieRepository;

public class GetFavoriteFilmUseCase {
    private MovieRepository movieRepository;

    public GetFavoriteFilmUseCase(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public Movie execute() {
        return movieRepository.getMovie();
    }
}