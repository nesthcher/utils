package ru.nesthcher.utils.converter;

import lombok.experimental.UtilityClass;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

@UtilityClass
public class PrimitiveConverter {
    private final Map<Class<?>, Class<?>> PRIMITIVE_TO_WRAPPER = new HashMap<>();
    static {
        PRIMITIVE_TO_WRAPPER.put(Boolean.TYPE, Boolean.class);
        PRIMITIVE_TO_WRAPPER.put(Byte.TYPE, Byte.class);
        PRIMITIVE_TO_WRAPPER.put(Short.TYPE, Short.class);
        PRIMITIVE_TO_WRAPPER.put(Character.TYPE, Character.class);
        PRIMITIVE_TO_WRAPPER.put(Integer.TYPE, Integer.class);
        PRIMITIVE_TO_WRAPPER.put(Long.TYPE, Long.class);
        PRIMITIVE_TO_WRAPPER.put(Float.TYPE, Float.class);
        PRIMITIVE_TO_WRAPPER.put(Double.TYPE, Double.class);
    }

    public <T> T convert(
            @NotNull Object obj,
            @NotNull Class<T> clazz
    ) {
        if ((PRIMITIVE_TO_WRAPPER.getOrDefault(clazz, clazz)).isAssignableFrom(obj.getClass())) {
            return (T) obj;
        } else {
            throw new IllegalArgumentException("Объект " + obj + " не может быть приведён к " + obj.getClass().getSimpleName());
        }
    }
}
