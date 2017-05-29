package de.dhbw.stginf16a.bankproject.groupa.data.account_types;

import de.dhbw.stginf16a.bankproject.groupa.data.Transaction;
import de.dhbw.stginf16a.bankproject.groupa.data.card_types.Card;
import de.dhbw.stginf16a.bankproject.groupa.data.person_types.Customer;

import java.util.ArrayList;

/**
 * Created by Jan-Robin Aumann on 23.05.2017.
 */
public abstract class Deposit {
    private int id;

    private ArrayList<Customer> accountHolders;
    private ArrayList<Card> cards;

    private long balance = 0;
    private ArrayList<Transaction> transactions;

    public abstract boolean customerEligible(Customer customer);
}
