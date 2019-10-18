import MapEditorController.MapController;
import GamePlayController.GameController;

import java.util.Scanner;

public class RiskGame {

    static String phase = "Menu";
    public static void main(String[] args) {
            newGame();
    }

    private static void newGame(){

        MapController mapController = new MapController();
        GameController gameController = new GameController();

        Scanner input = new Scanner(System.in);
        System.out.println("GamePlay, MapEditor or Exit?");
        
        while(phase!="Exit"){
            String command= input.nextLine();
            if ((command.equals("GamePlay")||(phase.equals("GamePlay")))) {
                
                System.out.println("Game Play > Please enter command:");
                phase=gameController.commandHandler(input.nextLine().split(" "));
                System.out.print("For next command press Enter or MapEditor:");
                
            } else if (command.equals("MapEditor")||(phase.equals("MapEditor"))){
                
                System.out.println("Map Editor > Please enter command:");
                phase=mapController.commandHandler(input.nextLine().split(" "));
                System.out.print("For next command press Enter or GamePlay:");
                
            }else if (command.equals("Exit")) {
                phase="Exit";
            }else {
                //gameController.commandHandler(command.split(" ")
                System.out.println("Invalid input!");
                System.out.println("GamePlay or MapEditor?");
            }
        }

    }

}
