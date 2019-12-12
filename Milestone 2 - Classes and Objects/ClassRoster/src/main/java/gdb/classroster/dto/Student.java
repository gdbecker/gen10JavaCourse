package gdb.classroster.dto;

/**
 * @date Thursday December 12, 2019
 * @author garrettbecker
 */

public class Student {
    private String firstName;
    private String lastName;
    private String studentId; //read-only variable
    private String cohort; //Java or .Net + cohort month/year

    //Constructor
    public Student(String studentId) {
        this.studentId = studentId;
    }

    //Getters and setters
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getStudentId() {
        return studentId;
    }

    public String getCohort() {
        return cohort;
    }

    public void setCohort(String cohort) {
        this.cohort = cohort;
    }
}
