package de.dhbw.stginf16a.bankproject.groupa.data.account_types;

import de.dhbw.stginf16a.bankproject.groupa.data.Customer;

/**
 * Created by leons on 5/23/17.
 */
public class JuniorAccount extends Deposit {
    public static final int MAX_AGE = 16;

    public static boolean customerEglible(Customer customer) {
        if (customer.getAgeInYears() > MAX_AGE) {
            return false;
        }

        return true;
    }
}
