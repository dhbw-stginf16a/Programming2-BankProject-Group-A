package de.dhbw.stginf16a.bankproject.groupa.data.person_types;

/**
 * Created by leons on 5/23/17.
 */
public enum Gender {
    MALE, FEMALE, APACHEATTACKHELICOPTER, OTHER;

    @Override
    public String toString() {
        switch (this) {

            case MALE:
                return "Male";
            case FEMALE:
                return "Female";
            case APACHEATTACKHELICOPTER:
                return "Apache Attack Helicopter";
            case OTHER:
                return "Other";
            default:
                return name();
        }
    }
}
