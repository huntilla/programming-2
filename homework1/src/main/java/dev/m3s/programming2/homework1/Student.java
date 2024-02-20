package dev.m3s.programming2.homework1;

import java.time.Year;
import java.util.List;
import java.util.Random;
import java.util.regex.PatternSyntaxException;

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

    public String setPersonId(final String personID) {
        //System.out.println(personID);
        if (!checkPersonIDNumber(personID)) {
            return "Invalid birthday!";
        }
        String day = personID.substring(0,2);
        //System.out.println("day: " + day);
        String month = personID.substring(2,4);
        //System.out.println("month: " + month);
        int yearInt = Integer.parseInt(personID.substring(4,6));
        //System.out.println(String.format("year: %d", yearInt));

        if (personID.charAt(6) == 'A') {
            yearInt += 2000;
        }
        if (personID.charAt(6) == '-') {
            yearInt += 1900;
        }
        if (personID.charAt(6) == '+') {
            yearInt += 1800;
        }
        //System.out.println(String.format("year: %d", yearInt));

        String birthdate = String.format("%s.%s.%d", day, month, yearInt);
        //System.out.println(birthdate);
        //System.out.println(checkBirthdate(birthdate));
        if (!checkBirthdate(birthdate)) {
            return "Invalid birthday!";
        }
        //System.out.println(checkValidCharacter(personID));
        if (!checkValidCharacter(personID)) {
            return "Incorrect check mark!";
        }
        setBirthDate(birthdate);
        return "Ok";
    }

    private boolean checkValidCharacter(final String personID) {
        String birthDate = personID.substring(0, 6);
        //System.out.println(birthDate);
        String individualNumber = personID.substring(7, 10);
        //System.out.println(individualNumber);
        char character = Character.toLowerCase(personID.charAt(10));
        //System.out.println(character);
        int digits = Integer.parseInt((birthDate + individualNumber));
        //System.out.println(digits);

        char[] controlCharacters = {'0', '1', '2', '3', '4', '5','6', '7', '8', '9','a', 'b', 'c', 'd', 'e', 'f', 'h', 'j', 'k', 'l',
                'm', 'n', 'p', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y'};
        int remainder = digits % 31;
        //System.out.println(remainder);
        return character == controlCharacters[remainder];
    }

    private boolean checkPersonIDNumber(final String personID) {
        if (personID.length() != 11) {
            return false;
        }
        return personID.charAt(6) == 'A' || personID.charAt(6) == '+' || personID.charAt(6) == '-';
    }

    private boolean checkLeapYear(int year) {
        return Year.of(year).isLeap();
    }

    // This is disgusting... Too lazy to fix
    private boolean checkBirthdate(final String date) {
        //System.out.println(date);
        String[] splitDate;
        int day;
        int month;
        int year;

        try {
            splitDate = date.split("\\.");
        } catch (PatternSyntaxException e) {
            e.printStackTrace();
            return false;
        }
        //System.out.println(splitDate[0]);
        if (splitDate.length == 3) {
            day = Integer.parseInt(splitDate[0]);
            month = Integer.parseInt(splitDate[1]);
            year = Integer.parseInt(splitDate[2]);

            //System.out.println(String.format("day: %d", day));
            //System.out.println(String.format("month: %d", month));
            //System.out.println(String.format("year: %d", year));
        } else return false;

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
        if (day == 28 && (month == 2 && !checkLeapYear(year))) {
            return true;
        }
        if (day == 29 && (month == 2 && checkLeapYear(year))) {
            return true;
        }
        return day == 31 && (month == 1 || month == 3 || month == 5 || month == 7
                || month == 8 || month == 10 || month == 12);
    }

    private int getRandomId() {
        Random rand = new Random();
        int upperbound = 100;
        return rand.nextInt(upperbound) + 1;
    }

    private String printStatus() {
        if (hasGraduated()) {
            return String.format("The student has graduated in %d", getGraduationYear());
        } else return "The student has not graduated, yet";
    }

    private String printStartYear() {
        if (hasGraduated()) {
            return String.format("studies lasted for %d years", getStudyYears());
        }
        return String.format("studies have lasted for %d years", getStudyYears());
    }

    private String printBachelorCredits() {
        if (getBachelorCredits() >= ConstantValues.BACHELOR_CREDITS) {
            return String.format("%.01f ==> All required bachelor credits completed " +
                    "(%.01f/%.01f)", getBachelorCredits(), getBachelorCredits(), ConstantValues.BACHELOR_CREDITS);
        }
        return String.format("%.01f ==> Missing bachelor credits %.01f (%.01f/%.01f)",
                getBachelorCredits(), ConstantValues.BACHELOR_CREDITS - getBachelorCredits(),
                getBachelorCredits(), ConstantValues.BACHELOR_CREDITS);
    }

    private String printMasterCredits() {
        if (getMasterCredits() >= ConstantValues.MASTER_CREDITS) {
            return String.format("%.01f ==> All required master's credits completed " +
                    "(%.01f/%.01f)", getMasterCredits(), getMasterCredits(), ConstantValues.MASTER_CREDITS);
        }
        return String.format("%.01f ==> Missing master's credits %.01f (%.01f/%.01f)",
                getMasterCredits(), ConstantValues.MASTER_CREDITS - getMasterCredits(),
                getMasterCredits(), ConstantValues.MASTER_CREDITS);
    }

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
                getId(), getFirstName(), getLastName(), getBirthDate(), printStatus(),
                getStartYear(), printStartYear(), printBachelorCredits(),  getTitleOfBachelorThesis(),
                printMasterCredits(), getTitleOfMastersThesis());

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

    private static void test1_v2() {
        Student student1 = new Student();
        Student student2 = new Student("Mouse", "Mickey");
        Student student3 = new Student("Mouse", "Minnie");

        student1.setFirstName("Donald");
        student1.setLastName("Duck");
        student1.setBachelorCredits(120);
        student1.setMasterCredits(180);
        student1.setTitleOfMastersThesis("Masters thesis title");
        student1.setTitleOfBachelorThesis("Bachelor thesis title");
        student1.setStartYear(2001);
        student1.setGraduationYear(2020);

        student2.setPersonId("221199-123A");
        student2.setTitleOfBachelorThesis("A new exciting purpose of life");
        student2.setBachelorCredits(65);
        student2.setMasterCredits(22);

        student3.setPersonId("111111-3334");
        student3.setBachelorCredits(215);
        student3.setMasterCredits(120);
        student3.setTitleOfMastersThesis("Christmas - The most wonderful time of the year");
        student3.setTitleOfBachelorThesis("Dreaming of a white Christmas");
        student3.setStartYear(2018);
        student3.setGraduationYear(2022);

        System.out.println(student1);
        System.out.println(student2);
        System.out.println(student3);
    }

    public static void main(String[] args) {
        //test1();
        //test2();
        //test3();
        //test1_v2();

        //System.out.println();
    }
}
