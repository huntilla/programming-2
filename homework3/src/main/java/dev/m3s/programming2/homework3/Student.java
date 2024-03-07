package dev.m3s.programming2.homework3;

import java.security.spec.RSAOtherPrimeInfo;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Student extends Person {
    //private static Week1Tests week1Tests = new Week1Tests();
    private static PersonID personID = new PersonID();
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
        return average;
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

    private static void test1() {
        Student student1 =  new Student("Duck", "Donald");

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

        student1.setDegreeTitle(0, "Bachelor of Science");
        student1.setDegreeTitle(1, "Master of Science");
        student1.setTitleOfThesis(0, "Bachelor thesis title");
        student1.setTitleOfThesis(1, "Masters thesis title");

        student1.setStartYear(2001);
        student1.setGraduationYear(2020);

        System.out.println(student1);

        student1.setBirthDate("230403A519K");
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

    private static void test2() {
        ResponsibleTeacher responsibleTeacher1 = new ResponsibleTeacher("Mouse", "Mickey");
        responsibleTeacher1.setBirthDate("230498-045T");
        MonthlyPayment responsiblePayment = new MonthlyPayment();
        responsiblePayment.setSalary(756.85);
        responsibleTeacher1.setPayment(responsiblePayment);

        AssistantTeacher assistantTeacher1 = new AssistantTeacher("The Dog", "Goofy");
        assistantTeacher1.setBirthDate("141200A2315");
        HourBasedPayment assistantPayment = new HourBasedPayment();
        assistantPayment.setHours(11.0);
        assistantPayment.setEurosPerHour(3.5);
        assistantTeacher1.setPayment(assistantPayment);

        Student student1 =  new Student("Duck", "Donald");

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

        DesignatedCourse designatedCourse1 = new DesignatedCourse(course3, true, 2023);
        DesignatedCourse designatedCourse2 = new DesignatedCourse(course4, false, 2023);
        DesignatedCourse designatedCourse3 = new DesignatedCourse(course10, false, 2022);
        DesignatedCourse designatedCourse4 = new DesignatedCourse(course11, true, 2023);

        List<DesignatedCourse> designatedCourses = new ArrayList<>();
        designatedCourses.add(designatedCourse1);
        designatedCourses.add(designatedCourse2);
        designatedCourses.add(designatedCourse3);
        designatedCourses.add(designatedCourse4);

        responsibleTeacher1.setCourses(designatedCourses);
        assistantTeacher1.setCourses(designatedCourses);

        System.out.println(responsibleTeacher1);
        System.out.println(assistantTeacher1);

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

        List<StudentCourse> bachelor = new ArrayList<>();
        List<StudentCourse> master = new ArrayList<>();

        bachelor.add(studentCourse1);
        bachelor.add(studentCourse2);
        bachelor.add(studentCourse3);
        bachelor.add(studentCourse4);
        bachelor.add(studentCourse5);

        master.add(studentCourse6);
        master.add(studentCourse7);
        master.add(studentCourse8);
        master.add(studentCourse9);
        master.add(studentCourse10);
        master.add(studentCourse11);

        student1.setDegreeTitle(0, "Bachelor of Science");
        student1.setDegreeTitle(1, "Master of Science");
        student1.setTitleOfThesis(0, "Bachelor thesis title");
        student1.setTitleOfThesis(1, "Masters thesis title");

        student1.addCourses(0, bachelor);
        student1.addCourses(1, master);

        student1.setStartYear(2001);
        student1.setGraduationYear(2020);

        System.out.println(student1);

        student1.setBirthDate("230403A519K");
        student1.setTitleOfThesis(0, "Christmas - The most wonderful time of the year");
        student1.setTitleOfThesis(1, "Dreaming of a white Christmas");
        studentCourse9.setGrade(3);
        student1.setGraduationYear(2020);
        System.out.println(student1);

    }

    public static void main(String[] args) {
        //Student student1 =  new Student();
        //test1();
        //test2();
        //test3();
        //week1Tests.test1_v2();


        //System.out.println();
    }
}
