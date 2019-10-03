import MapEditorController.MapController;

import java.util.Scanner;

public class RiskGame {

    static String phase="MapEditor";
    public static void main(String[] args) {

        MapController mapController = new MapController();

        Scanner input = new Scanner(System.in);
        System.out.println("Please enter command");
        while(input.hasNext()){
            String command= input.nextLine();
            if(phase.equals("MapEditor")){
                mapController.commandHandler(command.split(" "));
            }else {
                System.out.println("GAME PLAY PHASE");
            }
        }

    }

}