package de.dhbw.stginf16a.bankproject.groupa.data.data_store_actions;

import de.dhbw.stginf16a.bankproject.groupa.data.BankDataStore;

/**
 * Created by thore on 5/29/17.
 */
public class DeleteCustomerAction extends DataStoreAction {
    private int customerId;

    public DeleteCustomerAction(int customerId){
       this.customerId = customerId;
    }


    @Override
    public BankDataStore apply(BankDataStore dataStore) throws DataStoreActionApplyException {
        try {
            dataStore.customers.remove(customerId);
        } catch (Exception e){
            throw new DataStoreActionApplyException("Error while deleting customer.");
        }
        return dataStore;
    }


    public String toString() {
        return String.format("%s: Deleted customer with id %d", this.getClass().getSimpleName(), customerId);
    }
}
