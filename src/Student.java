public class Student {
    private String id;
    private String name;
    private int module1Marks;
    private int module2Marks;
    private int module3Marks;

    public Student(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getModule1Marks() {
        return module1Marks;
    }

    public int getModule2Marks() {
        return module2Marks;
    }

    public int getModule3Marks() {
        return module3Marks;
    }

    public void setMarks(int module1Marks, int module2Marks, int module3Marks) {
        this.module1Marks = module1Marks;
        this.module2Marks = module2Marks;
        this.module3Marks = module3Marks;
    }

    public double getAverageMarks() {
        return (module1Marks + module2Marks + module3Marks) / 3.0;
    }

    public String getGrade() {
        double average = getAverageMarks();
        if (average >= 80) {
            return "Distinction";
        } else if (average >= 70) {
            return "Merit";
        } else if (average >= 40) {
            return "Pass";
        } else {
            return "Fail";
        }
    }

    @Override
    public String toString() {
        return "ID: " + id + ", Name: " + name + ", Module 1 Marks: " + module1Marks +
                ", Module 2 Marks: " + module2Marks + ", Module 3 Marks: " + module3Marks;
    }

    public String fullReport() {
        return toString() + ", Average Marks: " + getAverageMarks() + ", Grade: " + getGrade();
    }
}