package cpe121.karlvince.lab6_3;

import static kvx.cli.Std.*;
import static kvx.cli.Util.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class REYES_LE_6_3 {
    
    private static final int LINE_LENGTH = 70;
    
    public static void main(String[] args) throws IOException {
        clear();
        
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        
        format("bold", "white");
        drawLine(LINE_LENGTH, '═');
        print("                      ADDRESS BOOK SYSTEM");
        newl();
        drawLine(LINE_LENGTH, '═');
        format("reset");
        newl();
        
        format("cyan");
        print("                    (Type /e to cancel input)");
        format("reset");
        newl(2);
        
        // Load Database (kk)
        AdrbkMechanics addressBook;
        try {
            addressBook = AddrbkPersistenceHandler.loadFromJson();
        } catch (IOException e) {
            format("white");
            print("[ERROR] Error loading database: %s", e.getMessage());
            format("reset");
            newl();
            format("white");
            print("[WARN] Starting with empty address book.");
            format("reset");
            newl();
            addressBook = new AdrbkMechanics();
        }
        
        AddrbkPersistenceHandler.waitForEnter(new BufferedReader(new InputStreamReader(System.in)));
        
        while (true) {
            try {
                format("bold");
                print("Address Book Menu:");
                format("reset");
                newl();
                drawLine(40, '─');
                print("1. Add Entry");
                newl();
                print("2. View All Entries");
                newl();
                print("3. Update Entry");
                newl();
                print("4. Delete Entry");
                newl();
                print("5. Search Entry");
                newl();
                print("6. Load from Database");
                newl();
                print("7. Save to Database");
                newl();
                print("8. Exit");
                newl();
                drawLine(40, '─');
                newl();
                
                print("Enter your choice (1-8): ");
                int choice = Integer.parseInt(in.readLine());
                
                switch (choice) {
                    case 1:
                        clear();
                        addressBook.addEntry(in);
                        AddrbkPersistenceHandler.waitForEnter(in);
                        break;
                    case 2:
                        clear();
                        addressBook.viewAllEntries();
                        AddrbkPersistenceHandler.waitForEnter(in);
                        break;
                    case 3:
                        clear();
                        addressBook.updateEntry(in);
                        AddrbkPersistenceHandler.waitForEnter(in);
                        break;
                    case 4:
                        clear();
                        addressBook.deleteEntry(in);
                        AddrbkPersistenceHandler.waitForEnter(in);
                        break;
                    case 5:
                        clear();
                        addressBook.searchEntry(in);
                        AddrbkPersistenceHandler.waitForEnter(in);
                        break;
                    case 6:
                        clear();
                        format("bold");
                        print("Load Address Book from Database:");
                        format("reset");
                        newl();
                        drawLine(40, '─');
                        newl();
                        
                        try {
                            addressBook = AddrbkPersistenceHandler.loadFromJson();
                        } catch (IOException e) {
                            format("white");
                            print("[ERROR] Failed to load database: %s", e.getMessage());
                            format("reset");
                            newl();
                        }
                        AddrbkPersistenceHandler.waitForEnter(in);
                        break;
                    case 7:
                        clear();
                        format("bold");
                        print("Save Address Book to Database:");
                        format("reset");
                        newl();
                        drawLine(40, '─');
                        newl();
                        
                        try {
                            AddrbkPersistenceHandler.saveToJson(addressBook);
                        } catch (IOException e) {
                            format("white");
                            print("[ERROR] Failed to save database: %s", e.getMessage());
                            format("reset");
                            newl();
                        }
                        AddrbkPersistenceHandler.waitForEnter(in);
                        break;
                    case 8:
                        return;
                    default:
                        format("white");
                        print("[ERROR] Invalid choice! Please enter 1-8.");
                        format("reset");
                        newl(2);
                }
                
            } catch (NumberFormatException e) {
                format("white");
                print("[ERROR] Please enter a valid number.");
                format("reset");
                newl(2);
            } catch (Exception e) {
                format("white");
                print("[ERROR] An unexpected error occurred: %s", e.getMessage());
                format("reset");
                newl(2);
            }
        }
    }
}