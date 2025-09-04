package org.shift.FileFilterUtility;

import com.beust.jcommander.Parameter;

import java.util.ArrayList;
import java.util.List;

public class ArgsFilter {
    @Parameter(names = "-o", description = "Путь для результатов")
    private String outputPath = ".";

    @Parameter(names = "-p", description = "Префикс имен выходных файлов")
    private String prefix = "";

    @Parameter(names = "-s", description = "Краткая статистика")
    private boolean shortStat = false;

    @Parameter(names = "-f", description = "Полная статистика")
    private boolean fullStat = false;

    @Parameter(names = "-a", description = "Режим добавления")
    private boolean append = false;

    @Parameter(description = "Входные файлы")
    private List<String> inputFiles = new ArrayList<>();

    public String getOutputPath() {
        return outputPath;
    }

    public String getPrefix() {
        return prefix;
    }

    public List<String> getInputFiles() {
        return inputFiles;
    }

    public boolean isAppend() {
        return append;
    }

    public boolean isShortStat() {
        return shortStat;
    }

    public boolean isFullStat() {
        return fullStat;
    }

}
