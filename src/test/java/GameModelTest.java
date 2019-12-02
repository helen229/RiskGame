import GamePlayController.GameController;
import GamePlayModel.GameModel;
import GamePlayModel.PlayerModel;
import MapEditorModel.ContinentModel;
import MapEditorModel.CountryModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class GameModelTest {
    private GameModel game;
    private PlayerModel player;
    private GameController gameController;

    /**
     * Each time invoke a method, set up this context
     */
    @BeforeEach
    public void setUp() {
        gameController = new GameController();
        gameController.getGame().loadMap("test.txt");
        gameController.getGame().addPlayer("Player1","Human");
        gameController.getGame().addPlayer("Player2","Cheater");
        gameController.getGame().populateCountries();
        gameController.getGame().placeAllAmy();
    }


    @Test
    public void testReinforcementArmies() {
        int armyNum= gameController.getGame().getCurrentPlayer().getTotalNumReinforceArmy();
        System.out.println(armyNum);
        assertTrue(armyNum>0);
    }


}
