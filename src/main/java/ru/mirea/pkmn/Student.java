package ru.mirea.pkmn;

import java.io.Serial;
import java.io.Serializable;

/**
 * The Student class represents a student with some fields: firstName, surName, familyName, group
 */
public class Student implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * Represents the first name of the student.
     */
    private String firstName;

    public Student(String firstName, String surName, String familyName, String group) {
        this.firstName = firstName;
        this.surName = surName;
        this.familyName = familyName;
        this.group = group;
    }

    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String value) {
        firstName = value;
    }

    /**
     * Represents the surname of the student.
     */
    private String surName;
    public String getSurName() {
        return surName;
    }
    public void setSurName(String value) {
        surName = value;
    }

    /**
     * Represents the family name of the student.
     */
    private String familyName;
    public String getFamilyName() {
        return familyName;
    }
    public void setFamilyName(String value) {
        familyName = value;
    }

    /**
     * Represents the group to which the student belongs.
     */
    private String group;
    public String getGroup() {
        return group;
    }
    public void setGroup(String value) {
        group = value;
    }

    @Override
    public String toString() {
//        return "Student{" +
//                "firstName='" + firstName + '\'' +
//                ", surName='" + surName + '\'' +
//                ", familyName='" + familyName + '\'' +
//                ", group='" + group + '\'' +
//                '}';

        return surName + " " + firstName + " " + familyName + " " + group;
    }
}
