package cpe121.karlvince.lab6_3;

import static kvx.cli.Std.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class AddrbkPersistenceHandler {
    
    private static final String DB_FILENAME = "kvx.adbk.db";
    
    public static void saveToJson(AdrbkMechanics addressBook) throws IOException {
        if (addressBook.getEntryCount() == 0) {
            format("white");
            print("[WARN] No entries to save.");
            format("reset");
            newl();
            return;
        }
        
        StringBuilder jsonBuilder = new StringBuilder();
        jsonBuilder.append("[\n");
        
        for (int i = 0; i < addressBook.getEntryCount(); i++) {
            AdrbkEntryHandler entry = addressBook.getEntries()[i];
            jsonBuilder.append("  {\n");
            jsonBuilder.append(String.format("    \"name\": \"%s\",\n", escapeJson(entry.getName())));
            jsonBuilder.append(String.format("    \"address\": \"%s\",\n", escapeJson(entry.getAddress())));
            jsonBuilder.append(String.format("    \"telephoneNumber\": \"%s\",\n", escapeJson(entry.getTelephoneNumber())));
            jsonBuilder.append(String.format("    \"emailAddress\": \"%s\"\n", escapeJson(entry.getEmailAddress())));
            jsonBuilder.append("  }");
            
            if (i < addressBook.getEntryCount() - 1) {
                jsonBuilder.append(",");
            }
            jsonBuilder.append("\n");
        }
        
        jsonBuilder.append("]");
        
        try {
            Files.write(Paths.get(DB_FILENAME), jsonBuilder.toString().getBytes(), 
                       StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
            
            format("white");
            print("[INFO] Address book successfully saved.");
            format("reset");
            newl();
            format("white");
            print("[INFO] Total entries saved: %d", addressBook.getEntryCount());
            format("reset");
            newl();
            
        } catch (IOException e) {
            format("white");
            print("[ERROR] Error saving to file: %s", e.getMessage());
            format("reset");
            newl();
            throw e;
        }
    }
    
    public static AdrbkMechanics loadFromJson() throws IOException {
        AdrbkMechanics addressBook = new AdrbkMechanics();
        File file = new File(DB_FILENAME);
        
        if (!file.exists()) {
            format("white");
            print("[WARN] Database file %s not found. Starting with empty address book.", DB_FILENAME);
            format("reset");
            newl();
            return addressBook;
        }
        
        try {
            String content = new String(Files.readAllBytes(Paths.get(DB_FILENAME)));
            content = content.trim();
            
            if (content.isEmpty() || content.equals("[]")) {
                format("white");
                print("[WARN] Database file is empty. Starting with empty address book.");
                format("reset");
                newl();
                return addressBook;
            }
            
            // parser (kk)
            String[] entries = content.split("\\},\\s*\\{");
            
            for (String entryStr : entries) {
                entryStr = entryStr.replace("[", "").replace("]", "")
                                 .replace("{", "").replace("}", "")
                                 .trim();
                
                if (entryStr.isEmpty()) continue;
                
                String name = extractJsonValue(entryStr, "name");
                String address = extractJsonValue(entryStr, "address");
                String telephoneNumber = extractJsonValue(entryStr, "telephoneNumber");
                String emailAddress = extractJsonValue(entryStr, "emailAddress");
                
                if (!name.isEmpty() && !address.isEmpty() && 
                    !telephoneNumber.isEmpty() && !emailAddress.isEmpty()) {
                    
                    AdrbkEntryHandler entry = new AdrbkEntryHandler(
                        unescapeJson(name), 
                        unescapeJson(address), 
                        unescapeJson(telephoneNumber), 
                        unescapeJson(emailAddress)
                    );
                    
                    // Note Ayawg kalimi og uncomment (kk)
                    addressBook.addEntryDirect(entry);
                }
            }
            
            format("white");
            print("[INFO] Address book loaded from %s", DB_FILENAME);
            format("reset");
            newl();
            format("white");
            print("[INFO] Total entries loaded: %d", addressBook.getEntryCount());
            format("reset");
            newl();
            
        } catch (IOException e) {
            format("white");
            print("[ERROR] Error loading from file: %s", e.getMessage());
            format("reset");
            newl();
            throw e;
        }
        
        return addressBook;
    }
    
    // (kk)
    private static String extractJsonValue(String jsonStr, String key) {
        String pattern = "\"" + key + "\"\\s*:\\s*\"([^\"]+)\"";
        java.util.regex.Pattern p = java.util.regex.Pattern.compile(pattern);
        java.util.regex.Matcher m = p.matcher(jsonStr);
        
        if (m.find()) {
            return m.group(1);
        }
        return "";
    }
    
    private static String unescapeJson(String input) {
        if (input == null) {
            return "";
        }
        return input.replace("\\\\", "\\")
                    .replace("\\\"", "\"")
                    .replace("\\n", "\n")
                    .replace("\\r", "\r")
                    .replace("\\t", "\t");
    }
    
    private static String escapeJson(String input) {
        if (input == null) {
            return "";
        }
        return input.replace("\\", "\\\\")
                    .replace("\"", "\\\"")
                    .replace("\n", "\\n")
                    .replace("\r", "\\r")
                    .replace("\t", "\\t");
    }
    
    public static void waitForEnter(BufferedReader in) throws IOException {
        newl();
        format("yellow");
        print("Press Enter to continue...");
        format("reset");
        in.readLine();
        clear();
    }
}
