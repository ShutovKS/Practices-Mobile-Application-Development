package ru.mirea.shutov.mobiletour.data.repository;

import java.util.ArrayList;
import java.util.List;

import ru.mirea.shutov.mobiletour.domain.models.TourEntity;
import ru.mirea.shutov.mobiletour.domain.repository.TourRepository;

public class TourRepositoryImpl implements TourRepository {
    @Override
    public List<TourEntity> getTours() {
        List<TourEntity> tours = new ArrayList<>();
        tours.add(new TourEntity("1", "Beach Holiday", "https://example.com/beach.jpg"));
        tours.add(new TourEntity("2", "Mountain Adventure", "https://example.com/mountain.jpg"));
        return tours;
    }

    @Override
    public TourEntity getTourDetail(String tourId) {
        if ("1".equals(tourId)) {
            return new TourEntity("1", "Beach Holiday", "https://example.com/beach.jpg");
        } else if ("2".equals(tourId)) {
            return new TourEntity("2", "Mountain Adventure", "https://example.com/mountain.jpg");
        }
        return null;
    }
}