import GamePlayController.GameController;
import GamePlayModel.GameModel;
import GamePlayView.CardsExchangeView;
import GamePlayView.GamePhaseView;
import GamePlayView.PlayerDominationView;
import MapEditor.MapHandler;

import java.io.IOException;
import java.util.Scanner;

public class RiskGame {

    /**
     * Main Entry for the game
     * @param args
     */
    public static void main(String[] args) throws IOException {
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
    public void newGame() throws IOException {

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

                if(command.contains("-")){
                    String[] splitArray = command.split(" ",2);
                    String action = splitArray[0];
                    String options = splitArray[1];
                    String[] option = options.split("-");
                    for (String commandArg:option) {
                        if (commandArg.isEmpty())
                            continue;
                        String args = action+" "+commandArg;
                        mapHandler.commandHandler(args.split(" "));
                    }
                }else {
                    mapHandler.commandHandler(command.split(" "));
                }

            }else {

                System.out.println("Phase> "+gameController.getGame().getPhase());
                String[] countrys = new String[2];
                if(command.contains("-")){
                    String[] splitArray = command.split(" ",2);
                    String action = splitArray[0];
                    String options = splitArray[1];
                    if (action.equals("attack")){
                        countrys[0] =options.split(" ")[0]+" ";
                        countrys[1] =options.split(" ")[1]+" ";
                        options = options.replace(countrys[0],"");
                        options = options.replace(countrys[1],"");
                        String[] option = options.split("-| ");
                        for (String commandArg:option) {
                            if (commandArg.isEmpty())
                                continue;
                            String args = action+" "+ countrys[0] + countrys[1]+ commandArg;
                            gameController.commandHandler(args.split(" "),  gameController.getGame().getPhase());
                        }
                    }else if(action.equals("tournament")){
                        gameController.commandHandler(command.split(" "),  gameController.getGame().getPhase());
                    }else {
                        String[] option = options.split("-");
                        for (String commandArg:option) {
                            if (commandArg.isEmpty())
                                continue;
                            String args = action+" "+commandArg;
                            gameController.commandHandler(args.split(" "),  gameController.getGame().getPhase());
                        }
                    }

                }else {
                    gameController.commandHandler(command.split(" "),  gameController.getGame().getPhase());
                }

            }
        }

    }

}
