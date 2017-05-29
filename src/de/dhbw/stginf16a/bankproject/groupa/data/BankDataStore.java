package de.dhbw.stginf16a.bankproject.groupa.data;

import de.dhbw.stginf16a.bankproject.groupa.data.data_store_actions.DataStoreAction;
import de.dhbw.stginf16a.bankproject.groupa.data.person_types.Customer;

import java.io.*;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;

/**
 * Created by leons on 5/23/17.
 *
 * The BankDataStore object manages all bank-data of the application.
 *
 * It exposes methods to get data.
 * Altering Data is done by dispatching a DataStoreAction which will alter the store.
 *
 */
public class BankDataStore implements Serializable {
    // ---------- SERIALIZATION ----------
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
    public int
            customerIdCount = 0,
            depositIdCount = 0,
            cardIdCount = 0,
            investmentIdCount = 0,
            lendingIdCount = 0,
            transactionIdCount = 0;
    public HashMap<Integer, Customer> customers = new HashMap<>();



}
