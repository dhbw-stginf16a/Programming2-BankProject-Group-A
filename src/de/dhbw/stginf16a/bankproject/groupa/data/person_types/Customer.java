package de.dhbw.stginf16a.bankproject.groupa.data.person_types;

import de.dhbw.stginf16a.bankproject.groupa.data.account_types.Deposit;
import de.dhbw.stginf16a.bankproject.groupa.data.lending_types.Lending;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Created by leons on 5/23/17.
 */
public class Customer extends Person implements Serializable {
    private static final int LEGAL_AGE = 18;

    private int customerId = -1;
    private LegalGuardian legalGuardian = null;

    private ArrayList<Lending> lendings = new ArrayList<>();
    private ArrayList<Deposit> deposits = new ArrayList<>();

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

    public void setId(int id) {
        this.customerId = id;
    }

    public int getId() {
        return customerId;
    }

}
