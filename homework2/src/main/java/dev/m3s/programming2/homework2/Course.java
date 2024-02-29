package dev.m3s.programming2.homework2;

public class Course {
    private String name;
    private String courseCode;
    private Character courseBase;
    private int courseType;
    private int period;
    private double credits;
    private boolean numericGrade;

    Course() {
    }

    Course(String name, final int code, Character courseBase, final int type,
           final int period, final double credits, boolean numericGrade) {
        setName(name);
        setCourseCode(code);

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public Character getCourseBase() {
        return courseBase;
    }

    public void setCourseBase(Character courseBase) {
        this.courseBase = courseBase;
    }

    public int getCourseType() {
        return courseType;
    }

    public void setCourseType(int courseType) {
        this.courseType = courseType;
    }

    public int getPeriod() {
        return period;
    }

    public void setPeriod(int period) {
        this.period = period;
    }

    public double getCredits() {
        return credits;
    }

    public void setCredits(double credits) {
        this.credits = credits;
    }

    public boolean isNumericGrade() {
        return numericGrade;
    }

    public void setNumericGrade(boolean numericGrade) {
        this.numericGrade = numericGrade;
    }


}
