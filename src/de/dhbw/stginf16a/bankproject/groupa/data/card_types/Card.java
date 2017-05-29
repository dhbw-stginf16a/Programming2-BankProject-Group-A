package de.dhbw.stginf16a.bankproject.groupa.data.card_types;

import java.io.Serializable;

/**
 * Created by leons on 5/23/17.
 */
public abstract class Card implements Serializable {
    public int cardId, cardHolderId, depositId;
}
