package ru.nesthcher.utils.logger;

import org.jetbrains.annotations.NotNull;
import ru.nesthcher.utils.TimeUtil;
import ru.nesthcher.utils.logger.api.LogType;

public interface AbstractLoggerApi {
    void log(@NotNull Class<?> clazz, @NotNull LogType type, @NotNull String message);

    void log(@NotNull Class<?> clazz, @NotNull String message);

    void setLogger(@NotNull AbstractLogger logger);

    default String logMessage(
            @NotNull Class<?> clazz,
            @NotNull LogType type,
            long timestamp,
            @NotNull String message
    ) {
        return "\n" + type + " Time: " + TimeUtil.toString(timestamp) + "\nClassName: " + clazz.getName() + "\nMessage: " + message + "\n";
    }
}
