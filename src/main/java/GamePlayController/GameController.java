package GamePlayController;

import GamePlayModel.GameModel;

import java.util.ArrayList;

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
    public void
    commandHandler(String[] args, String phase) {

        if (args[0].equals("showmap")){
            showMap(phase);
            return;
        }
        if (args[0].equals("savegame")){
            game.saveGame("");
            return;
        }
        if (args[0].equals("loadgame")){
            game.loadGame("");
            return;
        }
        try
        {
            if (phase.equals("Startup")){
                switch (args[0]) {
                    case "start":
                        break;
                    case "loadmap":
                        game.loadMap(args[1]);
                        break;
                    case "populatecountries":
                        game.populateCountries();
                        break;
                    case "gameplayer":
                        parsePlayerOption(args[1],args[2],args[3]);
                        break;
                    case "placearmy":
                        game.placeArmy(args[1]);
                        break;
                    case "placeall":
                        game.placeAllAmy();
                        break;
                    case "tournament":
                        parseTournamentOption(args);
                        break;
                    default:
                        System.out.println("Invalid Command for startup phase");
                        break;
                }

            }else if (phase.equals("Reinforcement")){
                //reinforce countryname num
                if (args[0].equals("reinforce")){
                    game.reinforce(args[1], Integer.parseInt(args[2]));
                }
                else if(args[0].equals("exchangecards")){
                    if (args[1].equals("none")){
                        game.exchangeCardsNone();
                    }else{
                        game.exchangeCards( Integer.parseInt(args[1]),  Integer.parseInt(args[2]), Integer.parseInt(args[3]));
                    }
                }
                else{
                    System.out.println("Wrong command, The command is not valid in this phase");
                    return;
                }

            }else if (phase.equals("Attack")){
                switch (args[0]) {
                    case "attack":
                        parseAttackOption(args[1],args[2],args[3]);
                        break;
                    case "defend":
                        game.defendDiceNum(Integer.parseInt(args[1]));
                        break;
                    case "attackmove":
                        game.winnerMove(Integer.parseInt(args[1]));
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
        }catch(Exception e){
            System.out.println(e);
        }
    }

    /**
     * This method allows players to add or remove players.
     * @param operation
     * @param playerName
     */
    private void parsePlayerOption(String operation, String playerName, String playerStrategy) {
        if (operation.equals("add")){
            game.addPlayer(playerName,playerStrategy);
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
        if (mode.equals("noattack")){
            game.stopAttack();
        }else if (mode.equals("allout")){
            game.attackAllOut(attackCountry,defendCountry);
        }else if (Integer.parseInt(mode)>0 && Integer.parseInt(mode)<4){
            game.attackDiceNum(attackCountry,defendCountry,Integer.parseInt(mode),false);
        }else {
            System.out.println("Invalid Command");
        }
    }

    private void parseTournamentOption(String[] args) {

        ArrayList<String> mapList = new ArrayList<String>();
        ArrayList<String> playerList = new ArrayList<String>();
        int numberOfGames = 0;
        int maxNumberOfTurns = 0;
        boolean mapFlag = false;
        boolean playerFlag = false;
        for (int i = 0; i < args.length; i++) {

            if (args[i].equals("-M")){
                mapFlag = true;
                continue;
            }
            else if (args[i].equals("-P")){
                playerFlag = true;
                mapFlag = false;
                continue;
            }
            else if (args[i].equals("-G")){
                playerFlag = false;
                numberOfGames = Integer.parseInt(args[i+1]);
                continue;
            }
            else if (args[i].equals("-D")){
                maxNumberOfTurns = Integer.parseInt(args[i+1]);
                continue;
            }

            if (mapFlag){
                mapList.add(args[i]);
            }
            if (playerFlag){
                playerList.add(args[i]);
            }

        }

        //TODO: add the validation for those four variable
        // choosing M = 1 to 5 different maps, P = 2 to 4 different computer players strategies,
        // G = 1 to 5 games to be played on each map, D = 10 to 50 maximum number of turns for each game.

        game.tournament(mapList, playerList, numberOfGames, maxNumberOfTurns);

    }

    /**
     * This method  returns a game Model.
     * @return game
     */
    public GameModel getGame() {
        return game;
    }
}
