package cpe121.karlvince.lab3_1;

import static kvx.cli.Std.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// LAB 3.1
public class REYES_LE_3_1 {

    public static void main(String[] args) throws IOException {

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        double gradeTotal = 0;
        clear();

        for (int i = 1; i < 4; i++) {
            print("Enter grade %d: ", i);
            String data = in.readLine();
            gradeTotal += Integer.parseInt(data);
        }

        double gradeAverage =  (gradeTotal / 3);

        //print("Average: %.0f",  gradeAverage);
        newl(2);

        print("Result: ");
        if (gradeAverage >= 60 ) {print(":-)"); newl();} else {print(":-("); newl();}
    }
}
