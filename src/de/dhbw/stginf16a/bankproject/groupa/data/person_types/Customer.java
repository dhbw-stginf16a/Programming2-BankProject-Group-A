package de.dhbw.stginf16a.bankproject.groupa.data.person_types;

import de.dhbw.stginf16a.bankproject.groupa.data.account_types.Deposit;
import de.dhbw.stginf16a.bankproject.groupa.data.lending_types.Lending;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Created by leons on 5/23/17.
 */
public class Customer extends Person implements Serializable {
    public static final int LEGAL_AGE = 18;

    public int customerId = -1;
    public LegalGuardian legalGuardian = null;

    public ArrayList<Lending> lendings = new ArrayList<>();
    public ArrayList<Deposit> deposits = new ArrayList<>();
}
