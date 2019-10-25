import GamePlayController.GameController;
import MapEditorController.MapController;

import java.util.Scanner;

public class RiskGame {
    /**
     * Main Entry for the game
     * @param args
     */
    public static void main(String[] args) {
        //uncomment those hardcode for testing phase
//        args[0]="start";
//        args[1]="new";
//        args[2]="game";
//        if (args[0].equals("start")&&args[1].equals("new")&&args[2].equals("game"))
            newGame();
    }

    /**
     * Game method for all the phase
     */
    private static void newGame(){

        MapController mapController = new MapController();
        GameController gameController = new GameController();
        Scanner input = new Scanner(System.in);
        gameController.getGame().setPhase("MapEditor");
        System.out.println("Phase> "+gameController.getGame().getPhase()+"> Please enter command");
        while(input.hasNext()){
            String command= input.nextLine();
            if (command.equals("start game play")) {
                gameController.getGame().setPhase("Startup");
                System.out.println("Phase> "+gameController.getGame().getPhase()+"> Please enter command");
            }
            if ( gameController.getGame().getPhase().equals("MapEditor")){
                mapController.commandHandler(command.split(" "));
            }else {
                System.out.println("Phase> "+gameController.getGame().getPhase());
                gameController.commandHandler(command.split(" "),  gameController.getGame().getPhase());
            }
        }

    }

}
//    start game play
//        loadmap test.txt
//        gameplayer -add playername1
//        gameplayer -add playername2
//        gameplayer -add playername3
//        populatecountries
//        showmap