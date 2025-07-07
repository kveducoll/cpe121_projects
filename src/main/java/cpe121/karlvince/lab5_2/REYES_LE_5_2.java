package cpe121.karlvince.lab5_2;

import static kvx.cli.Std.*;
import static kvx.cli.Util.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class REYES_LE_5_2 {
    
    private static final int LINE_LENGTH = 70;
    
    public static void main(String[] args) throws IOException {
        clear();
        
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        
        format("bold", "bright_yellow");
        drawLine(LINE_LENGTH, '═');
        print("                    STUDENT GRADING SYSTEM");
        newl();
        drawLine(LINE_LENGTH, '═');
        format("reset");
        newl(2);
        
        while (true) {
            try {
                StudentRecord student = new StudentRecord();
                student.inputStudentData(in);
                student.calculateOverallGrade();
                student.calculateLetterGrade();
                student.displayStudentRecord();
                
                newl();
                format("yellow");
                print("Would you like to grade another student? (Y/n): ");
                format("reset");
                String continueChoice = in.readLine().trim().toLowerCase();
                
                if (continueChoice.equals("n") || continueChoice.equals("no") || 
                    continueChoice.equals("quit") || continueChoice.equals("exit")) {
                    break;
                }
                
                clear();
                
            } catch (NumberFormatException e) {
                format("red");
                print("[Error] Please enter valid numbers only.");
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
}

class StudentRecord {
    private String studentName;
    private double quiz1Score;
    private double quiz2Score;
    private double midtermScore;
    private double finalExamScore;
    private double overallNumericScore;
    private char finalLetterGrade;
    
    private static final double QUIZ_MAX_POINTS = 10.0;
    private static final double EXAM_MAX_POINTS = 100.0;
    private static final double QUIZ_WEIGHT = 0.25;
    private static final double MIDTERM_WEIGHT = 0.25;
    private static final double FINAL_WEIGHT = 0.50;
    
    public StudentRecord() {
        this.studentName = "";
        this.quiz1Score = 0.0;
        this.quiz2Score = 0.0;
        this.midtermScore = 0.0;
        this.finalExamScore = 0.0;
        this.overallNumericScore = 0.0;
        this.finalLetterGrade = 'F';
    }
    
    public void inputStudentData(BufferedReader in) throws IOException {
        format("bold");
        print("Enter Student Information:");
        format("reset");
        newl(2);
        
        print("Student Name: ");
        this.studentName = in.readLine().trim();
        while (this.studentName.isEmpty()) {
            format("red");
            print("Name cannot be empty! Please enter student name: ");
            format("reset");
            this.studentName = in.readLine().trim();
        }
        
        newl();
        format("bold");
        print("Enter Quiz Scores (out of 10 points each):");
        format("reset");
        newl();
        
        this.quiz1Score = getValidScore(in, "Quiz 1 score", QUIZ_MAX_POINTS);
        this.quiz2Score = getValidScore(in, "Quiz 2 score", QUIZ_MAX_POINTS);
        
        newl();
        format("bold");
        print("Enter Exam Scores (out of 100 points each):");
        format("reset");
        newl();
        
        this.midtermScore = getValidScore(in, "Midterm exam score", EXAM_MAX_POINTS);
        this.finalExamScore = getValidScore(in, "Final exam score", EXAM_MAX_POINTS);
    }
    
    private double getValidScore(BufferedReader in, String prompt, double maxPoints) throws IOException {
        double score;
        while (true) {
            try {
                print("%s (0-%.0f): ", prompt, maxPoints);
                score = Double.parseDouble(in.readLine());
                if (score < 0 || score > maxPoints) {
                    format("red");
                    print("Error: Score must be between 0 and %.0f points.", maxPoints);
                    format("reset");
                    newl();
                    continue;
                }
                break;
            } catch (NumberFormatException e) {
                format("red");
                print("Error: Please enter a valid number.");
                format("reset");
                newl();
            }
        }
        return score;
    }
    
    public void calculateOverallGrade() {
        double quiz1Percent = (quiz1Score / QUIZ_MAX_POINTS) * 100;
        double quiz2Percent = (quiz2Score / QUIZ_MAX_POINTS) * 100;
        double averageQuizPercent = (quiz1Percent + quiz2Percent) / 2.0;
        double midtermPercent = (midtermScore / EXAM_MAX_POINTS) * 100;
        double finalPercent = (finalExamScore / EXAM_MAX_POINTS) * 100;
        this.overallNumericScore = (averageQuizPercent * QUIZ_WEIGHT) +
                                 (midtermPercent * MIDTERM_WEIGHT) +
                                 (finalPercent * FINAL_WEIGHT);
    }
    
    public void calculateLetterGrade() {
        if (overallNumericScore >= 90) {
            this.finalLetterGrade = 'A';
        } else if (overallNumericScore >= 80) {
            this.finalLetterGrade = 'B';
        } else if (overallNumericScore >= 70) {
            this.finalLetterGrade = 'C';
        } else if (overallNumericScore >= 60) {
            this.finalLetterGrade = 'D';
        } else {
            this.finalLetterGrade = 'F';
        }
    }
    
    public void displayStudentRecord() {
        clear();
        newl(2);
        format("bold");
        drawLine(70, '═');
        print("                        STUDENT GRADE REPORT");
        newl();
        drawLine(70, '═');
        format("reset");
        newl();
        
        format("bold");
        print("Student Name: ");
        format("reset");
        print("%s", studentName);
        newl(2);
        
        format("bold");
        print("Individual Scores:");
        format("reset");
        newl();
        drawLine(70, '─');
        print("%-20s %15s %15s %15s", "Assessment", "Raw Score", "Max Points", "Percentage");
        newl();
        drawLine(70, '─');
        
        print("%-20s %15.1f %15.0f %15.1f%%", "Quiz 1", quiz1Score, QUIZ_MAX_POINTS, (quiz1Score/QUIZ_MAX_POINTS)*100);
        newl();
        print("%-20s %15.1f %15.0f %15.1f%%", "Quiz 2", quiz2Score, QUIZ_MAX_POINTS, (quiz2Score/QUIZ_MAX_POINTS)*100);
        newl();
        print("%-20s %15.1f %15.0f %15.1f%%", "Midterm Exam", midtermScore, EXAM_MAX_POINTS, (midtermScore/EXAM_MAX_POINTS)*100);
        newl();
        print("%-20s %15.1f %15.0f %15.1f%%", "Final Exam", finalExamScore, EXAM_MAX_POINTS, (finalExamScore/EXAM_MAX_POINTS)*100);
        newl();
        
        drawLine(70, '─');
        newl();
        
        double quizAverage = ((quiz1Score/QUIZ_MAX_POINTS)*100 + (quiz2Score/QUIZ_MAX_POINTS)*100) / 2.0;
        double midtermPercent = (midtermScore/EXAM_MAX_POINTS)*100;
        double finalPercent = (finalExamScore/EXAM_MAX_POINTS)*100;
        
        print("%-25s %10.1f%% × %5.0f%% = %8.2f points", "Quizzes Average", quizAverage, QUIZ_WEIGHT*100, quizAverage*QUIZ_WEIGHT);
        newl();
        print("%-25s %10.1f%% × %5.0f%% = %8.2f points", "Midterm Exam", midtermPercent, MIDTERM_WEIGHT*100, midtermPercent*MIDTERM_WEIGHT);
        newl();
        print("%-25s %10.1f%% × %5.0f%% = %8.2f points", "Final Exam", finalPercent, FINAL_WEIGHT*100, finalPercent*FINAL_WEIGHT);
        newl();
        
        drawLine(70, '─');
        newl();
        
        format("bold");
        print("FINAL RESULTS:");
        format("reset");
        newl();
        drawLine(70, '═');
        
        format("bold");
        print("Overall Numeric Score: ");
        format("reset");
        
        if (finalLetterGrade == 'A') {
            format("bright_green");
        } else if (finalLetterGrade == 'B') {
            format("green");
        } else if (finalLetterGrade == 'C') {
            format("yellow");
        } else if (finalLetterGrade == 'D') {
            format("red");
        } else {
            format("bright_red");
        }
        
        print("%.2f%%", overallNumericScore);
        format("reset");
        newl();
        
        format("bold");
        print("Final Letter Grade:    ");
        format("reset");
        
        if (finalLetterGrade == 'A') {
            format("bright_green");
        } else if (finalLetterGrade == 'B') {
            format("green");
        } else if (finalLetterGrade == 'C') {
            format("yellow");
        } else if (finalLetterGrade == 'D') {
            format("red");
        } else {
            format("bright_red");
        }
        
        print("%c", finalLetterGrade);
        format("reset");
        newl();
        
        drawLine(70, '═');
    }
    
    public String getStudentName() {
        return studentName;
    }
    
    public double getQuiz1Score() {
        return quiz1Score;
    }
    
    public double getQuiz2Score() {
        return quiz2Score;
    }
    
    public double getMidtermScore() {
        return midtermScore;
    }
    
    public double getFinalExamScore() {
        return finalExamScore;
    }
    
    public double getOverallNumericScore() {
        return overallNumericScore;
    }
    
    public char getFinalLetterGrade() {
        return finalLetterGrade;
    }
    
    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }
    
    public void setQuiz1Score(double quiz1Score) {
        if (quiz1Score >= 0 && quiz1Score <= QUIZ_MAX_POINTS) {
            this.quiz1Score = quiz1Score;
        }
    }
    
    public void setQuiz2Score(double quiz2Score) {
        if (quiz2Score >= 0 && quiz2Score <= QUIZ_MAX_POINTS) {
            this.quiz2Score = quiz2Score;
        }
    }
    
    public void setMidtermScore(double midtermScore) {
        if (midtermScore >= 0 && midtermScore <= EXAM_MAX_POINTS) {
            this.midtermScore = midtermScore;
        }
    }
    
    public void setFinalExamScore(double finalExamScore) {
        if (finalExamScore >= 0 && finalExamScore <= EXAM_MAX_POINTS) {
            this.finalExamScore = finalExamScore;
        }
    }
}
