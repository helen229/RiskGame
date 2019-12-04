/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Strategy;
import GamePlayModel.GameModel;
import GamePlayModel.PlayerModel;
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
public class CheaterStrategyTest {
    private ContinentModel continent1;
    private CountryModel country1;
    private PlayerModel player1;
    private GameModel gameModel;
    private CheaterStrategy cheaterStrategy;
    
    /**
     * Each time invoke a method, set up this context
     */
    @BeforeEach
    public void setUp() {

        player1 = new PlayerModel("Player1");
        gameModel= new GameModel.Builder().currentExchangeTry(1).currentPlayerNum(0).build();
        continent1= new ContinentModel ("Continent1",1);
        country1= new CountryModel (0,"Country1","Continent1");
        cheaterStrategy= new CheaterStrategy (player1, gameModel );
        ArrayList<String> playerList = new ArrayList<String>();
        playerList.add(player1.getPlayerName());
    }
    
    @Test
    public void testGetName() {
        assertTrue(cheaterStrategy.getName().equals("Cheater"));
    }
}
