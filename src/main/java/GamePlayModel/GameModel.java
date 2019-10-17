package GamePlayModel;

import MapEditorController.EditMap;
import MapEditorModel.ContinentModel;
import MapEditorModel.CountryModel;
import MapEditorModel.MapModel;
import com.sun.org.apache.bcel.internal.generic.CHECKCAST;

import java.io.IOException;
import java.lang.management.PlatformLoggingMXBean;
import java.util.ArrayList;
import java.util.Map;

public class GameModel {

    MapModel mapModel;
    ArrayList<PlayerModel> playerList;
    PlayerModel currentPlayer;
    int currentPlayerNum;
    String phase;

    public GameModel() {
        this.mapModel = new MapModel();
        this.playerList = new ArrayList<>();
        this.currentPlayerNum=0;
    }

    public ArrayList<String> getPlayerNameList(){
        ArrayList<String> playerNameList = new ArrayList<>();
        for (PlayerModel player:playerList) {
            playerNameList.add(player.getPlayerName());
        }
        return  playerNameList;
    }
    /**
     * This method allows a player to be added.
     * @param playerName the parameter used to identify the player to be added.
     */
    public void addPlayer(String playerName){
        ArrayList<String> playerNameList = getPlayerNameList();
        if (playerNameList.contains(playerName)){
            System.out.println("Add "+playerName+" Failed, it's already exist");
        }else {
            PlayerModel player= new PlayerModel(playerName);
            playerList.add(player);
            System.out.println("Add "+playerName+" Succeed");
        }

    }

    /**
     * This method allows a player to be removed
     * @param playerName the parameter used to identify the player to be removed.
     */
    public void removePlayer(String playerName){
        ArrayList<String> playerNameList = getPlayerNameList();
        if (playerNameList.contains(playerName)){
            playerList.remove(playerNameList.indexOf(playerName));
            System.out.println("Remove "+playerName+" Succeed");
        }else {
            System.out.println("Remove "+playerName+" Failed, it's not exist");
        }
    }

    public int getNumOfPlayers() {
        return playerList.size();
    }


    public void populateCountries() {

        int numOfPlayers= this.getNumOfPlayers();
        ArrayList<CountryModel> countries = mapModel.getCountryList();
        int countrySize = countries.size();
        int numberCountry = countrySize / numOfPlayers;
        int start=0;
        for (int j = 0; j < playerList.size(); j++) {
            for (int i = start; i < (j + 1) * numberCountry; i++) {
                countries.get(i).setOwner(playerList.get(j));
                playerList.get(j).addPlayerCountries(countries.get(i));
            }
            start=(j + 1) * numberCountry;
        }
        int checker = countrySize % numOfPlayers;
        if (checker != 0) {
//            int remainderCountries = countrySize - (numberCountry * numOfPlayers);
            for (int i = numberCountry * numOfPlayers; i < numberCountry * numOfPlayers+checker; i++) {
                for (int j = 0; j < playerList.size(); j++) {
                    countries.get(i).setOwner(playerList.get(j));
                    playerList.get(j).addPlayerCountries(countries.get(i));
                }
            }

        }
        System.out.println("populate countries succeed");
        setCurrentPlayer(playerList.get(currentPlayerNum));
        System.out.println("Start Placing army, Current Player is "+ getCurrentPlayer().getPlayerName());
        currentPlayer.setTotalNumArmy(currentPlayer.getPlayerCountries().size()/3);
        currentPlayer.setNumArmyRemainPlace(currentPlayer.getTotalNumArmy());
    }



    public void loadMap(String fileName) {
        EditMap readFile = new EditMap(fileName);
        try {
            this.mapModel = readFile.checkFile();
        } catch (IOException e) {
            System.out.println("Load Map fail, this file not exist");
            return;
//            e.printStackTrace();
        }
        if (mapModel.isValid()){
            System.out.println("Load Map Succeed");
        }else {
            /** not validate so clear all the content */
            mapModel.getCountryList().clear();
            mapModel.getContinentList().clear();
        }
    }

    public void placeArmy(String countryName) {
        int numArmy=currentPlayer.getTotalNumArmy();
        int armyLeft=currentPlayer.getNumArmyRemainPlace();
        CountryModel country = mapModel.getCountryList().get(mapModel.indexOfCountry(countryName));
        if (currentPlayer.getPlayerName().equals(country.getOwner().PlayerName)){
            if (armyLeft>=1){
                armyLeft = armyLeft -1;
                currentPlayer.setNumArmyRemainPlace(armyLeft);
                country.setArmyNum(country.getArmyNum()+1);
                System.out.println("Place Army Succeed!");
            }else {
                System.out.println("You already place All your army! please start Reinforcement phase");
                this.setPhase("Reinforcement");
            }
        }else {
            System.out.println("Not your country! please try again");
        }

    }

    public void placeAllAmy() {
        System.out.println("You already place All your army! please start Reinforcement phase");
        this.setPhase("Reinforcement");
    }

    public void reinforce(String countryName, int number) {
        System.out.println("You are Reinforcement phase");
        this.setPhase("Fortification");
    }

    public void fortify(String fromcountry, String tocountry, int number) {
        System.out.println("You Fortification phase");
        this.setPhase("Fortification");
    }

    public void fortifyNone() {
        System.out.println("You Fortification phase");
        System.out.println(getCurrentPlayer().getPlayerName()+" Your turn over!");
        if (this.currentPlayerNum+1<this.playerList.size()){
            this.currentPlayerNum++;
            setCurrentPlayer(this.playerList.get(this.currentPlayerNum));
            currentPlayer.setTotalNumArmy(currentPlayer.getPlayerCountries().size()/3);
            currentPlayer.setNumArmyRemainPlace(currentPlayer.getTotalNumArmy());
            this.setPhase("Startup");
            System.out.println("Start Placing army, Current Player is "+ getCurrentPlayer().getPlayerName());
        }else{
            System.out.println("GAME END");
        }
    }

    /**
     * Method for the command show map
     */
    public void showMap(){
        System.out.println("Continents: ");

        for (int i = 0; i < mapModel.getContinentList().size(); i++){
            printContinent(mapModel.getContinentList().get(i));
            System.out.println("\n");
        }

        System.out.println("\nAll Countries: ");
        for (int i = 0; i < mapModel.getCountryList().size(); i++){
            printCountry(mapModel.getCountryList().get(i));
        }
    }

    /**
     *
     * @param continent
     */
    private void printContinent(ContinentModel continent){

        System.out.print(
                "Continent Name:" +
                        continent.getContinentName() + "\n" +
                        "Continent Value:" +
                        continent.getContinentValue() + "\n" +
                        "Countries List: ");

        for (int i = 0; i < continent.getCountriesList().size(); i++){
//            String countryName= continent.getCountriesList().get(i);
//            printCountry(this.countryList.get(indexOfCountry(countryName)));
            if (i==0)
                System.out.print("[");
            if (i == continent.getCountriesList().size()-1){
                System.out.print(continent.getCountriesList().get(i)+"]\n");
                break;
            }
            System.out.print(continent.getCountriesList().get(i)+",");
        }

    }

    /**
     *
     * @param country
     */
    private void printCountry(CountryModel country){

        System.out.print(
                "Country Name:" +
                        country.getCountryName() + "\n" +
                        "Country Value:" +
                        country.getCountryValue() + "\n" +
                        "Country Owner:" +
                        country.getOwner().getPlayerName() + "\n" +
                        "Country Army:" +
                        country.getArmyNum() + "\n" +
                        "Neighbor Country List: ");

        for (int i = 0; i < country.getNeighbours().size(); i++){
            int neighbourValue= country.getNeighbours().get(i);
            if (i==0)
                System.out.print("[");
            if (i==country.getNeighbours().size()-1){
                System.out.println(mapModel.getCountryList().get(mapModel.indexOfCountry(neighbourValue)).getCountryName()+"]\n");
                break;
            }
            System.out.print(mapModel.getCountryList().get(mapModel.indexOfCountry(neighbourValue)).getCountryName()+", ");
        }

    }

    public PlayerModel getCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(PlayerModel currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    public String getPhase() {
        return phase;
    }

    public void setPhase(String phase) {
        this.phase = phase;
    }
}
