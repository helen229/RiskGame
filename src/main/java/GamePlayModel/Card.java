package GamePlayModel;
import java.util.Random;

/**
 * This is the card class. We use this class to define player's cards.
 *
 */

public class Card {

    /**
     * The player.
     */
    private PlayerModel player;

    /**
     * The card type.
     */
    private CardType cardType;

    /**
     * Gets the player.
     *
     * @return the player
     */
    public PlayerModel getPlayer() {
        return player;
    }

    /**
     * Sets the player.
     *
     * @param player the new player
     */
    public void setPlayer(PlayerModel player) {
        this.player = player;
    }

    /**
     * Gets the card type.
     *
     * @return the card type
     */
    public CardType getCardType() {
        return cardType;
    }
    
    /**
     * Gets the card type name.
     * @return card Type name
    */
    public String getCardTypeName() {
        return cardType.name();
    }

    /**
     * Sets the card type.
     *
     * @param cardType the new card type
     */
    public void setCardType(CardType cardType) {
        this.cardType = cardType;
    }

    /**
     * Instantiates a new card.
     *
     * @param player the player
     */
    public Card(PlayerModel player) {
        this.player = player;
        Random random = new Random();
        int rand = random.nextInt(3);
        switch (rand) {
            case 0:
                cardType = CardType.Infantry;
                break;
            case 1:
                cardType = CardType.Cavalry;
                break;
            case 2:
                cardType = CardType.Artillery;
                break;
            default:
                break;
        }
    }

    public Card(PlayerModel player, String CardTypeName) {
        this.player = player;
        switch (CardTypeName) {
            case "Infantry":
                cardType = CardType.Infantry;
                break;
            case "Cavalry":
                cardType = CardType.Cavalry;
                break;
            case "Artillery":
                cardType = CardType.Artillery;
                break;
            default:
                break;
        }
    }

    @Override
    public String toString() {
        return cardType.name();
    }
}
