package ru.nesthcher.utils;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import com.google.gson.stream.MalformedJsonException;
import lombok.experimental.UtilityClass;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;

import static com.google.gson.stream.JsonToken.END_DOCUMENT;

@UtilityClass
public class JsonUtil {
    public final Gson GSON = new Gson();

    public <T> T fromJson(
            @NotNull final String json,
            @NotNull final Class<T> clazz
    ) {
        try {
            if(!isJsonValid(json)) return null;
            return GSON.fromJson(json, clazz);
        } catch (IOException ignore) {
        }
        return null;
    }

    public String toJson(
            @NotNull final Object object
    ) {
        return GSON.toJson(object);
    }

    public <T> String toJson(
            @NotNull final Object object,
            @NotNull final Class<T> clazz
    ) {
        return GSON.toJson(object, clazz);
    }

    public <T> void toJson(
            @NotNull final Object object,
            @NotNull final Class<T> clazz,
            @NotNull final JsonWriter jsonWriter
    ) {
        GSON.toJson(object, clazz, jsonWriter);
    }

    public boolean isJsonValid(
            final String json
    ) throws IOException {
        return isJsonValid(new StringReader(json));
    }

    public boolean isJsonValid(
            final Reader reader
    ) throws IOException {
        return isJsonValid(new JsonReader(reader));
    }

    public boolean isJsonValid(
            final JsonReader jsonReader
    ) throws IOException {
        try {
            JsonToken token;
            loop:
            while ((token = jsonReader.peek()) != END_DOCUMENT && token != null) {
                switch (token) {
                    case BEGIN_ARRAY:
                        jsonReader.beginArray();
                        break;
                    case END_ARRAY:
                        jsonReader.endArray();
                        break;
                    case BEGIN_OBJECT:
                        jsonReader.beginObject();
                        break;
                    case END_OBJECT:
                        jsonReader.endObject();
                        break;
                    case NAME:
                        jsonReader.nextName();
                        break;
                    case STRING:
                    case NUMBER:
                    case BOOLEAN:
                    case NULL:
                        jsonReader.skipValue();
                        break;
                    case END_DOCUMENT:
                        break loop;
                    default:
                        throw new AssertionError(token);
                }
            }
            return true;
        } catch (final MalformedJsonException ignored) {
            return false;
        }
    }
}
