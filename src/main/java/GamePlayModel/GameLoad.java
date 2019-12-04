package GamePlayModel;

import MapEditorModel.ContinentModel;
import MapEditorModel.CountryModel;

import java.io.*;

/**
 * This class handles Game Loading
 */

public class GameLoad {
    public GameModel game;

    public GameLoad(GameModel gameM) {
        //gameM =  new GameModel.Builder().currentExchangeTry(1).currentPlayerNum(0).build();
        this.game = gameM;
    }

    /**
     * This method loads a GameModel
     * @param fileName
     * @return
     */
    public GameModel Loading(String fileName) {
        String result = "";
        BufferedReader br = null;
        String inputLine = null;
        String checker = "";
        int index;
        int rowNum = 0;
        int playerIndex = 0;

        PlayerModel currentPlayer;
        int currentPlayerNum;
        int currentExchangeTry;
        String phase;
        //To make sure attack move command only can run when this flag is true
        boolean ifAttackerWin=false;
        //To check if the current player can get a card
        boolean hasPlayerConquered=false;
        int NumofTurns = 1;
        String gameMode = "Single";

        try {
            br = new BufferedReader(new FileReader(fileName));
//            int countryCount = 0;
            while ((inputLine = br.readLine()) != null) {
                rowNum++;
                inputLine = inputLine.trim();
                if (!inputLine.isEmpty()) {
                    switch (inputLine) {
                        case "[Map]":
                            checker = "Map";
                            break;
                        case "[PlayerList]":
                            checker = "PlayerList";
                            break;
                        case "[CountryList]":
                            checker = "CountryList";
                            break;
                        case "[GameState]":
                            checker = "GameState";
                            break;
                        default:
                            inputLine.trim();
                            int size = inputLine.length();
                            switch (checker) {
                                case "Map":
                                    if (size > 0) {
                                        game.loadMap(inputLine);
                                    } else {
                                        continue;
                                    }
                                    break;

                                case "PlayerList":
                                    if (size > 0) {
                                        String[] playerFiled = inputLine.split(" ");
                                        switch (playerFiled[0]){
                                            case "--Player":
                                                game.addPlayer(playerFiled[1], playerFiled[2]);
                                                playerIndex = game.getPlayerNameList().indexOf(playerFiled[1]);
                                                game.getPlayerList().get(playerIndex).setTotalNumArmy(Integer.parseInt(playerFiled[3]));
                                                game.getPlayerList().get(playerIndex).setNumArmyRemainPlace(Integer.parseInt(playerFiled[4]));
                                                game.getPlayerList().get(playerIndex).setTotalNumReinforceArmy(Integer.parseInt(playerFiled[5]));
                                                game.getPlayerList().get(playerIndex).setNumReinforceArmyRemainPlace(Integer.parseInt(playerFiled[6]));
                                                break;
                                            case "-CountryList":
                                                for (int i = 1; i <playerFiled.length; i++){
                                                    game.getPlayerList().get(playerIndex).addPlayerCountries(game.mapModel.getCountryList().get(game.mapModel.indexOfCountry(playerFiled[i])));
                                                    game.mapModel.getCountryList().get(game.mapModel.indexOfCountry(playerFiled[i])).setOwner(game.getPlayerList().get(playerIndex));
                                                }
                                                break;
                                            case "-ContinentList":
                                                for (int i = 1; i <playerFiled.length; i++){
                                                    String continentName = playerFiled[i];
                                                    int continentIndex = game.getMapModel().indexOfContinent(continentName);
                                                    ContinentModel continent = game.getMapModel().getContinentList().get(continentIndex);
                                                    game.getPlayerList().get(playerIndex).addPlayerContinents(continent);
                                                }
                                                break;
                                            case "-CardList":
                                                for (int i = 1; i <playerFiled.length; i++){
                                                    PlayerModel player = game.getPlayerList().get(playerIndex);
                                                    Card card = new Card(player, playerFiled[i]);
                                                    player.addCard(card);
                                                }
                                                break;
                                        }
                                    } else {
                                        continue;
                                    }
                                    break;
                                case "CountryList":
                                    if (size > 0) {
                                        String[] value = inputLine.split(" ");
                                        int indexofCountry = game.getMapModel().indexOfCountry(value[0]);
                                        CountryModel country = game.getMapModel().getCountryList().get(indexofCountry);
                                        int armyNum = Integer.parseInt(value[1]);
                                        playerIndex = game.getPlayerNameList().indexOf(value[2]);
                                        PlayerModel player = game.getPlayerList().get(playerIndex);
                                        country.setOwner(player);
                                        country.setArmyNum(armyNum);
                                    } else {
                                        continue;
                                    }
                                    break;
                                case "GameState":
                                    if (size > 0) {
                                        String[] gameFiled = inputLine.split(" ");
                                        switch (gameFiled[0]){
                                            case "currentPlayer":
                                                int playerNameIndex = game.getPlayerNameList().indexOf(gameFiled[1]);
                                                game.currentPlayer = game.getPlayerList().get(playerNameIndex);
                                                break;
                                            case "currentPlayerNum":
                                                game.currentPlayerNum = Integer.parseInt(gameFiled[1]);
                                                break;
                                            case "currentExchangeTry":
                                                game.currentExchangeTry = Integer.parseInt(gameFiled[1]);
                                                break;
                                            case "phase":
                                                game.setPhase(gameFiled[1]);
                                                break;
                                            case "ifAttackerWin":
                                                game.ifAttackerWin = Boolean.parseBoolean(gameFiled[1]);
                                                break;
                                            case "hasPlayerConquered":
                                                game.hasPlayerConquered = Boolean.parseBoolean(gameFiled[1]);
                                                break;
                                            case "NumofTurns":
                                                game.NumofTurns = Integer.parseInt(gameFiled[1]);
                                                break;
                                            case "gameMode":
                                                game.gameMode = gameFiled[1];
                                                break;
                                        }

                                    } else {
                                        continue;
                                    }
                                    break;
                            }
                    }
                }
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("Error in " + rowNum + "row");
            e.printStackTrace();
        }

        return this.game;
    }

}
