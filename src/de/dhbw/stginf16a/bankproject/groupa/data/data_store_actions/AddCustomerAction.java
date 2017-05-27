package de.dhbw.stginf16a.bankproject.groupa.data.data_store_actions;

import de.dhbw.stginf16a.bankproject.groupa.data.BankDataStore;
import de.dhbw.stginf16a.bankproject.groupa.data.person_types.Customer;

import java.io.Serializable;

/**
 * Created by leons on 5/24/17.
 */
public class AddCustomerAction extends DataStoreAction implements Serializable {
    private Customer customer;

    public AddCustomerAction(Customer customer) {
        super();
        this.customer = customer;
    }

    @Override
    public BankDataStore apply(BankDataStore originalDataStore) {
        originalDataStore.customers.put(
                ++originalDataStore.customerIdCount,
                this.customer
        );

        return originalDataStore;
    }
}
