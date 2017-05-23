package de.dhbw.stginf16a.bankproject.groupa.data;

import java.util.Date;

/**
 * Created by leons on 5/23/17.
 */
public class Transaction {
    private Customer sender;
    private Customer receiver;

    private long amount;

    private String message;
    private Date timestamp;
}
