package ru.nesthcher.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import org.jetbrains.annotations.NotNull;

import lombok.Cleanup;
import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;

/**
 * Утилитный класс для работы с файлами и директориями.
 */
@UtilityClass
public class FileUtil {
    /**
     * Копирует содержимое директории sourceDirectory в директорию destinationDirectory.
     * @param sourceDirectory путь к исходной директории
     * @param destinationDirectory путь к целевой директории
     */
    @SneakyThrows
    public void copyDirectory(@NotNull final String sourceDirectory, @NotNull final String destinationDirectory) {
        Files.walk(Paths.get(sourceDirectory)).forEach(source -> {
            Path destination = Paths.get(destinationDirectory, source.toString().substring(sourceDirectory.length()));
            try {
                Files.copy(source, destination);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    /**
     * Копирует файл var в файл var2.
     * @param fileInput исходный файл
     * @param fileOutput целевой файл
     */
    @SneakyThrows
    public void copy(@NotNull final File fileInput, @NotNull final File fileOutput) {
        @Cleanup InputStream is = null;
        @Cleanup OutputStream os = null;

        is = new FileInputStream(fileInput);
        os = new FileOutputStream(fileOutput);
        byte[] buffer = new byte[1024];
        int length;
        while ((length = is.read(buffer)) > 0) {
            os.write(buffer, 0, length);
        }

    }

    /**
     * Создаёт файл, включая необходимые родительские директории.
     * @param file файл для создания
     */
    @SneakyThrows
    public void createFile(@NotNull final File file) {
        if (file.exists()) return;
        final File parentFile = file.getParentFile();
        if (!parentFile.exists()) parentFile.mkdirs();
        file.createNewFile();
    }

    /**
     * Удаляет файл или директорию рекурсивно.
     * @param file файл или директория для удаления
     */
    public void delete(@NotNull final File file) {
        if (!file.exists()) return;
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            if (files != null) for (File f : files)
                delete(f);
            file.delete();
        } else {
            file.delete();
        }
    }

    /**
     * Распаковывает zip-архив в указанную директорию.
     * @param zip zip-файл
     * @param dir директория для распаковки
     */
    @SneakyThrows
    public void unzip(@NotNull final File zip, @NotNull final File dir) {
        byte[] buffer = new byte[1024];
        if (!dir.exists()) dir.mkdir();
        @Cleanup ZipInputStream zis = new ZipInputStream(new FileInputStream(zip));
        ZipEntry ze = zis.getNextEntry();
        while (ze != null) {
            File newFile = new File(dir, ze.getName());
            if (!ze.isDirectory()) {
                FileUtil.createFile(newFile);
                FileOutputStream fos = new FileOutputStream(newFile);
                int len;
                while ((len = zis.read(buffer)) > 0) fos.write(buffer, 0, len);
                fos.close();
            }
            ze = zis.getNextEntry();
        }
        zis.closeEntry();
        zis.close();
    }
}
