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

    public static int getAgeInYears(LocalDate birthday) {
        return calculateAge(birthday, LocalDate.now());
    }
}
