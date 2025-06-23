package cpe121.karlvince.lab4_3;

import static kvx.cli.Std.*;
import static kvx.cli.Util.drawLine;

public class REYES_LE_4_3 {
    
    public static void main(String[] args) {
        
        clear();
        String[][] studentList = {
            {"peter", "75", "77"},
            {"clark", "78", "80"},
            {"logan", "82", "84"},
        };

        print("|  NAME   |GRADE1|GRADE2|AVERAGE|");newl();
        drawLine(33, '-');
        for (int i = 0; i < studentList.length; i++) {
            double average = 0;

            print("|  %s  |  ", studentList[i][0]);

            for (int k = 1; k < studentList[i].length; k++) {

                average += Integer.parseInt(studentList[i][k]);
                print("%d  |  ", Integer.parseInt(studentList[i][k]));

            }

            print("%.0f   |", (average / 2)); newl();
            drawLine(33, '-');



        }

    }

}
