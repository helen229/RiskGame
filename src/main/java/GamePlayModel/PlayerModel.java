package GamePlayModel;

import MapEditorModel.CountryModel;

import java.util.ArrayList;

/**
 * This class  defines the characteristics of a class
 */

public class PlayerModel {
    String PlayerName;
//    int id;
    int totalNumArmy;
    int numArmyRemainPlace;
    ArrayList<CountryModel> playerCountries;

    public PlayerModel(String playerName) {
        PlayerName = playerName;
        playerCountries = new ArrayList<>();
    }

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

    public int getTotalNumArmy() {
        return totalNumArmy=playerCountries.size()/3;
    }

    public void setTotalNumArmy(int totalNumArmy) {
        this.totalNumArmy = totalNumArmy;
    }

    public int getNumArmyRemainPlace() {
        return numArmyRemainPlace;
    }

    public void setNumArmyRemainPlace(int numArmyRemainPlace) {
        this.numArmyRemainPlace = numArmyRemainPlace;
    }
}
