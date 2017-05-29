package de.dhbw.stginf16a.bankproject.groupa.data;

import de.dhbw.stginf16a.bankproject.groupa.data.account_types.CorporateSavings;
import de.dhbw.stginf16a.bankproject.groupa.data.account_types.CurrentAccount;
import de.dhbw.stginf16a.bankproject.groupa.data.card_types.CreditCard;
import de.dhbw.stginf16a.bankproject.groupa.data.data_store_actions.*;
import de.dhbw.stginf16a.bankproject.groupa.data.person_types.CustomerTooYoungException;
import de.dhbw.stginf16a.bankproject.groupa.data.person_types.Gender;

import java.time.LocalDate;

/**
 * Created by leons on 5/29/17.
 */
public class DummyData {

    public static void insertDummyData(BankDataStoreWrapper dataStore) throws CustomerTooYoungException {
        dataStore.dispatch(new CreateCustomerAction(
                "Max",
                "Mustermann",
                "Musterstr. 4\n78234 Musterstadt",
                "muster@mann.tld",
                LocalDate.of(1969, 04, 14),
                Gender.APACHEATTACKHELICOPTER
        ));
        dataStore.dispatch(new CreateCustomerAction(
                "Hans",
                "Mustermann",
                "Musterstr. 4\n78234 Musterstadt",
                "muster@mann.tld",
                LocalDate.of(1987, 04, 14),
                Gender.APACHEATTACKHELICOPTER
        ));
        dataStore.dispatch(new CreateCustomerAction(
                "Heike",
                "Mustermann",
                "Musterstr. 4\n78234 Musterstadt",
                "muster@mann.tld",
                LocalDate.of(1802, 04, 14),
                Gender.OTHER
        ));
        dataStore.dispatch(new CreateCustomerAction(
                "Gert",
                "Mustermann",
                "Musterstr. 4\n78234 Musterstadt",
                "muster@mann.tld",
                LocalDate.of(1804, 04, 14),
                Gender.FEMALE
        ));

        dataStore.dispatch(new CreateDepositAction(CurrentAccount.class, 1));
        dataStore.dispatch(new CreateDepositAction(CurrentAccount.class, 2));
        dataStore.dispatch(new CreateDepositAction(CorporateSavings.class, 2));
        dataStore.dispatch(new CreateDepositAction(CurrentAccount.class, 3));
        dataStore.dispatch(new CreateDepositAction(CurrentAccount.class, 4));

        dataStore.dispatch(new CreateDepositCardAction(CreditCard.class, 1, 1));
        dataStore.dispatch(new CreateDepositCardAction(CreditCard.class, 2, 2));
        dataStore.dispatch(new CreateDepositCardAction(CreditCard.class, 3, 4));
        dataStore.dispatch(new CreateDepositCardAction(CreditCard.class, 4, 5));

        dataStore.dispatch(new MakeWithdrawalAction(3, 4, -50000));
        dataStore.dispatch(new CreateTransactionAction(3, 4, 1, 1 30000, "Koks"));
    }

}
