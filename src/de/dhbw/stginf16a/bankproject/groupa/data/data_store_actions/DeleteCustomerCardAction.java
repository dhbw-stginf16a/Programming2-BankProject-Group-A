package de.dhbw.stginf16a.bankproject.groupa.data.data_store_actions;

import de.dhbw.stginf16a.bankproject.groupa.data.BankDataStore;
import de.dhbw.stginf16a.bankproject.groupa.data.card_types.Card;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by thore on 5/30/17.
 */
public class DeleteCustomerCardAction extends DataStoreAction {
    private int customerId;
    private int depositId;
    private int cardId;

    public DeleteCustomerCardAction (int customerId, int depositId, int cardId) {
        this.customerId = customerId;
        this.depositId = depositId;
        this.cardId = cardId;
    }

    @Override
    public BankDataStore apply(BankDataStore dataStore) throws DataStoreActionApplyException {
        try {
            ArrayList<Card> cards = dataStore.customers.get(customerId).deposits.get(depositId).cards;
            Iterator<Card> itr = cards.iterator();
            while (itr.hasNext()) {
                Card card = itr.next();
                if (card.cardId == cardId) {
                    itr.remove();
                    break;
                }

            }

        } catch (Exception e) {
            throw new DataStoreActionApplyException("Error while deleting card from customer.");
        }
        return dataStore;
    }

    @Override
    public String toString() {
        return String.format("%s: Deleted card id %d for deposit %d from customer %d",this.getClass().getSimpleName(), cardId, depositId, customerId);
    }
}