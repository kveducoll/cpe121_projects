package cpe121.karlvince.lab5_1;

import static kvx.cli.Std.*;

public class Monkey extends Animal {
    public Monkey(String name, int age) {
        super(name, age, "Monkey");
    }
    
    @Override
    public void talk() {
        format("bold", "green");
        print("OHH OHH OHH AHH AHH AHH ");
        format("reset");
        print("I am a " + species + " named " + name + " and I am " + age + " years old.");
        newl();
    }
}
