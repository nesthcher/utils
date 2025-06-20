# Utils Library

Библиотека утилитных классов для Java-проектов.

> [!IMPORTANT]\
> Данный проект создается исключительно в целях проверки и улучшения навыков. Его использование в продакшен-версиях не рекомендуется, так как код может содержать нестабильные или неоптимизированные решения. Если у вас есть время и желание помочь в развитии проекта, я буду рад любым замечаниям и критике. Все идеи и предложения по улучшению приветствуются — они помогут сделать проект лучше!

## Оглавление

-   [Обзор](#обзор)
-   [Функции](#функции)
    -   [StringUtil](#stringutil)
    -   [ArrayUtil](#arrayutil)
    -   [EnumUtil](#enumutil)
    -   [Контейнеры](#контейнеры)
    -   [PatternUtil](#patternutil)

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

### PatternUtil

Утилитный класс для работы с регулярными выражениями и шаблонами.

```java
// Проверка строки на соответствие URL
boolean valid = PatternUtil.isUrl("https://example.com"); // true

// Email
boolean email = PatternUtil.isEmail("test@mail.com"); // true

// Телефон
boolean phone = PatternUtil.isPhone("+79991234567"); // true

// Username
boolean username = PatternUtil.isUsername("user_name"); // true

// IPv4
boolean ipv4 = PatternUtil.isIPv4("192.168.1.1"); // true

// Hex-цвет
boolean hex = PatternUtil.isHexColor("#FFAABB"); // true

// UUID
boolean uuid = PatternUtil.isUUID("123e4567-e89b-12d3-a456-426614174000"); // true

// Дата (yyyy-MM-dd)
boolean date = PatternUtil.isDate("2024-05-01"); // true

// Время (HH:mm:ss)
boolean time = PatternUtil.isTime("12:34:56"); // true

// Число
boolean number = PatternUtil.isNumber("-123.45"); // true

// MAC-адрес
boolean mac = PatternUtil.isMac("00:1A:2B:3C:4D:5E"); // true

// Base64
boolean base64 = PatternUtil.isBase64("SGVsbG8gd29ybGQ="); // true

// Пустая или пробельная строка
boolean blank = PatternUtil.isBlank("   "); // true

// Только русские буквы
boolean onlyRussian = PatternUtil.isRussian("Привет"); // true

// Только английские буквы
boolean onlyEnglish = PatternUtil.isEnglish("Hello"); // true

// Только русские и английские буквы
boolean ruEn = PatternUtil.isRussianEnglish("HelloПривет"); // true

// Только русские буквы и цифры
boolean ruDigits = PatternUtil.isRussianWithDigits("Тест123"); // true

// Только английские буквы и цифры
boolean enDigits = PatternUtil.isEnglishWithDigits("Test123"); // true

// Только русские, английские буквы и цифры
boolean ruEnDigits = PatternUtil.isRussianEnglishWithDigits("TestТест123"); // true
```
