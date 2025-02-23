package ru.mirea.shutov.mobiletour.domain.usecases;

import ru.mirea.shutov.mobiletour.domain.repository.TourRepository;
import ru.mirea.shutov.mobiletour.domain.models.TourEntity;

public class GetTourDetailUseCase {
    private TourRepository repository;

    public GetTourDetailUseCase(TourRepository repository) {
        this.repository = repository;
    }

    public TourEntity execute(String tourId) {
        return repository.getTourDetail(tourId);
    }
}