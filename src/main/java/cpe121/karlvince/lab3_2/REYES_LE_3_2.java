package cpe121.karlvince.lab3_2;

import java.io.IOException;

import static kvx.cli.Std.*;
import static kvx.cli.Util.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;

// LAB 3.2
public class REYES_LE_3_2 {

    public final static int DAYS_IN_YEAR = 365;
    public final static int MONTHS_IN_YEAR = 12;
    public final static int YEARS = 10;

    public final static int LINE_LENGHT = 70;

    public static void main (String[] args) throws IOException {
        clear();

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        for (;;) {
            try {
                clear();
                print("Enter bank account balance: ₱");
                String balanceInput = in.readLine();
                
                if (balanceInput.equalsIgnoreCase("quit") || balanceInput.equalsIgnoreCase("exit")) {
                    print("Thank you for using the Bank Account Interest Calculator!");
                    break;
                }
                
                double balance = Double.parseDouble(balanceInput);
                
                if (balance < 0) {
                    print("Error: Balance cannot be negative. Please try again."); newl();
                    continue;
                }

                print("Enter annual interest rate (%): ");
                String rateInput = in.readLine();
                
                if (rateInput.equalsIgnoreCase("quit") || rateInput.equalsIgnoreCase("exit")) {
                    print("Thank you for using the Bank Account Interest Calculator!"); newl();
                    break;
                }
                
                double interestRate = Double.parseDouble(rateInput);
                
                if (interestRate < 0) {
                    print("Error: Interest rate cannot be negative. Please try again."); newl();
                    continue;
                }

                double annualInterestDecimal = CalculatorModule.fromPercentage(interestRate);
                
                double annualBalance = CalculatorModule.calculateAnnualCompounding(balance, annualInterestDecimal);
                double monthlyBalance = CalculatorModule.calculateMonthlyCompounding(balance, annualInterestDecimal);
                double dailyBalance = CalculatorModule.calculateDailyCompounding(balance, annualInterestDecimal);

                displayResults(balance, interestRate, annualBalance, monthlyBalance, dailyBalance);

                print("Calculate for another account? (Y/n): ");
                String continueChoice = in.readLine().trim().toLowerCase();
                
                if (continueChoice.equals("n") || continueChoice.equals("no") || 
                    continueChoice.equals("quit") || continueChoice.equals("exit") || continueChoice.equals("end") || continueChoice.equals("q")) {
                    break;
                }
                
                newl();
                
            } catch (NumberFormatException e) {
                print("[Error] Please enter a valid number. Try again."); newl();
            } catch (Exception e) {
                print("[Error] An error occurred: %s", e.getMessage()); newl();
            }
        }
    }


    public static void displayResults(double initialBalance, double interestRate, 
                                    double annualBalance, double monthlyBalance, double dailyBalance) {
        newl();
        format("bold");
        drawLine(LINE_LENGHT, '─');
        print("                    INTEREST CALCULATION RESULTS"); newl();
        drawLine(LINE_LENGHT, '─');
        format("reset");
        newl();
        
        print("Initial Balance:         ₱%.2f", initialBalance); newl();
        print("Annual Interest Rate:    %.2f%%", interestRate); newl();
        print("Time Period:             %d years", YEARS); newl();
        newl();
        
        format("bold");
        drawLine(LINE_LENGHT, '─'); newl();
        print("%-15s %20s %20s", "Compounding Method", "Final Balance", "Total Interest"); newl();
        drawLine(LINE_LENGHT, '─'); newl();
        format("reset");
        
        print("%-25s ₱%10.2f         ₱%10.2f", "Annual  (1x/year)", annualBalance, (annualBalance - initialBalance)); newl();
        print("%-25s ₱%10.2f         ₱%10.2f", "Monthly (12x/year)", monthlyBalance, (monthlyBalance - initialBalance)); newl();
        print("%-25s ₱%10.2f         ₱%10.2f", "Daily   (365x/year)", dailyBalance, (dailyBalance - initialBalance)); newl();
        
        format("bold"); drawLine(LINE_LENGHT, '─'); format("reset"); newl();
        
        newl();
    }

}
