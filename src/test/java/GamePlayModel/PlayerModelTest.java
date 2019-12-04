/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GamePlayModel;

import MapEditorModel.ContinentModel;
import MapEditorModel.CountryModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 *
 * @author Ehsan
 */
public class PlayerModelTest {
    private ContinentModel Continent1;
    private CountryModel Country1;
    public PlayerModel Player1;
    public PlayerModel Player2;
    public PlayerModel Player3;
    
    /**
     * Each time invoke a method, set up this context
     */
    @BeforeEach
    public void setUp() {

        Player1 = new PlayerModel("Player1");
        Player2 = new PlayerModel("Player2");
        Player3 = new PlayerModel("Player3");
        Continent1= new ContinentModel ("Continent1",1);
        Country1= new CountryModel (0,"Country1","Continent1");
        ArrayList<String> playerList = new ArrayList<String>();
        playerList.add(Player1.getPlayerName());
        playerList.add(Player2.getPlayerName());
        playerList.add(Player3.getPlayerName());
    }

    @Test
    public void testSetPlayerName() {
        Player1.setPlayerName("NewName");
        assertTrue(Player1.getPlayerName().equals("NewName"));
    }    
    
    @Test
    public void testGetPlayerCountries() {
        assertFalse(Player1.getPlayerCountries().isEmpty());
    }
    
    @Test
    public void testAddPlayerCountries() {
        Player1.addPlayerCountries(Country1);
        assertTrue(Player1.getPlayerCountries().contains(Country1.getCountryName()));
    }
    
    @Test
    public void testSetTotalNumArmy() {
        Player1.setTotalNumArmy(10);
        assertTrue(Player1.getTotalNumArmy()==10);
    }
}
