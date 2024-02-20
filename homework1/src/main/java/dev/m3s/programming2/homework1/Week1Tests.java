package dev.m3s.programming2.homework1;

public class Week1Tests {

    Student student;

    public static void test1() {
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

    public static void test2() {
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

    public static void test3() {
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

    public static void test1_v2() {
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

        student2.setPersonId("220146+611K");
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

        System.out.println();
    }
}
