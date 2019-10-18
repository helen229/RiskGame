package GamePlayModel;

import java.util.ArrayList;

/**
 * This class create object of Player. Each player has:
 * @param playerName name as string
 * @param playerValue ID as int
 * @param playerCountriesList list of the names of the owned countries as arrayList string (should be changed to IDs int for the next build)
 * @param armyNum number of armies unassigned to the owned countries as int
 * For the next build cards should be added.
 * @author Ehsan
 */

public class PlayerModel {
 
    private String playerName;
    private int playerValue;
    private ArrayList<String> playerCountriesList;
    private int armyNum;
    
    /**Constructor for Player class.*/
    public PlayerModel(String playerName, int playerValue, int armyNum) {
        this.setPlayerName(playerName);
        this.setPlayerValue(playerValue);
        this.armyNum= armyNum;
        this.playerCountriesList = new ArrayList<String>();
    }
    
    public int getArmyNum() {
        return armyNum;
    }

    public void setArmyNum(int armyNum) {
        this.armyNum = armyNum;
    }
    
    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public int getPlayerValue() {
        return playerValue;
    }

    public void setPlayerValue(int playerValue) {
        this.playerValue = playerValue;
    }

    public ArrayList<String> getPlayerCountriesList() {
        return playerCountriesList;
    }

    public void addCountryToListPlayer(String countryName) {
        this.playerCountriesList.add(countryName);
    }

    public void removeCountryFromListPlayer(String countryName) {
        this.playerCountriesList.remove(countryName);
    }

    public void setPlayerCountriesList(ArrayList<String> playerCountriesList) {
        this.playerCountriesList = playerCountriesList;
    } 
    
}
