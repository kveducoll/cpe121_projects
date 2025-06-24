package cpe121.karlvince.lab4_3;

import static kvx.cli.Std.*;
import static kvx.cli.Util.drawSingleLine;

public class REYES_LE_4_3 {
    
    public static void main(String[] args) {
        
        clear();
        String[][] studentList = {
            {"Peter", "75", "77"},
            {"Clark", "78", "80"},
            {"Logan", "82", "84"},
            {"Bruce", "90", "88"},
            {"Diana", "85", "91"},
            {"Barry", "79", "81"},
            {"Steve", "88", "92"},
            {"Carol", "95", "94"},
        };

        predefinedLine(2);
        print("│  NAME   │  GRADE1  │  GRADE2  │ AVERAGE │");newl();
        predefinedLine(1);
        for (int i = 0; i < studentList.length; i++) {
            double average = 0;

            print("│  %s  │    ", studentList[i][0]);

            for (int k = 1; k < studentList[i].length; k++) {

                average += Integer.parseInt(studentList[i][k]);
                print("%d    │    ", Integer.parseInt(studentList[i][k]));

            }
            
            print("%.0f   │", (average / 2)); newl();

            if (i == studentList.length - 1) {
                predefinedLine(3);
            } else {
                predefinedLine(1);
            }
        }

        print("└"); drawSingleLine(41, '─'); print("┘"); newl();

    }

    // For table
    public static void predefinedLine(int selection) {
        switch (selection) {
            case 1:
                print("├"); drawSingleLine(9, '─'); print("┼"); drawSingleLine(10, '─'); print("┼"); drawSingleLine(10, '─'); print("┼"); drawSingleLine(9, '─'); print("┤"); newl();
                break;
            case 2:
                print("┌"); drawSingleLine(9, '─'); print("┬"); drawSingleLine(10, '─'); print("┬"); drawSingleLine(10, '─'); print("┬"); drawSingleLine(9, '─'); print("┐"); newl();
                break;
            case 3:
                print("├"); drawSingleLine(9, '─'); print("┴"); drawSingleLine(10, '─'); print("┴"); drawSingleLine(10, '─'); print("┴"); drawSingleLine(9, '─'); print("┤"); newl();
                break;
            default:
                break;
        }
    }
}
