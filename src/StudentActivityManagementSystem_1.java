import java.io.*;
import java.util.InputMismatchException;
import java.util.Scanner;

public class StudentActivityManagementSystem_1 {
    private static final int maxStudents = 100; //set maximum number can be registered
    private static Student[] students = new Student[maxStudents]; //array to store student objects
    private static int studentCount = 0;
    private static final String fileName = "Data/Student_list.txt"; //create file name for store and load details

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        //Main loop for display menu and get user input
        while (true) {
            printMenu();
            int choice;
            try{
                choice = scanner.nextInt();
            } catch(InputMismatchException e){
                System.out.println("Invalid option!.Please try again.");
                scanner.nextLine();
                continue;
            }
            scanner.nextLine();
            //Execute selected method by user option
            switch (choice) {
                case 1:
                    checkAvailableSeats();
                    break;
                case 2:
                    registerStudent(scanner);
                    break;
                case 3:
                    deleteStudent(scanner);
                    break;
                case 4:
                    findStudent(scanner);
                    break;
                case 5:
                    storeStudentDetails();
                    break;
                case 6:
                    loadStudentDetails();
                    break;
                case 7:
                    viewStudentsByName();
                    break;
                case 0:
                    System.exit(0);
                default:
                    System.out.println("Invalid!. Please try again.");
            }
        }
    }

    //Method to display main menu
    private static void printMenu() {
        System.out.println("Menu:");
        System.out.println("- 1. Check available seats");
        System.out.println("- 2. Register student (with ID and Name)");
        System.out.println("- 3. Delete student");
        System.out.println("- 4. Find student (with student ID)");
        System.out.println("- 5. Store student details into a file");
        System.out.println("- 6. Load student details from the file to the system");
        System.out.println("- 7. View the list of students based on their names");
        System.out.println("- 0. Exit the program");
        System.out.print("Enter your option : ");
    }

    private static void checkAvailableSeats() {
        System.out.println("Available seats : " + (maxStudents - studentCount));
    }

    private static void registerStudent(Scanner scanner) {
        if (studentCount >= maxStudents) {
            System.out.println("No seats available!.");
            return;
        }
        System.out.print("Enter the student ID : ");
        String id = scanner.nextLine();
        if (!id.matches("w\\d{7}" )){
            System.out.println("Invalid student ID format. It should be 8 characters long with 'w' in first");
            return;
        }
        System.out.print("Enter the student name : ");
        String name = scanner.nextLine();
        students[studentCount++] = new Student(id, name);
        System.out.println("Student registered!.");
    }

    private static void deleteStudent(Scanner scanner) {
        System.out.print("Enter the student ID to delete : ");
        String id = scanner.nextLine();
        for (int i = 0; i < studentCount; i++) {
            if (students[i].getId().equals(id)) {
                students[i] = students[--studentCount];
                students[studentCount] = null;
                System.out.println("Student deleted!.");
                return;
            }
        }
        System.out.println("Student not found!.");
    }

    private static void findStudent(Scanner scanner) {
        System.out.print("Enter the student ID to find : ");
        String id = scanner.nextLine();
        for (int i = 0; i < studentCount; i++) {
            if (students[i].getId().equals(id)) {
                System.out.println(students[i]);
                return;
            }
        }
        System.out.println("Student not found!.");
    }

    private static void viewStudentsByName() {
        sortStudentsByName();
        for (int i = 0; i < studentCount; i++) {
            System.out.println(students[i]);
        }
    }

    private static void sortStudentsByName() {
        for (int i = 0; i < studentCount - 1; i++) {
            for (int j = 0; j < studentCount - i - 1; j++) {
                if (students[j].getName().compareTo(students[j + 1].getName()) > 0) {
                    Student temp = students[j];
                    students[j] = students[j + 1];
                    students[j + 1] = temp;
                }
            }
        }
    }

    private static void storeStudentDetails() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(fileName))) {
            for (int i = 0; i < studentCount; i++) {
                Student student = students[i];
                writer.println(student.getId() + "," + student.getName() + "," +
                        student.getModule1Marks() + "," + student.getModule2Marks() + "," +
                        student.getModule3Marks());
            }
            System.out.println("Successfully saved student details to file!.");
        } catch (IOException e) {
            System.out.println("Error saving student details! : " + e.getMessage());
        }
    }

    private static void loadStudentDetails() {
        int count = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null && count < students.length) {
                String[] parts = line.split(",");
                if (parts.length == 5) {
                    Student student = new Student(parts[0], parts[1]);
                    student.setMarks(Integer.parseInt(parts[2]), Integer.parseInt(parts[3]), Integer.parseInt(parts[4]));
                    students[count++] = student;
                }
            }
            studentCount = count;
            System.out.println("Student details loaded from file!.");
        } catch (IOException e) {
            System.out.println("Error loading student details! : " + e.getMessage());
        }
    }
}