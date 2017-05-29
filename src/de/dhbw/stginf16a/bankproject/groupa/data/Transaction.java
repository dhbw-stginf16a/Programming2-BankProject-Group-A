package de.dhbw.stginf16a.bankproject.groupa.data;

import de.dhbw.stginf16a.bankproject.groupa.data.person_types.Customer;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

/**
 * Created by leons on 5/23/17.
 */
public class Transaction implements Serializable {
    public int id;

    public int senderId;
    public int receiverId;

    public long amount;

    public String message;
    public LocalDate timestamp;
}
