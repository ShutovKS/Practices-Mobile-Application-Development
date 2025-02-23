package ru.mirea.shutov.mobiletour.domain.usecases;

public class AuthUseCase {
    // Для демонстрации возвращает true, если логин "test" и пароль "1234"
    public boolean execute(String username, String password) {
        return "test".equals(username) && "1234".equals(password);
    }
}