// MIT Copyright (c) 2023 Karl Vince
package cpe121.karlvince.lab3_2;

public class CalculatorModule {

    public final static int DAYS_IN_YEAR = 365;
    public final static int MONTHS_IN_YEAR = 12;
    public final static int YEARS = 10;

    public static double fromPercentage(double number) {
        return number / 100.0;
    }

    public static double toPercentage(double number) {
        return number * 100;
    }

    public static double monthlyInterest(double percent) {

        return percent / 12;
    }

    public static double dailyInterest(double percent) {
        return percent / 365;
    }

    public static double calculateAnnualCompounding(double principal, double annualRate) {
        double balance = principal;

        for (int year = 1; year <= YEARS; year++) {
            balance = balance + (balance * annualRate);
        }
        
        return balance;
    }

    public static double calculateMonthlyCompounding(double principal, double annualRate) {
        double balance = principal;
        double monthlyRate = CalculatorModule.monthlyInterest(annualRate);

        int totalMonths = YEARS * MONTHS_IN_YEAR;
        for (int month = 1; month <= totalMonths; month++) {
            balance = balance + (balance * monthlyRate);
        }
        
        return balance;
    }

    public static double calculateDailyCompounding(double principal, double annualRate) {
        double balance = principal;
        double dailyRate = CalculatorModule.dailyInterest(annualRate);
        
        int totalDays = YEARS * DAYS_IN_YEAR;
        for (int day = 1; day <= totalDays; day++) {
            balance = balance + (balance * dailyRate);
        }
        
        return balance;
    }

}
