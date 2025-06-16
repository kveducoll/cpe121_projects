package cpe121.karlvince.lab1;

import static kvx.cli.Std.print;
import static kvx.cli.Std.clear;
import static kvx.cli.Std.format;
import static kvx.cli.Std.newl;
import static kvx.cli.Util.drawLine;

public class REYES_LE_2 {

    public final static int WIDTH_LENGTH = 80;
    public final static String SCHOOL_NAME = "University of Mindanao Digos College";

    public static void main(String[] args) {
        int age = 20;
        char middleInitial = 'A';
        boolean isStudent = true;
        double gpa = 3.75;

        clear();
        drawLine(WIDTH_LENGTH, '─');
        print("School: ");
        format("bold"); print("%s", SCHOOL_NAME);format("reset"); newl();
        print("Age: %d", age); newl();
        print("Middle Initial: %c\n", middleInitial);
        print("Is Student: %b", isStudent); newl();
        print("GPA: %.2f", gpa); newl();
        drawLine(WIDTH_LENGTH, '─');
    }
}
