package GamePlayController;

import GamePlayModel.PlayerModel;
import GamePlayModel.GameModel;
import GamePlayModel.CountryModel;

import java.io.IOException;
import static java.lang.Integer.parseInt;
import java.util.ArrayList;

/**
 * This class control the whole process of the GamePlay mode.
 * @author Ehsan
 */
public class GameController {
    private GameModel gameModel; 

    public GameController() {
        this.gameModel= new GameModel();
    }
    
   /**
     *  This method returns an string containing the last mode of the game according to the command.
     * @return  gameMode
     */
    public String commandHandler(String[] args) {
        String gameMode="GamePlay";
        try
        {
            switch (args[0]) {
                case "showmap":
                    gameModel.showMap();
                    return gameMode;
                case "loadmap":
                    loadMap(args[1]);
                    return gameMode;
                case "gameplayer":
                    parseCommandOption(args,args[0],args[1]);
                    return gameMode;
                case "populatecountries":
                    gameModel.populateCountries();
                    return gameMode;
                case "placearmy":
                    gameModel.placeArmy(args[1]);
                    return gameMode;
                case "placeall":
                    gameModel.placeAll();
                    return gameMode;
                case "reinforce":
                    gameModel.reinforce(args[1], Integer.parseInt(args[2]));
                    return gameMode;
                case "fortify":
                    System.out.println("place fortify method");
                    return gameMode;
                default:
                    System.out.println("Invalid Command");
                    if ("Exit".equals(args[0])){
                        gameMode="Exit";
                        return gameMode;
                    }else if ("MapEditor".equals(args[0])){
                        gameMode="MapEditor";
                        return gameMode;
                    } else return gameMode;
            }
        }catch(ArrayIndexOutOfBoundsException e){
            System.out.println("Arguments number invalid");
            return gameMode;
        }
    } 
    
    // This method calls ReafFile class to load the map from the file.
    private void loadMap(String fileName) {
        ReadFile readFile = new ReadFile(fileName);
        this.gameModel.setContinentList(readFile.getContinents());
        this.gameModel.setCountryList(readFile.getCountries());
    }
    
    // The commandHandler method use this method to parse the input command and calls realated methods.
    public void parseCommandOption(String[] args, String command, String operation){
        try
        {
            if (operation.equals("-add")){
                switch (command){
                    case "gameplayer":
                        addPlayer(args[2]);
                        break;
                    default:
                        System.out.println("Invalid Command");
                        break;
                }
            }
            else if(operation.equals("-remove")){
                switch (command){
                    case "gameplayer":
                        removePlayer(args[2]);
                        break;
                    default:
                        System.out.println("Invalid Command");
                        break;
                }
            }
            else{
                System.out.println("Invalid Option");
            }
        }catch(ArrayIndexOutOfBoundsException e){
            System.out.println("Arguments number invalid");
        }
    }
    
    // This method calls addPlayer method from GameModel class to add the new player if possible.
    private void addPlayer(String playerName) {
        if (this.gameModel.addPlayer(playerName)){
            System.out.println("Add Player succeed");
        }
        else{
            System.out.println("Add Player failed");
        }
    }
    
    // This method calls removePlayer method from GameModel class to remove the player if availbe.
    private void removePlayer(String playerName) {
        if (this.gameModel.removePlayer(playerName)){
            System.out.println("Remove succeed");
        }
        else{
            System.out.println("Remove failed");
        }
    }
}
