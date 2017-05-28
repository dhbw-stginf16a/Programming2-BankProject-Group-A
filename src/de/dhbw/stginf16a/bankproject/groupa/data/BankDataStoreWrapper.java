package de.dhbw.stginf16a.bankproject.groupa.data;

import de.dhbw.stginf16a.bankproject.groupa.data.data_store_actions.DataStoreAction;
import de.dhbw.stginf16a.bankproject.groupa.data.person_types.Customer;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by leons on 5/28/17.
 */
public class BankDataStoreWrapper {
    public static BankDataStoreWrapper mainDataStore = new BankDataStoreWrapper();

    private BankDataStore dataStore = new BankDataStore();
    private HashMap<Long, BankDataStore> dataStoreHistory = new HashMap<>();

    // ---------- EVENT-LISTENER STUFF ----------
    private int eventListenersCount = 0;
    private HashMap<Integer, DataStoreUpdateEventListener> eventListeners = new HashMap<>();

    public int addEventListener(DataStoreUpdateEventListener eventListener) {
        eventListeners.put(++eventListenersCount, eventListener);

        // Return the id of the event-listener to be able to remove it later
        return eventListenersCount;
    }

    public void removeEventListener(int eventListenerId) {
        eventListeners.remove(eventListenerId);
    }

    private void callEventListeners() {
        for (DataStoreUpdateEventListener eventListener: this.eventListeners.values()) {
            eventListener.onDataStoreUpdate(dataStore);
        }
    }

    // ---------- EVENT DISPATCHING ----------
    public void dispatch(DataStoreAction action) {

        try {
            // Clone the data store (actions shouldn't mutate the existing one)
            BankDataStore newDataStore =
                    BankDataStore.deserialize(BankDataStore.serialize(dataStore));
            // Archive the old data-store
            dataStoreHistory.put(System.currentTimeMillis(), dataStore);

            System.out.println("DEBUG: Pre-dispatch");
            dataStore = action.apply(newDataStore);
            System.out.println("DEBUG: Post-dispatch");

            callEventListeners();
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    // ---------- DATA-STORE GETTERS ----------
    public Customer getCustomerById(int id) {
        return dataStore.customers.get(id);
    }

}
