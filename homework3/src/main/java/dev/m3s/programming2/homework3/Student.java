package dev.m3s.programming2.homework3;

import java.security.spec.RSAOtherPrimeInfo;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Student extends Person {
    private PersonID personID = new PersonID();
    private int id;
    private int startYear = Year.now().getValue();
    private int graduationYear;
    private final int degreeCount = 3;
    private List<Degree> degrees = new ArrayList<Degree>();

    Student(String lname, String fname) {
        super(lname, fname);
        for (int i = 0; i < degreeCount; i++) {
            degrees.add(new Degree());
        }
        this.id = super.getRandomId(ConstantValues.MIN_STUDENT_ID, ConstantValues.MAX_STUDENT_ID);
    }

    public int getId() {
        return id;
    }

    public void setId(final int id) {
        if (id >= ConstantValues.MIN_STUDENT_ID && id <= ConstantValues.MAX_STUDENT_ID) {
            this.id = id;
        }
    }

    public int getStartYear() {
        return startYear;
    }

    public void setStartYear(final int startYear) {
        if (startYear > 2000 && startYear <= Year.now().getValue()) {
            this.startYear = startYear;
        }
    }

    public int getGraduationYear() {
        return graduationYear;
    }

    public String setGraduationYear(final int graduationYear) {
        if (canGraduate()) {
            if (graduationYear >= getStartYear() && graduationYear <= Year.now().getValue()) {
                this.graduationYear = graduationYear;
                return "Ok";
            } else return "Check graduation year";
        }
        return "Check amount of required credits";
    }

    public void setDegreeTitle(final int i, String dName) {
        if (i >= 0 && i < degreeCount && dName != null) {
            degrees.get(i).setDegreeTitle(dName);
        }
    }

    public boolean addCourse(final int i, StudentCourse course) {
        if (i >= 0 && i < degreeCount && course != null) {
            return degrees.get(i).addStudentCourse(course);
        }
        return false;
    }

    public int addCourses(final int i, List<StudentCourse> courses) {
        int count = 0;

        if (i >= 0 && i < degreeCount && courses != null) {
            for (StudentCourse course : courses) {
                if (degrees.get(i).addStudentCourse(course)) count++;
            }
        }
        return count;

    }

    public void printCourses() {
        for (int i = 0; i < degrees.size(); i++) {
            if (degrees.get(i) != null) {
                degrees.get(i).printCourses();
            }
        }
    }

    public void printDegrees() {
        for (int i = 0; i < degrees.size(); i++) {
            System.out.println(degrees.get(i).toString());
        }
    }

    public void setTitleOfThesis(final int i, String title) {
        if (i >= 0 && i < degreeCount && title != null) {
            degrees.get(i).setTitleOfThesis(title);
        }
    }

    public boolean hasGraduated() {
        return getGraduationYear() != 0;
    }

    // Checks if student can graduate. Needs to have thesis titles and required credits.
    private boolean canGraduate() {
        if (!degrees.get(ConstantValues.BACHELOR_TYPE).getTitleOfThesis().equals(ConstantValues.NO_TITLE) &&
                !degrees.get(ConstantValues.MASTER_TYPE).getTitleOfThesis().equals(ConstantValues.NO_TITLE)) {
            return degrees.get(ConstantValues.BACHELOR_TYPE).getCredits() >= ConstantValues.BACHELOR_CREDITS
                    && degrees.get(ConstantValues.BACHELOR_TYPE).getCreditsByType(ConstantValues.MANDATORY)
                    >= ConstantValues.BACHELOR_MANDATORY
                    && degrees.get(ConstantValues.MASTER_TYPE).getCredits() >= ConstantValues.MASTER_CREDITS
                    && degrees.get(ConstantValues.MASTER_TYPE).getCreditsByType(ConstantValues.MANDATORY)
                    >= ConstantValues.MASTER_MANDATORY;
        }
        return false;
    }

    public int getStudyYears() {
        if (hasGraduated()) {
            return getGraduationYear() - getStartYear();
        } else {
            return Year.now().getValue() - getStartYear();
        }
    }

    // Methods to help with toString
    private String toStringStatus() {
        if (hasGraduated()) {
            return String.format("The student has graduated in %d", getGraduationYear());
        } else return "The student has not graduated, yet";
    }

    private String toStringStartYear() {
        if (hasGraduated()) {
            return String.format("studies lasted for %d years", getStudyYears());
        }
        return String.format("studies have lasted for %d years", getStudyYears());
    }

    private String toStringBachelorCredits() {
        String string = String.format("%.01f\n", degrees.get(ConstantValues.BACHELOR_TYPE).getCredits());
        if (degrees.get(ConstantValues.BACHELOR_TYPE).getCredits() >= ConstantValues.BACHELOR_CREDITS) {
            string = string + String.format("\t\t\t\tTotal bachelor credits completed (%.01f/%.01f)\n",
                    degrees.get(ConstantValues.BACHELOR_TYPE).getCredits(), ConstantValues.BACHELOR_CREDITS);
        } else {
            string = string + String.format("\t\t\t\tMissing bachelor credits %.01f (%.01f/%.01f)\n",
                    ConstantValues.BACHELOR_CREDITS - degrees.get(ConstantValues.BACHELOR_TYPE).getCredits(),
                    degrees.get(ConstantValues.BACHELOR_TYPE).getCredits(), ConstantValues.BACHELOR_CREDITS);
        }
        if (degrees.get(ConstantValues.BACHELOR_TYPE).getCreditsByType(ConstantValues.MANDATORY)
                >= ConstantValues.BACHELOR_MANDATORY) {
            string = string + String.format("\t\t\t\tAll mandatory bachelor credits completed (%.01f/%.01f)\n",
                    degrees.get(ConstantValues.BACHELOR_TYPE).getCreditsByType(ConstantValues.MANDATORY),
                    ConstantValues.BACHELOR_MANDATORY);
        } else {
            string = string + String.format("\t\t\t\tMissing mandatory bachelor credits %.01f (%.01f/%.01f\n",
                    ConstantValues.BACHELOR_MANDATORY -
                            degrees.get(ConstantValues.BACHELOR_TYPE).getCreditsByType(ConstantValues.MANDATORY),
                    degrees.get(ConstantValues.BACHELOR_TYPE).getCreditsByType(ConstantValues.MANDATORY),
                    ConstantValues.BACHELOR_MANDATORY);
        }
        string = string + String.format("\t\t\t\tGPA of Bachelor studies: %.02f\n",
                degrees.get(ConstantValues.BACHELOR_TYPE).getGPA(ConstantValues.ALL).get(2));
        string = string + String.format("\t\t\t\tTitle of BSc Thesis: \"%s\"",
                degrees.get(ConstantValues.BACHELOR_TYPE).getTitleOfThesis());
        return string;
    }

    private String toStringMasterCredits() {
        String string = String.format("%.01f\n", degrees.get(ConstantValues.MASTER_TYPE).getCredits());
        if (degrees.get(ConstantValues.MASTER_TYPE).getCredits() >= ConstantValues.MASTER_CREDITS) {
            string = string + String.format("\t\t\t\tTotal master's credits completed (%.01f/%.01f)\n",
                    degrees.get(ConstantValues.MASTER_TYPE).getCredits(), ConstantValues.MASTER_CREDITS);
        } else {
            string = string + String.format("\t\t\t\tMissing master's credits %.01f (%.01f/%.01f)\n",
                    ConstantValues.MASTER_CREDITS - degrees.get(ConstantValues.MASTER_TYPE).getCredits(),
                    degrees.get(ConstantValues.MASTER_TYPE).getCredits(), ConstantValues.MASTER_CREDITS);
        }
        if (degrees.get(ConstantValues.MASTER_TYPE).getCreditsByType(ConstantValues.MANDATORY)
                >= ConstantValues.MASTER_MANDATORY) {
            string = string + String.format("\t\t\t\tAll mandatory master credits completed (%.01f/%.01f)\n",
                    degrees.get(ConstantValues.MASTER_TYPE).getCreditsByType(ConstantValues.MANDATORY),
                    ConstantValues.MASTER_MANDATORY);
        } else {
            string = string + String.format("\t\t\t\tMissing mandatory master credits %.01f (%.01f/%.01f\n",
                    ConstantValues.MASTER_MANDATORY -
                            degrees.get(ConstantValues.MASTER_TYPE).getCreditsByType(ConstantValues.MANDATORY),
                    degrees.get(ConstantValues.MASTER_TYPE).getCreditsByType(ConstantValues.MANDATORY),
                    ConstantValues.MASTER_MANDATORY);
        }
        string = string + String.format("\t\t\t\tGPA of Master studies: %.02f\n",
                degrees.get(ConstantValues.MASTER_TYPE).getGPA(ConstantValues.ALL).get(2));
        string = string + String.format("\t\t\t\tTitle of MSc Thesis: \"%s\"",
                degrees.get(ConstantValues.MASTER_TYPE).getTitleOfThesis());
        return string;
    }

    private double toStringTotalCredits() {
        return degrees.get(ConstantValues.BACHELOR_TYPE).getCredits() + degrees.get(ConstantValues.MASTER_TYPE).getCredits();
    }

    private double getGPA() {
        double sum = 0;
        double count = 0;
        double average = 0;

        for (int i = 0; i <= ConstantValues.MASTER_TYPE; i++) {
            sum += degrees.get(i).getGPA(ConstantValues.ALL).get(0);
            count += degrees.get(i).getGPA(ConstantValues.ALL).get(1);
        }
        if (count > 0) {
            average = sum / count;
        }
        return Math.round(average * 100d) / 100d;
    }

    // Custom toString
    @Override
    public String toString() {
        String string = String.format("Student id: %d\n" +
                "\t\tFirst name: %s, Last name: %s\n" +
                "\t\tDate of birth: %s\n" +
                "\t\tStatus: %s\n" +
                "\t\tStart year: %d (%s)\n" +
                "\t\tTotal credits: %.01f (GPA = %.02f)\n" +
                "\t\tBachelor credits: %s\n" +
                "\t\tMaster credits: %s\n",
                getId(), getFirstName(), getLastName(), getBirthDate(), toStringStatus(),
                getStartYear(), toStringStartYear(), toStringTotalCredits(), getGPA(),
                toStringBachelorCredits(), toStringMasterCredits());

        return string;
    }

    public String getIdString() {
        return String.format("Student id :%d", getId());
    }

    public static void main(String[] args) {
        StudentTest.test2();
    }
}
