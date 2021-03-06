package de.dhbw.stginf16a.bankproject.groupa.data.data_store_actions;

import de.dhbw.stginf16a.bankproject.groupa.data.BankDataStore;
import de.dhbw.stginf16a.bankproject.groupa.data.account_types.*;
import de.dhbw.stginf16a.bankproject.groupa.data.person_types.Customer;

/**
 * Created by leons on 5/29/17.
 */
public class CreateDepositAction extends DataStoreAction {

    private Deposit deposit;
    private int customerId;

    public CreateDepositAction(Class depositClass, int customerId) {
        this.customerId = customerId;

        if (depositClass == JuniorAccount.class) {
            deposit = new JuniorAccount();
        } else if (depositClass == CurrentAccount.class) {
            deposit = new CurrentAccount();
        } else if (depositClass == StudentSavings.class) {
            deposit = new StudentSavings();
        } else if (depositClass == CorporateSavings.class) {
            deposit = new CorporateSavings();
        }
    }


    @Override
    public BankDataStore apply(BankDataStore dataStore) throws DataStoreActionApplyException {
        try {
            int id = ++dataStore.depositIdCount;

            deposit.id = id;

            Customer customer = dataStore.customers.get(customerId);
            customer.deposits.put(id, deposit);
        } catch (Exception e) {
            throw new DataStoreActionApplyException("Error while adding a deposit to a customer");
        }

        return dataStore;
    }

    @Override
    public String toString() {
        return String.format("%s: Create Deposit with id %d for Customer %d",this.getClass().getSimpleName(), deposit.id, customerId);
    }
}
