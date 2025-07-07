package cpe121.karlvince.lab5_1;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import static kvx.cli.Std.*;
import static kvx.cli.Util.*;

public class Zoo {
    private List<Animal> animals;
    private Scanner scanner;
    
    public Zoo() {
        this.animals = new ArrayList<>();
        this.scanner = new Scanner(System.in);
    }
    
    public void addAnimal(Animal animal) {
        animals.add(animal);
        format("green");
        print(animal.getName() + " the " + animal.getSpecies() + " has been added to the zoo.");
        format("reset");
        newl();
    }
    
    public boolean removeAnimal(String name) {
        for (int i = 0; i < animals.size(); i++) {
            if (animals.get(i).getName().equalsIgnoreCase(name)) {
                Animal removedAnimal = animals.remove(i);
                format("red");
                print(removedAnimal.getName() + " the " + removedAnimal.getSpecies() + " has been removed from the zoo.");
                format("reset");
                newl();
                return true;
            }
        }
        format("red");
        print("Animal named " + name + " not found in the zoo.");
        format("reset");
        newl();
        return false;
    }
    
    public void feedingTime() {
        newl();
        format("bold");
        drawLine(40, '─');
        print("            FEEDING TIME");
        newl();
        drawLine(40, '─');
        format("reset");
        newl();
        
        if (animals.isEmpty()) {
            format("yellow");
            print("The zoo is empty - no animals to feed!");
            format("reset");
            newl();
        } else {
            for (Animal animal : animals) {
                animal.talk();
            }
        }
        
        format("bold");
        drawLine(40, '─');
        format("reset");
        newl();
        
        format("bright_yellow");
        print("Press Enter to continue...");
        format("reset");
        scanner.nextLine();
    }
    
    public void listAnimals() {
        newl();
        format("bold");
        drawLine(40, '─');
        print("            ZOO ANIMALS");
        newl();
        drawLine(40, '─');
        format("reset");
        newl();
        
        if (animals.isEmpty()) {
            format("yellow");
            print("The zoo is empty!");
            format("reset");
            newl();
        } else {
            for (int i = 0; i < animals.size(); i++) {
                Animal animal = animals.get(i);
                format("bright_white");
                print((i + 1) + ". ");
                format("reset");
                format("bold");
                print(animal.getName());
                format("reset");
                print(" - " + animal.getSpecies() + " (Age: " + animal.getAge() + ")");
                newl();
            }
        }
        
        format("bold");
        drawLine(40, '─');
        format("reset");
        newl();
        
        format("bright_yellow");
        print("Press Enter to continue...");
        format("reset");
        scanner.nextLine();
    }
    
    public int getAnimalCount() {
        return animals.size();
    }
}
