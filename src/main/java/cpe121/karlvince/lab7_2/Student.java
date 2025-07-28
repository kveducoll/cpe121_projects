package cpe121.karlvince.lab7_2;

public class Student extends Person {
    private double grade;

    public Student() {}

    public Student(String name, int age, char gender, double grade) {
        super(name, age, gender);
        this.grade = grade;
    }

    public double getGrade() {
        return grade;
    }

    public void setGrade(double grade) {
        this.grade = grade;
    }
}
