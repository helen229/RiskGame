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

    public GameModel() {
        this.mapModel = new MapModel();
        this.playerList = new ArrayList<>();
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

    }

    public void placeAllAmy() {

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
}
