package dev.m3s.programming2.homework2;

import java.util.Arrays;

public class Degree {
    private static final int MAX_COURSES = 50;
    private int count;
    private String degreeTitle = ConstantValues.NO_TITLE;
    private String titleOfThesis = ConstantValues.NO_TITLE;
    private StudentCourse[] myCourses = new StudentCourse[MAX_COURSES];

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
    public StudentCourse[] getCourses() {
        return myCourses;
    }

    public void addStudentCourses(StudentCourse[] courses) {
        if (courses != null && count < MAX_COURSES) {
            for (int i = 0; i < courses.length; i++) {
                if (!addStudentCourse(courses[i])) break;
            }
        }
    }

    public boolean addStudentCourse(StudentCourse course) {
        if (course != null && getCount() < MAX_COURSES) {
            myCourses[count] = course;
            count ++;
            return true;
        }
        return false;
    }

    public String getDegreeTitle() {
        return degreeTitle;
    }

    public void setDegreeTitle(String degreeTitle) {
        if (degreeTitle != null) {
            this.degreeTitle = degreeTitle;
        }
    }

    public String getTitleOfThesis() {
        return titleOfThesis;
    }

    public void setTitleOfThesis(String titleOfThesis) {
        if (titleOfThesis != null) {
            this.titleOfThesis = titleOfThesis;
        }
    }

    public double getCreditsByBase(Character base) {
        double credits = 0;

        for (int i = 0; i < getCourses().length; i++) {
            if (isCourseCompleted(getCourses()[i]) && getCourses()[i].getCourse().getCourseBase() == base) {
                credits += getCourses()[i].getCourse().getCredits();
            }
        }
        return credits;
    }

    public double getCreditsByType(final int courseType) {
        double credits = 0;

        for (int i = 0; i < getCourses().length; i++) {
            if (isCourseCompleted(getCourses()[i]) && getCourses()[i].getCourse().getCourseType() == courseType) {
                credits += getCourses()[i].getCourse().getCredits();
            }
        }
        return credits;
    }

    public double getCredits() {
        double credits = 0;
            for (int i = 0; i < getCourses().length; i++) {
                if (isCourseCompleted(getCourses()[i])) {
                    credits += getCourses()[i].getCourse().getCredits();
                }
            }
        return credits;
    }

    private boolean isCourseCompleted(StudentCourse c) {
        if (c != null) {
            if (c.getCourse().isNumericGrade() && c.isPassed()) {
                return true;
            }
            return c.isPassed();
        }
        return false;
    }

    public void printCourses() {
        for (int i = 0; i < getCourses().length; i++) {
            if (getCourses()[i] != null) {
                System.out.println(getCourses()[i]);
            }
        }
    }

    public String toStringCourses() {
        StringBuilder string;
        if (getCourses()[0] != null) {
            string = new StringBuilder(String.format("\n\t\t%d. %s\n", 1, getCourses()[0].toString()));
            int numCourses = getCourses().length;
            for (int i = 1; i < numCourses && getCourses()[i] != null; i++) {
                if (i == numCourses - 1 || getCourses()[i + 1] == null) {
                    string.append(String.format("\t\t%d. %s", i + 1, getCourses()[i].toString()));
                } else {
                    string.append(String.format("\t\t%d. %s\n", i + 1, getCourses()[i].toString()));
                }
            }
            string.append("]");
            return string.toString();
        }
        return "]";
    }

    @Override
    public String toString() {
        String string = String.format("Degree [Title: \"%s\" (courses: %d)\n" +
                "\t\tThesis title: \"%s\"" +
                "%s\n", getDegreeTitle(), getCount(), getTitleOfThesis(), toStringCourses());

        return string;
    }

    public static void main(String[] args) {
//        Degree degree1 = new Degree();
//
//        Course course1 = new Course("Programming 1", 811104, 'P', 1, 1, 5, false);
//        Course course2 = new Course("Programming 2", 811104, 'P', 1, 1, 5, false);
//        StudentCourse studentCourse2 = new StudentCourse(course1, 2, 2013);
//        StudentCourse studentCourse1 = new StudentCourse(course2, 2, 2013);
//        degree1.addStudentCourse(studentCourse2);
//        degree1.addStudentCourse(studentCourse1);
//        degree1.setDegreeTitle("Jaas queen");
//        degree1.setTitleOfThesis("UwU Senpai");
//
//        System.out.println(degree1);
    }
}
