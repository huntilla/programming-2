package dev.m3s.programming2.homework3;

import java.util.ArrayList;
import java.util.List;

public class AssistantTeacher extends Employee implements Teacher, Payment {
    private List<DesignatedCourse> courses = new ArrayList<DesignatedCourse>();

    AssistantTeacher(String lname, String fname) {
        super(lname, fname);
    }

    public String getEmployeeIdString() {
        return "OY_ASSISTANT_";
    }

    public String getCourses() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < courses.size(); i++) {
            stringBuilder.append("\t\t[course=")
                    .append(courses.get(i).getCourse().toString())
                    .append(", year=").append(courses.get(i).getYear())
                    .append("]\n");
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
                "\t\tAssistant for courses: \n" +
                "%s", getIdString(), getFirstName(), getLastName(), getBirthDate(), calculatePayment(), getCourses());
        return string;
    }
}
