package org.shift.FileFilterUtility.statistics;

public interface Statistics {

    void calculate(String value);
    String getReport(boolean full);
}
