package kvx.cli;

import static kvx.cli.Std.print;

public class Util {
    
    public static void drawLine(int length, char character) {
        for (int i = 0; i < length; i++) {
            print("%c", character);
        }
        System.out.println();
    }

    public static void drawSingleLine(int length, char character) {
        for (int i = 0; i < length; i++) {
            print("%c", character);
        }
    }

    public static String returnLine(int length, char character) {
        
        String _finatStr = "";
        
        for (int i = 0; i < length; i++) {
            _finatStr += character;
        }

        return _finatStr;
    }
}
