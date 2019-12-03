//package GamePlayModel;
//
//import GamePlayController.GameController;
//import GamePlayModel.GameModel;
//import GamePlayModel.PlayerModel;
//import MapEditorModel.ContinentModel;
//import MapEditorModel.CountryModel;
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//import java.io.ByteArrayOutputStream;
//import java.io.PrintStream;
//import java.util.ArrayList;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//public class GameModelTest {
//    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
//    private final PrintStream originalOut = System.out;
//    private GameModel game;
//    private PlayerModel player;
//    private GameController gameController;
//    CountryModel attackCtry ;
//    CountryModel defendCtry ;
//    CountryModel originalCountry ;
//    CountryModel destCountry ;
//
//    /**
//     * Each time invoke a method, set up this context
//     */
//    @BeforeEach
//    public void setUp() {
//        gameController = new GameController();
//        gameController.getGame().loadMap("test.txt");
//        gameController.getGame().addPlayer("Player1","human");
//        gameController.getGame().addPlayer("Player2","random");
//
//        gameController.getGame().populateCountries();
//        gameController.getGame().placeAllAmy();
//
//        game = gameController.getGame();
//        attackCtry = game.getMapModel().getCountryList().get(0);
//        defendCtry = game.getMapModel().getCountryList().get(0);
//        System.setOut(new PrintStream(outContent));
//    }
//
//    @AfterEach
//    public void restoreStreams() {
//        System.setOut(originalOut);
//    }
//
//    @Test
//    public void testReinforcementArmies() {
//        int armyNum= gameController.getGame().getCurrentPlayer().getTotalNumReinforceArmy();
//        System.out.println(armyNum);
//        assertTrue(armyNum>=0);
//    }
//
//
//
//    /**
//     * To test if the attack country is belonged to attacker/current player
//     */
//    @Test
//    public void attackerValidationTest() {
//        PlayerModel attacker= game.getPlayerList().get(0);
//        PlayerModel defender= game.getPlayerList().get(1);
//        attackCtry.setOwner(attacker);
//        defendCtry.setOwner(defender);
//        PlayerModel thirdPlayer= new PlayerModel("player3","human");
//        attackCtry.setOwner(thirdPlayer);
//        assertFalse(thirdPlayer.equals(game.getCurrentPlayer()));
//    }
//
//
//    /**
//     * To test if the defend country is belonged to defender
//     */
//    @Test
//    public void defenderValidationTest() {
//        PlayerModel forthPlayer= new PlayerModel("player4", "random");
//        defendCtry.setOwner(forthPlayer);
//        assertFalse(forthPlayer.equals(game.getPlayerList().get(1)));
//    }
//
//
//    /**
//     * To test if attacker has more than one army to attack
//     */
//    @Test
//    public void attackerArmyNumTest() {
//        //If attacker has only one army,then the attack operation is rejected,
//        //and there is nothing changes in the army number of both countries
//        assertTrue(attackCtry.getArmyNum()>=1);
//        assertTrue(defendCtry.getArmyNum()>=1);
//    }
//
//    @Test
//    public void FortificaitonTestOne() {
//        originalCountry = game.getMapModel().getCountryList().get(0);
//        destCountry = game.getMapModel().getCountryList().get(1);
//        game.fortify(originalCountry.getCountryName(), destCountry.getCountryName(),3);
//        assertNotNull( outContent.toString());
//    }
//
//    /**
//     * test whether the destination country is your country
//     */
//    @Test
//    public void FortificaitonTestErrorTwo() {
//        originalCountry = game.getMapModel().getCountryList().get(0);
//        destCountry = game.getMapModel().getCountryList().get(1);
//        game.fortify(originalCountry.getCountryName(), destCountry.getCountryName(),3);
//        assertNotNull( outContent.toString());
//    }
//
//    /**
//     * test whether the army of original country equal 1
//     */
//    @Test
//    public void errorThree() {
//        originalCountry = game.getMapModel().getCountryList().get(0);
//        destCountry = game.getMapModel().getCountryList().get(3);
//        game.fortify(originalCountry.getCountryName(), destCountry.getCountryName(),1);
//        assertNotNull( outContent.toString());
//    }
//
//    /**
//     * test the successful situation,and move army
//     */
//    @Test
//    public void FortificaitonTestSucced() {
//        originalCountry = game.getMapModel().getCountryList().get(0);
//        destCountry = game.getMapModel().getCountryList().get(2);
//        game.fortify(originalCountry.getCountryName(), destCountry.getCountryName(),3);
//        assertNotNull( outContent.toString());
//    }
//
//}
