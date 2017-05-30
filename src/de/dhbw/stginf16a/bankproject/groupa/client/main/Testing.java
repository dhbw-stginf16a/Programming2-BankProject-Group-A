package de.dhbw.stginf16a.bankproject.groupa.client.main;

import de.dhbw.stginf16a.bankproject.groupa.data.BankDataStoreSerializationException;
import de.dhbw.stginf16a.bankproject.groupa.data.BankDataStoreWrapper;
import de.dhbw.stginf16a.bankproject.groupa.data.DataStoreUpdateEventListener;
import de.dhbw.stginf16a.bankproject.groupa.data.DummyData;
import de.dhbw.stginf16a.bankproject.groupa.data.account_types.CurrentAccount;
import de.dhbw.stginf16a.bankproject.groupa.data.card_types.CreditCard;
import de.dhbw.stginf16a.bankproject.groupa.data.data_store_actions.*;
import de.dhbw.stginf16a.bankproject.groupa.data.lending_types.PersonalLoan;
import de.dhbw.stginf16a.bankproject.groupa.data.person_types.CustomerTooYoungException;
import de.dhbw.stginf16a.bankproject.groupa.data.person_types.Gender;

import java.time.LocalDate;

/**
 * Created by leons on 5/23/17.
 */
public class Testing {
    public static void main(String[] args) throws CustomerTooYoungException, BankDataStoreSerializationException {
        // The global & primary data-store of our application
        BankDataStoreWrapper dataStore = BankDataStoreWrapper.mainDataStore;

        dataStore.addEventListener(new DataStoreUpdateEventListener() {
            @Override
            public void onDataStoreUpdate(BankDataStoreWrapper dataStore) {
                System.out.println(dataStore.getCustomerById(1).customerId);
            }
        });

        // Dispatch an action (add customer in this case)
        dataStore.dispatch(new CreateCustomerAction(
                "Apache",
                "WebServer",
                "Apache Foundation",
                "apache@apache.apache",
                LocalDate.of(1932, 04, 30),
                Gender.APACHEATTACKHELICOPTER
        ));

        // Dispatch an action (add customer in this case)
        dataStore.dispatch(new CreateCustomerAction(
                "Engine",
                "X",
                "nginx Street",
                "nginx@retardedmailadress.haesslich",
                LocalDate.of(1944, 06, 06),
                Gender.OTHER
        ));

        dataStore.dispatch(new CreateDepositAction(
                CurrentAccount.class,
                1));

        dataStore.dispatch(new CreateDepositAction(CurrentAccount.class, 2));
        dataStore.dispatch(new CreateDepositCardAction(
                CreditCard.class,
                1,
                1
        ));

        dataStore.dispatch(new MakeWithdrawalAction(
                1,
                1,
                30000
        ));

        dataStore.dispatch(new CreateLendingAction(PersonalLoan.class, 1, 100));

        dataStore.dispatch(new CreateTransactionAction(
                1,
                1,
                2,
                2,
                100,
                "I hate Webservers"));
        dataStore.dispatch(new DeleteCustomerLendingAction(1,1));

        dataStore.dispatch(new DeleteCustomerCardAction(1,1,1));

        dataStore.dispatch(new DeleteCustomerDepositAction(2,2));

        dataStore.dispatch(new DeleteCustomerAction(2));

        DummyData.insertDummyData(dataStore);

        System.out.println(dataStore.getCustomerById(1));
    }
}
