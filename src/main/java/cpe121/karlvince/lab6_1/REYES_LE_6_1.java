package cpe121.karlvince.lab6_1;

import static kvx.cli.Std.*;
import static kvx.cli.Util.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class REYES_LE_6_1 {
    
    private static final int LINE_LENGTH = 70;
    
    public static void main(String[] args) throws IOException {
        clear();
        
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        
        format("bold", "bright_cyan");
        drawLine(LINE_LENGTH, '═');
        print("                    ROOM MANAGEMENT SYSTEM");
        newl();
        drawLine(LINE_LENGTH, '═');
        format("reset");
        newl(2);
        
        Room room = new Room();
        
        while (true) {
            try {
                format("bold");
                print("Room Management Menu:");
                format("reset");
                newl();
                drawLine(40, '─');
                print("1. Set Room Data");
                newl();
                print("2. Display Room Data");
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
                        room.setData(in);
                        waitForEnter(in);
                        break;
                    case 2:
                        clear();
                        room.displayData();
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
    
    private static void waitForEnter(BufferedReader in) throws IOException {
        newl();
        format("yellow");
        print("Press Enter to continue...");
        format("reset");
        in.readLine();
        clear();
    }
}

class Room {
    private String roomNo;
    private String roomType;
    private double roomArea;
    private boolean acMachine;
    
    public Room() {
        this.roomNo = "";
        this.roomType = "";
        this.roomArea = 0.0;
        this.acMachine = false;
    }
    
    public Room(String roomNo, String roomType, double roomArea, boolean acMachine) {
        this.roomNo = roomNo;
        this.roomType = roomType;
        this.roomArea = roomArea;
        this.acMachine = acMachine;
    }
    
    public void setData(BufferedReader in) throws IOException {
        format("bold");
        print("Enter Room Information:");
        format("reset");
        newl();
        drawLine(40, '─');
        newl();
        
        print("Room Number: ");
        this.roomNo = in.readLine().trim();
        while (this.roomNo.isEmpty()) {
            format("red");
            print("Room number cannot be empty! Enter room number: ");
            format("reset");
            this.roomNo = in.readLine().trim();
        }
        
        print("Room Type (e.g., Single, Double, Suite): ");
        this.roomType = in.readLine().trim();
        while (this.roomType.isEmpty()) {
            format("red");
            print("Room type cannot be empty! Enter room type: ");
            format("reset");
            this.roomType = in.readLine().trim();
        }
        
        while (true) {
            try {
                print("Room Area (sq. meters): ");
                this.roomArea = Double.parseDouble(in.readLine());
                if (this.roomArea <= 0) {
                    format("red");
                    print("Room area must be positive!");
                    format("reset");
                    newl();
                    continue;
                }
                break;
            } catch (NumberFormatException e) {
                format("red");
                print("Please enter a valid number for room area.");
                format("reset");
                newl();
            }
        }
        
        while (true) {
            print("Has AC Machine? (Y/N): ");
            String acInput = in.readLine().trim().toLowerCase();
            if (acInput.equals("y") || acInput.equals("yes")) {
                this.acMachine = true;
                break;
            } else if (acInput.equals("n") || acInput.equals("no")) {
                this.acMachine = false;
                break;
            } else {
                format("red");
                print("Please enter Y for Yes or N for No.");
                format("reset");
                newl();
            }
        }
        
        newl();
        format("green");
        print("Room data updated successfully!");
        format("reset");
        newl();
    }
    
    public void displayData() {
        format("bold");
        drawLine(50, '═');
        print("                ROOM INFORMATION");
        newl();
        drawLine(50, '═');
        format("reset");
        newl();
        
        if (roomNo.isEmpty()) {
            format("yellow");
            print("No room data available. Please set room data first.");
            format("reset");
            newl();
            return;
        }
        
        print("%-20s: %s", "Room Number", roomNo);
        newl();
        print("%-20s: %s", "Room Type", roomType);
        newl();
        print("%-20s: %.2f sq. meters", "Room Area", roomArea);
        newl();
        print("%-20s: %s", "AC Machine", acMachine ? "Yes" : "No");
        newl();
        
        drawLine(50, '═');
    }
    
    public String getRoomNo() { return roomNo; }
    public String getRoomType() { return roomType; }
    public double getRoomArea() { return roomArea; }
    public boolean hasAcMachine() { return acMachine; }
    
    public void setRoomNo(String roomNo) { this.roomNo = roomNo; }
    public void setRoomType(String roomType) { this.roomType = roomType; }
    public void setRoomArea(double roomArea) { 
        if (roomArea > 0) this.roomArea = roomArea; 
    }
    public void setAcMachine(boolean acMachine) { this.acMachine = acMachine; }
}
