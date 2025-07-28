package cpe121.karlvince.lab7_2;

import static kvx.cli.Std.*;

public class REYES_LE_7_2 {
    public static void main(String[] args) {
        java.util.ArrayList<Student> students = new java.util.ArrayList<>();
        java.util.ArrayList<Teacher> teachers = new java.util.ArrayList<>();
        java.io.BufferedReader in = new java.io.BufferedReader(new java.io.InputStreamReader(System.in));
        while (true) {
            clear();
            print("\nMain Menu\n");
            print("1 Add student\n");
            print("2 Add Teacher\n");
            print("3 Print output\n");
            print("4 Exit\n");
            print("Enter choice: ");
            try {
                int choice = Integer.parseInt(in.readLine());
                switch (choice) {
                    case 1: {
                        print("Enter student name: ");
                        String name = in.readLine();
                        print("Enter age: ");
                        int age = Integer.parseInt(in.readLine());
                        print("Enter gender (M/F): ");
                        char gender = in.readLine().trim().toUpperCase().charAt(0);
                        print("Enter grade: ");
                        double grade = Double.parseDouble(in.readLine());
                        students.add(new Student(name, age, gender, grade));
                        print("Student added!\n");
                        break;
                    }
                    case 2: {
                        print("Enter teacher name: ");
                        String name = in.readLine();
                        print("Enter age: ");
                        int age = Integer.parseInt(in.readLine());
                        print("Enter gender (M/F): ");
                        char gender = in.readLine().trim().toUpperCase().charAt(0);
                        print("Enter salary: ");
                        double salary = Double.parseDouble(in.readLine());
                        teachers.add(new Teacher(name, age, gender, salary));
                        print("Teacher added!\n");
                        break;
                    }
                    case 3: {
                        clear();
                        if (students.isEmpty() && teachers.isEmpty()) {
                            print("No students or teachers to display.\n");
                        } else {
                            int i = 1;
                            for (Student s : students) {
                                print("Student %d:\n", i++);
                                print("Name: %s\n", s.getName());
                                print("Age: %d\n", s.getAge());
                                print("Gender: %c\n", s.getGender());
                                print("Grade %.2f\n", s.getGrade());
                                newl();
                            }
                            i = 1;
                            for (Teacher t : teachers) {
                                print("Teacher %d:\n", i++);
                                print("Name: %s\n", t.getName());
                                print("Age: %d\n", t.getAge());
                                print("Gender: %c\n", t.getGender());
                                print("Salary %.2f\n", t.getSalary());
                                newl();
                            }
                        }
                        print("Press Enter to continue...");
                        in.readLine();
                        break;
                    }
                    case 4:
                        print("Exiting...\n");
                        return;
                    default:
                        print("Invalid choice!\n");
                }
            } catch (Exception e) {
                print("[ERROR] %s\n", e.getMessage());
            }
        }
    }
}
