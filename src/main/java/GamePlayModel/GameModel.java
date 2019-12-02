package GamePlayModel;

import MapEditor.EditMap;
import MapEditorModel.ContinentModel;
import MapEditorModel.CountryModel;
import MapEditorModel.MapModel;
import Strategy.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Observable;
import java.util.Random;
import static java.lang.System.exit;

/**
   * This class defines the characteristics of the a Game in a particular phase
   */
public class GameModel extends Observable{

    MapModel mapModel;
    ArrayList<PlayerModel> playerList;
    PlayerModel currentPlayer;
    int currentPlayerNum;
    int currentExchangeTry;
    String phase;
    public ArrayList<Integer> attackerDice;
    CountryModel defenderCountry;
    public CountryModel attackerCountry;
    //To make sure attack move command only can run when this flag is true
    boolean ifAttackerWin=false;
    boolean gameStopFlag = false;
    //To check if the current player can get a card
    boolean hasPlayerConquered=false;
    int NumofTurns = 1;
    int maxNumberOfTurns = 0;
    String gameMode = "Single";
    boolean gameEnd = false;
    String gameWinner = "";
    public  ArrayList<ArrayList<String>> tournamentResult;
    public  ArrayList<String> tournamentMaps;

    public GameModel(Builder builder) {
        this.mapModel = new MapModel();
        this.playerList = new ArrayList<>();
        this.attackerDice = new ArrayList<>();
        this.currentPlayerNum=0;
        this.currentExchangeTry=builder.currentExchangeTry;
        this.tournamentResult = new ArrayList<>();
        this.tournamentMaps = new ArrayList<>();
        defenderCountry = null;
        attackerCountry = null;
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
    public void addPlayer(String playerName, String strategy){
        ArrayList<String> playerNameList = getPlayerNameList();
        if ((getNumOfPlayers()<=5) && (isNotPopulated())){
            if (playerNameList.contains(playerName)){
                System.out.println("Add "+playerName+" Failed, it's already exist");
            }else {
                PlayerModel player= new PlayerModel(playerName);
                //if the Strategy not exist
                if (!setPlayerStrategy(player, strategy)){
                    return;
                }
                playerList.add(player);
                System.out.println("Add "+playerName+" Succeed");
            }
        } else if (getNumOfPlayers()>5){
            System.out.println("Add "+playerName+" Failed, the maximum number of players is 6.");
        } else if (!isNotPopulated()) {
            System.out.println("Add "+playerName+" Failed, There is no more unowned country.");
        } else {
            System.out.println("Add "+playerName+" Failed!");
        }

    }
public boolean setPlayerStrategy(PlayerModel player, String strategyName) {
        Strategy strategy;
        boolean res = true;
        switch (strategyName) {
            case "Cheater":
                strategy = new CheaterStrategy(player,this);
                player.setStrategy(strategy);
                break;
            case "Aggressive":
                strategy = new AggressiveStrategy(player,this);
                player.setStrategy(strategy);
                break;
            case "Benevolent":
                strategy = new BenevolentStrategy(player,this);
                player.setStrategy(strategy);
                break;
            case "Random":
                strategy = new RandomStrategy(player, this);
                player.setStrategy(strategy);
                break;
            case "Human":
                strategy = new HumanStrategy(player);
                player.setStrategy(strategy);
                break;
            default:
                System.out.println("Invalid Strategy name");
                res = false;
                break;
        }
        return res;
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
    /**
     * This method returns the number of players
     */


    public int getNumOfPlayers() {
        return playerList.size();
    }

/**
     * This method assigns countries to the players
     */

    public void populateCountries() {

        int numOfPlayers= this.getNumOfPlayers();
        ArrayList<CountryModel> countries = mapModel.getCountryList();
        int countrySize = countries.size();
        if ((isNotPopulated()) && (countrySize>0) && (numOfPlayers>0)) {
            int numberCountry = countrySize / numOfPlayers;
            if (numberCountry==0) {
                System.out.println("Error: Number of Players > Number of Countries");
            } else {
                int totalCountriesAssigned=0;
                int selectedCountry=0;
               int selectedOwner=-1;
               for (int j=0; j<numberCountry; j++)
                   for (int i=0; i<numOfPlayers;i++){
                       do{
                           selectedCountry=(int) Math.floor((Math.random() * countrySize));
                       }
                       while (!"".equals(countries.get(selectedCountry).getOwner().getPlayerName()));
                       countries.get(selectedCountry).setOwner(playerList.get(i));
                       countries.get(selectedCountry).setArmyNum(countries.get(selectedCountry).getArmyNum()+1);
                       playerList.get(i).addPlayerCountries(countries.get(selectedCountry));
                       totalCountriesAssigned++;
                   }
               while(totalCountriesAssigned<countrySize){
                   selectedOwner++;
                   do{
                       selectedCountry=(int) Math.floor((Math.random() * countrySize));
                   }
                   while (!"".equals(countries.get(selectedCountry).getOwner().getPlayerName()));
                   countries.get(selectedCountry).setOwner(playerList.get(selectedOwner));
                   countries.get(selectedCountry).setArmyNum(countries.get(selectedCountry).getArmyNum()+1);
                   playerList.get(selectedOwner).addPlayerCountries(countries.get(selectedCountry));
                   totalCountriesAssigned++;
               }
                System.out.println("Populate countries succeed");
                if (selectedOwner<0) selectedOwner=0;
                else selectedOwner=1;
                setCurrentPlayer(playerList.get(currentPlayerNum));
                for (int i=0; i<numOfPlayers;i++) {
                    playerList.get(i).setTotalNumArmy(this.playerList.size());
                    playerList.get(i).setNumArmyRemainPlace(playerList.get(i).getTotalNumArmy()-playerList.get(i).playerCountries.size());
                }
               System.out.println("Assigned initial armies (Number of players):"+ currentPlayer.getTotalNumArmy());
               System.out.println("Assigned initial countries (one army included)");
               System.out.println("Total number of countries: "+countrySize);
               System.out.println("Total number of players: "+numOfPlayers);
               System.out.println("Minimum number of owned countries: " +numberCountry);
               System.out.println("Maximum number of owned countries: " +(numberCountry+selectedOwner));
                
               
                System.out.println("Start Placing army, Current Player is "+ getCurrentPlayer().getPlayerName());
                
            }
        } else if (countrySize==0) {
            System.out.println("Populate countries failed! First add some countries.");
        } else if (numOfPlayers<2) {
            System.out.println("Populate countries failed! First add some players.");
        } else if (numOfPlayers>6) {
            System.out.println("Populate countries failed! First remove some players.");
        } else if (!isNotPopulated()) {
            System.out.println("Populate countries failed! The map has been populated before.");
        } else {
            System.out.println("Populate countries failed!");
        }
    }

    /**
     * @return true if there is a country without owner.
     * @return false if all countries have owner.
     */
    
    private boolean isNotPopulated(){
        ArrayList<CountryModel> countries = mapModel.getCountryList();
        for (int i = 0; i < countries.size(); i++){
            if("".equals(countries.get(i).getOwner().getPlayerName())){
                return true;
            } 
        }
       return false;
   }
    
 /**
     * This method allows players load a map file, that was previously saved
     */

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
            mapModel.setMapName(fileName);
        }else {
            /** not validate so clear all the content */
            mapModel.getCountryList().clear();
            mapModel.getContinentList().clear();
        }
    } 
    
    /**
     * This method allows armies to be assigned to countries
     */

    public void placeArmy(String countryName) {
        int numOfPlayers= this.getNumOfPlayers();
        ArrayList<CountryModel> countries = mapModel.getCountryList();
        int countrySize = countries.size();
        if ((!(isNotPopulated())) && (countrySize>0) && (numOfPlayers>0)) {
            if (mapModel.indexOfCountry(countryName)!=-1) {
                int numArmy=currentPlayer.getTotalNumArmy();
                int armyLeft=currentPlayer.getNumArmyRemainPlace();
                CountryModel country = mapModel.getCountryList().get(mapModel.indexOfCountry(countryName));
                if (currentPlayer.getPlayerName().equals(country.getOwner().PlayerName)){
                    if (armyLeft>=1){
                        armyLeft = armyLeft -1;
                        currentPlayer.setNumArmyRemainPlace(armyLeft);
                        country.addArmyNum();
                        System.out.println("Place Army Succeed! "+ currentPlayer.getPlayerName() + " left " + currentPlayer.getNumArmyRemainPlace() );
                    }else {
                        currentPlayer.setTotalNumReinforceArmy(currentPlayer.getPlayerCountries().size()/3);
                        currentPlayer.setNumReinforceArmyRemainPlace(currentPlayer.getTotalNumReinforceArmy());
                        if(currentPlayer.getNumReinforceArmyRemainPlace()==0) {
                            System.out.println("You already place All your army! please start Reinforcement phase");
                            this.setPhase("Reinforcement");
                            System.out.println("Phase> "+this.getPhase());
                            System.out.println("You have 0 Reinforcement army! Please start Fortification phase");
                            this.setPhase("Fortification");
                            System.out.println("Phase> "+this.getPhase());
                        } else {
                            System.out.println("You already place All your army! Please start Reinforcement phase");
                            System.out.println(currentPlayer.getPlayerName() + " has " + currentPlayer.getNumReinforceArmyRemainPlace()+" reinforcement.");
                            this.setPhase("Reinforcement");
                            System.out.println("Phase> "+this.getPhase());
                        }
                    }
                }else {
                    System.out.println("Not your country! please try again");
                }
            } else{
                System.out.println("Country name is not valid! please try again");
            }
        } else if (countrySize==0) {
            System.out.println("Place army failed! First add some countries.");
        } else if (numOfPlayers==0) {
            System.out.println("Place army failed! First add some players.");
        } else {
            System.out.println("Place army failed! First populate countries.");
        }
    } 
    /**
     * This method allows a player to place armies
     */

    public void placeAllAmy() {
        int numOfPlayers= this.getNumOfPlayers();
        ArrayList<CountryModel> countries = mapModel.getCountryList();
        int countrySize = countries.size();
        if ((!(isNotPopulated())) && (countrySize>0) && (numOfPlayers>0)) {
            int armyLeft=currentPlayer.getNumArmyRemainPlace();
            while (armyLeft>0){
                int randomNum=(int)(Math.random() * (currentPlayer.playerCountries.size()));
                currentPlayer.playerCountries.get(randomNum).addArmyNum();
                armyLeft--;
            }
            currentPlayer.setNumArmyRemainPlace(0);
            System.out.println("You already place All your army! Please start Reinforcement phase");
            this.setPhase("Reinforcement");
            System.out.println("Phase> "+this.getPhase());
            currentPlayer.setTotalNumReinforceArmy(currentPlayer.getPlayerCountries().size()/3);
            currentPlayer.setNumReinforceArmyRemainPlace(currentPlayer.getTotalNumReinforceArmy());
            if(currentPlayer.getNumReinforceArmyRemainPlace()==0) {
                System.out.println("You have 0 Reinforcement army! Please start Fortification phase");
                this.setPhase("Fortification");
                System.out.println("Phase> "+this.getPhase());
            } else {
                System.out.println(currentPlayer.getPlayerName() + " has " + currentPlayer.getNumReinforceArmyRemainPlace()+" reinforcement.");
            }
        } else if (countrySize==0) {
            System.out.println("Place all army failed! First add some countries.");
        } else if (numOfPlayers==0) {
            System.out.println("Place all army failed! First add some players.");
        } else {
            System.out.println("Place all army failed! First populate countries.");
        }
    } 
    /**
     * This method allows a player to reinforce
     */

    public void reinforce(String countryName, int number) {
        int armyLeft=currentPlayer.getNumReinforceArmyRemainPlace();
        if ((mapModel.indexOfCountry(countryName)!=-1)&&(number>=0)) {
            CountryModel country = mapModel.getCountryList().get(mapModel.indexOfCountry(countryName));
            if (armyLeft<number){
                System.out.println(" the number is too big!");
                return;
            }
            if (currentPlayer.getPlayerName().equals(country.getOwner().PlayerName)){
                if (armyLeft>=number){
                    armyLeft = armyLeft - number;
                    currentPlayer.setNumReinforceArmyRemainPlace(armyLeft);
                    country.setArmyNum(country.getArmyNum()+number);
                    System.out.println("Place Reinforcement Army Succeed! "+ currentPlayer.getPlayerName() + " left " + currentPlayer.getNumReinforceArmyRemainPlace());
                }
                if(armyLeft==0) {
                    System.out.println("You already place All your Reinforcement army! please start Fortification phase");
                    this.setPhase("Fortification");
                    System.out.println("Phase> "+this.getPhase());
                }
            }else {
                System.out.println("Not your country! please try again");
            }
        } else{
                System.out.println("Country name/number is not valid! please try again");
        }
    } 
    /**
     * This method allows a player to fortify a country with armies from another related country
     */

    public void fortify(String fromcountry, String tocountry, int number) {

        CountryModel sourceCountry= mapModel.getCountryList().get(mapModel.indexOfCountry(fromcountry));
        CountryModel targetCountry= mapModel.getCountryList().get(mapModel.indexOfCountry(tocountry));
        ArrayList<Boolean> visitedCountryList=new ArrayList<>();
        for (int i = 0; i < mapModel.getCountryList().size(); i++) {
            visitedCountryList.add(false);
        }
        //can't less than one army
        if ((sourceCountry.getArmyNum()-number)<1){
            System.out.println("the Army number is greater than the fromcountry number! Please try again");
            return;
        }
        if (sourceCountry.getOwner().getPlayerName().equals(targetCountry.getOwner().PlayerName)){
           if (!currentPlayer.getPlayerName().equals(sourceCountry.getOwner().getPlayerName())){
               System.out.println("the two countries are not belong to current player! Please try again");
               return;
           }
        }else{
            System.out.println("the two countries are not belong to same player! Please try again");
            return;
        }

        if (existPath(sourceCountry,targetCountry,visitedCountryList)){
            sourceCountry.setArmyNum(sourceCountry.getArmyNum()-number);
            targetCountry.setArmyNum(targetCountry.getArmyNum()+number);
            System.out.println("this path is validate, fortify succeed");
            this.fortifyNone();
        }else {
            System.out.println("this path is not validate");
        }

    } 
    /**
     * This method checks if a path exist between the two countries
     */

    public boolean existPath (CountryModel country1, CountryModel country2, ArrayList<Boolean> visited) {
        ArrayList<Integer> neighbours = country1.getNeighbours();
        for (Integer neighbour : neighbours) {
            if (visited.get(neighbour)) {
                continue;
            }
            visited.set(neighbour, true);
            CountryModel neighbourCountryModel = mapModel.getCountryList().get(neighbour);
            if (neighbourCountryModel.getOwner().getPlayerName().equals(country1.getOwner().getPlayerName())) {
                if (neighbour == country2.getCountryValue()) {
                    return true;
                }
                boolean b = existPath(neighbourCountryModel, country2, visited);
                if (b) {
                    return true;
                }
            }
        }
        return false;
    } 
    /**
     * This method fortifies no countries for the player
     */

    public void fortifyNone() {

        System.out.println(getCurrentPlayer().getPlayerName()+" Your turn over!");
        if (this.currentPlayerNum+1<this.playerList.size()){
            this.currentPlayerNum++;
            setCurrentPlayer(this.playerList.get(this.currentPlayerNum));
            //currentPlayer.setTotalNumArmy(this.playerList.size());
            currentPlayer.setNumArmyRemainPlace(currentPlayer.getNumArmyRemainPlace());
            this.setPhase("Startup");
            System.out.println("Phase> "+this.getPhase());
            System.out.println("Start Placing army, Current Player is "+ getCurrentPlayer().getPlayerName());
        }else{
            System.out.println("GAME END");
            exit(0);
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
    /**
     * This method returns the current Player
     */

    public PlayerModel getCurrentPlayer() {
        return currentPlayer;
    }
 /**
     * This method sets the current player
     */
    public void setCurrentPlayer(PlayerModel currentPlayer) {
        this.currentPlayer = currentPlayer;
    } 
    /**
     * This method  returns the phase
     */

    public String getPhase() {
        return phase;
    } 
    /**
     * This method sets the phase 
     */

    public void setPhase(String phase) {
        this.phase = phase;
    }
}
