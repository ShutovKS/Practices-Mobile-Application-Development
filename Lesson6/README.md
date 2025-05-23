# Отчёт по практике 5

## Описание выполненных заданий

### 1. Shared Preferences

Создан интерфейс с тремя полями ввода и кнопкой сохранения информации:

* Номер группы
* Номер по списку
* Название любимого фильма

Реализовано сохранение данных через getSharedPreferences и их последующая загрузка при запуске приложения.

Создана директория raw, в которой размещён скриншот экрана со значениями файла настроек.

### 2. Encrypted Shared Preferences

Создан новый модуль SecureSharedPreferences, отображающий имя любимого поэта и его изображение.

Для безопасного хранения данных использован класс EncryptedSharedPreferences из библиотеки Jetpack Security Crypto. Реализовано создание мастер-ключа (MasterKeys.AES256_GCM_SPEC) и шифрование данных AES256.

Cкриншот сохранен в директории raw.

### 3. Запись во внутреннее хранилище

Создан модуль InternalFileStorage.

На экране размещены поле ввода и кнопка. Реализована запись памятной даты и её описания во внутреннее хранилище.

Файлы перенесены с устройства в директорию проекта raw.

### 4. Запись файлов во внешнее хранилище

Создан модуль Notebook.

Разработано приложение «Блокнот» с полями ввода для названия файла и цитаты, с кнопками «сохранить» и «загрузить». Данные записываются в общедоступную директорию Documents с перезаписью существующих файлов.

Созданы два файла с цитатами известных личностей, перенесённые в директорию проекта raw.

### 5. SQLite, Room

Создан модуль EmployeeDB, содержащий базу данных о супергероях с помощью библиотеки Room:

* Создан класс-сущность для таблицы.
* Реализован интерфейс DAO с методами CRUD.
* Разработан класс базы данных.
* Создан класс Application с реализацией Singleton-паттерна для инициализации БД.

Примерные данные успешно внесены и извлечены из БД.

### 6. Mirea project

Создан экран профиля пользователя с сохранением параметров в SharedPreferences.

Для экрана «Работа с файлами» добавлен следующий функционал:

* Пользователь может ввести текст, выбрать действие "кодировать" или "декодировать"
* При нажатии на Floating Action Button появляется диалог, где пользователь вводит текст и выбирает действие.
* Результат отображается на экране.

## Скриншоты

![скриншот](./screenshots/screenshot%20(1).png)
![скриншот](./screenshots/screenshot%20(2).png)
![скриншот](./screenshots/screenshot%20(3).png)
![скриншот](./screenshots/screenshot%20(4).png)
![скриншот](./screenshots/screenshot%20(5).png)
![скриншот](./screenshots/screenshot%20(6).png)
![скриншот](./screenshots/screenshot%20(7).png)
![скриншот](./screenshots/screenshot%20(8).png)
![скриншот](./screenshots/screenshot%20(9).png)
