package de.dhbw.stginf16a.bankproject.groupa.data.data_store_actions;

import de.dhbw.stginf16a.bankproject.groupa.data.BankDataStore;

/**
 * Created by thore on 5/29/17.
 */
public class DeleteCustomerDepositAction extends DataStoreAction {
    private int customerId;
    private int depositId;

    public DeleteCustomerDepositAction (int customerId, int depositId) {
        this.customerId = customerId;
        this.depositId = depositId;
    }

    @Override
    public BankDataStore apply(BankDataStore dataStore) throws DataStoreActionApplyException {
        try {
            dataStore.customers.get(customerId).deposits.remove(depositId);
        } catch (Exception e) {
            throw new DataStoreActionApplyException("Error while deleting deposit from customer.");
        }
        return dataStore;
    }

    @Override
    public String toString() {
        return String.format("%s: Deleted deposit id %d for customer %d",this.getClass().getSimpleName(), depositId, customerId);
    }
}
