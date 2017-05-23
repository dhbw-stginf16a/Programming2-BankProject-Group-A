package de.dhbw.stginf16a.bankproject.groupa.data;

import de.dhbw.stginf16a.bankproject.groupa.data.person_types.Customer;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.HashMap;

/**
 * Created by leons on 5/23/17.
 */
public class BankDataStore implements Serializable {
    private int
            customerIdCount = 0,
            depositIdCount = 0,
            cardIdCount = 0,
            investmentIdCount = 0,
            lendingIdCount = 0;

    private HashMap<Integer, Customer> customers = new HashMap<>();

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

    public Customer getCustomerById(int id) {
        return customers.get(id);
    }

    public Customer addCustomer(Customer customer) {
        customer.setId(++customerIdCount);
        customers.put(customer.getId(), customer);

        // Return the Customer as you may want to know the ID directly
        return customer;
    }

}
