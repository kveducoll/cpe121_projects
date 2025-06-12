package kvx.cli;

import java.io.IOException;

/**
 * Copyright (c) 2025 Kaviyes. All rights reserved.
 * Proprietary/internal use only.
 * This library provides standard CLI utilities for Java applications.
 * 
 * Derived from Jcandy (internal).
 */

public class Std {

    public static void newl() {System.out.println();}

    public static void delay(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public static void clear() {

        print("[INFO]   Attempted to clear the terminal.");
        print("[WARN]   If you see this, clearing the terminal was unsuccessful.");

        String os = System.getProperty("os.name");
        
        try {
            if (os.contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                new ProcessBuilder("clear").inheritIO().start().waitFor();
            }
        } catch (IOException | InterruptedException e) {
            for (int i = 0; i < 120; i++) {
                System.out.println();
            }
        }
    }

    public static void format(String... formats) {
        for (String format : formats) {
            System.out.print("\u001B[");
            switch (format.toLowerCase()) {
                case "reset"        :   System.out.print("0"); break;
                case "bold"         :   System.out.print("1"); break;
                case "dim"          :   System.out.print("2"); break;
                case "underline"    :   System.out.print("4"); break;
                case "black"        :   System.out.print("30"); break;
                case "red"          :   System.out.print("31"); break;
                case "green"        :   System.out.print("32"); break;
                case "yellow"       :   System.out.print("33"); break;
                case "blue"         :   System.out.print("34"); break;
                case "magenta"      :   System.out.print("35"); break;
                case "cyan"         :   System.out.print("36"); break;
                case "white"        :   System.out.print("37"); break;
            }
            System.out.print("m");
        }
    }

    public static void print(String message){System.out.print(message);}
    public static void print(String format, Object... args) {System.out.printf(format, args);}

    public static void printError(String message, Object... args) {System.err.printf(message, args);}

}
