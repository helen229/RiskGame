import GamePlayController.GameController;
import GamePlayModel.GameModel;
import GamePlayView.CardsExchangeView;
import GamePlayView.GamePhaseView;
import GamePlayView.PlayerDominationView;
import MapEditor.MapHandler;

import java.util.Scanner;

public class RiskGame {

    /**
     * Main Entry for the game
     * @param args
     */
    public static void main(String[] args) {
        //uncomment those hardcode for Junit test
//        if (args[0].equals("start")&&args[1].equals("new")&&args[2].equals("game")){
            RiskGame riskGame = new RiskGame();
            riskGame.newGame();
//        }else{
//            throw new IllegalArgumentException("Wrong Args");
//        }
    }

    /**
     * Game method for all the phase
     */
    public void newGame(){

        MapHandler mapHandler = new MapHandler();
        GameController gameController = new GameController();
        GameModel game = gameController.getGame();
        GamePhaseView gamePhaseView = new GamePhaseView();
        CardsExchangeView cardsExchangeView = new CardsExchangeView();
        PlayerDominationView playerDominationView = new PlayerDominationView();

        game.addObserver(gamePhaseView);
        game.addObserver(cardsExchangeView);
        game.addObserver(playerDominationView);

        game.setPhase("MapEditor");
        System.out.println("Phase> "+gameController.getGame().getPhase()+"> Please enter command");

        Scanner input = new Scanner(System.in);
        while(input.hasNext()){
            String command= input.nextLine();
            if (command.equals("start game play")) {
                gameController.getGame().setPhase("Startup");
                System.out.println("Phase> "+gameController.getGame().getPhase()+"> Please enter command");
            }
            if ( gameController.getGame().getPhase().equals("MapEditor")){
                mapHandler.commandHandler(command.split(" "));
            }else {
                System.out.println("Phase> "+gameController.getGame().getPhase());
                gameController.commandHandler(command.split(" "),  gameController.getGame().getPhase());
            }
        }

    }

}
