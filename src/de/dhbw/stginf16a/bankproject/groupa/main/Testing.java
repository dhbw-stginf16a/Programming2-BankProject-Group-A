package de.dhbw.stginf16a.bankproject.groupa.main;

import de.dhbw.stginf16a.bankproject.groupa.data.BankDataStore;
import de.dhbw.stginf16a.bankproject.groupa.data.BankDataStoreSerializationException;
import de.dhbw.stginf16a.bankproject.groupa.data.person_types.Customer;
import de.dhbw.stginf16a.bankproject.groupa.data.person_types.CustomerTooYoungException;
import de.dhbw.stginf16a.bankproject.groupa.data.person_types.Gender;

import java.time.LocalDate;

/**
 * Created by leons on 5/23/17.
 */
public class Testing {

    public static void main(String[] args) throws CustomerTooYoungException, BankDataStoreSerializationException {
        BankDataStore bds = new BankDataStore();

        Customer newCustomer = new Customer(
                "John",
                "Doe",
                "1 Infinite Loop\nCupertino, CA",
                "john.doe@domain.tld",
                LocalDate.of(1981, 11, 24),
                Gender.MALE
        );

        newCustomer = bds.addCustomer(newCustomer);
        int id = newCustomer.getId();

        System.out.println(newCustomer.getId() + " - " + newCustomer.getAgeInYears());

        String serialized = BankDataStore.serialize(bds);
        System.out.println(serialized);

        BankDataStore newBankDataStore = BankDataStore.deserialize(serialized);
        Customer hopefullyOldCustomer = newBankDataStore.getCustomerById(id);

        System.out.println(hopefullyOldCustomer.getId() + " - " + hopefullyOldCustomer.getAgeInYears());
    }

}
