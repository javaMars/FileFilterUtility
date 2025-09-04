package org.shift.FileFilterUtility;

import org.shift.FileFilterUtility.statistics.StatisticManager;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class FileFilterHandler {
    private final List<String> inputFiles;
    private final FileWriterManager fileWriterManager;
    private final StatisticManager statisticManager;

    public FileFilterHandler(List<String> inputFiles, FileWriterManager fileWriterManager, StatisticManager statisticManager){
        this.inputFiles = inputFiles;
        this.fileWriterManager = fileWriterManager;
        this.statisticManager = statisticManager;
    }

    public void HandleFiles(boolean append) {
        for (String inputFile : inputFiles) {
            try (BufferedReader reader = new BufferedReader(new FileReader(inputFile))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    if (line.matches("-?\\d+(\\.\\d+)?([eE][-+]?\\d+)?")) {
                        fileWriterManager.writeNumber(line);
                        statisticManager.calculateNumber(line);
                    } else {
                        fileWriterManager.writeString(line);
                        statisticManager.calculateString(line);
                    }
                }
            } catch (IOException e) {
                System.err.println("Ошибка чтения файла " + inputFile + ": " + e.getLocalizedMessage());
            }
        }
    }
}
