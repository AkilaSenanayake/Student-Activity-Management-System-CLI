public class Module {
    private int marks;
    private String grade;

    public Module() {
    }

    public int getMarks() {
        return marks;
    }

    public void setMarks(int marks) {
        this.marks = marks;
        setGrade();
    }

    public String getGrade() {
        return grade;
    }

    private void setGrade() {
        if (marks >= 80) {
            grade = "Distinction";
        } else if (marks >= 70) {
            grade = "Merit";
        } else if (marks >= 40) {
            grade = "Pass";
        } else {
            grade = "Fail";
        }
    }
}