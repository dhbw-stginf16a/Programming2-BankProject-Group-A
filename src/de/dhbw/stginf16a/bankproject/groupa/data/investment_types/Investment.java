package de.dhbw.stginf16a.bankproject.groupa.data.investment_types;

import de.dhbw.stginf16a.bankproject.groupa.data.Transaction;
import de.dhbw.stginf16a.bankproject.groupa.data.card_types.Card;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by leons on 5/23/17.
 */
public abstract class Investment implements Serializable {
    public int investmentId;

    public int investorId;

    public long amount = 0;
    public HashMap<Integer, Transaction> transactions = new HashMap<>();
}