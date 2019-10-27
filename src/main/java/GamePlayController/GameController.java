package GamePlayController;

import GamePlayModel.GameModel; 
/**
 * This class handles commands that are require while playing the game.
 */
public class GameController {
    private GameModel game;

    /**
     * Constructor for game controller
     */
    public GameController(){
        this.game = new GameModel();
    }


    public void showMap(String phase){
        game.showMap();
    } 
    /**
     * This method shows the map based on the phase selected.
     * @param phase
     */
    public void commandHandler(String[] args, String phase) {

        if (args[0].equals("showmap")){
            showMap(phase);
            return;
        }

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
                else{
                    System.out.println("Wrong command, The command is not valid in this phase");
                    return;
                }

            }else if (phase.equals("Attack")){
                switch (args[0]) {
                    case "attack":
                        parseAttackOption(args[0],args[1],args[3]);
                        break;
                    case "defend":
                        game.defendDiceNum(Integer.parseInt(args[1]));
                        break;
                    case "attackmove":
                        game.winnerMove(Integer.parseInt(args[1]));
                        break;
                    case "noattack":
                        game.stopAttack();
                        break;
                    default:
                        System.out.println("Invalid Command in Attack Phase");
                        break;
                }

            }else if (phase.equals("Fortification")){
                //fortify fromcountry tocountry num
                //fortify none (choose to not do a move)
                if  (args[0].equals("fortify")){
                    if (args[1].equals("none")){
                        game.fortifyNone();
                    }else{
                        game.fortify(args[1], args[2], Integer.parseInt(args[3]));
                    }
                }else{
                    System.out.println("Wrong command, The command is not valid in this phase");
                }

            }else {
                System.out.println("The command is not valid in this phase");
            }
        }catch(ArrayIndexOutOfBoundsException e){
            System.out.println("Arguments number invalid");
        }
    }

    /**
     * This method allows players to add or remove players.
     * @param operation
     * @param playerName
     */
    private void parsePlayerOption(String operation, String playerName) {
        if (operation.equals("add")){
            game.addPlayer(playerName);
        }else if (operation.equals("remove")){
            game.removePlayer(playerName);
        }else {
            System.out.println("Invalid Command");
        }
    }

    /**
     *
     * @param attackCountry
     * @param defendCountry
     * @param mode
     */
    private void parseAttackOption(String attackCountry, String defendCountry, String mode) {

        //TODO:Integer failed exception handle!
        if (mode.equals("allout")){
            game.attackAllOut(attackCountry,defendCountry);
        }else if (Integer.parseInt(mode)>0 && Integer.parseInt(mode)<4){
            game.attackDiceNum(attackCountry,defendCountry,Integer.parseInt(mode));
        }else {
            System.out.println("Invalid Command");
        }
    }

    /**
     * This method  returns a game Model.
     * @return game
     */
    public GameModel getGame() {
        return game;
    }
}
