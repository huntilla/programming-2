package dev.m3s.programming2.homework3;

import java.util.ArrayList;
import java.util.List;

public class ResponsibleTeacher extends Employee implements Teacher, Payment {
    private List<DesignatedCourse> courses = new ArrayList<>();

    ResponsibleTeacher(String lname, String fname) {
        super(lname, fname);
    }

    public String getEmployeeIdString() {
        return "OY_TEACHER_";
    }

    public String getCourses() {
        StringBuilder stringBuilder = new StringBuilder();
        for (DesignatedCourse course:courses) {
            if (course.isResponsible()) {
                stringBuilder.append("\t\tResponsible teacher: ");
            } else {
                stringBuilder.append("\t\tTeacher: ");
            }
            stringBuilder.append(course.toString()).append("\n");
        }
        return stringBuilder.toString();
    }

    public void setCourses(List<DesignatedCourse> courses) {
        if (courses != null) {
            this.courses = new ArrayList<>(courses);
        }
    }

    public String toString() {
        String string = String.format("Teacher id: %s\n" +
                "\t\tFirst name: %s, Last name: %s\n" +
                "\t\tBirthdate: %s\n" +
                "\t\tSalary: %.02f\n" +
                "\t\tTeacher for courses:\n" +
                        " %s", getIdString(),
                getFirstName(), getLastName(), getBirthDate(), this.calculatePayment(), getCourses());
        return string;
    }

}
