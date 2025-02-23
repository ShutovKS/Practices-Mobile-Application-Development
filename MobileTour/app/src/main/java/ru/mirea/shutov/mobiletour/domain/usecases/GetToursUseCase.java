package ru.mirea.shutov.mobiletour.domain.usecases;

import java.util.List;

import ru.mirea.shutov.mobiletour.domain.repository.TourRepository;
import ru.mirea.shutov.mobiletour.domain.models.TourEntity;

public class GetToursUseCase {
    private TourRepository repository;

    public GetToursUseCase(TourRepository repository) {
        this.repository = repository;
    }

    public List<TourEntity> execute() {
        return repository.getTours();
    }
}