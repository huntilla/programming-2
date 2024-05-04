package dev.m3s.programming2.homework3;

import java.util.ArrayList;
import java.util.List;

public class StudentTest {

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

    public static void test2() {
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

        student1.setBirthDate("");
        student1.setTitleOfThesis(0, "Christmas - The most wonderful time of the year");
        student1.setTitleOfThesis(1, "Dreaming of a white Christmas");
        studentCourse9.setGrade(3);
        student1.setGraduationYear(2020);
        System.out.println(student1);

    }

}
