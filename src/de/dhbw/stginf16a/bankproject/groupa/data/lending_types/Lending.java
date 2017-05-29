package de.dhbw.stginf16a.bankproject.groupa.data.lending_types;

import de.dhbw.stginf16a.bankproject.groupa.data.person_types.Customer;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by leons on 5/23/17.
 */
public abstract class Lending implements Serializable {
    public int id;
    public int customerId;

    public long amount;

    public long interestRate; // per 1000
}
