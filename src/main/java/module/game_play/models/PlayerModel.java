package module.game_play.models;

import module.map_editor.models.CountryModel;

import java.util.ArrayList;

/**
 * This class  defines the characteristics of a class
 */

public class PlayerModel {
    String PlayerName;
    ArrayList<CountryModel> playerCountries;

    /**
     * This method  retrieves the name of the player
     * @return playerName
     */

    public String getPlayerName() {
        return PlayerName;
    }

    /**
     * This method sets Player's name
     * @param playerName
     */
    public void setPlayerName(String playerName) {
        PlayerName = playerName;
    }

    /**
     * This method retrieves the list of countries associated to the player
     * @return
     */
    public ArrayList<CountryModel> getPlayerCountries() {
        return playerCountries;
    }

    /**
     * This method sets the list of countries that belong to the player.
     * @param playerCountries
     */
    public void setPlayerCountries(ArrayList<CountryModel> playerCountries) {
        this.playerCountries = playerCountries;
    }

    public void addPlayerCountries(CountryModel country){
        playerCountries.add(country);
    }
}
