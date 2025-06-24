package cpe121.karlvince.lab4_3;

import static kvx.cli.Std.clear;
import static kvx.cli.Std.newl;
import static kvx.cli.Std.print;
import static kvx.cli.Util.drawLine;

public class REYES_LE_4_3_BASIC {
    public static void main(String[] args) {
        clear();
        String[][] studentList = {
                {"Karl Vince Reyes", "75", "77", "80", "76"},
                {"Juan Carlos Fernandez", "78", "80", "82", "87"},
                {"Lukasz Kazmierski", "85", "91", "88", "88"},
                {"Raj Patel Sharma", "79", "81", "83", "65"},
                {"Sofia Isabella Dela Cruz", "88", "92", "90", "89"},
                {"Yekatrina Nazarko", "95", "94", "96", "94"},
                {"Olga Petrova Ivanova", "87", "89", "88", "86"},
                {"Kwame Nkrumah Osei", "95", "92", "94", "91"},
                {"John Perfect", "100", "100", "100", "100"}
        };
        
        print("Student\t\t\tGrade1\tGrade2\tGrade3\tGrade4\tAverage"); newl();
        drawLine(65, '─');
        
        for (String[] student : studentList) {
            print(adjustNamePadding(student[0], 20) + "\t");
            
            double sum = 0;
            for (int i = 1; i < student.length; i++) {
                int grade = Integer.parseInt(student[i]);
                sum += grade;
                print(grade + "\t");
            }
            
            double average = Math.floor(sum / (student.length - 1));
            print("%.0f\n", average);
        }

        newl(2);
    }
    
    private static String adjustNamePadding(String s, int length) {
        if (s.length() >= length) {
            return s.substring(0, length);
        }
        return s + " ".repeat(length - s.length());
    }
}