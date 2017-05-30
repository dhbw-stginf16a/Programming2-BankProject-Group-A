package de.dhbw.stginf16a.bankproject.groupa.data.data_store_actions;

import de.dhbw.stginf16a.bankproject.groupa.data.BankDataStore;
import de.dhbw.stginf16a.bankproject.groupa.data.person_types.*;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * Created by leons on 5/24/17.
 */
public class CreateCustomerAction extends DataStoreAction implements Serializable {
    private Customer customer;

    public CreateCustomerAction(
            String firstName,
            String lastName,
            String address,
            String email,
            LocalDate birthday,
            Gender gender,
            LegalGuardian legalGuardian
    ) throws CustomerTooYoungException {
        customer = new Customer();
        customer.firstName = firstName;
        customer.lastName = lastName;
        customer.address = address;
        customer.email = email;
        customer.birthday = birthday;
        customer.gender = gender;
        // Will be overwritten by apply
        customer.customerId = -1;

        if (legalGuardian == null && Person.getAgeInYears(birthday) < Customer.LEGAL_AGE) {
            throw new CustomerTooYoungException();
        }
    }

    public CreateCustomerAction(
            String firstName,
            String lastName,
            String address,
            String email,
            LocalDate birthday,
            Gender gender
    ) throws CustomerTooYoungException {
            this(
                    firstName,
                    lastName,
                    address,
                    email,
                    birthday,
                    gender,
                    null
            );
    }

    @Override
    public BankDataStore apply(BankDataStore dataStore) {
        // DataStore is already cloned, can be mutated now
        int id = ++dataStore.customerIdCount;

        customer.customerId = id;
        dataStore.customers.put(id, this.customer);

        return dataStore;
    }

    @Override
    public String toString() {
        return String.format("%s: Create Customer with id %d (%s, %s)",this.getClass().getSimpleName(), customer.customerId, customer.lastName, customer.firstName);
    }
}
