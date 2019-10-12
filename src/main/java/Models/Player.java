/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import Models.GameBoard;

/**
 *
 * @author Ehsan
 */
public class Player {
    /**
     * Object of the GameBoard {@link models.GameBoard}
     */
    GameBoard gameBoard;

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


    /**
     * Default constructor of the Player
     */
    public Player() {
    }

    /**
     * Gets the GameBoard
     *
     * @return {@link models.GameBoard}
     */
    public GameBoard getGameBoard() {
        return gameBoard;
    }

    /**
     * Sets the GameBoard
     *
     * @param gameBoard {@link models.GameBoard}
     */
    public void setGameBoard(GameBoard gameBoard) {
        this.gameBoard = gameBoard;
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

 

    /**
     * Gets the name of countries of the player
     *
     * @return {@link #nameOfCountries}
     */
    public ArrayList<String> getNameOfCountries() {
        return nameOfCountries;
    }

    /**
     * Sets the name of countries of the player
     *
     * @param nameOfCountries {@link #nameOfCountries}
     */
    public void setNameOfCountries(ArrayList<String> nameOfCountries) {
        this.nameOfCountries = nameOfCountries;
    }

    /**
     * Gets the name of continents
     *
     * @return {@link #nameOfContinents}
     */
    public ArrayList<String> getNameOfContinents() {
        return nameOfContinents;
    }

    /**
     * Sets the name of continents
     *
     * @param nameOfContinents {@link #nameOfContinents}
     */
    public void setNameOfContinents(ArrayList<String> nameOfContinents) {
        this.nameOfContinents = nameOfContinents;
    }


    /**
     * Gets the neighbors of the country
     *
     * @return {@link #neighboringCountries}
     */
    public HashMap<String, ArrayList> getNeighboringCountries() {
        return neighboringCountries;
    }

    /**
     * Sets the neighbors of the country
     *
     * @param neighboringCountries {@link #neighboringCountries}
     */
    public void setNeighboringCountries(HashMap<String, ArrayList> neighboringCountries) {
        this.neighboringCountries = neighboringCountries;
    }


    /**
     * Sets the army information of the player
     *
     * @param countryArmyInfo {@link #countryArmyInfo}
     */
    public void setCountryArmyInfo(HashMap<String, Integer> countryArmyInfo) {
        this.countryArmyInfo = countryArmyInfo;

        modifyNameOfContinents(countryArmyInfo);
        modifyNameOfCountries(countryArmyInfo);
        modifyNumberOfContinents(countryArmyInfo);
        modifyNumberOfCountries(countryArmyInfo);
        modifyNumberOfArmies(countryArmyInfo);
    }

    ///**
    // * Reinforcement Phase method
    // *
    // * @param gameBoard Object of the GameBoard {@link models.GameBoard}
    // */
    //public void reinforcement(GameBoard gameBoard) {
    //    new StrategyContext(this.strategy).initReinforce(gameBoard, this);

    //    ReinforcementPhaseController rpc = new ReinforcementPhaseController();

    //    PhaseUpdateService.setCurrentPhase(gameBoard, Constants.REINFORCEMENT_PHASE);
    //    PhaseUpdateService.setPlayerName(gameBoard, getPlayerName());
    //    PhaseUpdateService.clearActions(gameBoard);
    //    gameBoard.stateChanged();
    //    // CardExchangeViewGUI cardExchangeView = new CardExchangeViewGUI();
    //   // this.addObserver(cardExchangeView);
    //    //   cardExchangeView.showView();
    //   rpc.start(gameBoard, this);
    //}


    ///**
    //* Fortification Phase method
    // *
    // * @param gameBoard Object of the GameBoard {@link models.GameBoard}
    // */
    //public void fortification(GameBoard gameBoard) {
    //    new StrategyContext(this.strategy).initFortify(gameBoard, this);

    //    FortificationPhaseController fpc = new FortificationPhaseController();

    //    PhaseUpdateService.setCurrentPhase(gameBoard, Constants.FORTIFICATION_PHASE);
    //    PhaseUpdateService.setPlayerName(gameBoard, getPlayerName());
    //    PhaseUpdateService.clearActions(gameBoard);
    //    gameBoard.stateChanged();
    //    fpc.startFortification(gameBoard, this);
    //}

    /**
     * Modify the name of countries of the player
     *
     * @param countryArmyInfo {@link #countryArmyInfo}
     */
    void modifyNameOfCountries(HashMap<String, Integer> countryArmyInfo) {
        ArrayList<String> nameOfCountries = new ArrayList<String>();;
        for (String countryName : countryArmyInfo.keySet()) {
            nameOfCountries.add(countryName);
        }
        setNameOfCountries(nameOfCountries);
    }

    /**
     * Modify the number of countries of the player
     *
     * @param countryArmyInfo {@link #countryArmyInfo}
     */
    void modifyNumberOfCountries(HashMap<String, Integer> countryArmyInfo) {
        int numberOfCountries = 0;
        for (String countryName : countryArmyInfo.keySet()) {
            int army = countryArmyInfo.get(countryName);
            if (army > 0) {
                numberOfCountries = numberOfCountries + 1;
            }
        }
        setNumberOfCountries(numberOfCountries);
    }

    /**
     * Modify the name of the continents of the player
     *
     * @param countryArmyInfo {@link #countryArmyInfo}
     */
    void modifyNameOfContinents(HashMap<String, Integer> countryArmyInfo) {
        ArrayList<String> nameOfContinents = new ArrayList<>();
        List<String> countryList = new ArrayList<String>();

        for (String countryName : countryArmyInfo.keySet()) {
            countryList.add(countryName);
        }

        HashMap<String, ArrayList<String>> continentCountries = gameBoard.getMap().getContinentCountries();

        for (String continentName : continentCountries.keySet()) {
            List temp = continentCountries.get(continentName);
            if (countryList.containsAll(temp)) {
                nameOfContinents.add(continentName);
            }
        }

        setNameOfContinents(nameOfContinents);
    }

    /**
     * Modify number of continents of the player
     *
     * @param countryArmyInfo {@link #countryArmyInfo}
     */
    void modifyNumberOfContinents(HashMap<String, Integer> countryArmyInfo) {
        int numberOfContinents;
        ArrayList<String> nameOfContinents = new ArrayList<>();
        List<String> countryList = new ArrayList<String>();

        for (String countryName : countryArmyInfo.keySet()) {
            countryList.add(countryName);
        }

        HashMap<String, ArrayList<String>> continentCountries = gameBoard.getMap().getContinentCountries();

        for (String continentName : continentCountries.keySet()) {
            List temp = continentCountries.get(continentName);
            if (countryList.containsAll(temp)) {
                nameOfContinents.add(continentName);
            }
        }

        numberOfContinents = nameOfContinents.size();
        setNumberOfContinents(numberOfContinents);

    }

    /**
     * Modify the number of armies of the player
     *
     * @param countryArmyInfo {@link #countryArmyInfo}
     */
    void modifyNumberOfArmies(HashMap<String, Integer> countryArmyInfo) {
        int numberOfArmies = 0;
        for (String countryName : countryArmyInfo.keySet()) {
            numberOfArmies = numberOfArmies + countryArmyInfo.get(countryName);
        }

        setNumberOfArmies(numberOfArmies);
    }
    
}
