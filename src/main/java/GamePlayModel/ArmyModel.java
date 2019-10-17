package GamePlayModel;

import MapEditorModel.CountryModel;
 /**
     * This class defines the characteristics of an Army in the Game
     */ 

public class ArmyModel {
    private PlayerModel player;

    private CountryModel country;

    /**
     * Creates a new army.
     */
    public ArmyModel(PlayerModel player) {

        this.player = player;
    }

    /**
     * Gets the player.
     */
    public PlayerModel getPlayer() {
        return player;
    }

    /**
     * Sets the player.
     */
    public void setPlayer(PlayerModel player) {
        this.player = player;
    }

    /**
     * Gets the country.
     */
    public CountryModel getCountry() {
        return country;
    }

    /**
     * Sets the country.
     */
    public void setCountry(CountryModel country) {
        this.country = country;
    }

    @Override
    public String toString() {
        String info = "Army : player - " + player + ", country - " + country + "\n";
        return info;
    }
}
