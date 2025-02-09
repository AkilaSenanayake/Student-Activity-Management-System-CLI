import java.io.*;
import java.util.InputMismatchException;
import java.util.Scanner;

public class StudentActivityManagementSystem_2 {
    private static final int maxStudents = 100; //set maximum number can be registered
    private static Student[] students = new Student[maxStudents]; //array to store student objects
    private static int studentCount = 0;
    private static final String FILE_NAME = "Data/Student_list.txt"; //create file name for store and load details

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
                case 8:
                    additionalMenu(scanner);
                    break;
                case 0:
                    System.exit(0);
                default:
                    System.out.println("Invalid option!. Please try again.");
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
        System.out.println("- 8. More controls");
        System.out.println("- 0. Exit the program");
        System.out.print("Enter your option : ");
    }

    //Method to check and display available seats
    private static void checkAvailableSeats() {
        System.out.println("Available seats : " + (maxStudents - studentCount));
    }

    //Method to new student register
    private static void registerStudent(Scanner scanner) {
        if (studentCount >= maxStudents) {
            System.out.println("No seats available!");
            return;
        }
        System.out.print("Enter the student ID : ");
        String id = scanner.nextLine();
        if (!id.matches("w\\d{7}" )){
            System.out.println("Error: Invalid student ID format. It should be 8 characters long with 'w' in first");
            return;
        }
        System.out.print("Enter the student name : ");
        String name = scanner.nextLine();
        students[studentCount++] = new Student(id, name);
        System.out.println("Student registered!.");
    }

    //method to delete student by ID
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

    //method to find student by ID
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

    //method to display sorted list of students
    private static void viewStudentsByName() {
        sortStudentsByName();
        for (int i = 0; i < studentCount; i++) {
            System.out.println(students[i]);
        }
    }

    //help method to sort students using bubble sort
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

    //Method to display additional menu
    private static void additionalMenu(Scanner scanner) {
        System.out.println("- a. Add student name");
        System.out.println("- b. Add module marks");
        System.out.println("- c. Generate summary");
        System.out.println("- d. Generate complete report");
        System.out.print("Enter your option : ");
        char choice = scanner.next().charAt(0);
        scanner.nextLine(); // consume newline
        switch (choice) {
            case 'a':
                addStudentName(scanner);
                break;
            case 'b':
                addModuleMarks(scanner);
                break;
            case 'c':
                generateSummary();
                break;
            case 'd':
                generateCompleteReport();
                break;
            default:
                System.out.println("Invalid!. Please try again.");
        }
    }

    //Method to update student name by ID
    private static void addStudentName(Scanner scanner) {
        System.out.print("Enter student ID to add name: ");
        String id = scanner.nextLine();
        for (int i = 0; i < studentCount; i++) {
            if (students[i].getId().equals(id)) {
                System.out.print("Enter student name: ");
                String name = scanner.nextLine();
                students[i].setName(name);
                System.out.println("Student name added.");
                return;
            }
        }
        System.out.println("Student not found!.");
    }

    //Method to add module marks by using ID
    private static void addModuleMarks(Scanner scanner) {
        System.out.print("Enter the student ID to add marks : ");
        String id = scanner.nextLine();
        for (int i = 0; i < studentCount; i++) {
            if (students[i].getId().equals(id)) {
                System.out.print("Enter the marks for Module 1 : ");
                int mark1 = scanner.nextInt();
                System.out.print("Enter the marks for Module 2 : ");
                int mark2 = scanner.nextInt();
                System.out.print("Enter the marks for Module 3 : ");
                int mark3 = scanner.nextInt();
                scanner.nextLine(); // consume newline
                students[i].setMarks(mark1, mark2, mark3);
                System.out.println("Module marks added successfully!.");
                return;
            }
        }
        System.out.println("Student not found!.");
    }

    // Method to display a summary report of student registrations
    private static void generateSummary() {
        int totalRegistrations = studentCount;
        int passCount1 = 0, passCount2 = 0, passCount3 = 0;
        for (int i = 0; i < studentCount; i++) {
            if (students[i].getModule1Marks() >= 40) passCount1++;
            if (students[i].getModule2Marks() >= 40) passCount2++;
            if (students[i].getModule3Marks() >= 40) passCount3++;
        }
        System.out.println("Total student registrations : " + totalRegistrations);
        System.out.println("Students passed in Module 1 : " + passCount1);
        System.out.println("Students passed in Module 2 : " + passCount2);
        System.out.println("Students passed in Module 3 : " + passCount3);
    }

    // Method to display complete report for all students
    private static void generateCompleteReport() {
        sortStudentsByAverage();
        for (int i = 0; i < studentCount; i++) {
            System.out.println(students[i].fullReport());
        }
    }


    // help method to sort students by average marks using bubble sort
    private static void sortStudentsByAverage() {
        for (int i = 0; i < studentCount - 1; i++) {
            for (int j = 0; j < studentCount - i - 1; j++) {
                if (students[j].getAverageMarks() < students[j + 1].getAverageMarks()) {
                    Student temp = students[j];
                    students[j] = students[j + 1];
                    students[j + 1] = temp;
                }
            }
        }
    }

    // Method to save student details to the  file
    private static void storeStudentDetails() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_NAME))) {
            for (int i = 0; i < studentCount; i++) {
                Student student = students[i];
                writer.println(student.getId() + "," + student.getName() + "," +
                        student.getModule1Marks() + "," + student.getModule2Marks() + "," +
                        student.getModule3Marks());
            }
            System.out.println("Student details saved to file!.");
        } catch (IOException e) {
            System.out.println("Error saving details : " + e.getMessage());
        }
    }

    //Method to load details from file
    private static void loadStudentDetails() {
        int count = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
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
            System.out.println("Error loading student details : " + e.getMessage());
        }
    }
}