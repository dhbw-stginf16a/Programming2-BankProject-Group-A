package de.dhbw.stginf16a.bankproject.groupa.data.person_types;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.Period;

/**
 * Created by leons on 5/23/17.
 */
public class Person implements Serializable {
    public String firstName, lastName;
    public LocalDate birthday;
    public String address;
    public String email;
    public Gender gender;

    public static int calculateAge(LocalDate birthDate, LocalDate currentDate) {
        if ((birthDate != null) && (currentDate != null)) {
            return Period.between(birthDate, currentDate).getYears();
        } else {
            return 0;
        }
    }

    @Override

    public String toString(){
       String output;
       output =
        firstName +
        ", " +
        lastName +
        ", " +
        birthday +
        ", " +
        email +
        ", " +
        address +
        ", " +
        gender.toString();

       return output.replace("\n", " ");
    }

    public static int getAgeInYears(LocalDate birthday) {
        return calculateAge(birthday, LocalDate.now());
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public String getAddress() {
        return address;
    }

    public String getEmail() {
        return email;
    }

    public Gender getGender() {
        return gender;
    }

}
