package cpe121.karlvince.lab5_3;

import static kvx.cli.Std.*;
import static kvx.cli.Util.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class REYES_LE_5_3 {
    
    private static final int LINE_LENGTH = 70;
    
    public static void main(String[] args) throws IOException {
        clear();
        
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        
        format("bold", "bright_cyan");
        drawLine(LINE_LENGTH, '═');
        print("                  GRADE DISTRIBUTION SYSTEM");
        newl();
        drawLine(LINE_LENGTH, '═');
        format("reset");
        newl(2);
        
        GradeDistribution distribution = new GradeDistribution();
        
        while (true) {
            try {
                format("bold");
                print("Grade Distribution Menu:");
                format("reset");
                newl();
                drawLine(40, '─');
                print("1. Enter grade counts");
                newl();
                print("2. Display distribution graph");
                newl();
                print("3. Display statistics");
                newl();
                print("4. Clear all data");
                newl();
                print("5. Exit");
                newl();
                drawLine(40, '─');
                newl();
                
                print("Enter your choice (1-5): ");
                int choice = Integer.parseInt(in.readLine());
                
                switch (choice) {
                    case 1:
                        clear();
                        distribution.inputGradeCounts(in);
                        break;
                    case 2:
                        clear();
                        distribution.drawGraph();
                        waitForEnter(in);
                        break;
                    case 3:
                        clear();
                        distribution.displayStatistics();
                        waitForEnter(in);
                        break;
                    case 4:
                        distribution.clearData();
                        format("green");
                        print("All data cleared!");
                        format("reset");
                        newl(2);
                        break;
                    case 5:
                        return;
                    default:
                        format("red");
                        print("Invalid choice! Please enter 1-5.");
                        format("reset");
                        newl(2);
                }
                
            } catch (NumberFormatException e) {
                format("red");
                print("[Error] Please enter a valid number.");
                format("reset");
                newl(2);
            } catch (Exception e) {
                format("red");
                print("[Error] An unexpected error occurred: %s", e.getMessage());
                format("reset");
                newl(2);
            }
        }
    }
    
    private static void waitForEnter(BufferedReader in) throws IOException {
        newl();
        format("yellow");
        print("Press Enter to continue...");
        format("reset");
        in.readLine();
        clear();
    }
}

class GradeDistribution {
    private int countA;
    private int countB;
    private int countC;
    private int countD;
    private int countF;
    
    private static final int PERCENT_PER_ASTERISK = 2;
    
    public GradeDistribution() {
        this.countA = 0;
        this.countB = 0;
        this.countC = 0;
        this.countD = 0;
        this.countF = 0;
    }
    
    public void inputGradeCounts(BufferedReader in) throws IOException {
        format("bold");
        print("Enter Grade Counts:");
        format("reset");
        newl();
        drawLine(40, '─');
        newl();
        
        this.countA = getValidCount(in, "Number of A grades");
        this.countB = getValidCount(in, "Number of B grades");
        this.countC = getValidCount(in, "Number of C grades");
        this.countD = getValidCount(in, "Number of D grades");
        this.countF = getValidCount(in, "Number of F grades");
        
        newl();
        format("green");
        print("Grade counts updated successfully!");
        format("reset");
        newl(2);
    }
    
    private int getValidCount(BufferedReader in, String prompt) throws IOException {
        int count;
        while (true) {
            try {
                print("%s: ", prompt);
                count = Integer.parseInt(in.readLine());
                
                if (count < 0) {
                    format("red");
                    print("Error: Count cannot be negative.");
                    format("reset");
                    newl();
                    continue;
                }
                break;
            } catch (NumberFormatException e) {
                format("red");
                print("Error: Please enter a valid whole number.");
                format("reset");
                newl();
            }
        }
        return count;
    }
    
    public void drawGraph() {
        if (getTotalGrades() == 0) {
            format("yellow");
            print("No grade data available. Please enter grade counts first.");
            format("reset");
            newl();
            return;
        }
        
        newl();
        format("bold");
        drawLine(70, '═');
        print("                     GRADE DISTRIBUTION GRAPH");
        newl();
        drawLine(70, '═');
        format("reset");
        newl();
        
        print("    0   10   20   30   40   50   60   70   80   90  100");
        newl();
        print("    |    |    |    |    |    |    |    |    |    |    |");
        newl();
        drawLine(70, '─');
        newl();
        
        drawGradeLine('A', countA, "bright_green");
        drawGradeLine('B', countB, "green");
        drawGradeLine('C', countC, "yellow");
        drawGradeLine('D', countD, "red");
        drawGradeLine('F', countF, "bright_red");
        
        drawLine(70, '─');
        newl();
    }
    
    private void drawGradeLine(char grade, int count, String color) {
        int percentage = getPercentOfGrade(grade);
        int asterisks = (int) Math.round((double) percentage / PERCENT_PER_ASTERISK);
        
        format(color);
        print("%c ", grade);
        format("reset");
        
        for (int i = 0; i < asterisks; i++) {
            format(color);
            print("*");
            format("reset");
        }
        
        format("bold");
        print(" (%d%% - %d students)", percentage, count);
        format("reset");
        newl();
    }
    
    public void displayStatistics() {
        newl();
        format("bold");
        drawLine(50, '═');
        print("              GRADE STATISTICS");
        newl();
        drawLine(50, '═');
        format("reset");
        newl();
        
        int total = getTotalGrades();
        
        if (total == 0) {
            format("yellow");
            print("No grade data available.");
            format("reset");
            newl();
            return;
        }
        
        print("%-15s %10s %15s", "Grade", "Count", "Percentage");
        newl();
        drawLine(50, '─');
        newl();
        
        displayGradeStatistic('A', countA);
        displayGradeStatistic('B', countB);
        displayGradeStatistic('C', countC);
        displayGradeStatistic('D', countD);
        displayGradeStatistic('F', countF);
        
        drawLine(50, '─');
        format("bold");
        print("%-15s %10d %15s", "TOTAL", total, "100%");
        format("reset");
        newl();
        drawLine(50, '═');
    }
    
    private void displayGradeStatistic(char grade, int count) {
        int percentage = getPercentOfGrade(grade);
        
        String color = "";
        switch (grade) {
            case 'A': color = "bright_green"; break;
            case 'B': color = "green"; break;
            case 'C': color = "yellow"; break;
            case 'D': color = "red"; break;
            case 'F': color = "bright_red"; break;
        }
        
        format(color);
        print("%-15c %10d %15d%%", grade, count, percentage);
        format("reset");
        newl();
    }
    
    public int getCountA() {
        return countA;
    }
    
    public int getCountB() {
        return countB;
    }
    
    public int getCountC() {
        return countC;
    }
    
    public int getCountD() {
        return countD;
    }
    
    public int getCountF() {
        return countF;
    }
    
    public int getTotalGrades() {
        return countA + countB + countC + countD + countF;
    }
    
    public int getPercentOfGrade(char grade) {
        int total = getTotalGrades();
        if (total == 0) return 0;
        
        int count = 0;
        switch (grade) {
            case 'A': count = countA; break;
            case 'B': count = countB; break;
            case 'C': count = countC; break;
            case 'D': count = countD; break;
            case 'F': count = countF; break;
        }
        
        return (int) Math.round((double) count / total * 100);
    }
    
    public void setCountA(int countA) {
        if (countA >= 0) this.countA = countA;
    }
    
    public void setCountB(int countB) {
        if (countB >= 0) this.countB = countB;
    }
    
    public void setCountC(int countC) {
        if (countC >= 0) this.countC = countC;
    }
    
    public void setCountD(int countD) {
        if (countD >= 0) this.countD = countD;
    }
    
    public void setCountF(int countF) {
        if (countF >= 0) this.countF = countF;
    }
    
    public void clearData() {
        this.countA = 0;
        this.countB = 0;
        this.countC = 0;
        this.countD = 0;
        this.countF = 0;
    }
}
