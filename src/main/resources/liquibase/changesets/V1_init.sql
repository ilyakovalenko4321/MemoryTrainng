-- Шаг 1. Создание таблицы, если её ещё нет
CREATE TABLE IF NOT EXISTS words
(
    id   BIGSERIAL PRIMARY KEY, -- Автоматическая генерация ID
    word VARCHAR(255) NOT NULL  -- Колонка для слова
);

-- Шаг 2. Очистка таблицы перед загрузкой (опционально)
TRUNCATE TABLE words;

-- Шаг 3. Загрузка данных из файла (ид автоматически генерируется)
INSERT INTO words
VALUES (0, 'проверка'),
       (1, 'фывап');

DELETE
FROM words w
WHERE LENGTH(w.word) NOT BETWEEN 5 AND 8