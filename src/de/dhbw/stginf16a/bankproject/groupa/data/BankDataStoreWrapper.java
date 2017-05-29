package de.dhbw.stginf16a.bankproject.groupa.data;

import de.dhbw.stginf16a.bankproject.groupa.data.account_types.Deposit;
import de.dhbw.stginf16a.bankproject.groupa.data.card_types.Card;
import de.dhbw.stginf16a.bankproject.groupa.data.data_store_actions.DataStoreAction;
import de.dhbw.stginf16a.bankproject.groupa.data.data_store_actions.DataStoreActionApplyException;
import de.dhbw.stginf16a.bankproject.groupa.data.lending_types.Lending;
import de.dhbw.stginf16a.bankproject.groupa.data.person_types.Customer;

import javax.swing.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by leons on 5/28/17.
 *
 * The BankDataStore-Wrapper holds the application-state in form of the BankDataStore
 * and makes it easily available through convenient getters and dispatching of
 * actions.
 *
 * This has the benefit of providing a well-defined API for retrieving values from the
 * state as well having a State-History for rollbacks and avoiding side-effects
 * when mutating it.
 */
public class BankDataStoreWrapper {
    public static BankDataStoreWrapper mainDataStore = new BankDataStoreWrapper();

    private BankDataStore dataStore = new BankDataStore();
    private HashMap<Long, BankDataStore> dataStoreHistory = new HashMap<>();

    private LinkedBlockingQueue<DataStoreAction> actionQueue = new LinkedBlockingQueue<>();
    private boolean actionApplyInProgress = false;
    private HashMap<Long, DataStoreAction> executedActions = new HashMap<>();

    // ---------- EVENT-LISTENER STUFF ----------
    private int eventListenersCount = 0;
    private HashMap<Integer, DataStoreUpdateEventListener> eventListeners = new HashMap<>();

    /**
     * Add an event-listener
     *
     * To get notified of any state-updates, you can subscribe an
     * DataStoreUpdateEventListener.
     *
     * The onDataStoreUpdate-method will get called with the state wrapper
     * having access to the newest state.
     *
     * The return-value is the subscription-identifier.
     * Use this value on removeEventListener to unsubscribe.
     *
     * @param eventListener The event-listener to call
     * @return Subscription-identifier to be used with removeEventListener
     */
    public int addEventListener(DataStoreUpdateEventListener eventListener) {
        eventListeners.put(++eventListenersCount, eventListener);

        // Return the id of the event-listener to be able to remove it later
        return eventListenersCount;
    }

    /**
     * Remove an event-listener
     *
     * If you subscribed an event-listener and don't want to receive updates anymore
     * and have his reference removed for garbage-collection, call this method with the
     * subscription-identifier returned when subscribing.
     *
     * @param eventListenerId Subscription-Identifier to remove
     */
    public void removeEventListener(int eventListenerId) {
        eventListeners.remove(eventListenerId);
    }

    /**
     * Call all event-listeners currently subscribed
     */
    private void callEventListeners() {
        for (DataStoreUpdateEventListener eventListener: this.eventListeners.values()) {
            eventListener.onDataStoreUpdate(this);
        }
    }

    // ---------- EVENT DISPATCHING ----------

    /**
     * Dispatch an action to the data-store.
     *
     * When dispatching an action, it gets added to the queue with actions to apply.
     * If an action-apply isn't already in progress, the action will be applied immediately.
     *
     * In the case that another action is currently being applied, the action will be
     * applied after all others have been processed to avoid side-effects.
     *
     * After each action has been applied, all event-listeners will get called.
     *
     * @param action The action to apply
     */
    public void dispatch(DataStoreAction action) {
        actionQueue.add(action);

        if (actionApplyInProgress) {
            // There is another element in the queue, we don't have to apply it manually
            return;
        }
        // This is the only action, apply it now
        applyActions();
    }

    /**
     * Apply all actions currently in the queue.
     *
     * This method will apply all actions currently in the queue one after another.
     * A locking mechanism is built in to avoid collisions.
     * If an action gets added to the queue while it is being processed,
     * this method will process it too. (You should just call it once when adding
     * an action to the queue)
     *
     * When an action was successfully applied to the state, all event-listeners
     * will get called.
     */
    private void applyActions() {
        if (actionApplyInProgress) {
            // Actions are already being processed, nothing to do here
            return;
        }

        // Lock apply actions
        actionApplyInProgress = true;

        DataStoreAction action;
        while ((action = actionQueue.poll()) != null) {
            // There is an action to apply

            BankDataStore newDataStore = null;
            try {
                // Clone the data store (actions shouldn't mutate the existing one)
                newDataStore = BankDataStore.deserialize(BankDataStore.serialize(dataStore));

                // Archive the old data-store
                dataStoreHistory.put(System.currentTimeMillis(), dataStore);
            } catch (Exception e) {
                e.printStackTrace();
                System.exit(1);
            }

            try {
                System.out.println("DEBUG: Pre-dispatch");
                dataStore = action.apply(newDataStore);
                executedActions.put(System.currentTimeMillis(), action);
                System.out.println("DEBUG: Post-dispatch");
            } catch (DataStoreActionApplyException e) {
                System.err.println("There was an error while applying an action to the BankDataStore:");
                e.printStackTrace();
            }

            callEventListeners();
        }

        // Unlock apply actions
        actionApplyInProgress = false;
    }

    // ---------- DATA-STORE GETTERS ----------
    public Customer getCustomerById(int id) {
        return dataStore.customers.get(id);
    }

    public ArrayList<Customer> getCustomers() {
        return new ArrayList<Customer>(dataStore.customers.values());
    }

    public ArrayList<Deposit> getCustomerDeposits(int customerId) {
        return new ArrayList<Deposit>(dataStore.customers.get(customerId).deposits.values());
    }

    public ArrayList<Lending> getCustomerLendings(int customerId) {
        return new ArrayList<Lending>(dataStore.customers.get(customerId).lendings.values());
    }

    public ArrayList<Card> getCustomerDepositCards(int customerId, int depositId) {
        return dataStore.customers.get(customerId).deposits.get(depositId).cards;
    }

    public ArrayList<Transaction> getCustomerDepositTransactions(int customerId, int depositId) {
        return new ArrayList<Transaction>(dataStore.customers.get(customerId).deposits.get(depositId).transactions.values());
    }

    public HashMap<Long, DataStoreAction> geExecutedActions() {
        return executedActions;
    }

}
