package org.shift.FileFilterUtility.statistics;

public class FloatStatistics implements Statistics{
    private int count = 0;
    private double sum = 0;
    private double max = Double.MIN_VALUE;
    private double min = Integer.MAX_VALUE;

    @Override
    public void calculate(String stringValue){
        double value = Double.parseDouble(stringValue);
        count++;
        sum += value;
        if (value > max) max = value;
        if (value < min) min = value;
    }

    @Override
    public String getReport(boolean full){
        if (count == 0 )
            return "Вещественных чисел нет";

        if (full){
            return "Количество вещественных чисел " + count + ", минимальное значение: " + min + ", максимальное значение: "
                    + max + ", сумма: " + sum + ", среднее " + sum / count;
        } else {
            return "Количество вещественных чисел " + count;
        }
    }
}
