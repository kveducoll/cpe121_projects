package kvx.cli;

import java.io.IOException;

/**
 * Copyright (c) 2025 Kaviyes. All rights reserved.
 * Proprietary/internal use only.
 * This library provides standard CLI utilities for Java related laboratory tasks to reduce boilerplates.
 * 
 * Derived from Jcandy (internal).
 */

public class Std {

    public static void newl() {System.out.println();}
    public static void newl(int range) {
        for (int i = 1; i <= range; i++) {System.out.println();}
    }

    public static void delay(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public static void clear() {

        System.out.println("[INFO]   Attempting to clear the terminal.");
        System.out.println("[WARN]   If you see this, clearing the terminal was unsuccessful."); newl();

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

    /**
     * ANSI Terminal Color Codes Reference
     * @see <a href="https://gist.github.com/fnky/458719343aabd01cfb17a3a4f7296797">ANSI Escape Sequences Documentation</a>
     */
    public static void format(String... formats) {
        for (String format : formats) {
            System.out.print("\u001B[");
            switch (format.toLowerCase()) {
                case "reset"            :   System.out.print("0"); break;
                case "bold"             :   System.out.print("1"); break;
                case "underline"        :   System.out.print("4"); break;
                case "black"            :   System.out.print("30"); break;
                case "red"              :   System.out.print("31"); break;
                case "green"            :   System.out.print("32"); break;
                case "yellow"           :   System.out.print("33"); break;
                case "blue"             :   System.out.print("34"); break;
                case "magenta"          :   System.out.print("35"); break;
                case "cyan"             :   System.out.print("36"); break;
                case "white"            :   System.out.print("37"); break;
                case "bg_black"         :   System.out.print("40"); break;
                case "bg_red"           :   System.out.print("41"); break;
                case "bg_green"         :   System.out.print("42"); break;
                case "bg_yellow"        :   System.out.print("43"); break;
                case "bg_blue"          :   System.out.print("44"); break;
                case "bg_magenta"       :   System.out.print("45"); break;
                case "bg_cyan"          :   System.out.print("46"); break;
                case "bg_white"         :   System.out.print("47"); break;
                case "bright_black"     :   System.out.print("90"); break;
                case "bright_red"       :   System.out.print("91"); break;
                case "bright_green"     :   System.out.print("92"); break;
                case "bright_yellow"    :   System.out.print("93"); break;
                case "bright_blue"      :   System.out.print("94"); break;
                case "bright_magenta"   :  System.out.print("95"); break;
                case "bright_cyan"      :   System.out.print("96"); break;
                case "bright_white"     :   System.out.print("97"); break;
            }
            System.out.print("m");
        }
    }

    public static void print(String message){System.out.print(message);}
    public static void print(String format, Object... args) {System.out.printf(format, args);}

    public static void printError(String message, Object... args) {System.err.printf(message, args);}

}
