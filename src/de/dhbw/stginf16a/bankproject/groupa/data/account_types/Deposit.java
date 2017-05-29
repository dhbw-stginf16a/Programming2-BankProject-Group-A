package de.dhbw.stginf16a.bankproject.groupa.data.account_types;

import de.dhbw.stginf16a.bankproject.groupa.data.Transaction;
import de.dhbw.stginf16a.bankproject.groupa.data.card_types.Card;
import de.dhbw.stginf16a.bankproject.groupa.data.person_types.Customer;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Jan-Robin Aumann on 23.05.2017.
 */
public abstract class Deposit {
    public int id;

    public int accountHolderId;
    public ArrayList<Card> cards;

    public long balance = 0;
    public HashMap<Integer, Transaction> transactions;

    public abstract boolean customerEligible(Customer customer);
}

