package StudentAttPerTacker;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StudentManager manager = new StudentManager();

        while (true) {
            System.out.println("\n📘 Student Attendance & Performance Tracker");
            System.out.println("1. Add Student");
            System.out.println("2. View All Students");
            System.out.println("3. Update Student");
            System.out.println("4. Delete Student");
            System.out.println("5. Mark Attendance");
            System.out.println("6. Add Mark");
            System.out.println("7. Exit");
            System.out.print("Choose an option: ");
            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter ID: ");
                    int id = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Enter Name: ");
                    String name = sc.nextLine();
                    System.out.print("Enter Age: ");
                    int age = sc.nextInt();
                    manager.addStudent(new Student(id, name, age, 0, new ArrayList<>()));
                    break;

                case 2:
                    manager.viewAllStudents();
                    break;

                case 3:
                    System.out.print("Enter ID to update: ");
                    int uid = sc.nextInt();
                    sc.nextLine();
                    System.out.print("New Name: ");
                    String newName = sc.nextLine();
                    System.out.print("New Age: ");
                    int newAge = sc.nextInt();
                    manager.updateStudent(uid, newName, newAge);
                    break;

                case 4:
                    System.out.print("Enter ID to delete: ");
                    int did = sc.nextInt();
                    manager.deleteStudent(did);
                    break;

                case 5:
                    System.out.print("Enter ID to mark attendance: ");
                    int aid = sc.nextInt();
                    manager.markAttendance(aid);
                    break;

                case 6:
                    System.out.print("Enter ID to add mark: ");
                    int mid = sc.nextInt();
                    System.out.print("Enter mark: ");
                    int mark = sc.nextInt();
                    manager.addMark(mid, mark);
                    break;

                case 7:
                    System.out.println("👋 Exiting...");
                    sc.close();
                    return;

                default:
                    System.out.println("Invalid option. Try again.");
            }
        }
    }
}
