package de.dhbw.stginf16a.bankproject.groupa.data.person_types;

import java.time.LocalDate;

/**
 * Created by leons on 5/23/17.
 */
public class LegalGuardian extends Person {

   public LegalGuardian(
           String firstName,
           String lastName,
           String address,
           String email,
           LocalDate birthday,
           Gender gender
   ) {
      super(firstName, lastName, address, email, birthday, gender);
   }

}
