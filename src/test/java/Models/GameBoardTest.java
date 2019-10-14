/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import Utilities.Country;
import java.util.ArrayList;
import java.util.HashMap;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author User
 */
public class GameBoardTest {
    
    public GameBoardTest() {
    }

    @org.junit.jupiter.api.BeforeAll
    public static void setUpClass() throws Exception {
    }

    @org.junit.jupiter.api.AfterAll
    public static void tearDownClass() throws Exception {
    }

    @org.junit.jupiter.api.BeforeEach
    public void setUp() throws Exception {
    }

    @org.junit.jupiter.api.AfterEach
    public void tearDown() throws Exception {
    }
    

    /**
     * Test of setMap method, of class GameBoard.
     */
    @org.junit.jupiter.api.Test
    public void testSetMap() {
        System.out.println("setMap");
        GameMap map = null;
        GameBoard instance = new GameBoard();
        instance.setMap(map);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPlayerStatus method, of class GameBoard.
     */
    @org.junit.jupiter.api.Test
    public void testGetPlayerStatus() {
        System.out.println("getPlayerStatus");
        GameBoard instance = new GameBoard();
        HashMap<Player, String> expResult = null;
        HashMap<Player, String> result = instance.getPlayerStatus();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setPlayerStatus method, of class GameBoard.
     */
    @org.junit.jupiter.api.Test
    public void testSetPlayerStatus() {
        System.out.println("setPlayerStatus");
        HashMap<Player, String> playerStatus = null;
        GameBoard instance = new GameBoard();
        instance.setPlayerStatus(playerStatus);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setNumberOfPlayers method, of class GameBoard.
     */
    @org.junit.jupiter.api.Test
    public void testSetNumberOfPlayers_int() {
        System.out.println("setNumberOfPlayers");
        int numberOfPlayers = 0;
        GameBoard instance = new GameBoard();
        instance.setNumberOfPlayers(numberOfPlayers);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setPlayerNamePlayerID method, of class GameBoard.
     */
    @org.junit.jupiter.api.Test
    public void testSetPlayerNamePlayerID() {
        System.out.println("setPlayerNamePlayerID");
        HashMap<Integer, String> playerIdPlayerName = null;
        GameBoard instance = new GameBoard();
        instance.setPlayerNamePlayerID(playerIdPlayerName);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setPlayerCountriesInitial method, of class GameBoard.
     */
    @org.junit.jupiter.api.Test
    public void testSetPlayerCountriesInitial() {
        System.out.println("setPlayerCountriesInitial");
        HashMap<String, HashMap<String, Integer>> playerCountries = null;
        GameBoard instance = new GameBoard();
        instance.setPlayerCountriesInitial(playerCountries);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setPlayerCountries method, of class GameBoard.
     */
    @org.junit.jupiter.api.Test
    public void testSetPlayerCountries() {
        System.out.println("setPlayerCountries");
        HashMap<String, HashMap<String, Integer>> playerCountries = null;
        GameBoard instance = new GameBoard();
        instance.setPlayerCountries(playerCountries);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setNumberOfPlayers method, of class GameBoard.
     */
    @org.junit.jupiter.api.Test
    public void testSetNumberOfPlayers_HashMap() {
        System.out.println("setNumberOfPlayers");
        HashMap<String, HashMap<String, Integer>> playerCountries = null;
        GameBoard instance = new GameBoard();
        instance.setNumberOfPlayers(playerCountries);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setPlayerContinents method, of class GameBoard.
     */
    @org.junit.jupiter.api.Test
    public void testSetPlayerContinents() {
        System.out.println("setPlayerContinents");
        HashMap<String, ArrayList> playerContinents = null;
        GameBoard instance = new GameBoard();
        instance.setPlayerContinents(playerContinents);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setPlayerMapPercentage method, of class GameBoard.
     */
    @org.junit.jupiter.api.Test
    public void testSetPlayerMapPercentage() {
        System.out.println("setPlayerMapPercentage");
        HashMap<String, Float> playerMapPercentage = null;
        GameBoard instance = new GameBoard();
        instance.setPlayerMapPercentage(playerMapPercentage);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setPlayerArmies method, of class GameBoard.
     */
    @org.junit.jupiter.api.Test
    public void testSetPlayerArmies() {
        System.out.println("setPlayerArmies");
        HashMap<String, Integer> playerArmies = null;
        GameBoard instance = new GameBoard();
        instance.setPlayerArmies(playerArmies);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setPlayerContinentsFromPlayerCountries method, of class GameBoard.
     */
    @org.junit.jupiter.api.Test
    public void testSetPlayerContinentsFromPlayerCountries() {
        System.out.println("setPlayerContinentsFromPlayerCountries");
        HashMap<String, HashMap<String, Integer>> playerCountries = null;
        GameBoard instance = new GameBoard();
        instance.setPlayerContinentsFromPlayerCountries(playerCountries);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setPlayerMapPercentageFromPlayerCountries method, of class GameBoard.
     */
    @org.junit.jupiter.api.Test
    public void testSetPlayerMapPercentageFromPlayerCountries() {
        System.out.println("setPlayerMapPercentageFromPlayerCountries");
        HashMap<String, HashMap<String, Integer>> playerCountries = null;
        GameBoard instance = new GameBoard();
        instance.setPlayerMapPercentageFromPlayerCountries(playerCountries);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setPlayerArmiesFromPlayerCountries method, of class GameBoard.
     */
    @org.junit.jupiter.api.Test
    public void testSetPlayerArmiesFromPlayerCountries() {
        System.out.println("setPlayerArmiesFromPlayerCountries");
        HashMap<String, HashMap<String, Integer>> playerCountries = null;
        GameBoard instance = new GameBoard();
        instance.setPlayerArmiesFromPlayerCountries(playerCountries);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setPlayerStatusFromPlayerCountries method, of class GameBoard.
     */
    @org.junit.jupiter.api.Test
    public void testSetPlayerStatusFromPlayerCountries() {
        System.out.println("setPlayerStatusFromPlayerCountries");
        HashMap<String, HashMap<String, Integer>> playerCountries = null;
        GameBoard instance = new GameBoard();
        instance.setPlayerStatusFromPlayerCountries(playerCountries);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of stateChanged method, of class GameBoard.
     */
    @org.junit.jupiter.api.Test
    public void testStateChanged() {
        System.out.println("stateChanged");
        GameBoard instance = new GameBoard();
        instance.stateChanged();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getMap method, of class GameBoard.
     */
    @org.junit.jupiter.api.Test
    public void testGetMap() {
        System.out.println("getMap");
        GameBoard instance = new GameBoard();
        GameMap expResult = null;
        GameMap result = instance.getMap();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getNumberOfPlayers method, of class GameBoard.
     */
    @org.junit.jupiter.api.Test
    public void testGetNumberOfPlayers() {
        System.out.println("getNumberOfPlayers");
        GameBoard instance = new GameBoard();
        int expResult = 0;
        int result = instance.getNumberOfPlayers();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPlayerNamePlayerID method, of class GameBoard.
     */
    @org.junit.jupiter.api.Test
    public void testGetPlayerNamePlayerID() {
        System.out.println("getPlayerNamePlayerID");
        GameBoard instance = new GameBoard();
        HashMap<Integer, String> expResult = null;
        HashMap<Integer, String> result = instance.getPlayerNamePlayerID();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPlayerCountries method, of class GameBoard.
     */
    @org.junit.jupiter.api.Test
    public void testGetPlayerCountries() {
        System.out.println("getPlayerCountries");
        GameBoard instance = new GameBoard();
        HashMap<String, HashMap<String, Integer>> expResult = null;
        HashMap<String, HashMap<String, Integer>> result = instance.getPlayerCountries();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPlayerContinents method, of class GameBoard.
     */
    @org.junit.jupiter.api.Test
    public void testGetPlayerContinents() {
        System.out.println("getPlayerContinents");
        GameBoard instance = new GameBoard();
        HashMap<String, ArrayList> expResult = null;
        HashMap<String, ArrayList> result = instance.getPlayerContinents();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPlayerMapPercentage method, of class GameBoard.
     */
    @org.junit.jupiter.api.Test
    public void testGetPlayerMapPercentage() {
        System.out.println("getPlayerMapPercentage");
        GameBoard instance = new GameBoard();
        HashMap<String, Float> expResult = null;
        HashMap<String, Float> result = instance.getPlayerMapPercentage();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPlayerArmies method, of class GameBoard.
     */
    @org.junit.jupiter.api.Test
    public void testGetPlayerArmies() {
        System.out.println("getPlayerArmies");
        GameBoard instance = new GameBoard();
        HashMap<String, Integer> expResult = null;
        HashMap<String, Integer> result = instance.getPlayerArmies();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPlayerIdPlayerName method, of class GameBoard.
     */
    @org.junit.jupiter.api.Test
    public void testGetPlayerIdPlayerName() {
        System.out.println("getPlayerIdPlayerName");
        GameBoard instance = new GameBoard();
        HashMap<Integer, String> expResult = null;
        HashMap<Integer, String> result = instance.getPlayerIdPlayerName();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setPlayerIdPlayerName method, of class GameBoard.
     */
    @org.junit.jupiter.api.Test
    public void testSetPlayerIdPlayerName() {
        System.out.println("setPlayerIdPlayerName");
        HashMap<Integer, String> playerIdPlayerName = null;
        GameBoard instance = new GameBoard();
        instance.setPlayerIdPlayerName(playerIdPlayerName);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPlayerNamePlayerObject method, of class GameBoard.
     */
    @org.junit.jupiter.api.Test
    public void testGetPlayerNamePlayerObject() {
        System.out.println("getPlayerNamePlayerObject");
        GameBoard instance = new GameBoard();
        HashMap<String, Player> expResult = null;
        HashMap<String, Player> result = instance.getPlayerNamePlayerObject();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setPlayerNamePlayerObject method, of class GameBoard.
     */
    @org.junit.jupiter.api.Test
    public void testSetPlayerNamePlayerObject() {
        System.out.println("setPlayerNamePlayerObject");
        HashMap<String, Player> playerNamePlayerObject = null;
        GameBoard instance = new GameBoard();
        instance.setPlayerNamePlayerObject(playerNamePlayerObject);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPlayerObjectFromPlayerName method, of class GameBoard.
     */
    @org.junit.jupiter.api.Test
    public void testGetPlayerObjectFromPlayerName() {
        System.out.println("getPlayerObjectFromPlayerName");
        String playerName = "";
        GameBoard instance = new GameBoard();
        Player expResult = null;
        Player result = instance.getPlayerObjectFromPlayerName(playerName);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getCountryDetails method, of class GameBoard.
     */
    @org.junit.jupiter.api.Test
    public void testGetCountryDetails() {
        System.out.println("getCountryDetails");
        String countryName = "";
        GameBoard instance = new GameBoard();
        Country expResult = null;
        Country result = instance.getCountryDetails(countryName);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isGameOver method, of class GameBoard.
     */
    @org.junit.jupiter.api.Test
    public void testIsGameOver() {
        System.out.println("isGameOver");
        GameBoard instance = new GameBoard();
        boolean expResult = false;
        boolean result = instance.isGameOver();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getCountryArmy method, of class GameBoard.
     */
    @org.junit.jupiter.api.Test
    public void testGetCountryArmy() {
        System.out.println("getCountryArmy");
        String countryName = "";
        GameBoard instance = new GameBoard();
        int expResult = 0;
        int result = instance.getCountryArmy(countryName);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of printGameBoard method, of class GameBoard.
     */
    @org.junit.jupiter.api.Test
    public void testPrintGameBoard() {
        System.out.println("printGameBoard");
        GameBoard instance = new GameBoard();
        instance.printGameBoard();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getWinner method, of class GameBoard.
     */
    @org.junit.jupiter.api.Test
    public void testGetWinner() {
        System.out.println("getWinner");
        GameBoard instance = new GameBoard();
        String expResult = "";
        String result = instance.getWinner();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isActivePlayer method, of class GameBoard.
     */
    @org.junit.jupiter.api.Test
    public void testIsActivePlayer() {
        System.out.println("isActivePlayer");
        Player p = null;
        GameBoard instance = new GameBoard();
        boolean expResult = false;
        boolean result = instance.isActivePlayer(p);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
