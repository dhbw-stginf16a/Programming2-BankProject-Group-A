package de.dhbw.stginf16a.bankproject.groupa.data.data_store_actions;

import de.dhbw.stginf16a.bankproject.groupa.data.BankDataStore;
import de.dhbw.stginf16a.bankproject.groupa.data.card_types.Card;
import de.dhbw.stginf16a.bankproject.groupa.data.card_types.ChequeCard;
import de.dhbw.stginf16a.bankproject.groupa.data.card_types.CreditCard;

/**
 * Created by leons on 5/29/17.
 */
public class CreateDepositCardAction extends DataStoreAction {
    public Card card;

    public CreateDepositCardAction(Class type, int customerId, int depositId) {
        if (type == ChequeCard.class) {
            card = new ChequeCard();
        } else if (type == CreditCard.class) {
            card = new CreditCard();
        }

        card.cardHolderId = customerId;
        card.depositId = depositId;
    }

    @Override
    public BankDataStore apply(BankDataStore dataStore) throws DataStoreActionApplyException {
        try {
            int id = ++dataStore.cardIdCount;

            card.cardId = id;
            dataStore.customers.get(card.cardHolderId).deposits.get(card.depositId).cards.add(card);
        } catch (Exception e) {
            throw new DataStoreActionApplyException("An error occured while creating a card and adding it to a customers deposit.");
        }

        return dataStore;
    }
}
