package GamePlayModel;

import java.util.Random;

/**
 * This is the class used to define the card type
 *
 */
public enum CardType {

    /**
     * The infantry.
     */
    Infantry(0),

    /**
     * The cavalry.
     */
    Cavalry(1),

    /**
     * The artillery.
     */
    Artillery(2);

    /**
     * The card type number.
     */
    private int CardTypeNumber;

    /**
     * Instantiates a new card type.
     *
     * @param cardTypeNumber the card type number.
     */
    private CardType(int cardTypeNumber) {
        this.CardTypeNumber = cardTypeNumber;
    }

    /**
     * Gets the card type number.
     *
     * @return the card type number.
     */
    public int getCardTypeNumber() {
        return CardTypeNumber;
    }



}
