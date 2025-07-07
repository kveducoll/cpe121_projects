package cpe121.karlvince.lab5_1;

import static kvx.cli.Std.*;

public class Tiger extends Animal {
    public Tiger(String name, int age) {
        super(name, age, "Tiger");
    }
    
    @Override
    public void talk() {
        format("bold", "yellow");
        print("GROWL! ");
        format("reset");
        print("I am a " + species + " named " + name + " and I am " + age + " years old.");
        newl();
    }
}
