package cpe121.karlvince.lab4_3;

import static kvx.cli.Std.*;
import static kvx.cli.Util.returnLine;

public class REYES_LE_4_3 {
    
    public static void main(String[] args) {
    
        int maxStudentNameLength = 0;
        int gradeCellLength = 8; // EVEN ONLY, 7 MINIMUM
        int cellPaddingSize = 1;
        int totalGradesAvailable = 4;
        int cellAmount = (totalGradesAvailable + 2);

        String firstCell = "NAME";

        enum TableChar {
            LEFT_TOP('┌'),
            LEFT_BOTTOM('└'),
            RIGHT_TOP('┐'),
            RIGHT_BOTTOM('┘'),
            HORIZONTAL('─'),
            LEFT_MIDDLE('├'),
            RIGHT_MIDDLE('┤'),
            VERTICAL('│'),
            MIDDLE_JUNCTION('┼'),
            TOP_JUNCTION('┬'),
            BOTTOM_JUNCTION('┴');

            final char symbol;
            
            TableChar(char symbol) {
            this.symbol = symbol;
            }
        }

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
            {"John Perpek", "100", "100", "100", "100"}
        };

        // ----------------- TABLE DISPLAY -----------------//

        // GET STUDENT NAME LENGTH AND COMPARE
        for (int i = 0; i < studentList.length; i++) {

            String currentStudent = studentList[i][0];

            if (currentStudent.length() > maxStudentNameLength) {
                maxStudentNameLength = currentStudent.length();
            }
        }

        // TOP BORDER

        String padding = returnLine(cellPaddingSize, ' ');
        String gradePadding = (returnLine((gradeCellLength/2), ' '));
        

        format("bg_white", "black");
        
        // TOP BORDER
        print("%c", TableChar.LEFT_TOP.symbol);
        print("%s%s%s", returnLine(cellPaddingSize, TableChar.HORIZONTAL.symbol), returnLine(maxStudentNameLength, TableChar.HORIZONTAL.symbol), returnLine(cellPaddingSize, TableChar.HORIZONTAL.symbol));
        print("%c", TableChar.TOP_JUNCTION.symbol);
        for (int t = 0; t < (cellAmount - 1); t++) {
            print("%s%s%s", returnLine(cellPaddingSize, TableChar.HORIZONTAL.symbol), returnLine(gradeCellLength, TableChar.HORIZONTAL.symbol), returnLine(cellPaddingSize, TableChar.HORIZONTAL.symbol));
            
            if (t == (cellAmount - 2)) {
            print("%c", TableChar.RIGHT_TOP.symbol);
            } else {
            print("%c", TableChar.TOP_JUNCTION.symbol);
            }
        }
        
        // HEADER ROW
        newl();
        print("%c", TableChar.VERTICAL.symbol);
        print("%s%s", firstCell, returnLine((maxStudentNameLength - firstCell.length() + (cellPaddingSize*2)), ' '));
        print("%c", TableChar.VERTICAL.symbol);
        
        for (int i = 1; i < cellAmount - 1; i++) {
            String gradeStr = "GRADE" + i;
            print("%s%s", gradeStr, returnLine(gradeCellLength - gradeStr.length() + (cellPaddingSize*2), ' '));
            print("%c", TableChar.VERTICAL.symbol);
        }
        
        String gradeAverageStr = "AVERAGE";
        print("%s%s", gradeAverageStr, returnLine(gradeCellLength - gradeAverageStr.length() + (cellPaddingSize*2), ' '));
        print("%c", TableChar.VERTICAL.symbol);

        newl();
        print("%c", TableChar.LEFT_MIDDLE.symbol);
        print("%s%s%s", returnLine(cellPaddingSize, TableChar.HORIZONTAL.symbol), returnLine(maxStudentNameLength, TableChar.HORIZONTAL.symbol), returnLine(cellPaddingSize, TableChar.HORIZONTAL.symbol));
        print("%c", TableChar.MIDDLE_JUNCTION.symbol);
        for (int t = 0; t < (cellAmount - 1); t++) {

            print("%s%s%s", returnLine(cellPaddingSize, TableChar.HORIZONTAL.symbol), returnLine(gradeCellLength, TableChar.HORIZONTAL.symbol), returnLine(cellPaddingSize, TableChar.HORIZONTAL.symbol));

            if (t == (cellAmount - 2)) {print("%c", TableChar.RIGHT_MIDDLE.symbol);}
            else {
                print("%c", TableChar.MIDDLE_JUNCTION.symbol);
            }

        }

        // GRADE HANDLER
        for (int i = 0; i < studentList.length; i++) {

            String currentStd = studentList[i][0];
            newl();
            print("%c", TableChar.VERTICAL.symbol);
            print("%s%s%s",padding , currentStd, returnLine((maxStudentNameLength - currentStd.length() + cellPaddingSize), ' '));
            print("%s", TableChar.VERTICAL.symbol);

            double gradeSum = 0;
            for (int g = 1; g < studentList[i].length; g++) {
                if (99 < Integer.parseInt(studentList[i][g])) {
                    print("%s%s%s", gradePadding, studentList[i][g], returnLine((gradeCellLength/2)-1, ' '));
                } else {
                    print("%s%s%s", gradePadding, studentList[i][g], gradePadding);
                }
                
                gradeSum += Integer.parseInt(studentList[i][g]);
                print("%s", TableChar.VERTICAL.symbol);
            }
            
            double average = Math.floor(gradeSum/totalGradesAvailable);
            if (99.0 < average) {
                print("%s%.0f%s", gradePadding, average, returnLine((gradeCellLength/2)-1, ' '));
            } else {
                print("%s%.0f%s", gradePadding, average, gradePadding);
            }
            
            print("%s", TableChar.VERTICAL.symbol);
            newl();

            if (i == (studentList.length - 1)) {
                print("%c", TableChar.LEFT_BOTTOM.symbol);
            } else {
                print("%c", TableChar.LEFT_MIDDLE.symbol);
            }
            
            print("%s", returnLine(maxStudentNameLength+(cellPaddingSize*2), TableChar.HORIZONTAL.symbol));

            // LINE SEPARATOR
            if (i == (studentList.length - 1)) {
                print("%s", TableChar.BOTTOM_JUNCTION.symbol);
            } else {
                print("%s", TableChar.MIDDLE_JUNCTION.symbol);
            }
            
            for (int t = 0; t < (cellAmount - 1); t++) {

                if (i == (studentList.length - 1) && t == (cellAmount - 2)) {
                    print("%s%c", returnLine(gradeCellLength+(cellPaddingSize*2), TableChar.HORIZONTAL.symbol), TableChar.RIGHT_BOTTOM.symbol);
                }
                else if (i == (studentList.length - 1)) {
                    print("%s%c", returnLine(gradeCellLength+(cellPaddingSize*2), TableChar.HORIZONTAL.symbol), TableChar.BOTTOM_JUNCTION.symbol);
                } else if (t == (cellAmount - 2)) {
                    print("%s%c", returnLine(gradeCellLength+(cellPaddingSize*2), TableChar.HORIZONTAL.symbol), TableChar.RIGHT_MIDDLE.symbol);
                } else {
                    print("%s%c", returnLine(gradeCellLength+(cellPaddingSize*2), TableChar.HORIZONTAL.symbol), TableChar.MIDDLE_JUNCTION.symbol);
                }
                
            }

        }
        format("reset");
        newl(2);

    }
}
