package dev.m3s.programming2.homework2;

import javax.crypto.Cipher;
import java.lang.reflect.Array;
import java.time.Year;
import java.util.Random;
import java.util.regex.PatternSyntaxException;

public class Student {
    //private static Week1Tests week1Tests = new Week1Tests();
    private static PersonID personID = new PersonID();
    private String firstName = ConstantValues.NO_NAME;
    private String  lastName = ConstantValues.NO_NAME;
    private int id;
    private int startYear = Year.now().getValue();
    private int graduationYear;
    private int degreeCount = 3;
    private Degree[] degrees;
    private String birthDate = ConstantValues.NO_BIRTHDATE;

    Student()  {
        this.id = getRandomId();
        degrees = new Degree[degreeCount];
        for (int i = 0; i < degreeCount; i++) {
            degrees[i] = new Degree();
        }
    }

    Student(String lname, String fname) {
        this();
        if (lname != null) {
            this.lastName = lname;
        }
        if (fname != null) {
            this.firstName = fname;
        }
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
        if (id >= ConstantValues.MIN_ID && id <= ConstantValues.MAX_ID) {
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
            degrees[i].setDegreeTitle(dName);
        }
    }

    public boolean addCourse(final int i, StudentCourse course) {
        if (i >= 0 && i < degreeCount && course != null) {
            return degrees[i].addStudentCourse(course);
        }
        return false;
    }

    public int addCourses(final int i, StudentCourse[] courses) {
        int count = 0;

        if (i >= 0 && i < degreeCount && courses != null) {
            for (StudentCourse course : courses) {
                if (degrees[i].addStudentCourse(course)) count++;
            }
        }
        return count;

    }

    public void printCourses() {
        for (int i = 0; i < degrees.length; i++) {
            if (degrees[i] != null) {
                degrees[i].printCourses();
            }
        }
    }

    public void printDegrees() {
        for (int i = 0; i < degrees.length; i++) {
            //if (degrees[i] != null) {
                System.out.println(degrees[i].toString());
            //}
        }
    }

    public void setTitleOfThesis(final int i, String title) {
        if (i >= 0 && i < degreeCount && title != null) {
            degrees[i].setTitleOfThesis(title);
        }
    }

    public String getBirthDate() {
        return birthDate;
    }

    public String setBirthDate(String personId) {
        if (personID.setPersonId(personId).equals("Ok")) {
            this.birthDate = personID.getBirthDate();
            return personID.getBirthDate();
        }
        return "No change";
    }

    public boolean hasGraduated() {
        return graduationYear != 0;
    }

    // Checks if student can graduate. Needs to have thesis titles and required credits.
    private boolean canGraduate() {
        if (!degrees[ConstantValues.BACHELOR_TYPE].getTitleOfThesis().equals(ConstantValues.NO_TITLE) &&
                !degrees[ConstantValues.MASTER_TYPE].getTitleOfThesis().equals(ConstantValues.NO_TITLE)) {
            return degrees[ConstantValues.BACHELOR_TYPE].getCredits() >= ConstantValues.BACHELOR_CREDITS
                    && degrees[ConstantValues.MASTER_TYPE].getCredits() >= ConstantValues.MASTER_CREDITS;
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
        String string = String.format("%.01f\n", degrees[ConstantValues.BACHELOR_TYPE].getCredits());
        if (degrees[ConstantValues.BACHELOR_TYPE].getCredits() >= ConstantValues.BACHELOR_CREDITS) {
            string = string + String.format("\t\t\t\tTotal bachelor credits completed (%.01f/%.01f)\n" +
                     "\t\t\t\tTitle of BSc Thesis: \"%s\"",
                    degrees[ConstantValues.BACHELOR_TYPE].getCredits(), ConstantValues.BACHELOR_CREDITS,
                    degrees[ConstantValues.BACHELOR_TYPE].getTitleOfThesis());
            return string;
        }
        string = string + String.format("\t\t\t\tMissing bachelor credits %.01f (%.01f/%.01f)\n" +
                "\t\t\t\tTitle of BSc Thesis: \"%s\"",
                ConstantValues.BACHELOR_CREDITS - degrees[ConstantValues.BACHELOR_TYPE].getCredits(),
                degrees[ConstantValues.BACHELOR_TYPE].getCredits(), ConstantValues.BACHELOR_CREDITS,
                degrees[ConstantValues.BACHELOR_TYPE].getTitleOfThesis());
        return string;
    }

    private String toStringMasterCredits() {
        String string = String.format("%.01f\n", degrees[ConstantValues.MASTER_TYPE].getCredits());
        if (degrees[ConstantValues.MASTER_TYPE].getCredits() >= ConstantValues.MASTER_CREDITS) {
            string = string + String.format("\t\t\t\tTotal master's credits completed (%.01f/%.01f)\n" +
                            "\t\t\t\tTitle of Master Thesis: \"%s\"",
                    degrees[ConstantValues.MASTER_TYPE].getCredits(), ConstantValues.MASTER_CREDITS,
                    degrees[ConstantValues.MASTER_TYPE].getTitleOfThesis());
            return string;
        }
        string = string + String.format("\t\t\t\tMissing master's credits %.01f (%.01f/%.01f)\n" +
                        "\t\t\t\tTitle of Master Thesis: \"%s\"",
                ConstantValues.MASTER_CREDITS - degrees[ConstantValues.MASTER_TYPE].getCredits(),
                degrees[ConstantValues.MASTER_TYPE].getCredits(), ConstantValues.MASTER_CREDITS,
                degrees[ConstantValues.MASTER_TYPE].getTitleOfThesis());
        return string;
    }

    private double toStringTotalCredits() {
        return degrees[ConstantValues.BACHELOR_TYPE].getCredits() + degrees[ConstantValues.MASTER_TYPE].getCredits();
    }

    // Custom toString
    public String toString() {
        String string = String.format("Student id: %d\n" +
                "\t\tFirst name: %s, Last name: %s\n" +
                "\t\tDate of birth: %s\n" +
                "\t\tStatus: %s\n" +
                "\t\tStart year: %d (%s)\n" +
                "\t\tTotal credits: %.01f\n" +
                "\t\tBachelorCredits: %s\n" +
                "\t\tMasterCredits: %s\n",
                getId(), getFirstName(), getLastName(), getBirthDate(), toStringStatus(),
                getStartYear(), toStringStartYear(), toStringTotalCredits(),
                toStringBachelorCredits(), toStringMasterCredits());

        return string;
    }

    private static void test1() {
        Student student1 =  new Student();

        Course course1 = new Course("Programming 1", 811104, 'P', 1, 1, 5.0, true);
        Course course2 = new Course("All kinds of basic studies", 112233, 'P', 1, 2, 45.0, true);
        Course course3 = new Course("More basic studies", 223344, 'a', 1, 1, 50.5, true);
        Course course4 = new Course("Even more basic studies", 556677, 'a', 0, 3, 50.0, true);
        Course course5 = new Course("Final basic studies", 123123, 'A', 1, 4, 50.5, true);
        Course course6 = new Course("Programming 2", 616161, 'A', 1, 3, 25.0, true);
        Course course7 = new Course("All kinds of master studies", 717171, 'P', 0, 2, 45.0, true);
        Course course8 = new Course("More master studies", 818181, 'A', 1, 1, 25.0, true);
        Course course9 = new Course("Even more master studies", 919191, 'S', 1, 3, 20.0, true);
        Course course10 = new Course("Extra master studies", 666666, 'S', 0, 5, 8.0, false);
        Course course11 = new Course("Final master studies", 888888, 'S', 1, 5, 18.0, false);

        StudentCourse studentCourse1 = new StudentCourse(course1, 1, 2013);
        StudentCourse studentCourse2 = new StudentCourse(course2, 1, 2014);
        StudentCourse studentCourse3 = new StudentCourse(course3, 1, 2015);
        StudentCourse studentCourse4 = new StudentCourse(course4, 4, 2016);
        StudentCourse studentCourse5 = new StudentCourse(course5, 5, 2017);
        StudentCourse studentCourse6 = new StudentCourse(course6, 1, 2018);
        StudentCourse studentCourse7 = new StudentCourse(course7, 1, 2019);
        StudentCourse studentCourse8 = new StudentCourse(course8,2, 2020);
        StudentCourse studentCourse9 = new StudentCourse(course9, 0, 2021);
        StudentCourse studentCourse10 = new StudentCourse(course10, 'A', 2021);
        StudentCourse studentCourse11 = new StudentCourse(course11, 'f', 2025);

        StudentCourse[] bachelor = {studentCourse1, studentCourse2, studentCourse3, studentCourse4, studentCourse5};
        StudentCourse[] master = {studentCourse6, studentCourse7, studentCourse8, studentCourse9, studentCourse10, studentCourse11};

        student1.setDegreeTitle(0, "Bachelor of Science");
        student1.setDegreeTitle(1, "Master of Science");
        student1.setTitleOfThesis(0, "Bachelor thesis title");
        student1.setTitleOfThesis(1, "Masters thesis title");

        student1.addCourses(0, bachelor);
        student1.addCourses(1, master);

        student1.setStartYear(2001);
        student1.setGraduationYear(2020);
        student1.setFirstName("Donald");
        student1.setLastName("Duck");

        System.out.println(student1);

        student1.setBirthDate("230498-045T");
        student1.setTitleOfThesis(0, "Christmas - The most wonderful time of the year");
        student1.setTitleOfThesis(1, "Dreaming of a white Christmas");
        student1.printDegrees();
        studentCourse9.setGrade(3);

        System.out.println(student1);

        student1.printDegrees();
        student1.printCourses();
        studentCourse11.setGrade('X');

        System.out.println(studentCourse11);
        studentCourse11.setGrade('a');
        System.out.println(studentCourse11);
        studentCourse1.setGrade(6);
        System.out.println(studentCourse1);
        studentCourse1.setGrade(5);
        System.out.println(studentCourse1);
    }

    public static void main(String[] args) {
        //Student student1 =  new Student();
        test1();
        //test2();
        //test3();
        //week1Tests.test1_v2();


        //System.out.println();
    }
}
