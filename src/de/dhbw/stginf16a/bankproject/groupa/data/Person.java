package de.dhbw.stginf16a.bankproject.groupa.data;

import java.time.LocalDate;
import java.time.Period;

/**
 * Created by leons on 5/23/17.
 */
public class Person {
    private String firstName, lastName;
    private LocalDate birthday;
    private String address;
    private String email;
    private Gender gender;

    public static int calculateAge(LocalDate birthDate, LocalDate currentDate) {
        if ((birthDate != null) && (currentDate != null)) {
            return Period.between(birthDate, currentDate).getYears();
        } else {
            return 0;
        }
    }

    public Person(
            String firstName,
            String lastName,
            String address,
            String email,
            LocalDate birthday,
            Gender gender
    ) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.email = email;
        this.birthday = birthday;
        this.gender = gender;
    }

    public int getAgeInYears() {
        return calculateAge(this.birthday, LocalDate.now());
    }
}
