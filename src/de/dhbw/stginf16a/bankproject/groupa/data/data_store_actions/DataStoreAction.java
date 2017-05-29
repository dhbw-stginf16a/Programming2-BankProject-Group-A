package de.dhbw.stginf16a.bankproject.groupa.data.data_store_actions;

import de.dhbw.stginf16a.bankproject.groupa.data.BankDataStore;

import java.io.Serializable;

/**
 * Created by leons on 5/24/17.
 */
public abstract class DataStoreAction implements Serializable {

    public abstract BankDataStore apply(BankDataStore clonedDataStore) throws DataStoreActionApplyException;

}
