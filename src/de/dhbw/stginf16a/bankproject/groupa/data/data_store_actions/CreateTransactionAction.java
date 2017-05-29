package de.dhbw.stginf16a.bankproject.groupa.data.data_store_actions;

import de.dhbw.stginf16a.bankproject.groupa.data.BankDataStore;
import de.dhbw.stginf16a.bankproject.groupa.data.Transaction;
import de.dhbw.stginf16a.bankproject.groupa.data.account_types.Deposit;

import java.time.LocalDate;

/**
 * Created by leons on 5/29/17.
 */
public class CreateTransactionAction extends DataStoreAction {
    Transaction transaction = new Transaction();
    int senderDepositId, receiverDepositId;

    public CreateTransactionAction(int senderId, int senderDepositId, int receiverId, int receiverDepositId, long amount, String message) {
        transaction.senderId = senderId;
        transaction.receiverId = receiverId;
        transaction.amount = amount;
        transaction.message = message;

        this.senderDepositId = senderDepositId;
        this.receiverDepositId = receiverDepositId;
    }

    @Override
    public BankDataStore apply(BankDataStore dataStore) throws DataStoreActionApplyException {
        transaction.id = ++dataStore.transactionIdCount;
        transaction.timestamp = LocalDate.now();

        Deposit senderDeposit = dataStore.customers.get(transaction.senderId).deposits.get(senderDepositId);
        Deposit receiverDeposit = dataStore.customers.get(transaction.receiverId).deposits.get(receiverDepositId);

        senderDeposit.balance -= transaction.amount;
        receiverDeposit.balance += transaction.amount;

        senderDeposit.transactions.put(transaction.id, transaction);
        receiverDeposit.transactions.put(transaction.id, transaction);

        return dataStore;
    }
}
