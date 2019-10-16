import GamePlayController.GameController;
import MapEditorController.MapController;

import java.util.Scanner;

public class RiskGame {

    static String phase = "MapEditor";
    public static void main(String[] args) {
        //uncomment those hardcode for testing phase
//        args[0]="start";
//        args[1]="new";
//        args[2]="game";
//        if (args[0].equals("start")&&args[1].equals("new")&&args[2].equals("game"))
            newGame();
    }

    private static void newGame(){

        MapController mapController = new MapController();
        GameController gameController = new GameController();
        Scanner input = new Scanner(System.in);
        System.out.println("Please enter command");
        while(input.hasNext()){
            String command= input.nextLine();
            if (command.equals("start game play"))
                phase = "GamePlay";
            if (phase.equals("MapEditor")){
                mapController.commandHandler(command.split(" "));
            }else {
//                System.out.println("GAME PLAY PHASE START");

                gameController.getGame().setPhase("Startup");
//                if (command.equals("Startup phase done"))
//                    phase="Reinforcement";
//                else if (command.equals("Reinforcement phase done"))
//                    phase="Fortification";

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