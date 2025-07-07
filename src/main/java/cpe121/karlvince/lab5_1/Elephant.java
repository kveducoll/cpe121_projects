package cpe121.karlvince.lab5_1;

import static kvx.cli.Std.*;

public class Elephant extends Animal {
    public Elephant(String name, int age) {
        super(name, age, "Elephant");
    }
    
    @Override
    public void talk() {
        format("bold", "blue");
        print("EEEERNK! ");
        format("reset");
        print("I am a " + species + " named " + name + " and I am " + age + " years old.");
        newl();
    }
}
