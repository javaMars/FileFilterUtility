package org.shift.FileFilterUtility.statistics;

import java.math.BigInteger;

public class IntegerStatistics implements Statistics{
    private int count = 0;
    private BigInteger  sum = BigInteger.ZERO;
    private BigInteger  max = BigInteger.valueOf(Long.MIN_VALUE);
    private BigInteger  min = BigInteger.valueOf(Long.MAX_VALUE);

    @Override
    public void calculate(String stringValue){
        BigInteger value = new BigInteger(stringValue);
        count++;
        sum.add(value);
        if (value.compareTo(max) > 0) max = value;
        if (value.compareTo(min) < 0 ) min = value;
    }

    @Override
    public String getReport(boolean full){
        if (count == 0 )
            return "Целых чисел нет";

        if (full){
            return "Количество целых чисел " + count + ", минимальное значение: " + min + ", максимальное значение: "
                    + max + ", сумма: " + sum + ", среднее " + sum.divide(BigInteger.valueOf(count));
        } else {
            return "Количество целых чисел " + count;
        }
    }
}
