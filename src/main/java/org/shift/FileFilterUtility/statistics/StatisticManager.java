package org.shift.FileFilterUtility.statistics;

public class StatisticManager {
    private final Statistics integerStatistics = new IntegerStatistics();
    private final Statistics floatStatistics = new FloatStatistics();
    private final Statistics stringStatistics = new StringStatistics();

    public void calculateNumber(String stringNumb){
        if (stringNumb.contains(".") || stringNumb.toLowerCase().contains("e")) {
            floatStatistics.calculate(stringNumb);
        } else {
            integerStatistics.calculate(stringNumb);
        }
    }

    public void calculateString(String value){
        stringStatistics.calculate(value);
    }

    public void printReports(boolean full)
    {
        System.out.println(integerStatistics.getReport(full));
        System.out.println(floatStatistics.getReport(full));
        System.out.println(stringStatistics.getReport(full));
    }
}
