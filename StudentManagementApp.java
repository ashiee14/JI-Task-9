import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

class Student {
    private int id;
    private String name;
    private double marks;

    public Student(int id, String name, double marks) {
        this.id = id;
        this.name = name;
        this.marks = marks;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public double getMarks() { return marks; }

    // Overriding equals and hashCode to identify duplicates by student ID
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Student)) return false;
        Student other = (Student) obj;
        return this.id == other.id;
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(id);
    }

    @Override
    public String toString() {
        return "Student [ID=" + id + ", Name=" + name + ", Marks=" + marks + "]";
    }
}

public class StudentManagementApp {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        // 1. Store students in ArrayList
        List<Student> students = new ArrayList<>();

        // 2. Use HashMap for fast lookup by ID
        Map<Integer, Student> studentMap = new HashMap<>();

        // 4. Use Set to remove duplicates
        Set<Student> studentSet = new LinkedHashSet<>();

        // Adding students (with duplicate ID)
        Student s1 = new Student(1, "Alice", 85.5);
        Student s2 = new Student(2, "Bob", 78.0);
        Student s3 = new Student(3, "Charlie", 92.3);
        Student s4 = new Student(2, "Bob Duplicate", 78.0);

        students.add(s1);
        students.add(s2);
        students.add(s3);
        students.add(s4);

        // Map insertion (duplicates overwrite by key)
        studentMap.put(s1.getId(), s1);
        studentMap.put(s2.getId(), s2);
        studentMap.put(s3.getId(), s3);
        studentMap.put(s4.getId(), s4);

        // Add all to set to remove duplicates
        studentSet.addAll(students);

        // 6. Demonstrate collection hierarchy
        System.out.println("COLLECTION HEIRARCHY DEMO");
        System.out.println("List (ArrayList) - allows duplicates, preserves order:");
        printCollection(students);

        System.out.println("\nSet (LinkedHashSet) - removes duplicates, preserves insertion order:");
        printCollection(studentSet);

        System.out.println("\nMap (HashMap) - key-value store, fast lookup:");
        for (Map.Entry<Integer, Student> entry : studentMap.entrySet()) {
            System.out.println("ID: " + entry.getKey() + " -> " + entry.getValue());
        }

        // 3. Sorting using Comparator
        List<Student> uniqueStudents = new ArrayList<>(studentSet);

        System.out.println("\nStudents Sorted by Marks (descending)");
        sortByMarksDescending(uniqueStudents);
        printCollection(uniqueStudents);

        System.out.println("\nStudents Sorted by Name (alphabetical)");
        sortByName(uniqueStudents);
        printCollection(uniqueStudents);

        // 8. Print formatted report of unique students
        System.out.println("\n\nFORMATTED STUDENT REPORT");
        printFormattedReport(uniqueStudents);

        // 7. Memory optimization: duplicates removed by Set, no redundant objects stored
        System.out.println("\n(Note: Duplicate students removed from collection to optimize memory.)");

        sc.close();
    }

    // 5. Helper: print collection using enhanced for loop
    private static void printCollection(Collection<Student> students) {
        for (Student s : students) {
            System.out.println(s);
        }
    }

    // 3. Sort by marks descending
    private static void sortByMarksDescending(List<Student> students) {
        students.sort(Comparator.comparingDouble(Student::getMarks).reversed());
    }

    // 3. Sort by name ascending (alphabetical)
    private static void sortByName(List<Student> students) {
        students.sort(Comparator.comparing(Student::getName));
    }

    // 8. Formatted report printing using printf
    private static void printFormattedReport(List<Student> students) {
        System.out.printf("%-5s %-15s %-7s%n", "ID", "Name", "Marks");
        System.out.println("-----------------------------------");
        for (Student s : students) {
            System.out.printf("%-5d %-15s %-7.2f%n", s.getId(), s.getName(), s.getMarks());
        }
    }
}
