package cpe121.karlvince.lab5_1;

import java.util.Scanner;
import static kvx.cli.Std.*;
import static kvx.cli.Util.*;

public class Menu {
    private String[] menuItems;
    private Scanner scanner;
    
    public Menu(String[] menuItems) {
        this.menuItems = menuItems;
        this.scanner = new Scanner(System.in);
    }
    
    public int displayMenuAndGetChoice() {
        clear();
        format("bold");
        drawLine(50, '─');
        print("              ZOO MANAGEMENT SYSTEM");
        newl();
        drawLine(50, '─');
        format("reset");
        newl();
        
        for (int i = 0; i < menuItems.length; i++) {
            format("bright_white");
            print("  " + (i + 1) + " ─ ");
            format("reset");
            print(menuItems[i]);
            newl();
        }
        
        newl();
        drawLine(50, '─');
        newl();
        
        while (true) {
            format("bright_yellow");
            print("Enter your choice (1-" + menuItems.length + "): ");
            format("reset");
            
            try {
                String input = scanner.nextLine().trim();
                int choice = Integer.parseInt(input);
                
                if (choice >= 1 && choice <= menuItems.length) {
                    return choice;
                } else {
                    format("red");
                    print("Invalid choice! Please enter a number between 1 and " + menuItems.length + ".");
                    format("reset");
                    newl();
                }
            } catch (NumberFormatException e) {
                format("red");
                print("Invalid input! Please enter a valid number.");
                format("reset");
                newl();
            }
        }
    }
    
    public String getStringInput(String prompt) {
        format("bright_yellow");
        print(prompt);
        format("reset");
        return scanner.nextLine().trim();
    }
    
    public int getIntInput(String prompt) {
        while (true) {
            format("bright_yellow");
            print(prompt);
            format("reset");
            
            try {
                String input = scanner.nextLine().trim();
                int value = Integer.parseInt(input);
                if (value >= 0) {
                    return value;
                } else {
                    format("red");
                    print("Please enter a non-negative number.");
                    format("reset");
                    newl();
                }
            } catch (NumberFormatException e) {
                format("red");
                print("Invalid input! Please enter a valid number.");
                format("reset");
                newl();
            }
        }
    }
}
