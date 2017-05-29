package de.dhbw.stginf16a.bankproject.groupa.data.data_store_actions;

import de.dhbw.stginf16a.bankproject.groupa.data.BankDataStore;
import de.dhbw.stginf16a.bankproject.groupa.data.Transaction;
import de.dhbw.stginf16a.bankproject.groupa.data.account_types.Deposit;

import java.time.LocalDate;
import java.util.Date;

/**
 * Created by leons on 5/29/17.
 */
public class MakeWithdrawalAction extends DataStoreAction {
    private int customerId, depositId;
    private long amount;

    public MakeWithdrawalAction(int customerId, int depositId, long amount) {
        this.customerId = customerId;
        this.depositId = depositId;
        this.amount = amount;
    }

    @Override
    public BankDataStore apply(BankDataStore dataStore) throws DataStoreActionApplyException {
        try {
            Deposit deposit = dataStore.customers.get(customerId).deposits.get(depositId);
            deposit.balance -= amount;

            int transactionId = ++dataStore.transactionIdCount;
            Transaction transaction = new Transaction();
            transaction.amount = -amount;
            transaction.message = "Withdrawal";
            transaction.timestamp = LocalDate.now();
            transaction.senderId = customerId;
            transaction.receiverId = -1;
            transaction.id = transactionId;

            deposit.transactions.put(transactionId, transaction);
        } catch (Exception e) {
            throw new DataStoreActionApplyException("An error occured while trying to make a withdrawal.");
        }

        return dataStore;
    }
}
