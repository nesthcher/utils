package ru.nesthcher.utils;

import org.jetbrains.annotations.NotNull;

import lombok.experimental.UtilityClass;

/**
 * Утилитный класс для работы со строками.
 */
@UtilityClass
public class StringUtil {

    /**
     * Удаляет указанное количество символов с конца строки.
     * @param str Исходная строка
     * @param count Количество символов для удаления
     * @return Обрезанная строка
     */
    public String subLastString(
            @NotNull final String str,
            final int count
    ) {
        return str.substring(0, str.length() - count);
    }

    /**
     * Обрезает строку до указанной позиции.
     * @param str Исходная строка
     * @param end Конечная позиция
     * @return Обрезанная строка
     */
    public String subEndString(
            @NotNull final String str,
            final int end
    ) {
        return str.substring(0, end);
    }

    /**
     * Извлекает подстроку из строки по указанным позициям.
     * @param str Исходная строка
     * @param start Начальная позиция
     * @param end Конечная позиция
     * @return Извлеченная подстрока
     */
    public String subString(
            @NotNull final String str,
            final int start,
            final int end
    ) {
        return str.substring(start, end);
    }

    /**
     * Удаляет подстроку из строки по указанным позициям.
     * @param str Исходная строка
     * @param start Начальная позиция
     * @param end Конечная позиция
     * @return Строка с удаленной подстрокой
     */
    public String removeSubstring(
            @NotNull final String str,
            final int start,
            final int end
    ) {
        return str.replace(str.substring(start, end), "");
    }

    /**
     * Возвращает правильную форму слова в зависимости от числа.
     * @param one Форма для одного
     * @param two Форма для двух
     * @param five Форма для пяти
     * @param n Число
     * @return Правильная форма слова
     */
    public String plurals(
            @NotNull final String one,
            @NotNull final String two,
            @NotNull final String five,
            int n
    ) {
        if (n < 0) n = -n;
        return n % 10 == 1 && n % 100 != 11 ?
                one :
                n % 10 >= 2 && n % 10 <= 4 && (n % 100 < 10 || n % 100 >= 20) ?
                        two :
                        five;
    }

    /**
     * Добавляет разделители в строку до достижения указанной длины.
     * @param delimiter Разделитель
     * @param origin Исходная строка
     * @param count Желаемая длина
     * @param cut Обрезать ли строку, если она длиннее
     * @return Строка с добавленными разделителями
     */
    public String addSpaces(
            @NotNull final String delimiter,
            @NotNull String origin,
            final int count,
            final boolean cut
    ) {
        if (!origin.contains(delimiter)) throw new RuntimeException("No " + delimiter + " in " + origin + " string");
        if (cut && origin.length() > count) {
            origin = subLastString(origin, count);
        }
        final StringBuilder sb = new StringBuilder(origin);
        int point = 0;
        while (sb.length() < count) {
            int index = sb.indexOf(delimiter, point);
            if (index == -1) {
                point = 0;
                continue;
            }
            point = index + delimiter.length();
            if (point == sb.indexOf(delimiter, point)) continue;
            sb.replace(point, point, delimiter);
            point += delimiter.length();
        }
        return sb.toString();
    }

    /**
     * Заменяет все вхождения подстроки в строке.
     * @param findTxt Искомая подстрока
     * @param replaceTxt Замещающая подстрока
     * @param str Исходная строка
     * @param isCaseInsensitive Учитывать ли регистр
     * @return Строка с замененными подстроками
     */
    public String replaceAll(
            @NotNull final String findTxt,
            @NotNull final String replaceTxt,
            @NotNull String str,
            final boolean isCaseInsensitive
    ) {
        if (findTxt.length() > str.length()) return str;
        int counter = 0;
        String thesubstr;
        while ((counter < str.length()) && (str.substring(counter).length() >= findTxt.length())) {
            thesubstr = str.substring(counter, counter + findTxt.length());
            if (isCaseInsensitive) {
                if (thesubstr.equalsIgnoreCase(findTxt)) {
                    str = str.substring(0, counter) + replaceTxt + str.substring(counter + findTxt.length());
                    counter += replaceTxt.length();
                } else {
                    counter++;
                }
            } else {
                if (thesubstr.equals(findTxt)) {
                    str = str.substring(0, counter) + replaceTxt + str.substring(counter + findTxt.length());
                    counter += replaceTxt.length();
                } else {
                    counter++;
                }
            }
        }
        return str;
    }

    /**
     * Генерирует строку прогресс-бара.
     * @param length Длина бара
     * @param progress Прогресс (от 0 до 1)
     * @param c Символ для заполнения
     * @param background Строка фона
     * @param filled Строка заполнения
     * @return Строка прогресс-бара
     */
    public String genBar(
            final int length,
            final float progress,
            final char c,
            @NotNull final String background,
            @NotNull final String filled
    ) {
        final StringBuilder sb = new StringBuilder(length + 4);
        sb.append(filled);
        boolean filled0 = false;
        for (int i = 0; i < length; ++i) {
            if (!filled0 && (float) length * progress <= (float) i) {
                sb.append(background);
                filled0 = true;
            }
            sb.append(c);
        }
        return sb.toString();
    }
}
