package de.dhbw.stginf16a.bankproject.groupa.data.data_store_actions;

import de.dhbw.stginf16a.bankproject.groupa.data.BankDataStore;
import de.dhbw.stginf16a.bankproject.groupa.data.account_types.CorporateSavings;
import de.dhbw.stginf16a.bankproject.groupa.data.lending_types.*;

/**
 * Created by leons on 5/29/17.
 */
public class CreateLendingAction extends DataStoreAction {
    private Lending lending;
    private int customerId;
    private long interestRate;

    public CreateLendingAction(Class lendingClass, int customerId, long interestRate) {
        this.customerId = customerId;
        this.interestRate = interestRate;

        if (lendingClass == CorporateLoan.class) {
            lending = new CorporateLoan();
        } else if (lendingClass == PersonalLoan.class) {
            lending = new PersonalLoan();
        } else if (lendingClass == Mortgage.class) {
            lending = new Mortgage();
        }
    }

    @Override
    public BankDataStore apply(BankDataStore dataStore) throws DataStoreActionApplyException {
        try {
            int id = ++dataStore.lendingIdCount;
            lending.id = id;
            lending.customerId = customerId;
            lending.interestRate = interestRate;

            dataStore.customers.get(customerId).lendings.put(id, lending);
        } catch (Exception e) {
            throw new DataStoreActionApplyException("Error while adding a lending to a customer");
        }

        return dataStore;
    }

    public String toString() {
        return String.format("%s: Create lending with id %d for customer %d", this.getClass().getSimpleName(), lending.id, customerId);
    }
}