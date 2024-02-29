package dev.m3s.programming2.homework2;

import java.time.Year;
import java.util.regex.PatternSyntaxException;

public class PersonID {
    Student student;
    private String birthDate = ConstantValues.NO_BIRTHDATE;

    public String setPersonId(final String personID) {
        //System.out.println(personID);
        //System.out.println(checkPersonIDNumber(personID));
        if (!checkPersonIDNumber(personID)) {
            return "Invalid birthday!";
        }
        String day = personID.substring(0,2);
        //System.out.println("day: " + day);
        String month = personID.substring(2,4);
        //System.out.println("month: " + month);
        int yearInt = Integer.parseInt(personID.substring(4,6));
        //System.out.println(String.format("year: %d", yearInt));
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
        student.setBirthDate(birthDate);
        return "Ok";
    }

    // Checks if century character is correct
    private boolean checkValidCharacter(final String personID) {
        //System.out.println(personID);
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

    // Checks if personID has correct format
    private boolean checkPersonIDNumber(final String personID) {
        //System.out.println(personID);
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
        //System.out.println(date);
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
        //System.out.println(splitDate[0]);
        if (splitDate.length == 3) {
            day = Integer.parseInt(splitDate[0]);
            month = Integer.parseInt(splitDate[1]);
            year = Integer.parseInt(splitDate[2]);

            //System.out.println(String.format("day: %d", day));
            //System.out.println(String.format("month: %d", month));
            //System.out.println(String.format("year: %d", year));
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
}
