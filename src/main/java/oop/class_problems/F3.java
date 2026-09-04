package oop.class_problems;

import java.util.Arrays;

class PatientVitals {

    private double[] readings;
    private int count;

    PatientVitals(double[] initialReadings) {

        readings = new double[500];
        count = 0;

        if (initialReadings != null) {

            for (double reading : initialReadings) {
                recordReading(reading);
            }
        }
    }

    void recordReading(double reading) {

        if (reading <= 0 || reading > 45) {
            return;
        }

        if (count < readings.length) {
            readings[count] = reading;
            count++;
        }
    }

    double getAverage() {

        if (count == 0) {
            return 0.0;
        }

        double sum = 0;

        for (int i = 0; i < count; i++) {
            sum += readings[i];
        }

        return sum / count;
    }

    double[] getAllReadings() {

        return Arrays.copyOf(readings, count);
    }
}

public class F3 {

    public static void main(String[] args) {

        PatientVitals vitals =
                new PatientVitals(
                        new double[]{36.5, -2, 37.1}
                );

        System.out.println(
                Arrays.toString(vitals.getAllReadings())
        );

        double[] copy = vitals.getAllReadings();

        copy[0] = 999;

        System.out.println(
                vitals.getAllReadings()[0]
        );

        vitals.recordReading(40.0);
        vitals.recordReading(50.0);

        System.out.println(
                Arrays.toString(vitals.getAllReadings())
        );

        System.out.println(
                "Average: " + vitals.getAverage()
        );
    }
}