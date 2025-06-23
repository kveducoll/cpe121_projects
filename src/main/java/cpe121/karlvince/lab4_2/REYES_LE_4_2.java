package cpe121.karlvince.lab4_2;

import static kvx.cli.Std.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class REYES_LE_4_2 {

    public static void main(String[] args) throws IOException {
        
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        boolean isInvalid = false;
        int loopTimes = 0;
        int getSum = 0;
        ArrayList<Integer> integerList = new ArrayList<>();
        
        do {
            clear();
            if (isInvalid) {print("[WARN] Digits only."); newl();}

            isInvalid = false;

            print("How many integers will you enter?\n> ");
            
            try{
                loopTimes = Integer.parseInt(in.readLine());
            } catch (NumberFormatException e) {
                isInvalid = true;
            }

        } while (isInvalid);

        

        print("%sEnter %d integers, one per line:", loopTimes > 10 ? "You've got issues man, good luck on long entries.\n" : "", loopTimes); newl();
        
        for (int k = 0; k < loopTimes; k++) {

            try {
                print("> ");
                integerList.add(Integer.parseInt(in.readLine()));
            } catch (NumberFormatException e) {
                k -= 1;
                print("└[ERROR] Invalid entry detected, add a valid integer."); newl();
            }

        }

        for (int k = 0; k < integerList.size(); k++) {
            getSum += integerList.get(k);
        }

        print("The sum is %s", getSum); newl();
        print("The numbers are:"); newl();

        for (int k = 0; k < integerList.size(); k++ ) {
            double proccess = (((double)integerList.get(k)/getSum)*100);
            print("%-4s%d, which is %2.2f%% of the sum.", "", integerList.get(k), proccess); newl();
        } newl();
        
    }

}
