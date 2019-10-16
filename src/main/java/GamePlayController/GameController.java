package GamePlayController;

import GamePlayModel.GameModel;

public class GameController {
    private GameModel game;

    public GameController(){
        this.game = new GameModel();
    }


    public void showMap(String phase){
        if (phase.equals("Startup")){
            game.showMap();
        }else if (phase.equals("Reinforcement")){

        }else if (phase.equals("Fortification")){

        }

    }

    public void commandHandler(String[] args, String phase) {

        if (args[0].equals("showmap"))
            showMap(phase);
        try
        {
            if (phase.equals("Startup")){
                switch (args[0]) {
                    case "loadmap":
                        game.loadMap(args[1]);
                        break;
                    case "populatecountries":
                        game.populateCountries();
                        break;
                    case "gameplayer":
                        parsePlayerOption(args[1],args[2]);
                        break;
                    case "placearmy":
                        game.placeArmy(args[1]);
                        break;
                    case "placeall":
                        game.placeAllAmy();
                        break;

                    default:
//                        System.out.println("Invalid Command");
                        break;
                }

            }else if (phase.equals("Reinforcement")){
                //reinforce countryname num
                if (args[0].equals("reinforce"))
                    game.reinforce(args[1], Integer.parseInt(args[2]));

            }else if (phase.equals("Fortification")){
                //fortify fromcountry tocountry num
                //fortify none (choose to not do a move)
                if  (args[0].equals("fortify")){
                    if (args[1].equals("none")){
                        game.fortifyNone();
                    }else{
                        game.fortify(args[1], args[2], Integer.parseInt(args[3]));
                    }
                }

            }else {
                System.out.println("The command is not valid in this phase");
            }
        }catch(ArrayIndexOutOfBoundsException e){
            System.out.println("Arguments number invalid");
        }
    }

    private void parsePlayerOption(String operation, String playerName) {
        if (operation.equals("-add")){
            game.addPlayer(playerName);
        }else if (operation.equals("-remove")){
            game.removePlayer(playerName);
        }else {
            System.out.println("Invalid Command");
        }
    }

    public GameModel getGame() {
        return game;
    }
}
