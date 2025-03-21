# Тестовые задания Nexign Bootcamp 2025

## 📌 Описание проекта
Этот проект представляет собой автоматизированные тесты сайта [Nexign](https://nexign.com/ru/) с использованием Selenium WebDriver, JUnit и Яндекс Спеллер API.

## 📝 Основные классы
1. [NavigationTest](https://github.com/msnchk/testing-nexign/blob/main/src/test/java/com/github/msnchk/tests/navigation/NavigationTest.java) – осуществляет переход в раздел "Nexign Nord" с главной страницы с помощью кликов.
2. [SearchWordTest](https://github.com/msnchk/testing-nexign/blob/main/src/test/java/com/github/msnchk/tests/search/SearchWordTest.java) – подсчитывает количество упоминаний слова "Nexign" в контенте главной страницы.
3. [SpellingTest](https://github.com/msnchk/testing-nexign/blob/main/src/test/java/com/github/msnchk/tests/spelling/SpellingTest.java) – переходит по всем внутренним ссылкам главной страницы и проверяет орфографию с помощью сервиса Яндекс Спеллер.

## 🚀Технологии
- Java 17
- Selenium WebDriver
- JUnit
- Maven
- RestAssured
- Яндекс Спеллер
- SLF4J + Logback