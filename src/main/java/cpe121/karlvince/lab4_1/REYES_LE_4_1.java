package cpe121.karlvince.lab4_1;

import static kvx.cli.Std.*;
import static kvx.cli.Util.drawLine;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class REYES_LE_4_1 {
    
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        int decimalVal = 0;
        int choiceVal = 0;
        boolean invalidIn = false;

        do {
            clear();
            if (invalidIn) {print("[WARN] Digits only."); newl();}
            if (decimalVal < 0) {print("[WARN] Invalid number entered, only positive number."); newl();}
            print("Enter a positive decimal value: ");
            
            invalidIn = false; decimalVal = 0;
            try {
                decimalVal = Integer.parseInt(in.readLine());
                invalidIn = false;
            } catch (NumberFormatException e) {
                invalidIn = true;
            }

        } while (decimalVal < 0 || invalidIn == true);

        newl();
        print("Value to which number system:"); newl(2);
        print("%-4s%s", "","1 ─ Decimal to Binary");        newl();
        print("%-4s%s", "","2 ─ Decimal to Octal");         newl();
        print("%-4s%s", "","3 ─ Decimal to Hexadecimal");   newl();

        drawLine(60, '─'); newl();
        print("Enter of Choice: ");

        try {
            choiceVal = Integer.parseInt(in.readLine());
            newl();
            
            if (choiceVal <= 0 || choiceVal >= 4) {
                choiceVal = 1;
                print("[WARN] Invalid choice detected, using default value: 1"); newl(2);
            }
        } catch (NumberFormatException e) {
            print("[WARN] Invalid choice detected, using default value: 1"); newl(2);
            choiceVal = 1;
        }

        if (choiceVal == 1) print("The equivalent of %d in Binary is %s", decimalVal, Integer.toBinaryString(decimalVal));
        if (choiceVal == 2) print("The equivalent of %d in Octal is %s", decimalVal, Integer.toOctalString(decimalVal));
        if (choiceVal == 3) print("The equivalent of %d in Hexadecimal is %s", decimalVal, Integer.toHexString(decimalVal));

        newl(2);
    }

}
