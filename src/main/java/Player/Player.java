/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Player;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Observable;

/**
 *
 * @author User
 */

    public class Player extends Observable implements Serializable {

    /**
     * Object of the GameBoard {@link models.GameBoard}
     */
    

    /**
     * name of the current player
     */
    private String playerName;

    /**
     * number of armies
     */
    private int numberOfArmies;
    /**
     * reinforcement army
     */
    private int reinforcementArmy;

    /**
     * number of countries of the player
     */
    int numberOfCountries;

    /**
     * number of continents of the player
     */
    int numberOfContinents;
    
    /**
     * name of countries
     */
    public ArrayList<String> nameOfCountries = new ArrayList<>();
    /**
     * name of continents
     */
    public ArrayList<String> nameOfContinents = new ArrayList<>();

     /**
     * name of country as key and an ArrayList of neighboring countries
     */
    public HashMap<String, ArrayList> neighboringCountries = new HashMap<>();//country name as key and neighboursList as value

    /**
     * country name as key and its number of armies
     */
    public HashMap<String, Integer> countryArmyInfo = new HashMap<>();// country name and its num of armies

    public Player() {
    }


    /**
     * Gets the current player name
     *
     * @return {@link #playerName}
     */
    public String getPlayerName() {
        return playerName;
    }

    /**
     * Sets the name of the player
     *
     * @param playerName {@link #playerName}
     */
    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    /**
     * Gets the number of armies
     *
     * @return {@link #numberOfArmies}
     */
    public int getNumberOfArmies() {
        return numberOfArmies;
    }

    /**
     * Sets the number of armies
     *
     * @param numberOfArmies {@link #numberOfArmies}
     */
    public void setNumberOfArmies(int numberOfArmies) {
        this.numberOfArmies = numberOfArmies;
    }

    /**
     * Gets the Reinforcement armies of the player owned countries
     *
     * @return {@link #reinforcementArmy}
     */
    public int getReinforcementArmy() {
        return reinforcementArmy;
    }

    /**
     * Sets the Reinforcement armies of the player owned countries
     *
     * @param reinforcementArmy {@link #reinforcementArmy}
     */
    public void setReinforcementArmy(int reinforcementArmy) {
        this.reinforcementArmy = reinforcementArmy;
    }

    /**
     * Gets the number of countries
     *
     * @return {@link #numberOfCountries}
     */
    public int getNumberOfCountries() {
        return numberOfCountries;
    }

    /**
     * Sets the number of countries
     *
     * @param numberOfCountries {@link #numberOfCountries}
     */
    public void setNumberOfCountries(int numberOfCountries) {
        this.numberOfCountries = numberOfCountries;
    }

    /**
     * Gets the number of continents
     *
     * @return {@link #numberOfContinents}
     */
    public int getNumberOfContinents() {
        return numberOfContinents;
    }

    /**
     * Sets the number of continents
     *
     * @param numberOfContinents {@link #numberOfContinents}
     */
    public void setNumberOfContinents(int numberOfContinents) {
        this.numberOfContinents = numberOfContinents;
    }


    
}
