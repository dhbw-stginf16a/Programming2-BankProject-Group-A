package de.dhbw.stginf16a.bankproject.groupa.data.account_types;

import de.dhbw.stginf16a.bankproject.groupa.data.person_types.Customer;

/**
 * Created by leons on 5/23/17.
 */
public class CurrentAccount extends Deposit {

    @Override
    public boolean customerEligible(Customer customer) {
        return true;
    }

}
