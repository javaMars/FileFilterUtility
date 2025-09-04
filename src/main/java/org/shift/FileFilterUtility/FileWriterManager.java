package org.shift.FileFilterUtility;

import org.shift.FileFilterUtility.statistics.StatisticManager;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileWriterManager implements AutoCloseable{
    private final String prefix;
    private final Path outputDir;
    private final boolean append;

    public FileWriterManager(String outputPath, String prefix, boolean append){
        this.outputDir = Path.of(outputPath);
        this.prefix = prefix;
        this.append = append;
    }

    public void writeNumber(String stringNumber) {
        Path outputNumbersPath;
        if (stringNumber.contains(".") || stringNumber.toLowerCase().contains("e")) {
            outputNumbersPath = outputDir.resolve(prefix + "float.txt");

        } else {
            outputNumbersPath = outputDir.resolve(prefix + "integer.txt");
        }

        createPathAndFile(outputNumbersPath);

        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(outputNumbersPath.toFile(), append))) {
            bufferedWriter.write(stringNumber);
            bufferedWriter.newLine();
        }
        catch (IOException e){
            System.err.println("Ошибка записи числа в файл " + outputNumbersPath + ": " + e.getLocalizedMessage());
        }
    }

    public void writeString(String text) {
        Path outputStringPath = outputDir.resolve(prefix + "strings.txt");
        createPathAndFile(outputStringPath);
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(outputStringPath.toFile(), append))) {
            bufferedWriter.write(text);
            bufferedWriter.newLine();
        }
        catch (IOException e){
            System.err.println("Ошибка записи строки в файл " + outputStringPath + ": " + e.getLocalizedMessage());
        }
    }

    private void createPathAndFile(Path path){
        try {
            Files.createDirectories(path.getParent());

            if (Files.notExists(path)) {
                Files.createFile(path);
            }
        } catch (IOException e) {
            System.err.println("Ошибка при создании файла или путей " + path + ": " + e.getLocalizedMessage());
        }
    }

    @Override
    public void close() throws Exception {
    }
}
