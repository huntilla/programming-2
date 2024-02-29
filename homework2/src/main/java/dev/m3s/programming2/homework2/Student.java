package dev.m3s.programming2.homework2;

import java.time.Year;
import java.util.Random;
import java.util.regex.PatternSyntaxException;

public class Student {
    //private static Week1Tests week1Tests = new Week1Tests();
    private String firstName = ConstantValues.NO_NAME;
    private String  lastName = ConstantValues.NO_NAME;
    private int id;
    private double bachelorCredits;
    private double masterCredits;
    private String titleOfMastersThesis = ConstantValues.NO_TITLE;
    private String titleOfBachelorThesis = ConstantValues.NO_TITLE;
    private int startYear = Year.now().getValue();
    private int graduationYear;
    private String birthDate = ConstantValues.NO_BIRTHDATE;

    private Student()  {
        this.id = getRandomId();
    }

    Student(String lname, String fname) {
        if (lname != null) {
            this.lastName = lname;
        }
        if (fname != null) {
            this.firstName = fname;
        }
        this.id = getRandomId();
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        if (firstName != null) {
            this.firstName = firstName;
        }
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        if (lastName != null) {
            this.lastName = lastName;
        }
    }

    public int getId() {
        return id;
    }

    public void setId(final int id) {
        if (id >= 1 && id <= 100) {
            this.id = id;
        }
    }

    public double getBachelorCredits() {
        return bachelorCredits;
    }

    public void setBachelorCredits(final double bachelorCredits) {
        if (bachelorCredits >= 0.0 && bachelorCredits <= 300.0) {
            this.bachelorCredits = bachelorCredits;
        }
    }

    public double getMasterCredits() {
        return masterCredits;
    }

    public void setMasterCredits(final double masterCredits) {
        if (masterCredits >= 0.0 && masterCredits <= 300.0) {
            this.masterCredits = masterCredits;
        }
    }

    public String getTitleOfMastersThesis() {
        return titleOfMastersThesis;
    }

    public void setTitleOfMastersThesis(String titleOfMastersThesis) {
        if (titleOfMastersThesis != null) {
            this.titleOfMastersThesis = titleOfMastersThesis;
        }
    }

    public String getTitleOfBachelorThesis() {
        return titleOfBachelorThesis;
    }

    public void setTitleOfBachelorThesis(String titleOfBachelorThesis) {
        if (titleOfBachelorThesis != null) {
            this.titleOfBachelorThesis = titleOfBachelorThesis;
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
            if (graduationYear <= Year.now().getValue() && graduationYear > 2000) {
                this.graduationYear = graduationYear;
                return "Ok";
            } else return "Check graduation year";
        }
        return "Check the required studies";
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public boolean hasGraduated() {
        if (canGraduate() && getGraduationYear() != 0) {
            return getGraduationYear() <= Year.now().getValue();
        }
        return false;
    }

    // Checks if student can graduate. Needs to have thesis titles and required credits.
    private boolean canGraduate() {
        if (!getTitleOfBachelorThesis().equals(ConstantValues.NO_TITLE) &&
                !getTitleOfMastersThesis().equals(ConstantValues.NO_TITLE)) {
            return getBachelorCredits() >= ConstantValues.BACHELOR_CREDITS
                    && getMasterCredits() >= ConstantValues.MASTER_CREDITS;
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

    // Generates a random Id between 1-100
    private int getRandomId() {
        Random rand = new Random();
        int upperbound = 100;
        return rand.nextInt(upperbound) + 1;
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
        if (getBachelorCredits() >= ConstantValues.BACHELOR_CREDITS) {
            return String.format("%.01f ==> All required bachelor credits completed " +
                    "(%.01f/%.01f)", getBachelorCredits(), getBachelorCredits(), ConstantValues.BACHELOR_CREDITS);
        }
        return String.format("%.01f ==> Missing bachelor credits %.01f (%.01f/%.01f)",
                getBachelorCredits(), ConstantValues.BACHELOR_CREDITS - getBachelorCredits(),
                getBachelorCredits(), ConstantValues.BACHELOR_CREDITS);
    }

    private String toStringMasterCredits() {
        if (getMasterCredits() >= ConstantValues.MASTER_CREDITS) {
            return String.format("%.01f ==> All required master's credits completed " +
                    "(%.01f/%.01f)", getMasterCredits(), getMasterCredits(), ConstantValues.MASTER_CREDITS);
        }
        return String.format("%.01f ==> Missing master's credits %.01f (%.01f/%.01f)",
                getMasterCredits(), ConstantValues.MASTER_CREDITS - getMasterCredits(),
                getMasterCredits(), ConstantValues.MASTER_CREDITS);
    }

    // Custom toString
    public String toString() {
        String string = String.format("Student id: %d\n" +
                "FirstName: %s, LastName: %s\n" +
                "Date of birth: %s\n" +
                "Status: %s\n" +
                "StartYear: %d (%s)\n" +
                "BachelorCredits: %s\n" +
                "TitleOfBachelorThesis: \"%s\"\n" +
                "MasterCredits: %s\n" +
                "TitleOfMastersThesis: \"%s\"\n",
                getId(), getFirstName(), getLastName(), getBirthDate(), toStringStatus(),
                getStartYear(), toStringStartYear(), toStringBachelorCredits(),  getTitleOfBachelorThesis(),
                toStringMasterCredits(), getTitleOfMastersThesis());

        return string;
    }

    public static void main(String[] args) {
        //test1();
        //test2();
        //test3();
        //week1Tests.test1_v2();


        //System.out.println();
    }
}
