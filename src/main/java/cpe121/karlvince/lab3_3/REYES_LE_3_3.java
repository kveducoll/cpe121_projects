package cpe121.karlvince.lab3_3;

import static kvx.cli.Std.*;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.BufferedReader;

// LAB 3.3
public class REYES_LE_3_3 {

    public static void main(String[] args) throws IOException {

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        String monthStr, zodiacStr;

        clear();

        print("Enter Month: ");
        int month = Integer.parseInt(in.readLine());

        print("Enter Day: ");
        int day = Integer.parseInt(in.readLine());

        switch (month) {
            case 1:
                monthStr = "January";
                if (day <= 19) {zodiacStr = "Capricorn";}
                else {zodiacStr = "Aquarius";}
                break;
            case 2:
                monthStr = "February";
                if (day <= 18) {zodiacStr = "Aquarius";}
                else {zodiacStr = "Pisces";}
                break;
            case 3:
                monthStr = "March";
                if (day <= 20) {zodiacStr = "Pisces";}
                else {zodiacStr = "Aries";}
                break;
            case 4:
                monthStr = "April";
                if (day <= 19) {zodiacStr = "Aries";}
                else {zodiacStr = "Taurus";}
                break;
            case 5:
                monthStr = "May";
                if (day <= 20) {zodiacStr = "Taurus";}
                else {zodiacStr = "Gemini";}
                break;
            case 6:
                monthStr = "June";
                if (day <= 20) {zodiacStr = "Gemini";}
                else {zodiacStr = "Cancer";}
                break;
            case 7:
                monthStr = "July";
                if (day <= 22) {zodiacStr = "Cancer";}
                else {zodiacStr = "Leo";}
                break;
            case 8:
                monthStr = "August";
                if (day <= 22) {zodiacStr = "Leo";}
                else {zodiacStr = "Virgo";}
                break;
            case 9:
                monthStr = "September";
                if (day <= 22) {zodiacStr = "Virgo";}
                else {zodiacStr = "Libra";}
                break;
            case 10:
                monthStr = "October";
                if (day <= 22) {zodiacStr = "Libra";}
                else {zodiacStr = "Scorpio";}
                break;
            case 11:
                monthStr = "November";
                if (day <= 21) {zodiacStr = "Scorpio";}
                else {zodiacStr = "Sagittarius";}
                break;
            case 12:
                monthStr = "December";
                if (day <= 21) {zodiacStr = "Sagittarius";}
                else {zodiacStr = "Capricorn";}
                break;
            default:
                monthStr = "Invalid month";
                zodiacStr = "Not available.";
                break;
        }

        print("The zodiac sign for %s %d is %s", monthStr, day, zodiacStr); newl(2);
        
    }
}
