package ru.mirea.shutov.data.repository;

import ru.mirea.shutov.data.storage.MovieStorage;
import ru.mirea.shutov.data.storage.models.MovieData;
import ru.mirea.shutov.domain.data.Movie;
import ru.mirea.shutov.domain.repository.MovieRepository;

public class MovieRepositoryImpl implements MovieRepository {
    private MovieStorage movieStorage;

    public MovieRepositoryImpl(MovieStorage movieStorage) {
        this.movieStorage = movieStorage;
    }

    @Override
    public boolean saveMovie(Movie movie) {
        MovieData dataModel = mapToData(movie);
        return movieStorage.save(dataModel);
    }

    @Override
    public Movie getMovie() {
        MovieData dataModel = movieStorage.get();
        return mapToDomain(dataModel);
    }

    private MovieData mapToData(Movie domainModel) {
        return new MovieData(
                domainModel.getId(),
                domainModel.getName(),
                domainModel.getLocalDate()
        );
    }

    private Movie mapToDomain(MovieData dataModel) {
        return new Movie(
                dataModel.getId(),
                dataModel.getName(),
                dataModel.getLocalDate()
        );
    }
}
