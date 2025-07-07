package cpe121.karlvince.lab6_3;

import static kvx.cli.Std.*;
import static kvx.cli.Util.*;
import java.io.BufferedReader;
import java.io.IOException;

public class AdrbkMechanics {
    private AdrbkEntryHandler[] entries;
    private int entryCount;
    private static final int MAX_ENTRIES = 100;
    
    public AdrbkMechanics() {
        this.entries = new AdrbkEntryHandler[MAX_ENTRIES];
        this.entryCount = 0;
    }
    
    public void addEntry(BufferedReader in) throws IOException {
        if (entryCount >= MAX_ENTRIES) {
            format("red");
            print("Address book is full! Cannot add more entries.");
            format("reset");
            newl();
            return;
        }
        
        format("bold");
        print("Add New Address Book Entry:");
        format("reset");
        newl();
        drawLine(40, '─');
        newl();
        
        AdrbkEntryHandler newEntry = new AdrbkEntryHandler();
        boolean success = newEntry.inputData(in);
        
        if (success) {
            entries[entryCount] = newEntry;
            entryCount++;
            
            newl();
            format("green");
            print("Entry added successfully! Total entries: %d", entryCount);
            format("reset");
            newl();
        } else {
            newl();
            format("yellow");
            print("Entry addition cancelled.");
            format("reset");
            newl();
        }
    }
    
    public void viewAllEntries() {
        format("bold");
        print("ALL ADDRESS BOOK ENTRIES");
        newl();
        drawLine(40, '─');
        newl(2);
        format("reset");
        
        if (entryCount == 0) {
            format("yellow");
            print("No entries in the address book.");
            format("reset");
            newl();
            return;
        }
        
        displayEntriesTable();
        
        format("bold");
        print("Total Entries: %d", entryCount);
        format("reset");
        newl();
    }
    
    private void displayEntriesTable() {
        enum TableChar {
            LEFT_TOP('┌'),
            LEFT_BOTTOM('└'),
            RIGHT_TOP('┐'),
            RIGHT_BOTTOM('┘'),
            HORIZONTAL('─'),
            LEFT_MIDDLE('├'),
            RIGHT_MIDDLE('┤'),
            VERTICAL('│'),
            MIDDLE_JUNCTION('┼'),
            TOP_JUNCTION('┬'),
            BOTTOM_JUNCTION('┴');

            final char symbol;
            
            TableChar(char symbol) {
                this.symbol = symbol;
            }
        }
        
        int numWidth = 3;
        int nameWidth = 20;
        int addressWidth = 25;
        int phoneWidth = 15;
        int emailWidth = 25;
        int cellPadding = 1;
        
        for (int i = 0; i < entryCount; i++) {
            if (entries[i].getName().length() > nameWidth) {
                nameWidth = entries[i].getName().length();
            }
            if (entries[i].getAddress().length() > addressWidth) {
                addressWidth = entries[i].getAddress().length();
            }
            if (entries[i].getTelephoneNumber().length() > phoneWidth) {
                phoneWidth = entries[i].getTelephoneNumber().length();
            }
            if (entries[i].getEmailAddress().length() > emailWidth) {
                emailWidth = entries[i].getEmailAddress().length();
            }
        }
        
        format("bg_white", "black");
        
        print("%c", TableChar.LEFT_TOP.symbol);
        print("%s", returnLine(numWidth + (cellPadding * 2), TableChar.HORIZONTAL.symbol));
        print("%c", TableChar.TOP_JUNCTION.symbol);
        print("%s", returnLine(nameWidth + (cellPadding * 2), TableChar.HORIZONTAL.symbol));
        print("%c", TableChar.TOP_JUNCTION.symbol);
        print("%s", returnLine(addressWidth + (cellPadding * 2), TableChar.HORIZONTAL.symbol));
        print("%c", TableChar.TOP_JUNCTION.symbol);
        print("%s", returnLine(phoneWidth + (cellPadding * 2), TableChar.HORIZONTAL.symbol));
        print("%c", TableChar.TOP_JUNCTION.symbol);
        print("%s", returnLine(emailWidth + (cellPadding * 2), TableChar.HORIZONTAL.symbol));
        print("%c", TableChar.RIGHT_TOP.symbol);
        newl();
        
        print("%c", TableChar.VERTICAL.symbol);
        print("%s%s%s", returnLine(cellPadding, ' '), "#", returnLine(numWidth - 1 + cellPadding, ' '));
        print("%c", TableChar.VERTICAL.symbol);
        print("%s%s%s", returnLine(cellPadding, ' '), "NAME", returnLine(nameWidth - 4 + cellPadding, ' '));
        print("%c", TableChar.VERTICAL.symbol);
        print("%s%s%s", returnLine(cellPadding, ' '), "ADDRESS", returnLine(addressWidth - 7 + cellPadding, ' '));
        print("%c", TableChar.VERTICAL.symbol);
        print("%s%s%s", returnLine(cellPadding, ' '), "PHONE", returnLine(phoneWidth - 5 + cellPadding, ' '));
        print("%c", TableChar.VERTICAL.symbol);
        print("%s%s%s", returnLine(cellPadding, ' '), "EMAIL", returnLine(emailWidth - 5 + cellPadding, ' '));
        print("%c", TableChar.VERTICAL.symbol);
        newl();
        
        print("%c", TableChar.LEFT_MIDDLE.symbol);
        print("%s", returnLine(numWidth + (cellPadding * 2), TableChar.HORIZONTAL.symbol));
        print("%c", TableChar.MIDDLE_JUNCTION.symbol);
        print("%s", returnLine(nameWidth + (cellPadding * 2), TableChar.HORIZONTAL.symbol));
        print("%c", TableChar.MIDDLE_JUNCTION.symbol);
        print("%s", returnLine(addressWidth + (cellPadding * 2), TableChar.HORIZONTAL.symbol));
        print("%c", TableChar.MIDDLE_JUNCTION.symbol);
        print("%s", returnLine(phoneWidth + (cellPadding * 2), TableChar.HORIZONTAL.symbol));
        print("%c", TableChar.MIDDLE_JUNCTION.symbol);
        print("%s", returnLine(emailWidth + (cellPadding * 2), TableChar.HORIZONTAL.symbol));
        print("%c", TableChar.RIGHT_MIDDLE.symbol);
        newl();
        
        for (int i = 0; i < entryCount; i++) {
            String entryNum = String.valueOf(i + 1);
            
            print("%c", TableChar.VERTICAL.symbol);
            print("%s%s%s", returnLine(cellPadding, ' '), entryNum, returnLine(numWidth - entryNum.length() + cellPadding, ' '));
            print("%c", TableChar.VERTICAL.symbol);
            print("%s%s%s", returnLine(cellPadding, ' '), entries[i].getName(), returnLine(nameWidth - entries[i].getName().length() + cellPadding, ' '));
            print("%c", TableChar.VERTICAL.symbol);
            print("%s%s%s", returnLine(cellPadding, ' '), entries[i].getAddress(), returnLine(addressWidth - entries[i].getAddress().length() + cellPadding, ' '));
            print("%c", TableChar.VERTICAL.symbol);
            print("%s%s%s", returnLine(cellPadding, ' '), entries[i].getTelephoneNumber(), returnLine(phoneWidth - entries[i].getTelephoneNumber().length() + cellPadding, ' '));
            print("%c", TableChar.VERTICAL.symbol);
            print("%s%s%s", returnLine(cellPadding, ' '), entries[i].getEmailAddress(), returnLine(emailWidth - entries[i].getEmailAddress().length() + cellPadding, ' '));
            print("%c", TableChar.VERTICAL.symbol);
            newl();
            
            if (i < entryCount - 1) {
                print("%c", TableChar.LEFT_MIDDLE.symbol);
                print("%s", returnLine(numWidth + (cellPadding * 2), TableChar.HORIZONTAL.symbol));
                print("%c", TableChar.MIDDLE_JUNCTION.symbol);
                print("%s", returnLine(nameWidth + (cellPadding * 2), TableChar.HORIZONTAL.symbol));
                print("%c", TableChar.MIDDLE_JUNCTION.symbol);
                print("%s", returnLine(addressWidth + (cellPadding * 2), TableChar.HORIZONTAL.symbol));
                print("%c", TableChar.MIDDLE_JUNCTION.symbol);
                print("%s", returnLine(phoneWidth + (cellPadding * 2), TableChar.HORIZONTAL.symbol));
                print("%c", TableChar.MIDDLE_JUNCTION.symbol);
                print("%s", returnLine(emailWidth + (cellPadding * 2), TableChar.HORIZONTAL.symbol));
                print("%c", TableChar.RIGHT_MIDDLE.symbol);
                newl();
            }
        }
        
        print("%c", TableChar.LEFT_BOTTOM.symbol);
        print("%s", returnLine(numWidth + (cellPadding * 2), TableChar.HORIZONTAL.symbol));
        print("%c", TableChar.BOTTOM_JUNCTION.symbol);
        print("%s", returnLine(nameWidth + (cellPadding * 2), TableChar.HORIZONTAL.symbol));
        print("%c", TableChar.BOTTOM_JUNCTION.symbol);
        print("%s", returnLine(addressWidth + (cellPadding * 2), TableChar.HORIZONTAL.symbol));
        print("%c", TableChar.BOTTOM_JUNCTION.symbol);
        print("%s", returnLine(phoneWidth + (cellPadding * 2), TableChar.HORIZONTAL.symbol));
        print("%c", TableChar.BOTTOM_JUNCTION.symbol);
        print("%s", returnLine(emailWidth + (cellPadding * 2), TableChar.HORIZONTAL.symbol));
        print("%c", TableChar.RIGHT_BOTTOM.symbol);
        
        format("reset");
        newl(2);
    }
    
    public void updateEntry(BufferedReader in) throws IOException {
        if (entryCount == 0) {
            format("yellow");
            print("No entries to update.");
            format("reset");
            newl();
            return;
        }
        
        format("bold");
        print("Update Address Book Entry:");
        format("reset");
        newl();
        drawLine(40, '─');
        newl();
        
        viewAllEntries();
        
        int entryIndex;
        while (true) {
            try {
                print("Enter entry number to update (1-%d or /e to cancel): ", entryCount);
                String input = in.readLine().trim();
                
                if (input.equals("/e")) {
                    format("yellow");
                    print("Update cancelled.");
                    format("reset");
                    newl();
                    return;
                }
                
                entryIndex = Integer.parseInt(input) - 1;
                
                if (entryIndex < 0 || entryIndex >= entryCount) {
                    format("red");
                    print("Invalid entry number! Please enter a number between 1 and %d.", entryCount);
                    format("reset");
                    newl();
                    continue;
                }
                break;
            } catch (NumberFormatException e) {
                format("red");
                print("Please enter a valid number.");
                format("reset");
                newl();
            }
        }
        
        newl();
        format("bold");
        print("Current entry details:");
        format("reset");
        newl();
        drawLine(30, '─');
        entries[entryIndex].displayEntry();
        newl();
        
        String originalName = entries[entryIndex].getName();
        String originalAddress = entries[entryIndex].getAddress();
        String originalTelephone = entries[entryIndex].getTelephoneNumber();
        String originalEmail = entries[entryIndex].getEmailAddress();
        
        format("bold");
        print("Enter new details:");
        format("reset");
        newl();
        boolean success = entries[entryIndex].inputData(in);
        
        if (success) {
            newl();
            format("green");
            print("Entry updated successfully!");
            format("reset");
            newl();
        } else {
            entries[entryIndex].setName(originalName);
            entries[entryIndex].setAddress(originalAddress);
            entries[entryIndex].setTelephoneNumber(originalTelephone);
            entries[entryIndex].setEmailAddress(originalEmail);
            
            newl();
            format("yellow");
            print("Update cancelled. Original entry preserved.");
            format("reset");
            newl();
        }
    }
    
    public void deleteEntry(BufferedReader in) throws IOException {
        if (entryCount == 0) {
            format("yellow");
            print("No entries to delete.");
            format("reset");
            newl();
            return;
        }
        
        format("bold");
        print("Delete Address Book Entry:");
        format("reset");
        newl();
        drawLine(40, '─');
        newl();
        
        viewAllEntries();
        
        int entryIndex;
        while (true) {
            try {
                print("Enter entry number to delete (1-%d or /e to cancel): ", entryCount);
                String input = in.readLine().trim();
                
                if (input.equals("/e")) {
                    format("yellow");
                    print("Delete cancelled.");
                    format("reset");
                    newl();
                    return;
                }
                
                entryIndex = Integer.parseInt(input) - 1;
                
                if (entryIndex < 0 || entryIndex >= entryCount) {
                    format("red");
                    print("Invalid entry number! Please enter a number between 1 and %d.", entryCount);
                    format("reset");
                    newl();
                    continue;
                }
                break;
            } catch (NumberFormatException e) {
                format("red");
                print("Please enter a valid number.");
                format("reset");
                newl();
            }
        }
        
        newl();
        format("yellow");
        print("Are you sure you want to delete this entry? (Y/N or /e to cancel): ");
        format("reset");
        String confirm = in.readLine().trim().toLowerCase();
        
        if (confirm.equals("/e")) {
            format("cyan");
            print("Deletion cancelled.");
            format("reset");
            newl();
            return;
        }
        
        if (!confirm.equals("y") && !confirm.equals("yes")) {
            format("cyan");
            print("Deletion cancelled.");
            format("reset");
            newl();
            return;
        }
        
        for (int i = entryIndex; i < entryCount - 1; i++) {
            entries[i] = entries[i + 1];
        }
        entries[entryCount - 1] = null;
        entryCount--;
        
        newl();
        format("green");
        print("Entry deleted successfully! Total entries: %d", entryCount);
        format("reset");
        newl();
    }
    
    public void searchEntry(BufferedReader in) throws IOException {
        if (entryCount == 0) {
            format("yellow");
            print("No entries to search.");
            format("reset");
            newl();
            return;
        }
        
        format("bold");
        print("Search Address Book Entry:");
        format("reset");
        newl();
        drawLine(40, '─');
        newl();
        
        format("cyan");
        print("Type /e to cancel search.");
        format("reset");
        newl();
        print("Enter name to search: ");
        String searchName = in.readLine().trim();
        
        if (searchName.equals("/e")) {
            format("yellow");
            print("Search cancelled.");
            format("reset");
            newl();
            return;
        }
        
        if (searchName.isEmpty()) {
            format("red");
            print("Search term cannot be empty!");
            format("reset");
            newl();
            return;
        }
        
        searchName = searchName.toLowerCase();
        boolean found = false;
        newl();
        format("bold");
        print("Search Results:");
        format("reset");
        newl();
        drawLine(40, '─');
        newl();
        
        // STRING MATCHING WITH SEARCH
        // REFERENCE IN CASE I FORGET: https://www.geeksforgeeks.org/dsa/applications-of-string-matching-algorithms/
        for (int i = 0; i < entryCount; i++) {
            if (entries[i].getName().toLowerCase().contains(searchName)) {
                format("bold");
                print("Entry #%d:", (i + 1));
                format("reset");
                newl();
                drawLine(30, '─');
                entries[i].displayEntry();
                newl();
                found = true;
            }
        }
        
        if (!found) {
            format("yellow");
            print("No entries found matching '%s'", searchName);
            format("reset");
            newl();
        }
    }
    
    public AdrbkEntryHandler[] getEntries() {
        return entries;
    }
    
    public boolean addEntryDirect(AdrbkEntryHandler entry) {
        if (entryCount >= MAX_ENTRIES) {
            return false;
        }
        entries[entryCount] = entry;
        entryCount++;
        return true;
    }
    
    public int getEntryCount() {
        return entryCount;
    }
}
