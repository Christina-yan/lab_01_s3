# Лабораторная работа № 4: Сборка проекта с Maven и модульное тестирование с JUnit

## Дабагян Кристина Кареновна
## ФИТ-251

### Команды

- Запуск тестов: `./mvnw test`
- Сборка исполняемого JAR: `./mvnw clean package`
- Запуск приложения: `java -jar target/java-labs-1.0-SNAPSHOT.jar`

### Проверка обнаружения ошибки

Для проверки реакции Maven на ошибку в формуле площади прямоугольника
метод `Rectangle.area()` был временно изменён с `width * height` на `width + height`.
Тест `RectangleTest.areaOfThreeByFourRectangleIsTwelve` упал, и сборка
`./mvnw clean package` завершилась с ошибкой. После восстановления формулы
все тесты прошли успешно.
