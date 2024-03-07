package dev.m3s.programming2.homework3;

import java.util.Random;

abstract class Person {
    private String firstName = ConstantValues.NO_NAME;
    private String lastName = ConstantValues.NO_NAME;
    private String birthDate = ConstantValues.NO_BIRTHDATE;

    Person(String lname, String fname) {
        setFirstName(fname);
        setLastName(lname);
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

    public String getBirthDate() {
        return birthDate;
    }

    public String setBirthDate(String personId) {
        if (personId != null) {
            PersonID personID = new PersonID();
            if (personID.setPersonId(personId).equals("Ok")) {
                this.birthDate = personID.getBirthDate();
                return personID.getBirthDate();
            }
        }
        return "No change";
    }

    protected int getRandomId(final int min, final int max) {
        Random rand = new Random();
        return rand.nextInt(max - min +1) + min;
    }

    abstract String getIdString();
}
