package cpe121.karlvince.lab5_1;

import static kvx.cli.Std.*;
import static kvx.cli.Util.*;

public class REYES_LE_5_1 {
    private Zoo zoo;
    private Menu menu;
    
    public REYES_LE_5_1() {
        this.zoo = new Zoo();
        String[] menuOptions = {
            "Add Animal",
            "Remove Animal",
            "List All Animals",
            "Feeding Time",
            "Exit"
        };
        this.menu = new Menu(menuOptions);
    }
    
    public void run() {
        clear();
        format("bold", "bright_green");
        print("Welcome to the Zoo Management System!");
        format("reset");
        newl(2);
        
        while (true) {
            int choice = menu.displayMenuAndGetChoice();
            
            switch (choice) {
                case 1:
                    clear();
                    addAnimal();
                    break;
                case 2:
                    clear();
                    removeAnimal();
                    break;
                case 3:
                    clear();
                    zoo.listAnimals();
                    break;
                case 4:
                    clear();
                    zoo.feedingTime();
                    break;
                case 5:
                    return;
                default:
                    clear();
                    format("red");
                    print("Invalid choice!");
                    format("reset");
                    newl();
            }
        }
    }
    
    private void addAnimal() {
        newl();
        format("bold");
        drawLine(40, '─');
        print("            ADD ANIMAL");
        newl();
        drawLine(40, '─');
        format("reset");
        newl();
        
        print("Choose animal type:");
        newl(2);
        
        format("red");
        print("  1 ─ Lion");
        format("reset");
        newl();
        
        format("yellow");
        print("  2 ─ Tiger");
        format("reset");
        newl();
        
        format("blue");
        print("  3 ─ Elephant");
        format("reset");
        newl();
        
        format("green");
        print("  4 ─ Monkey");
        format("reset");
        newl(2);
        
        drawLine(40, '─');
        newl();
        
        int animalType = menu.getIntInput("Enter animal type (1-4): ");
        while (animalType < 1 || animalType > 4) {
            format("red");
            print("Invalid choice! Please enter 1-4.");
            format("reset");
            newl();
            animalType = menu.getIntInput("Enter animal type (1-4): ");
        }
        
        String name = menu.getStringInput("Enter animal name: ");
        while (name.isEmpty()) {
            format("red");
            print("Name cannot be empty!");
            format("reset");
            newl();
            name = menu.getStringInput("Enter animal name: ");
        }
        
        int age = menu.getIntInput("Enter animal age: ");
        
        Animal animal = null;
        switch (animalType) {
            case 1:
                animal = new Lion(name, age);
                break;
            case 2:
                animal = new Tiger(name, age);
                break;
            case 3:
                animal = new Elephant(name, age);
                break;
            case 4:
                animal = new Monkey(name, age);
                break;
        }
        
        zoo.addAnimal(animal);
    }
    
    private void removeAnimal() {
        newl();
        format("bold");
        drawLine(40, '─');
        print("           REMOVE ANIMAL");
        newl();
        drawLine(40, '─');
        format("reset");
        newl();
        
        if (zoo.getAnimalCount() == 0) {
            format("yellow");
            print("The zoo is empty! No animals to remove.");
            format("reset");
            newl();
            return;
        }
        
        zoo.listAnimals();
        String name = menu.getStringInput("Enter the name of the animal to remove: ");
        zoo.removeAnimal(name);
    }
    
    public static void main(String[] args) {
        REYES_LE_5_1 app = new REYES_LE_5_1();
        app.run();
    }
}