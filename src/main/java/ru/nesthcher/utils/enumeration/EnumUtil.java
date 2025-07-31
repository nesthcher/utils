package ru.nesthcher.utils.enumeration;

import org.jetbrains.annotations.NotNull;

import lombok.experimental.UtilityClass;

/**
 * Утилитный класс для работы с перечислениями (enum).
 */
@UtilityClass
public class EnumUtil {
    /**
     * Получает значение enum по строковому ключу.
     * @param enumClass Класс перечисления
     * @param key Строковый ключ
     * @param defaultValue Значение по умолчанию
     * @return Значение enum или значение по умолчанию
     */
    public <T extends Enum<T>> T getType(
            @NotNull final Class<T> enumClass,
            @NotNull final String key,
            final T defaultValue
    ) {
        try {
            return Enum.valueOf(enumClass, key.toUpperCase());
        } catch (IllegalArgumentException var2) {
            return defaultValue;
        }
    }
}
