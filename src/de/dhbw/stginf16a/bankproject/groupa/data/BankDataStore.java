package de.dhbw.stginf16a.bankproject.groupa.data;

import de.dhbw.stginf16a.bankproject.groupa.data.data_store_actions.DataStoreAction;
import de.dhbw.stginf16a.bankproject.groupa.data.person_types.Customer;

import java.io.*;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;

/**
 * Created by leons on 5/23/17.
 */
public class BankDataStore implements Serializable {
    public static BankDataStore dataStore = new BankDataStore();
    public static BankDataStore dispatchAction(BankDataStore dataStore, DataStoreAction action) {
        BankDataStore newDataStore = action.apply(dataStore);
        newDataStore.callEventListeners();
        return newDataStore;
    }

    public static String serialize(BankDataStore dataStore) throws BankDataStoreSerializationException {
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);

            objectOutputStream.writeObject(dataStore);
            objectOutputStream.flush();

            byte[] base64Encoded = Base64.getEncoder().encode(byteArrayOutputStream.toByteArray());
            return new String(base64Encoded);
        } catch (Exception e) {
            throw new BankDataStoreSerializationException(false, e);
        }
    }

    public static BankDataStore deserialize(String base64EncodedString) throws BankDataStoreSerializationException {
        Object object;

        try {
            byte[] byteArray = Base64.getDecoder().decode(base64EncodedString);
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byteArray);
            ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);

            object = objectInputStream.readObject();
        } catch (Exception e) {
            throw new BankDataStoreSerializationException(true, e);
        }

        if (!(object instanceof BankDataStore)) {
            throw new BankDataStoreSerializationException(true, "The deserialized object is not of type 'BankDataStore'");
        }

        return (BankDataStore) object;
    }

    // ---------- DATA SECTION ----------
    private ArrayList<DataStoreUpdateEventListener> eventListeners = new ArrayList<>();
    public int
            customerIdCount = 0,
            depositIdCount = 0,
            cardIdCount = 0,
            investmentIdCount = 0,
            lendingIdCount = 0;
    public HashMap<Integer, Customer> customers = new HashMap<>();

    public void addEventListener(DataStoreUpdateEventListener eventListener) {
        eventListeners.add(eventListener);
    }
    public void callEventListeners() {
        for (DataStoreUpdateEventListener eventListener: this.eventListeners) {
            eventListener.onDataStoreUpdate(this);
        }
    }
    public Customer getCustomerById(int id) {
        return customers.get(id);
    }
}
