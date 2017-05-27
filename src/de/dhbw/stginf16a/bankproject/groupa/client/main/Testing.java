package de.dhbw.stginf16a.bankproject.groupa.client.main;

import de.dhbw.stginf16a.bankproject.groupa.data.BankDataStore;
import de.dhbw.stginf16a.bankproject.groupa.data.BankDataStoreSerializationException;
import de.dhbw.stginf16a.bankproject.groupa.data.DataStoreUpdateEventListener;
import de.dhbw.stginf16a.bankproject.groupa.data.data_store_actions.AddCustomerAction;
import de.dhbw.stginf16a.bankproject.groupa.data.person_types.Customer;
import de.dhbw.stginf16a.bankproject.groupa.data.person_types.CustomerTooYoungException;
import de.dhbw.stginf16a.bankproject.groupa.data.person_types.Gender;

import java.time.LocalDate;

import static de.dhbw.stginf16a.bankproject.groupa.data.BankDataStore.dataStore;

/**
 * Created by leons on 5/23/17.
 */
public class Testing {
    public static void main(String[] args) throws CustomerTooYoungException, BankDataStoreSerializationException {

        BankDataStore.dataStore.addEventListener(new DataStoreUpdateEventListener() {
            public void onDataStoreUpdate(BankDataStore dataStore) {
                System.out.println("Hey, got an update!");
            }
        });

        Customer newCustomer = new Customer(
                "John",
                "Doe",
                "1 Infinite Loop\nCupertino, CA",
                "john.doe@domain.tld",
                LocalDate.of(1996, 02, 06),
                Gender.MALE
        );

        BankDataStore.dataStore = BankDataStore.dispatchAction(
                BankDataStore.dataStore,
                new AddCustomerAction(newCustomer)
        );

        System.out.println(BankDataStore.dataStore.getCustomerById(1).getAgeInYears());

    }
}
