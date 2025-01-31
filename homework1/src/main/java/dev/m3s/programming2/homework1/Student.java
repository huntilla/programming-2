package dev.m3s.programming2.homework1;

import java.time.Year;
import java.util.List;
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
            if (graduationYear >= getStartYear() && graduationYear <= Year.now().getValue()) {
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
        return graduationYear != 0;
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
        //int upperbound = 100;
        return rand.nextInt(ConstantValues.MAX_ID) + ConstantValues.MIN_ID;
    }

    public String setPersonId(final String personID) {
        if (!checkPersonIDNumber(personID)) {
            return "Invalid birthday!";
        }
        String day = personID.substring(0,2);
        String month = personID.substring(2,4);
        int yearInt = Integer.parseInt(personID.substring(4,6));
        //Add year to yearInt according to character
        if (personID.charAt(6) == 'A') {
            yearInt += 2000;
        }
        if (personID.charAt(6) == '-') {
            yearInt += 1900;
        }
        if (personID.charAt(6) == '+') {
            yearInt += 1800;
        }

        String birthdate = String.format("%s.%s.%d", day, month, yearInt);
        if (!checkBirthdate(birthdate)) {
            return ConstantValues.INVALID_BIRTHDAY;
        }
        if (!checkValidCharacter(personID)) {
            return ConstantValues.INCORRECT_CHECKMARK;
        }
        setBirthDate(birthdate);
        return "Ok";
    }

    // Checks if century character is correct
    private boolean checkValidCharacter(final String personID) {
        String birthDate = personID.substring(0, 6);
        String individualNumber = personID.substring(7, 10);
        char character = Character.toLowerCase(personID.charAt(10));
        int digits = Integer.parseInt((birthDate + individualNumber));

        char[] controlCharacters = {'0', '1', '2', '3', '4', '5','6', '7', '8', '9','a', 'b',
                'c', 'd', 'e', 'f', 'h', 'j', 'k', 'l',
                'm', 'n', 'p', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y'};
        int remainder = digits % 31;
        return character == controlCharacters[remainder];
    }

    // Checks if personID has correct format
    private boolean checkPersonIDNumber(final String personID) {
        if (personID == null || personID.length() != 11) {
            return false;
        }
        return personID.charAt(6) == 'A' || personID.charAt(6) == '+' || personID.charAt(6) == '-';
    }

    private boolean checkLeapYear(int year) {
        return Year.of(year).isLeap();
    }

    // Checks if given date is correct
    private boolean checkBirthdate(final String date) {
        String[] splitDate;
        int day;
        int month;
        int year;

        // Splits date in to a list
        try {
            splitDate = date.split("\\.");
        } catch (PatternSyntaxException e) {
            e.printStackTrace();
            return false;
        }

        if (splitDate.length == 3) {
            day = Integer.parseInt(splitDate[0]);
            month = Integer.parseInt(splitDate[1]);
            year = Integer.parseInt(splitDate[2]);

        } else return false;

        // This is disgusting...
        if (year <= 0) {
            return false;
        }
        if (month < 1 || month > 12) {
            return false;
        }
        if (day < 1 || day > 31) {
            return false;
        }
        if (day <= 30 && (month == 4 || month == 6 || month == 9 || month == 11)) {
            return true;
        }
        if (day <= 28 && (month == 2 && !checkLeapYear(year))) {
            return true;
        }
        if (day <= 29 && (month == 2 && checkLeapYear(year))) {
            return true;
        }
        return day <= 31 && (month == 1 || month == 3 || month == 5 || month == 7
                || month == 8 || month == 10 || month == 12);
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
                "\tFirstName: %s, LastName: %s\n" +
                "\tDate of birth: %s\n" +
                "\tStatus: %s\n" +
                "\tStartYear: %d (%s)\n" +
                "\tBachelorCredits: %s\n" +
                "\tTitleOfBachelorThesis: \"%s\"\n" +
                "\tMasterCredits: %s\n" +
                "\tTitleOfMastersThesis: \"%s\"\n",
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
