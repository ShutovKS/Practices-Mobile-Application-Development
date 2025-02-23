package ru.mirea.shutov.mobiletour.domain.repository;

import java.util.List;

import ru.mirea.shutov.mobiletour.domain.models.TourEntity;

public interface TourRepository {
    List<TourEntity> getTours();

    TourEntity getTourDetail(String tourId);
}