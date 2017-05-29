package de.dhbw.stginf16a.bankproject.groupa.data.data_store_actions;

import de.dhbw.stginf16a.bankproject.groupa.data.BankDataStore;
import de.dhbw.stginf16a.bankproject.groupa.data.lending_types.*;

/**
 * Created by leons on 5/29/17.
 */
public class CreateLendingAction extends DataStoreAction {
    Lending lending;
    int customer_id;

    public CreateLendingAction(LendingType type, int customer_id) {
        switch (type) {
            case CorporateLoan:
                lending = new CorporateLoan();
                break;
            case PersonalLoan:
                lending = new PersonalLoan();
                break;
            case Mortgage:
                lending = new Mortgage();
                break;
        }
    }

    @Override
    public BankDataStore apply(BankDataStore dataStore) throws DataStoreActionApplyException {
        try{
            dataStore.customers.get(customer_id).lendings.add(lending);
        } catch (Exception e) {
            throw new DataStoreActionApplyException("Error while adding a lending to a customer");
        }

        return dataStore;
    }
}
