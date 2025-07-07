package cpe121.karlvince.lab5_1;

import static kvx.cli.Std.*;

public class Lion extends Animal {
    public Lion(String name, int age) {
        super(name, age, "Lion");
    }
    
    @Override
    public void talk() {
        format("bold", "red");
        print("RAWR! ");
        format("reset");
        print("I am a " + species + " named " + name + " and I am " + age + " years old.");
        newl();
    }
}
