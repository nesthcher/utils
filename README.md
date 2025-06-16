# Utils Library

Библиотека утилитных классов для Java-проектов.

## Оглавление

-   [Обзор](#обзор)
-   [Функции](#функции)
    -   [StringUtil](#stringutil)
    -   [ArrayUtil](#arrayutil)
    -   [EnumUtil](#enumutil)
    -   [Контейнеры](#контейнеры)

## Обзор

Библиотека предоставляет набор утилитных классов для упрощения часто используемых операций в Java-проектах:

-   Работа со строками
-   Работа с массивами и списками
-   Работа с enum
-   Контейнеры для хранения пар и троек значений

## Функции

### StringUtil

Утилитный класс для работы со строками.

```java
// Удаление символов с конца строки
String result = StringUtil.subLastString("Hello World", 3); // "Hello Wo"

// Форматирование множественных форм
String word = StringUtil.plurals("день", "дня", "дней", 5); // "дней"

// Генерация прогресс-бара
String bar = StringUtil.genBar(10, 0.7f, '#', "░", "█"); // "███████░░░"

// Замена подстроки без учета регистра
String replaced = StringUtil.replaceAll("hello", "bye", "Hello World!", true); // "bye World!"
```

### ArrayUtil

Утилитный класс для работы с массивами и списками.

```java
Object obj = Arrays.asList("1", "2", "3");
List<String> strings = ArrayUtil.convertObjectToList(obj, String.class);
```

### EnumUtil

Утилитный класс для работы с перечислениями.

```java
enum Color { RED, GREEN, BLUE }

Color color = EnumUtil.getType(Color.class, "red", Color.BLUE); // Color.RED
Color invalid = EnumUtil.getType(Color.class, "YELLOW", Color.BLUE); // Color.BLUE
```

### Контейнеры

#### Pair

Контейнер для хранения пары значений.

```java
Pair<String, Integer> pair = new Pair<>("Age", 25);
String key = pair.getFirst(); // "Age"
int value = pair.getSecond(); // 25
boolean empty = pair.isEmpty(); // false
```

#### Triplet

Контейнер для хранения тройки значений.

```java
Triplet<String, Integer, Boolean> triplet = new Triplet<>("Name", 30, true);
String name = triplet.getFirst(); // "Name"
int age = triplet.getSecond(); // 30
boolean status = triplet.getThree(); // true
boolean empty = triplet.isEmpty(); // false
```
