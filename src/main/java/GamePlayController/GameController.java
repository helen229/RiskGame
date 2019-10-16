package GamePlayController;

import GamePlayModel.GameModel;

public class GameController {
    StartUpPhase startUpPhase;
    ReinforcementPhase reinforce;
    FortificationPhase fortify;
    private GameModel game;

    public GameController(){
        this.game = new GameModel();
//        StartUpPhase startUpPhase= new StartUpPhase();
//        ReinforcementPhase reinforce= new ReinforcementPhase();
//        FortificationPhase fortify= new FortificationPhase();
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
//                case "validatemap":
//                    mapModel.isValid();
//                    break;
//                case "editmap":
//                    editMap(args[1]);
//                    break;
//                case "savemap":
//                    saveMap(args[1]);
//                    break;

                    default:
//                        System.out.println("Invalid Command");
                        break;
                }

            }else if (phase.equals("")){
                //reinforce countryname num
            }else if (phase.equals("")){
                //fortify fromcountry tocountry num
                //fortify none (choose to not do a move)
            }
        }catch(ArrayIndexOutOfBoundsException e){
            System.out.println("Arguments number invalid");
        }    }

    private void parsePlayerOption(String operation, String playerName) {
        if (operation.equals("-add")){
            game.addPlayer(playerName);
        }else if (operation.equals("-remove")){
            game.removePlayer(playerName);
        }else {
            System.out.println("Invalid Command");
        }
    }
}
