package de.dhbw.stginf16a.bankproject.groupa.data;

import java.time.LocalDate;

/**
 * Created by leons on 5/23/17.
 */
public class Customer extends Person {
    private static final int LEGAL_AGE = 18;
    private static int customer_id_count;

    private int customer_id;
    private LegalGuardian legalGuardian = null;

    private static int getNextCustomerId() {
        return ++customer_id_count;
    }

    public Customer(
            String firstName,
            String lastName,
            String address,
            String email,
            LocalDate birthday,
            Gender gender,
            LegalGuardian legalGuardian
    ) throws CustomerTooYoungException {
        super(firstName, lastName, address, email, birthday, gender);

        if (legalGuardian == null && Person.calculateAge(birthday, LocalDate.now()) < LEGAL_AGE) {
            throw new CustomerTooYoungException();
        }
    }

    public Customer(
            String firstName,
            String lastName,
            String address,
            String email,
            LocalDate birthday,
            Gender gender
    ) throws CustomerTooYoungException {
        this(firstName, lastName, address, email, birthday, gender, null);
    }

}
