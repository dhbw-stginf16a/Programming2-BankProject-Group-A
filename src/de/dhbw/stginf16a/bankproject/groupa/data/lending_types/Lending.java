package de.dhbw.stginf16a.bankproject.groupa.data.lending_types;

import de.dhbw.stginf16a.bankproject.groupa.data.person_types.Customer;

import java.util.ArrayList;

/**
 * Created by leons on 5/23/17.
 */
public abstract class Lending {
    private int id;
    private ArrayList<Customer> customers;

    private long amount;

    private long interestRate; // per 1000
}
