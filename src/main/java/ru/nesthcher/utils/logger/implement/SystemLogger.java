package ru.nesthcher.utils.logger.implement;

import org.jetbrains.annotations.NotNull;
import ru.nesthcher.utils.logger.AbstractLogger;

public final class SystemLogger implements AbstractLogger {
    @Override
    public void log(
            @NotNull String message
    ) {
        System.out.println(message);
    }
}
