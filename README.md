# Лабораторная работа № 2. Коллекции и компараторы в Java

### Студент Дабагян Кристина
### Группа: ФИТ-251

## Описание
Лабораторная работа посвящена работе с коллекциями Java: методам класса `Collections`,
перебору через `Iterator`, интерфейсам `Comparable` и `Comparator`, множествам
(`HashSet`, `LinkedHashSet`, `TreeSet`) и подсчёту данных через `HashMap`.

## Структура проекта

- src/
- ├── Collections/Task1_Collections.java          — Задание № 1
- ├── PrimesGenerator/PrimesGenerator.java        — Задание № 2 (генератор)
- ├── PrimesGenerator/PrimesGeneratorTest.java    — Задание № 2 (тест)
- ├── Human/Human.java                            — Задание № 3 (модель)
- ├── Human/HumanComporatorByLastName.java        — Задание № 3 (компаратор)
- ├── Human/HumanTest.java                        — Задание № 3 (тест)
- ├── WordFrequency/WordFrequency4.java           — Задание № 4
- └── MapInverter/MapInverter5.java               — Задание № 5

## Соответствие заданий и классов

| № | Задание | Классы |
|---|---|---|
| 1 | Методы `Collections` | `Task1_Collections` |
| 2 | Генератор простых чисел | `PrimesGenerator`, `PrimesGeneratorTest` |
| 3 | Множества и сравнение объектов | `Human`, `HumanComporatorByLastName`, `HumanTest` |
| 4 | Частота слов | `WordFrequency4` |
| 5 | Обмен ключей и значений | `MapInverter5` |

## Как запустить

```bash
javac src/**/*.java
java Collections.Task1_Collections
java PrimesGenerator.PrimesGeneratorTest
java Human.HumanTest
java WordFrequency.WordFrequency4
java MapInverter.MapInverter5