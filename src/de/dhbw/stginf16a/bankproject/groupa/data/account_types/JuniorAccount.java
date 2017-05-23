package de.dhbw.stginf16a.bankproject.groupa.data.account_types;

import de.dhbw.stginf16a.bankproject.groupa.data.person_types.Customer;

/**
 * Created by leons on 5/23/17.
 */
public class JuniorAccount extends Deposit {
    public static final int MAX_AGE = 16;

    @Override
    public boolean customerEligible(Customer customer) {
        if (customer.getAgeInYears() > MAX_AGE) {
            return false;
        }

        return true;
    }
}
