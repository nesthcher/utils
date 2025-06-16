package ru.nesthcher.utils.container;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Класс-контейнер для хранения трех объектов разных типов.
 * @param <A> Тип первого объекта
 * @param <B> Тип второго объекта
 * @param <C> Тип третьего объекта
 */
@Getter
@Setter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
public class Triplet<A, B, C> {

    /**
     * Первый объект тройки
     */
    private A first;
    /**
     * Второй объект тройки
     */
    private B second;
    /**
     * Третий объект тройки
     */
    private C three;

    /**
     * Проверяет, является ли тройка пустой.
     * Тройка считается пустой, если хотя бы один из объектов равен null.
     * @return true, если тройка пустая, иначе false
     */
    public boolean isEmpty() {
        return first == null || second == null || three == null;
    }
}
