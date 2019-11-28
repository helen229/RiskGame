package GamePlayModel;

import MapEditorModel.ContinentModel;
import MapEditorModel.CountryModel;
import MapEditorModel.MapModel;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;


/**
 * This class  defines the characteristics of a class
 */

public class PlayerModel {
    String PlayerName;
    int totalNumArmy;
    int numArmyRemainPlace;
    int totalNumReinforceArmy;
    int NumReinforceArmyRemainPlace;
    ArrayList<CountryModel> playerCountries;
    ArrayList<ContinentModel> playerContinents;
    ArrayList<Card> cardList;
    
    public PlayerModel(String playerName) {
        PlayerName = playerName;
        playerCountries = new ArrayList<>();
        playerContinents = new ArrayList<>();
        cardList = new ArrayList<>();
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
     * @param
     */

    public void addPlayerCountries(CountryModel country){
        playerCountries.add(country);
    } 
    
     public ArrayList<ContinentModel> getPlayerContinents() {
        return playerContinents;
    }
     
    public void checkPlayerContinents(ArrayList<ContinentModel> continentList) {
        this.playerContinents.clear();
        HashMap<String, Integer> counter = new  HashMap<String, Integer>();
        for (CountryModel country:playerCountries) {
            String continent = country.getContinentName();
            if (counter.containsKey(continent)){
                counter.replace(continent,counter.get(continent)+1);
            }
            else {
                counter.put(continent,1);
            }
        }
        for (ContinentModel continent:continentList ){
            if (counter.containsKey(continent.getContinentName())){
                if (counter.get(continent.getContinentName())==continent.getCountriesSize() &&
                !this.playerContinents.contains(continent)){
                    //The continent is controlled by this player
                    this.playerContinents.add(continent);
                }
            }
        }
    }
     /**
     *  This method returns the total number of armies.
     *
     */

    public int getTotalNumArmy() {
        return totalNumArmy;
    }  
     /**
     * This methos assigns armies to players based on their size.
     *
     */

    public void setTotalNumArmy(int playerSize) {
        switch(playerSize){
            case 2:
                this.totalNumArmy = 40;
                break;
            case 3:
                this.totalNumArmy =35;
                break;
            case 4:
                this.totalNumArmy =30;
                break;
            case 5:
                this.totalNumArmy =25;
                break;
            case 6://20 armies
                this.totalNumArmy =20;
                break;
        }

    } 
     /**
     * This method return the remaining number of armies
     *
     */

    public int getNumArmyRemainPlace() {
        return numArmyRemainPlace;
    } 
     /**
     * This method sets the number of armies remaining
     *
     */

    public void setNumArmyRemainPlace(int numArmyRemainPlace) {
        this.numArmyRemainPlace = numArmyRemainPlace;
    }
 /**
     * This method returns the total number of reinforce army
     *
     */
    public int getTotalNumReinforceArmy() {
        return totalNumReinforceArmy;
    }
     /**
     * This method sets the total number of reinforce army
     *
     */

    public void setTotalNumReinforceArmy(int totalNumReinforceArmy) {
        this.totalNumReinforceArmy = totalNumReinforceArmy;
    } 
     /**
     * This method  get the total number of armies that remain in their place
     *
     */

    public int getNumReinforceArmyRemainPlace() {
        return NumReinforceArmyRemainPlace;
    }
     /**
     * This method sets the total number of armies that remian in the place
     *
     */

    public void setNumReinforceArmyRemainPlace(int numReinforceArmyRemainPlace) {
        NumReinforceArmyRemainPlace = numReinforceArmyRemainPlace;
    }
    
     public ArrayList<Card> getCardList() {
        return cardList;
    }

    public void addCard(Card card) {
        this.cardList.add(card);
    }

    public void removeCard(Card card) {
        this.cardList.remove(card);
    }

    /**
     * get the percentage of the map controlled by player
     */
    public String percentageOfmap(int totalCountriesNum) {
        String percent = "";
        NumberFormat numberFormat = NumberFormat.getInstance();
        numberFormat.setMaximumFractionDigits(2);
        int ownerCountryNum = this.playerCountries.size();
        percent = numberFormat.format((float) ownerCountryNum / (float) totalCountriesNum * 100);
        return percent;
    }

    public void reduceArmyNum(int num) {
        this.totalNumArmy= this.totalNumArmy - num;
    }
    public void addArmyNum(int num) {
        this.totalNumArmy= this.totalNumArmy + num;
    }
}
