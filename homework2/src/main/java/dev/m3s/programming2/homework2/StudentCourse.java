package dev.m3s.programming2.homework2;

import java.time.Year;

public class StudentCourse {
    private Course course;
    private int gradeNum;
    private int yearCompleted;

    StudentCourse() {
    }

    StudentCourse(Course course, final int gradeNum, final int yearCompleted) {
        this.course = course;
        setGrade(gradeNum);
        setYear(yearCompleted);
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public int getGradeNum() {
        return gradeNum;
    }

    public void setGradeNum(int gradeNum) {
        this.gradeNum = gradeNum;
    }

    protected void setGrade(int gradeNum) {
        gradeNum = Character.toUpperCase(gradeNum);
        if (checkGradeValidity(gradeNum)) {
            setGradeNum(gradeNum);
            if (getYear() == 0) {
                setYear(Year.now().getValue());
            }
        }
    }

    private boolean checkGradeValidity(final int gradeNum) {
        if (course.isNumericGrade()
                && (gradeNum >= ConstantValues.MIN_GRADE && gradeNum <= ConstantValues.MAX_GRADE)) {
            return true;
        }
        return (!course.isNumericGrade() && (Character.toUpperCase(gradeNum) == ConstantValues.GRADE_FAILED
                || Character.toUpperCase(gradeNum) == ConstantValues.GRADE_ACCEPTED));
    }

    public boolean isPassed() {
       if (course.isNumericGrade()) {
           return getGradeNum() != ConstantValues.MIN_GRADE;
       }
       return getGradeNum() == ConstantValues.GRADE_ACCEPTED;
    }

    public int getYear() {
        return yearCompleted;
    }

    public void setYear(int yearCompleted) {
        if (yearCompleted > 2000 && yearCompleted <= Year.now().getValue()) {
            this.yearCompleted = yearCompleted;
        }
    }

    private String toStringGrade() {
        if (getGradeNum() == 0) {
            return "\"Not graded\"";
        }
        if (getGradeNum() == 'A') {
            return "A";
        }
        if (getGradeNum() == 'F') {
            return "F";
        }
        return String.format("%d", getGradeNum());
    }

    public String toString() {
        return String.format("[%s Year: %d, Grade: %s.]", getCourse().toString(), getYear(), toStringGrade());
    }

    public static void main(String[] args) {
        Course course1 = new Course("Programming 1", 811104, 'P', 1, 1, 5, true);
        Course course2 = new Course("Programming 2", 811234, 'P', 0, 4, 12, true);

        StudentCourse studentCourse1 = new StudentCourse(course1, 0, 2022);
        StudentCourse studentCourse2 = new StudentCourse(course2, 'A', 2013);
        System.out.println(studentCourse1);
        System.out.println(studentCourse2);
    }
}
