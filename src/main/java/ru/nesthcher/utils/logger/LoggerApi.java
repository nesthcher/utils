package ru.nesthcher.utils.logger;

import lombok.Getter;
import org.jetbrains.annotations.NotNull;
import ru.nesthcher.utils.TimeUtil;
import ru.nesthcher.utils.logger.api.LogType;

@Getter
public final class LoggerApi implements AbstractLoggerApi {
    private final AbstractLogger logger;

    public LoggerApi(
            @NotNull AbstractLogger logger
    ) {
        this.logger = logger;
    }

    public void log(
            @NotNull Class<?> clazz,
            @NotNull LogType type,
            @NotNull String message
    ) {
        logger.log(logMessage(clazz, type, TimeUtil.getMillis(), message));
    }

    public void log(
            @NotNull Class<?> clazz,
            @NotNull String message
    ) {
        log(clazz, LogType.INFORMATION, message);
    }
}
