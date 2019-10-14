/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MapEditorController;

import java.util.ArrayList;
import java.util.HashMap;
import Models.GameBoard;
import Models.Player;


/**
 *
 * @author User
 */
public class ReinforcementPhaseController  {

    /**
     * GameBoard on which the game is being played
     */
    public GameBoard gameBoard;
    /**
     * Current player whose Reinforcement Phase is running
     */
    public Player player;

    /**
     * Reinforcement number
     */
    int reinforcement = 0;

    /**
     * Starts the Reinforcement Phase
     *
     * @param gameBoard Object of the GameBoard {@link models.GameBoard}
     * @param player Object of the current player {@link models.Player}
     */
    public void start(GameBoard gameBoard, Player player) {
        this.gameBoard = gameBoard;
        this.player = player;

        
      
        performMove();

       
    }

    /**
     * Gives the number of countries owned by the player
     *
     * @return number of countries
     */
    public int getNumberOfCountries() {
        return player.getNumberOfCountries();
    }

    /**
     * Gives the number of continents
     *
     * @return number of continents
     */
    public int getNumberOfContinents() {
        return player.getNumberOfContinents();
    }

    /**
     * Calculates the reinforcements from the number of continents
     *
     * @return the reinforcements from the continent
     */
    public int calculateReinforcementFromContinents() {
        int continentReinforcement = 0;
        int continentNumber = player.getNumberOfContinents();
        if (continentNumber > 0) {
            ArrayList<String> nameOfContinents = player.getNameOfContinents();
            for (int i = 0; i < nameOfContinents.size(); i++) {
                int continentValue = gameBoard.getMap().getContinentValue(nameOfContinents.get(i));
                continentReinforcement = continentReinforcement + continentValue;
            }
        } else {
            continentReinforcement = 0;
        }

        return continentReinforcement;

    }

    /**
     * Calculates the reinforcement from the number of country
     *
     * @return reinforcements from the country
     */
    public int calculateReinforcementFromCountry() {

        int countryReinforcement;
        int countryNumber = player.getNumberOfCountries();

        if (countryNumber > 9) {
            countryReinforcement = countryNumber / 3;
        } else {
            countryReinforcement = 3;
        }
        return countryReinforcement;
    }

    /**
     * Checks whether the choice entered is valid or not
     *
     * @param choice Choice of the player
     * @return true if the choice is valid or false if the choice is not valid
     */
    public boolean isValidChoice(int choice[]) {

        try {
            int c1 = choice[0];
            int c2 = choice[1];
            int c3 = choice[2];

            int c = c1 + c2 + c3;

            if (c == 3) {
                boolean b1 = (c1 == 3 && c2 == 0 && c3 == 0);
                boolean b2 = (c1 == 0 && c2 == 3 && c3 == 0);
                boolean b3 = (c1 == 0 && c2 == 0 && c3 == 3);

                if (b1 || b2 || b3) {
                    return true;
                } else if (c1 == 1 && c2 == 1 && c3 == 1) {
                    return true;
                } else {
                    return false;
                }
            } else {
                return false;
            }

        } catch (ArrayIndexOutOfBoundsException e) {
            return false;
        }

    }


    /**
     * Displays the menu for the moves to be performed by the player in
     * Reinforcement Phase.
     * <br>Along with it takes the player moves as input and updates the
     * Reinforcement Services accordingly
     */
    private void performMove() {
        while (reinforcement > 0) {
            System.out.println("Please select Country name:");
            System.out.println(player.getCountryArmyInfo());
            String countryName;
            while (true) {
                countryName = reinforcementPhaseView.getCountryName();
                if (isValidCountryName(countryName)) {
                    break;
                } else {
                    System.out.println("Invalid Country Name");
                }
            }
            int moveNumber;
            while (true) {
                moveNumber = reinforcementPhaseView.getMoveNumber();
                if (isValidMoveNumber(moveNumber)) {
                    break;
                }
            }

            doMove(countryName, moveNumber);
//            ReinforcementPhaseUpdateService.updateCountryArmyInfo(gameBoard, player, countryName, moveNumber);
//            ReinforcementPhaseUpdateService.updateActions(gameBoard, player, "Added " + reinforcement + " on " + countryName);
//            reinforcement = reinforcement - moveNumber;
//            System.out.println("Reinforcement yet to be placed : " + reinforcement);
//            ReinforcementPhaseUpdateService.updateActions(gameBoard, player, "Reinforcement left : " + reinforcement);
//            ReinforcementPhaseUpdateService.updateReinforcementArmy(player, reinforcement);
        }
    }

    /**
     * Displays the menu for the moves to be performed by the player in
     * Reinforcement Phase.
     * <br>Along with it takes the player moves as input and updates the
     * Reinforcement Services accordingly
     *
     * @param countryName name of the country
     * @param moveNumber number of army to move
     */
    public void doMove(String countryName, int moveNumber) {
        ReinforcementPhaseUpdateService.updateCountryArmyInfo(gameBoard, player, countryName, moveNumber);
        ReinforcementPhaseUpdateService.updateActions(gameBoard, player, "Added " + reinforcement + " on " + countryName);
        reinforcement = reinforcement - moveNumber;
        System.out.println("Reinforcement yet to be placed : " + reinforcement);
        ReinforcementPhaseUpdateService.updateActions(gameBoard, player, "Reinforcement left : " + reinforcement);
        ReinforcementPhaseUpdateService.updateReinforcementArmy(player, reinforcement);
    }

    /**
     * Checks whether the name of the country is valid or not
     *
     * @param countryName Name of the country
     * @return true if the country is valid and false if the country is invalid
     */
    @Override
    public boolean isValidCountryName(String countryName) {
        return player.getNameOfCountries().contains(countryName.trim().toUpperCase());
    }

    /**
     * Updates the actions performed in the Reinforcement Phase
     *
     * @param action Action performed by the player
     */
    private void updateActions(String action) {
        ReinforcementPhaseUpdateService.updateActions(gameBoard, player, action);
    }

    /**
     * Checks whether army to move is less or equal to reinforcement value
     *
     * @param moveNumber army to move
     * @return true if moveNumber is less than equal to reinforcement value
     * otherwise false
     */
    @Override
    public boolean isValidMoveNumber(int moveNumber) {
        return moveNumber <= reinforcement;
    }

    /**
     * Gets the current player
     *
     * @return current player
     */
    public Player getPlayer() {
        return player;
    }

    /**
     * Sets the current player
     *
     * @param player Object of current player {@link models.Player}
     */
    public void setPlayer(Player player) {
        this.player = player;
    }

    /**
     * Gets the GameBoard of the game
     *
     * @return Object of the GameBoard {@link models.GameBoard}
     */
    public GameBoard getGameBoard() {
        return gameBoard;
    }

    /**
     * Sets the GameBoard of the game
     *
     * @param gameBoard Object of the GameBoard {@link models.GameBoard}
     */
    public void setGameBoard(GameBoard gameBoard) {
        this.gameBoard = gameBoard;
    }

    /**
     * Gets the Reinforcements
     *
     * @return reinforcement The Reinforcements
     */
    public int getReinforcement() {
        return reinforcement;
    }

    /**
     * Sets the Reinforcements
     *
     * @param reinforcement The reinforcement
     */
    public void setReinforcement(int reinforcement) {
        this.reinforcement = reinforcement;
    }

}
