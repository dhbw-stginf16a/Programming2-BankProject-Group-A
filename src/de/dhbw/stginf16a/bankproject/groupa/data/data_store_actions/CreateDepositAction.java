package de.dhbw.stginf16a.bankproject.groupa.data.data_store_actions;

import de.dhbw.stginf16a.bankproject.groupa.data.BankDataStore;
import de.dhbw.stginf16a.bankproject.groupa.data.account_types.*;
import de.dhbw.stginf16a.bankproject.groupa.data.person_types.Customer;

/**
 * Created by leons on 5/29/17.
 */
public class CreateDepositAction extends DataStoreAction {

    private Deposit deposit;
    private int customer_id;

    public CreateDepositAction(DepositType type, int customer_id) {
        this.customer_id = customer_id;

        switch (type) {
            case JuniorAccount:
                deposit = new JuniorAccount();
                break;
            case CurrentAccount:
                deposit = new CurrentAccount();
                break;
            case StudentSavings:
                deposit = new StudentSavings();
                break;
            case CorporateSavings:
                deposit = new CorporateSavings();
                break;
        }
    }


    @Override
    public BankDataStore apply(BankDataStore dataStore) throws DataStoreActionApplyException {
        try {
            int id = ++dataStore.depositIdCount;

            deposit.id = id;

            Customer customer = dataStore.customers.get(customer_id);
            customer.deposits.put(id, deposit);
        } catch (Exception e) {
            throw new DataStoreActionApplyException("Error while adding a deposit to a customer");
        }

        return dataStore;
    }
}
