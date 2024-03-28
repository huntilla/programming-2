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
        setCourseCode(code, courseBase);
        setCourseBase(courseBase);
        setCourseType(type);
        setPeriod(period);
        setCredits(credits);
        setNumericGrade(numericGrade);
    }

    Course(Course course) {
        setName(course.getName());
        setCourseBase(course.getCourseBase());
        setCourseType(course.getCourseType());
        setPeriod(course.getPeriod());
        setCredits(course.getCredits());
        setNumericGrade(course.isNumericGrade());
        this.courseCode = course.getCourseCode();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name != null && !name.isEmpty()) {
            this.name = name;
        }
    }

    public int getCourseType() {
        return courseType;
    }

    public void setCourseType(final int courseType) {
        if (courseType == ConstantValues.OPTIONAL || courseType == ConstantValues.MANDATORY) {
            this.courseType = courseType;
        }
    }

    public String getCourseTypeString() {
        if (getCourseType() == ConstantValues.OPTIONAL) {
            return "Optional";
        } return "Mandatory";
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(final int courseCode, Character courseBase) {
        courseBase = Character.toUpperCase(courseBase);
        if (courseCode > 0 && courseCode < 1000000)
            if (courseBase == 'A' || courseBase == 'P' || courseBase == 'S') {
                setCourseBase(courseBase);
                this.courseCode = String.format("%d%c", courseCode, courseBase);
            }
    }

    public Character getCourseBase() {
        return courseBase;
    }

    private void setCourseBase(Character courseBase) {
        this.courseBase = Character.toUpperCase(courseBase);
    }

    public int getPeriod() {
        return period;
    }

    public void setPeriod(final int period) {
        if (period >= ConstantValues.MIN_PERIOD && period <= ConstantValues.MAX_PERIOD) {
            this.period = period;
        }
    }

    public double getCredits() {
        return credits;
    }

    private void setCredits(final double credits) {
        if (credits >= ConstantValues.MIN_CREDITS && credits <= ConstantValues.MAX_COURSE_CREDITS) {
            this.credits = credits;
        }
    }

    public boolean isNumericGrade() {
        return numericGrade;
    }

    public void setNumericGrade(boolean numericGrade) {
        this.numericGrade = numericGrade;
    }

    @Override
    public String toString() {
        return String.format("[%s (%5.02f cr), \"%s\". %s, period: %d.]",
                getCourseCode(), getCredits(), getName(), getCourseTypeString(), getPeriod());
    }

    public static void main(String[] args) {
//        Course course1 = new Course("Programming 1", 811104, 'P', 1, 1, 5, true);
//        Course course2 = new Course("Programming 2", 811234, 'P', 0, 4, 12, true);
//
//        System.out.println(course1);
//        System.out.println(course2);
    }
}
