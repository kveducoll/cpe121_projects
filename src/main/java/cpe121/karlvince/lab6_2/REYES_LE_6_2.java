package cpe121.karlvince.lab6_2;

import static kvx.cli.Std.*;
import static kvx.cli.Util.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class REYES_LE_6_2 {
    
    private static final int LINE_LENGTH = 70;
    
    public static void main(String[] args) throws IOException {
        clear();
        
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        
        format("bold", "bright_yellow");
        drawLine(LINE_LENGTH, '═');
        print("                   ADDRESS BOOK ENTRY SYSTEM");
        newl();
        drawLine(LINE_LENGTH, '═');
        format("reset");
        newl();
        
        format("cyan");
        print("                    (Type /e to cancel input)");
        format("reset");
        newl(2);
        
        AddressBookEntry entry = new AddressBookEntry();
        
        while (true) {
            try {
                format("bold");
                print("Address Book Entry Menu:");
                format("reset");
                newl();
                drawLine(40, '─');
                print("1. Enter/Update Contact Information");
                newl();
                print("2. Display Contact Information");
                newl();
                print("3. Exit");
                newl();
                drawLine(40, '─');
                newl();
                
                print("Enter your choice (1-3): ");
                int choice = Integer.parseInt(in.readLine());
                
                switch (choice) {
                    case 1:
                        clear();
                        manageContactInfo(entry, in);
                        waitForEnter(in);
                        break;
                    case 2:
                        clear();
                        displayContactInfo(entry);
                        waitForEnter(in);
                        break;
                    case 3:
                        return;
                    default:
                        format("red");
                        print("Invalid choice! Please enter 1-3.");
                        format("reset");
                        newl(2);
                }
                
            } catch (NumberFormatException e) {
                format("red");
                print("[Error] Please enter a valid number.");
                format("reset");
                newl(2);
            } catch (Exception e) {
                format("red");
                print("[Error] An unexpected error occurred: %s", e.getMessage());
                format("reset");
                newl(2);
            }
        }
    }
    
    private static void manageContactInfo(AddressBookEntry entry, BufferedReader in) throws IOException {
        format("bold");
        if (entry.getName().isEmpty()) {
            print("Enter Contact Information:");
        } else {
            print("Update Contact Information:");
        }
        format("reset");
        newl();
        drawLine(40, '─');
        newl();
        
        if (!entry.getName().isEmpty()) {
            format("bold");
            print("Current information:");
            format("reset");
            newl();
            displayContactTable(entry);
            newl();
            
            format("bold");
            print("Enter new information (or type /e to cancel):");
            format("reset");
            newl();
        } else {
            format("cyan");
            print("Type /e at any field to cancel input.");
            format("reset");
            newl(2);
        }
        
        boolean success = entry.inputData(in);
        
        if (success) {
            newl();
            format("green");
            if (entry.getName().isEmpty()) {
                print("Contact information saved successfully!");
            } else {
                print("Contact information updated successfully!");
            }
            format("reset");
            newl();
        } else {
            format("yellow");
            print("Input cancelled.");
            format("reset");
            newl();
        }
    }

    private static void displayContactInfo(AddressBookEntry entry) {
        if (entry.getName().isEmpty()) {
            format("yellow");
            print("No contact information available. Please enter contact details first.");
            format("reset");
            newl();
            return;
        }
        
        displayContactTable(entry);
    }
    
    private static void displayContactTable(AddressBookEntry entry) {
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
        
        int labelWidth = 15;
        int valueWidth = 30;
        int cellPadding = 1;
        
        String[] labels = {"Name", "Address", "Telephone", "Email"};
        String[] values = {entry.getName(), entry.getAddress(), entry.getTelephoneNumber(), entry.getEmailAddress()};
        
        for (String value : values) {
            if (value.length() > valueWidth) {
                valueWidth = value.length();
            }
        }
        
        format("bg_white", "black");
        
        print("%c", TableChar.LEFT_TOP.symbol);
        print("%s", returnLine(labelWidth + (cellPadding * 2), TableChar.HORIZONTAL.symbol));
        print("%c", TableChar.TOP_JUNCTION.symbol);
        print("%s", returnLine(valueWidth + (cellPadding * 2), TableChar.HORIZONTAL.symbol));
        print("%c", TableChar.RIGHT_TOP.symbol);
        newl();
        
        print("%c", TableChar.VERTICAL.symbol);
        print("%s%s%s", returnLine(cellPadding, ' '), "FIELD", returnLine(labelWidth - 5 + cellPadding, ' '));
        print("%c", TableChar.VERTICAL.symbol);
        print("%s%s%s", returnLine(cellPadding, ' '), "VALUE", returnLine(valueWidth - 5 + cellPadding, ' '));
        print("%c", TableChar.VERTICAL.symbol);
        newl();
        
        print("%c", TableChar.LEFT_MIDDLE.symbol);
        print("%s", returnLine(labelWidth + (cellPadding * 2), TableChar.HORIZONTAL.symbol));
        print("%c", TableChar.MIDDLE_JUNCTION.symbol);
        print("%s", returnLine(valueWidth + (cellPadding * 2), TableChar.HORIZONTAL.symbol));
        print("%c", TableChar.RIGHT_MIDDLE.symbol);
        newl();
        
        for (int i = 0; i < labels.length; i++) {
            print("%c", TableChar.VERTICAL.symbol);
            print("%s%s%s", returnLine(cellPadding, ' '), labels[i], returnLine(labelWidth - labels[i].length() + cellPadding, ' '));
            print("%c", TableChar.VERTICAL.symbol);
            print("%s%s%s", returnLine(cellPadding, ' '), values[i], returnLine(valueWidth - values[i].length() + cellPadding, ' '));
            print("%c", TableChar.VERTICAL.symbol);
            newl();
            
            if (i < labels.length - 1) {
                print("%c", TableChar.LEFT_MIDDLE.symbol);
                print("%s", returnLine(labelWidth + (cellPadding * 2), TableChar.HORIZONTAL.symbol));
                print("%c", TableChar.MIDDLE_JUNCTION.symbol);
                print("%s", returnLine(valueWidth + (cellPadding * 2), TableChar.HORIZONTAL.symbol));
                print("%c", TableChar.RIGHT_MIDDLE.symbol);
                newl();
            }
        }
        
        print("%c", TableChar.LEFT_BOTTOM.symbol);
        print("%s", returnLine(labelWidth + (cellPadding * 2), TableChar.HORIZONTAL.symbol));
        print("%c", TableChar.BOTTOM_JUNCTION.symbol);
        print("%s", returnLine(valueWidth + (cellPadding * 2), TableChar.HORIZONTAL.symbol));
        print("%c", TableChar.RIGHT_BOTTOM.symbol);
        
        format("reset");
        newl(2);    }
    
    private static void waitForEnter(BufferedReader in) throws IOException {
        newl();
        format("yellow");
        print("Press Enter to continue...");
        format("reset");
        in.readLine();
        clear();
    }
}

class AddressBookEntry {
    private String name;
    private String address;
    private String telephoneNumber;
    private String emailAddress;
    
    public AddressBookEntry() {
        this.name = "";
        this.address = "";
        this.telephoneNumber = "";
        this.emailAddress = "";
    }
    
    public AddressBookEntry(String name, String address, String telephoneNumber, String emailAddress) {
        this.name = name;
        this.address = address;
        this.telephoneNumber = telephoneNumber;
        this.emailAddress = emailAddress;
    }
    
    public boolean inputData(BufferedReader in) throws IOException {
        format("bold");
        print("Enter Contact Details:");
        format("reset");
        newl();
        drawLine(30, '─');
        newl();
        
        String originalName = this.name;
        String originalAddress = this.address;
        String originalTelephone = this.telephoneNumber;
        String originalEmail = this.emailAddress;
        
        print("Name: ");
        this.name = in.readLine().trim();
        if (this.name.equals("/e")) {
            restoreValues(originalName, originalAddress, originalTelephone, originalEmail);
            return false;
        }
        while (this.name.isEmpty()) {
            format("red");
            print("Name cannot be empty! Enter name: ");
            format("reset");
            this.name = in.readLine().trim();
            if (this.name.equals("/e")) {
                restoreValues(originalName, originalAddress, originalTelephone, originalEmail);
                return false;
            }
        }
        
        print("Address: ");
        this.address = in.readLine().trim();
        if (this.address.equals("/e")) {
            restoreValues(originalName, originalAddress, originalTelephone, originalEmail);
            return false;
        }
        while (this.address.isEmpty()) {
            format("red");
            print("Address cannot be empty! Enter address: ");
            format("reset");
            this.address = in.readLine().trim();
            if (this.address.equals("/e")) {
                restoreValues(originalName, originalAddress, originalTelephone, originalEmail);
                return false;
            }
        }
        
        print("Telephone Number: ");
        this.telephoneNumber = in.readLine().trim();
        if (this.telephoneNumber.equals("/e")) {
            restoreValues(originalName, originalAddress, originalTelephone, originalEmail);
            return false;
        }
        while (this.telephoneNumber.isEmpty()) {
            format("red");
            print("Telephone number cannot be empty! Enter telephone number: ");
            format("reset");
            this.telephoneNumber = in.readLine().trim();
            if (this.telephoneNumber.equals("/e")) {
                restoreValues(originalName, originalAddress, originalTelephone, originalEmail);
                return false;
            }
        }
        
        print("Email Address: ");
        this.emailAddress = in.readLine().trim();
        if (this.emailAddress.equals("/e")) {
            restoreValues(originalName, originalAddress, originalTelephone, originalEmail);
            return false;
        }
        while (this.emailAddress.isEmpty()) {
            format("red");
            print("Email address cannot be empty! Enter email address: ");
            format("reset");
            this.emailAddress = in.readLine().trim();
            if (this.emailAddress.equals("/e")) {
                restoreValues(originalName, originalAddress, originalTelephone, originalEmail);
                return false;
            }
        }
        
        return true;
    }
    
    private void restoreValues(String name, String address, String telephone, String email) {
        this.name = name;
        this.address = address;
        this.telephoneNumber = telephone;
        this.emailAddress = email;
    }
    
    public void displayEntry() {
        print("%-20s: %s", "Name", name);
        newl();
        print("%-20s: %s", "Address", address);
        newl();
        print("%-20s: %s", "Telephone", telephoneNumber);
        newl();
        print("%-20s: %s", "Email", emailAddress);
        newl();
    }
    
    public String getName() { return name; }
    public String getAddress() { return address; }
    public String getTelephoneNumber() { return telephoneNumber; }
    public String getEmailAddress() { return emailAddress; }
    
    public void setName(String name) { this.name = name; }
    public void setAddress(String address) { this.address = address; }
    public void setTelephoneNumber(String telephoneNumber) { this.telephoneNumber = telephoneNumber; }
    public void setEmailAddress(String emailAddress) { this.emailAddress = emailAddress; }
}
