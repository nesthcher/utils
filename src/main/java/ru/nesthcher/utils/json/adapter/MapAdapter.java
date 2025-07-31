package ru.nesthcher.utils.json.adapter;

import com.google.gson.*;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

public class MapAdapter implements JsonSerializer<Map<?, ?>>, JsonDeserializer<Map<?, ?>> {

    @Override
    public JsonElement serialize(
            Map<?, ?> src,
            Type typeOfSrc,
            JsonSerializationContext context
    ) {
        JsonObject jsonObject = new JsonObject();

        if (src == null) {
            return jsonObject;
        }

        for (Map.Entry<?, ?> entry : src.entrySet()) {
            Object key = entry.getKey();
            Object value = entry.getValue();

            String keyString;
            if (key == null) {
                keyString = "null";
            } else {
                keyString = key.toString();
            }

            JsonElement valueElement = context.serialize(value);
            jsonObject.add(keyString, valueElement);
        }

        return jsonObject;
    }

    @Override
    public Map<?, ?> deserialize(
            JsonElement json,
            Type typeOfT,
            JsonDeserializationContext context
    ) throws JsonParseException
    {
        if (!json.isJsonObject()) {
            throw new JsonParseException("Expected JsonObject but was " + json.getClass().getSimpleName());
        }

        JsonObject jsonObject = json.getAsJsonObject();
        Map<Object, Object> map = new HashMap<>();

        if (jsonObject.isEmpty()) {
            return map;
        }

        Type keyType = getKeyType(typeOfT);
        Type valueType = getValueType(typeOfT);

        for (Map.Entry<String, JsonElement> entry : jsonObject.entrySet()) {
            String keyString = entry.getKey();
            JsonElement valueElement = entry.getValue();

            Object key = deserializeKey(keyString, keyType, context);
            Object value = context.deserialize(valueElement, valueType);

            map.put(key, value);
        }

        return map;
    }

    private Type getKeyType(
            Type type
    ) {
        if (type instanceof ParameterizedType parameterizedType) {
            Type[] typeArguments = parameterizedType.getActualTypeArguments();
            if (typeArguments.length >= 2) {
                return typeArguments[0];
            }
        }
        return Object.class;
    }

    private Type getValueType(
            Type type
    ) {
        if (type instanceof ParameterizedType parameterizedType) {
            Type[] typeArguments = parameterizedType.getActualTypeArguments();
            if (typeArguments.length >= 2) {
                return typeArguments[1];
            }
        }
        return Object.class;
    }

    private Object deserializeKey(
            String keyString,
            Type keyType,
            JsonDeserializationContext context
    ) {
        if ("null".equals(keyString) && keyType != String.class) {
            return null;
        }

        if (keyType == String.class) {
            return keyString;
        } else if (keyType == Integer.class || keyType == int.class) {
            return Integer.valueOf(keyString);
        } else if (keyType == Long.class || keyType == long.class) {
            return Long.valueOf(keyString);
        } else if (keyType == Double.class || keyType == double.class) {
            return Double.valueOf(keyString);
        } else if (keyType == Float.class || keyType == float.class) {
            return Float.valueOf(keyString);
        } else if (keyType == Boolean.class || keyType == boolean.class) {
            return Boolean.valueOf(keyString);
        } else {
            try {
                JsonElement keyElement = JsonParser.parseString(keyString);
                return context.deserialize(keyElement, keyType);
            } catch (Exception e) {
                return keyString;
            }
        }
    }
}