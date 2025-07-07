package cpe121.karlvince.lab5_1;

public abstract class Animal {
    protected String name;
    protected int age;
    protected String species;
    
    public Animal(String name, int age, String species) {
        this.name = name;
        this.age = age;
        this.species = species;
    }
    
    public abstract void talk();
    
    public String getName() { return name; }
    public int getAge() { return age; }
    public String getSpecies() { return species; }
}
