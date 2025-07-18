package ru.nesthcher.utils.logger.api;

import org.jetbrains.annotations.NotNull;
import ru.nesthcher.utils.EnumUtil;

public enum LogType {
    INFORMATION, WARNING, ERROR;

    public static LogType getByName(
            @NotNull String name
    ) {
        return EnumUtil.getType(LogType.class, name, INFORMATION);
    }
}
