package de.dhbw.stginf16a.bankproject.groupa.data.person_types;

import de.dhbw.stginf16a.bankproject.groupa.data.account_types.Deposit;
import de.dhbw.stginf16a.bankproject.groupa.data.lending_types.Lending;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by leons on 5/23/17.
 */
public class Customer extends Person implements Serializable {
    public static final int LEGAL_AGE = 18;

    public int customerId = -1;
    public LegalGuardian legalGuardian = null;

    public HashMap<Integer, Lending> lendings = new HashMap<>();
    public HashMap<Integer, Deposit> deposits = new HashMap<>();
}
