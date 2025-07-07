package cpe121.karlvince.lab6_3;

import static kvx.cli.Std.*;
import java.io.BufferedReader;
import java.io.IOException;

public class AdrbkEntryHandler {
    private String name;
    private String address;
    private String telephoneNumber;
    private String emailAddress;
    
    public AdrbkEntryHandler() {
        this.name = "";
        this.address = "";
        this.telephoneNumber = "";
        this.emailAddress = "";
    }
    
    // (kk)
    public AdrbkEntryHandler(String name, String address, String telephoneNumber, String emailAddress) {
        this.name = name;
        this.address = address;
        this.telephoneNumber = telephoneNumber;
        this.emailAddress = emailAddress;
    }
    
    public boolean inputData(BufferedReader in) throws IOException {
        format("cyan");
        print("Type /e at any field to cancel input.");
        format("reset");
        newl(2);
        
        print("Name: ");
        this.name = in.readLine().trim();
        if (this.name.equals("/e")) {
            return false;
        }
        while (this.name.isEmpty()) {
            format("red");
            print("Name cannot be empty! Enter name: ");
            format("reset");
            this.name = in.readLine().trim();
            if (this.name.equals("/e")) {
                return false;
            }
        }
        
        print("Address: ");
        this.address = in.readLine().trim();
        if (this.address.equals("/e")) {
            return false;
        }
        while (this.address.isEmpty()) {
            format("red");
            print("Address cannot be empty! Enter address: ");
            format("reset");
            this.address = in.readLine().trim();
            if (this.address.equals("/e")) {
                return false;
            }
        }
        
        print("Telephone Number: ");
        this.telephoneNumber = in.readLine().trim();
        if (this.telephoneNumber.equals("/e")) {
            return false;
        }
        while (this.telephoneNumber.isEmpty()) {
            format("red");
            print("Telephone number cannot be empty! Enter telephone number: ");
            format("reset");
            this.telephoneNumber = in.readLine().trim();
            if (this.telephoneNumber.equals("/e")) {
                return false;
            }
        }
        
        print("Email Address: ");
        this.emailAddress = in.readLine().trim();
        if (this.emailAddress.equals("/e")) {
            return false;
        }
        while (this.emailAddress.isEmpty()) {
            format("red");
            print("Email address cannot be empty! Enter email address: ");
            format("reset");
            this.emailAddress = in.readLine().trim();
            if (this.emailAddress.equals("/e")) {
                return false;
            }
        }
        
        return true;
    }
    
    // (kk)
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
