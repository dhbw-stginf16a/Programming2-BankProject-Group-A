package de.dhbw.stginf16a.bankproject.groupa.data.data_store_actions;

import de.dhbw.stginf16a.bankproject.groupa.data.BankDataStore;

/**
 * Created by thore on 5/29/17.
 */
public class DeleteCustomerLendingAction extends DataStoreAction {
    private int customerId;
    private int lendingId;

    public DeleteCustomerLendingAction (int customerId, int lendingId) {
        this.customerId = customerId;
        this.lendingId = lendingId;
    }

    @Override
    public BankDataStore apply(BankDataStore dataStore) throws DataStoreActionApplyException {
        try {
            dataStore.customers.get(customerId).lendings.remove(lendingId);
        } catch (Exception e) {
            throw new DataStoreActionApplyException("Error while deleting lending from customer.");
        }
        return dataStore;
    }

    public String toString() {
        return String.format("%s: Deleted lending id %d for customer id %d",this.getClass().getSimpleName(), lendingId, customerId);
    }
}
