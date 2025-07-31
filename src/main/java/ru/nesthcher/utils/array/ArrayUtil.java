package ru.nesthcher.utils.array;

import java.util.ArrayList;
import java.util.List;

import org.jetbrains.annotations.NotNull;

import lombok.experimental.UtilityClass;

/**
 * Утилитный класс для работы с массивами и списками.
 */
@UtilityClass
public class ArrayUtil {
    /**
     * Конвертирует объект в список указанного типа.
     * @param object Объект для конвертации
     * @param clazz Класс элементов списка
     * @return Список сконвертированных элементов или null в случае неудачи
     */
    public <T> ArrayList<T> convertObjectToList(
            @NotNull final Object object,
            @NotNull final Class<T> clazz
    ) {
        if (!(object instanceof ArrayList<?>)) return null;
        final ArrayList<T> list = new ArrayList<>();
        for (final Object o : (List<?>) object) list.add(clazz.cast(o));
        return list;
    }
}
