package org.shift.FileFilterUtility.statistics;

public class StringStatistics implements Statistics{
    private int count = 0;
    private int maxLength = Integer.MIN_VALUE;
    private int minLength = Integer.MAX_VALUE;

    @Override
    public void calculate(String value){
        count++;
        int len = value.length();
        if (len > maxLength) maxLength = len;
        if (len < minLength) minLength = len;
    }

    @Override
    public String getReport(boolean full){
        if (count == 0 )
            return "Строк нет";

        if (full){
            return "Количество строк " + count + ", минимальное длина: " + minLength + ", максимальное длина: "
                    + maxLength;
        } else {
            return "Количество строк " + count;
        }
    }
}
