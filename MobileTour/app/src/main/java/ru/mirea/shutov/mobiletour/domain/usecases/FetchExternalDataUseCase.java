package ru.mirea.shutov.mobiletour.domain.usecases;

import ru.mirea.shutov.mobiletour.domain.models.ExternalData;

public class FetchExternalDataUseCase {
    public ExternalData execute() {
        return new ExternalData("Sunny", "25°C");
    }
}
