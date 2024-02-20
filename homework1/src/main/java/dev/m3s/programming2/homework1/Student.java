package dev.m3s.programming2.homework1;

import java.time.Year;
import java.util.Random;

public class Student {
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

    Student()  {
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

    private int getRandomId() {
        Random rand = new Random();
        int upperbound = 100;
        return rand.nextInt(upperbound) + 1;
    }

    private String printStatus() {
        if (hasGraduated()) {
            return String.format("The student has graduated in %s", getGraduationYear());
        } else return "The student has not graduated, yet.";
    }

    private String printStartYear() {
        if (hasGraduated()) {
            return String.format("studies lasted for %d years", getStudyYears());
        } else return String.format("studies have lasted for %d years", getStudyYears());
    }

    public String toString() {
        String string = String.format("Student id: %d\n" +
                "FirstName: %s, LastName: %s\n" +
                "Status: %s\n" +
                "StartYear: %d (%s)\n" +
                "BachelorCredits: %.01f\n" +
                "MasterCredits: %.01f\n" +
                "TitleOfMastersThesis: %s\n" +
                "TitleOfBachelorThesis: %s\n",
                getId(), getFirstName(), getLastName(), printStatus(),
                getStartYear(), printStartYear(), getBachelorCredits(), getMasterCredits(),
                getTitleOfMastersThesis(), getTitleOfBachelorThesis());

        return string;
    }

    private static void test1() {
        Student student1 = new Student();
        Student student2 = new Student("Mouse", "Mickey");

        student1.setFirstName("Donald");
        student1.setLastName("Duck");
        student1.setId(330);
        student1.setBachelorCredits(55);
        student1.setMasterCredits(14);
        student1.setTitleOfBachelorThesis("Bachelor thesis title");
        student1.setStartYear(2020);
        student1.setGraduationYear(2021);

        student2.setBachelorCredits(5);
        student2.setId(4);
        student2.setTitleOfBachelorThesis(null);

        System.out.println(student1);
        System.out.println(student2);
    }

    private static void test2() {
        Student student1 = new Student();
        Student student2 = new Student("Mouse", "Mickey");

        student1.setFirstName("Donald");
        student1.setLastName("Duck");
        student1.setId(0);
        student1.setBachelorCredits(180);
        student1.setMasterCredits(180);
        student1.setTitleOfMastersThesis("Masters thesis title");
        student1.setTitleOfBachelorThesis("Bachelor thesis title");
        student1.setStartYear(2001);
        student1.setGraduationYear(2020);

        System.out.println(student1);
        System.out.println(student2);
    }

    private static void test3() {
        Student student1 = new Student();
        Student student2 = new Student("Mouse", "Mickey");

        student1.setFirstName("Donald");
        student1.setLastName("Duck");
        student1.setId(0);
        student1.setBachelorCredits(180);
        student1.setMasterCredits(120);
        student1.setTitleOfBachelorThesis("Bachelor thesis title");
        student1.setStartYear(2021);
        student1.setGraduationYear(2021);

        student2.setFirstName(null);
        student2.setLastName(null);
        student2.setBachelorCredits(180);
        student2.setMasterCredits(120);
        student2.setTitleOfBachelorThesis("How to survive a bachelors thesis");
        student2.setTitleOfMastersThesis("Happy ending");
        student2.setId(101);

        System.out.println(student1);
        System.out.println(student2);

        System.out.println();
    }

    public static void main(String[] args) {
        //test1();
        //test2();
        //test3();
    }
}
