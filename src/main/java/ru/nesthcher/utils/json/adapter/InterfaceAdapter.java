package ru.nesthcher.utils.json.adapter;

import com.google.gson.*;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;

public class InterfaceAdapter<T> implements JsonSerializer<T>, JsonDeserializer<T> {

    private static final Gson delegateGson = new Gson();

    private static boolean isConcrete(
            Type type
    ) {
        if (type instanceof Class<?> clazz) {
            int modifiers = clazz.getModifiers();
            return !Modifier.isAbstract(modifiers) && !Modifier.isInterface(modifiers);
        }
        return false;
    }

    @Override
    public JsonElement serialize(
            T src,
            Type typeOfSrc,
            JsonSerializationContext context
    ) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("type", src.getClass().getName());
        JsonElement propertiesElement = delegateGson.toJsonTree(src, src.getClass());
        jsonObject.add("properties", propertiesElement);
        return jsonObject;
    }

    @Override
    public T deserialize(
            JsonElement json,
            Type typeOfT,
            JsonDeserializationContext context
    ) throws JsonParseException
    {
        if (!json.isJsonObject()) {
            if (isConcrete(typeOfT)) {
                return delegateGson.fromJson(json, typeOfT);
            } else {
                return null;
            }
        }

        JsonObject jsonObject = json.getAsJsonObject();

        if (!jsonObject.has("type") || !jsonObject.has("properties")) {
            throw new JsonParseException("JsonObject missing 'type' or 'properties' field. Json: " + jsonObject);
        }

        String className = jsonObject.get("type").getAsString();
        JsonElement properties = jsonObject.get("properties");

        try {
            Class<?> clazz = Class.forName(className);
            return delegateGson.fromJson(properties, (Type) clazz);
        } catch (ClassNotFoundException e) {
            throw new JsonParseException("Unknown class: " + className, e);
        }
    }
}