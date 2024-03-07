package dev.m3s.programming2.homework3;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Degree {
    private static final int MAX_COURSES = 50;
    private int count;
    private String degreeTitle = ConstantValues.NO_TITLE;
    private String titleOfThesis = ConstantValues.NO_TITLE;
    private List<StudentCourse> myCourses = new ArrayList<StudentCourse>();
    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
    public List<StudentCourse> getCourses() {
        return myCourses;
    }

    public void addStudentCourses(List<StudentCourse> courses) {
        if (courses != null) {
            for (StudentCourse course : courses) {
                if (!addStudentCourse(course)) break;
            }
        }
    }

    public boolean addStudentCourse(StudentCourse course) {
        if (course != null && getCourses().size() < MAX_COURSES) {
            myCourses.add(course);
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
        for (int i = 0; i < getCourses().size(); i++) {
            if (isCourseCompleted(myCourses.get(i)) && myCourses.get(i).getCourse().getCourseBase() == base) {
                credits += myCourses.get(i).getCourse().getCredits();
            }
        }
        return credits;
    }

    public double getCreditsByType(final int courseType) {
        double credits = 0;
        for (int i = 0; i < getCourses().size(); i++) {
            if (isCourseCompleted(myCourses.get(i)) && myCourses.get(i).getCourse().getCourseType() == courseType) {
                credits += myCourses.get(i).getCourse().getCredits();
            }
        }
        return credits;
    }

    public double getCredits() {
        double credits = 0;
            for (int i = 0; i < getCourses().size(); i++) {
                if (isCourseCompleted(myCourses.get(i))) {
                    credits += myCourses.get(i).getCourse().getCredits();
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
        for (int i = 0; i < getCourses().size(); i++) {
            if (getCourses().get(i) != null) {
                System.out.println(getCourses().get(i));
            }
        }
    }

    public List<Double> getGPA(int type) {
        List<Double> returnlist = new ArrayList<>();

        if (type == ConstantValues.ALL) {
            List<Double> bachelorGPA = getGPA(ConstantValues.OPTIONAL);
            List<Double> masterGPA = getGPA(ConstantValues.MANDATORY);

            for (int i = 0; i < 2; i++) {
                returnlist.add(bachelorGPA.get(i) + masterGPA.get(i));
            }
            returnlist.add(returnlist.get(0) / returnlist.get(1));
            return returnlist;
        }

        double sum = 0;
        double count = 0;
        double average = 0;

        for (int i = 0; i < getCourses().size(); i++) {
            if (getCourses().get(i).getCourse().getCourseType() == type
                    && getCourses().get(i).getCourse().isNumericGrade()) {
                sum += getCourses().get(i).getGradeNum();
                count++;
            }
        }
        if (count > 0) {
            average = sum / count;
        }
        returnlist.add(sum);
        returnlist.add(count);
        returnlist.add(Math.round(average * 100d) / 100d);
        return returnlist;
    }

    public String toStringCourses() {
        StringBuilder string;
        if (!getCourses().isEmpty() && getCourses().get(0) != null) {
            string = new StringBuilder(String.format("\n\t\t%d. %s\n", 1, getCourses().get(0).toString()));
            int numCourses = getCourses().size();
            for (int i = 1; i < numCourses && getCourses().get(i) != null; i++) {
                if (i == numCourses - 1 || getCourses().get(i + 1) == null) {
                    string.append(String.format("\t\t%d. %s", i + 1, getCourses().get(i).toString()));
                } else {
                    string.append(String.format("\t\t%d. %s\n", i + 1, getCourses().get(i).toString()));
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
                "%s\n", getDegreeTitle(), getCourses().size(), getTitleOfThesis(), toStringCourses());

        return string;
    }

    public static void main(String[] args) {
        Degree degree1 = new Degree();

        Course course1 = new Course("Programming 1", 811104, 'P', 1, 1, 5, true);
        Course course2 = new Course("Programming 2", 811104, 'P', 1, 1, 5, true);
        StudentCourse studentCourse2 = new StudentCourse(course1, 2, 2013);
        StudentCourse studentCourse1 = new StudentCourse(course2, 2, 2013);
        degree1.addStudentCourse(studentCourse2);
        degree1.addStudentCourse(studentCourse1);
        degree1.setDegreeTitle("Jaas queen");
        degree1.setTitleOfThesis("UwU Senpai");

        System.out.println(degree1);
    }
}
