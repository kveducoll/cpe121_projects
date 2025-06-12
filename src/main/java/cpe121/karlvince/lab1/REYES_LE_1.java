package cpe121.karlvince.lab1;

import static kvx.cli.Std.print;
import static kvx.cli.Std.clear;
import static kvx.cli.Std.format;
import static kvx.cli.Std.newl;
import static kvx.cli.Util.drawLine;

public class REYES_LE_1 {

    public final static int WIDTH_LENGTH = 80;

    public static void main(String[] args) {
        clear();
        format("bold");
        drawLine(WIDTH_LENGTH, '─');
        print("KARL VINCE REYES"); newl(); format("reset");
        drawLine(WIDTH_LENGTH, '─');
        print("%-15s : %-16s %s", "Year", "2024-Present", "Computer Engineering Student at UMDC");

    }
}
