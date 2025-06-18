package cpe121.karlvince.lab1;

import static kvx.cli.Std.print;
import static kvx.cli.Std.clear;
import static kvx.cli.Std.format;
import static kvx.cli.Std.newl;
import static kvx.cli.Util.drawLine;

public class REYES_LE_1 {

    public final static int WIDTH_LENGTH = 80;

    public final static String name = "KARL VINCE REYES";
    public final static String job1 = "NVIDIA";
    public final static String job2 = "FARMER";
    public final static String year1= "Jan 2018 - December 2024";
    public final static String year2= "Jan 2020 - December 2028";

    public static void main(String[] args) {
        clear();
        format("bold");
        print("%s", name); newl(); format("reset");
        drawLine(WIDTH_LENGTH, '─');

        format("bold");
        print("Experience"); newl(); format("reset");
        
        print("%-20s", year1);
        format("bold");
        print("   %s", job1); format("reset"); newl();
        print("  • Developed AI accelerator hardware");
        newl();
        print("  • Led machine learning optimization team");
        newl();
        print("  • Created GPU architecture innovations");
        newl();
        newl();
        
        print("%-20s", year2);
        format("bold");
        print("   %s", job2); format("reset"); newl();
        print("  • Managed sustainable agriculture projects");
        newl();
        print("  • Implemented advanced irrigation systems");
        newl();
        print("  • Specialized in organic crop production");
        newl();
        
        drawLine(WIDTH_LENGTH, '─');
        
        format("bold");
        print("Education"); newl(); format("reset");
        print("2014 - 2018     B.S. Computer Engineering, UM4EVER");
        newl();
        
        drawLine(WIDTH_LENGTH, '─');
        
        format("bold");
        print("Skills"); newl(); format("reset");
        print("Programming Languages: Java, Python, C++");
        newl();
        print("Technologies: CUDA, TensorFlow, Agricultural Systems");
        newl(); newl(); newl();
    }
}
