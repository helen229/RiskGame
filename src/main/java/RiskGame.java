import MapEditorController.MapController;

import java.util.Scanner;

public class RiskGame {

    static String phase = "MapEditor";
    public static void main(String[] args) {
        //uncomment those hardcode for testing phase
        args[0]="start";
        args[1]="new";
        args[2]="game";

        if (args[0].equals("start")&&args[1].equals("new")&&args[2].equals("game"))
            newGame();
    }

    private static void newGame(){

        MapController mapController = new MapController();

        Scanner input = new Scanner(System.in);
        System.out.println("Please enter command");
        while(input.hasNext()){
            String command= input.nextLine();
            if (command.equals("start game"))
                phase = "GamePlay";
            if (phase.equals("MapEditor")){
                mapController.commandHandler(command.split(" "));
            }else {
                //gameController.commandHandler(command.split(" ")
                System.out.println("GAME PLAY PHASE");
            }
        }

    }

}
