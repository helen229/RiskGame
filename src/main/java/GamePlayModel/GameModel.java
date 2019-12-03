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
public class GameModel extends Observable {

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
//        this.currentPlayerNum=0;
//        this.currentExchangeTry=1;
        this.currentPlayerNum=builder.currentPlayerNum;
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
     * This method assigns countries to the players. And first check inputs.
     */
    public void populateCountries() {

        int numOfPlayers= this.getNumOfPlayers();
        ArrayList<CountryModel> countries = mapModel.getCountryList();
        int countrySize = countries.size();
        if ((isNotPopulated()) && (countrySize>0) && (numOfPlayers>=2) && (numOfPlayers<=6)) {
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
                    playerList.get(i).distributeTotalNumArmy(this.playerList.size());
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
        if ((!(isNotPopulated())) && (countrySize>0) && (numOfPlayers>=2) && (numOfPlayers<=6)) {
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
                        if (this.currentPlayerNum+1<this.playerList.size()){
                            this.currentPlayerNum++;
                            setCurrentPlayer(this.playerList.get(this.currentPlayerNum));
                            System.out.println("Now is "+currentPlayer.getPlayerName()+" start to place army!");
                        }else{
                            this.currentPlayerNum = 0;
                            setCurrentPlayer(playerList.get(currentPlayerNum));
                            startReinforcement();
                            System.out.println("Current player is "+currentPlayer.getPlayerName());
                            gameStart();
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
        } else if (numOfPlayers<2) {
            System.out.println("Place army failed! First add some players.");
        } else if (numOfPlayers>6) {
            System.out.println("Populate countries failed! First remove some players.");
        } else {
            System.out.println("Place army failed! First populate countries.");
        }
    }

    //for single mode
    public void singleAutoPlay() {

        if (phase.equals("Reinforcement") && gameStopFlag == false) {
            getCurrentPlayer().getStrategy().reinforcement();
            setPhase("Attack");
        }

        if (phase.equals("Attack") && gameStopFlag == false) {
            System.out.println(getCurrentPlayer().getPlayerName()+ " Start to Attack");
            getCurrentPlayer().getStrategy().attack();
            setPhase("Fortification");
        }

        if (phase.equals("Fortification") && gameStopFlag == false) {
            getCurrentPlayer().getStrategy().fortification();
        }

    }


    public void gameStart() {
        if (getCurrentPlayer().getStrategy().getName().equals("Human")) {
            return;
        } else {
            singleAutoPlay();
        }
    }
    /**
     * This method reinforce armies after checking the input.
     */
    public void startReinforcement() {
        currentPlayer.setTotalNumReinforceArmy(currentPlayer.getPlayerContinents().size()+(currentPlayer.getPlayerCountries().size()/3));
//        if (currentPlayer.getTotalNumReinforceArmy()<3) currentPlayer.setTotalNumReinforceArmy(3);
        currentPlayer.setNumReinforceArmyRemainPlace(currentPlayer.getTotalNumReinforceArmy());
        System.out.println(currentPlayer.getPlayerName()+" You already place All your army! please start Reinforcement phase");
        this.setPhase("Reinforcement");
        System.out.println("Phase> "+this.getPhase());
        if (currentPlayer.getCardList().size()!=0) {
            //show the card view in the beginning of the Reinforcement
            setChanged();
            notifyObservers("CardsView");
        }
        if((currentPlayer.getNumReinforceArmyRemainPlace()==0)&&(currentPlayer.getCardList().size()<3)) {
            System.out.println("You have 0 Reinforcement army!");
            System.out.println("You have "+currentPlayer.getCardList().size()+" Card(s)! Please start Attack phase");
            this.setPhase("Attack");
            System.out.println("Phase> "+this.getPhase());
            if (!checkAttackChance()){
                stopAttack();
            }
            //show the domin view in the beginning of the Attack
            setChanged();
            notifyObservers("DominView");
        } else {
            if (!(getCurrentPlayer().getStrategy().getName().equals("Cheater"))) {
            System.out.println(currentPlayer.getPlayerName() + " has " + currentPlayer.getNumReinforceArmyRemainPlace()+" reinforcement.");
            }
        }

    }

    /**
     * This method allows a player to place armies after checking the status of the game
     */

    public void placeAllAmy() {
        int numOfPlayers= this.getNumOfPlayers();
        ArrayList<CountryModel> countries = mapModel.getCountryList();
        int countrySize = countries.size();
        if ((!(isNotPopulated())) && (countrySize>0) && (numOfPlayers>=2) && (numOfPlayers<=6)) {
            for (PlayerModel player:playerList) {
                int armyLeft=player.getNumArmyRemainPlace();
                while (armyLeft>0){
                    int randomNum=(int)(Math.random() * (player.playerCountries.size()));
                    player.playerCountries.get(randomNum).addArmyNum();
                    armyLeft--;
                }
                player.setNumArmyRemainPlace(0);
            }
            this.currentPlayerNum = 0;
            setCurrentPlayer(playerList.get(currentPlayerNum));
            System.out.println("Current player is "+currentPlayer.getPlayerName());
            startReinforcement();
            gameStart();
        } else if (countrySize==0) {
            System.out.println("Place all army failed! First add some countries.");
        } else if (numOfPlayers<2) {
            System.out.println("Place all army failed! First add some players.");
        } else if (numOfPlayers>6) {
            System.out.println("Populate countries failed! First remove some players.");
        } else {
            System.out.println("Place all army failed! First populate countries.");
        }
    }

    public void tournament(ArrayList<String> mapList, ArrayList<String> playerStrategyList, int numberOfGames, int maxNumberOfTurns) {

        setGameMode("Tournament");
        setMaxNumberOfTurns(maxNumberOfTurns);
        this.tournamentMaps = mapList;
        for (String map: mapList) {
            gameWinner=" ";
            ArrayList<String> gameResultList = new ArrayList<String>();
            int gameNum = numberOfGames;
            while(gameNum>0){
                loadMap(map);
                for (int i = 0; i< playerStrategyList.size(); i++) {
                    addPlayer("Player"+i, playerStrategyList.get(i));
                }
                populateCountries();
                placeAllAmy();
                gameResultList.add(gameWinner);
                gameNum--;
                for (int i = 0; i< playerStrategyList.size(); i++) {
                    removePlayer("Player"+i);
                }
                mapModel = new MapModel();
                NumofTurns = 1;
                gameEnd = false;
                System.out.println("****************************************");
                System.out.println("****************************************");
                System.out.println("****************************************");
                System.out.println("****************************************");

            }
            this.tournamentResult.add(gameResultList);
        }
        System.out.println(this.tournamentResult);
        printTournmentResult();
        exit(0);
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
                    currentPlayer.addArmyNum(number);
                    country.setArmyNum(country.getArmyNum()+number);
                    System.out.println("Place Reinforcement Army Succeed! "+ currentPlayer.getPlayerName() + " left " + currentPlayer.getNumReinforceArmyRemainPlace());
                }
                if(armyLeft==0) {
                    System.out.println("You already place All your Reinforcement army!");
                    currentPlayer.setTotalNumReinforceArmy(0);
                    if (currentPlayer.getCardList().size()<3){
                        System.out.println("You have "+currentPlayer.getCardList().size()+" Card(s)! Please start Attack phase");
                        this.setPhase("Attack");
                        System.out.println("Phase> "+this.getPhase());
                        if (!checkAttackChance()){
                            stopAttack();
                        }
                        //show the domin view in the beginning of the Attack
                        setChanged();
                        notifyObservers("DominView");
                    }
                }
            }else {
                System.out.println("Not your country! please try again");
            }
        } else{
            System.out.println("Country name/number is not valid! please try again");
        }

    }

    /**
     * This method allows players to exchange their cards to armies.
     */
    public void exchangeCards(int cardOne, int cardTwo, int cardThree){
        int firstCard=0;
        int secodnCard=0;
        int thirdCard=0;
        if (currentPlayer.getCardList().size()>=3)
            if ((cardOne>=0)&&(cardOne<=currentPlayer.getCardList().size())&&
                    (cardTwo>=0)&&(cardTwo<=currentPlayer.getCardList().size())&&
                    (cardThree>=0)&&(cardThree<=currentPlayer.getCardList().size())&&
                    (cardOne!=cardThree)&&(cardOne!=cardTwo)&&(cardTwo!=cardThree)){
                currentPlayer.setTotalNumReinforceArmy(currentPlayer.getTotalNumReinforceArmy()+currentExchangeTry*5);

                ArrayList<Card> cards = currentPlayer.getCardList();

                System.out.println("You echanged your "+(cards.get(cardOne).getCardType())+" card.");
                System.out.println("You echanged your "+(cards.get(cardTwo).getCardType())+" card.");
                System.out.println("You echanged your "+(cards.get(cardThree).getCardType())+" card.");

                if ((cardOne>cardTwo)&&((cardOne>cardThree)))
                    thirdCard=cardOne;
                else if ((cardTwo>cardOne)&&((cardTwo>cardThree)))
                    thirdCard=cardTwo;
                else if ((cardThree>cardOne)&&((cardThree>cardTwo)))
                    thirdCard=cardThree;

                if ((cardOne<cardTwo)&&((cardOne<cardThree)))
                    firstCard=cardOne;
                else if ((cardTwo<cardOne)&&((cardTwo<cardThree)))
                    firstCard=cardTwo;
                else if ((cardThree<cardOne)&&((cardThree<cardTwo)))
                    firstCard=cardThree;

                if ((cardOne!=firstCard)&&((cardOne!=thirdCard)))
                    secodnCard=cardOne;
                else if ((cardTwo!=firstCard)&&((cardTwo!=thirdCard)))
                    secodnCard=cardTwo;
                else if ((cardThree!=firstCard)&&((cardThree!=thirdCard)))
                    secodnCard=cardThree;

                currentPlayer.removeCard(cards.get(thirdCard));
                currentPlayer.removeCard(cards.get(secodnCard));
                currentPlayer.removeCard(cards.get(firstCard));

                System.out.println("You recived "+(getCurrentExchangeTry()*5)+" new Reinforcement armies.");
                System.out.println(currentPlayer.getPlayerName() + " has now " + currentPlayer.getNumReinforceArmyRemainPlace()+" Reinforcement armies.");
                setCurrentExchangeTry(getCurrentExchangeTry()+1);
                setChanged();
                notifyObservers("CardsView");
            }
            else {
                System.out.println("Invalid input number as card identifier.");
            }

        else {
            System.out.println("You do not have enough cards.");
        }
    }

    /**
     * This method allows to skipping the exchanging cards process if they are less than 5
     */
    public void exchangeCardsNone(){
        if (currentPlayer.getCardList().size()>=5) {
            System.out.println(currentPlayer.getPlayerName()+" has "+ currentPlayer.getCardList().size() + " cards. Exchange cards is a must.");
        } else {
            this.setPhase("Attack");
            System.out.println("Phase> "+this.getPhase());
            if (!checkAttackChance()){
                stopAttack();
            }
            //show the domin view in the beginning of the Attack
            setChanged();
            notifyObservers("DominView");
        }

    }

    public boolean checkAttackChance() {
        boolean ifAttackContinue = false;
        for (CountryModel playerCountry : currentPlayer.getPlayerCountries()) {
            if (playerCountry.getArmyNum()<2){
                continue;
            }else if (!ifCountriesBelongPlayer(playerCountry.getNeighbours())){
                ifAttackContinue = true;
            }
        }
        return ifAttackContinue;
    }


    public boolean ifCountriesBelongPlayer(ArrayList<CountryModel> countryList) {
        for (CountryModel country:countryList) {
            if (!currentPlayer.equals(country.getOwner())){
                return false;
            }
        }
        return true;
    }

    public boolean attackDiceNum(String attackCountryName, String defendCountryName, int diceNum, boolean fromAllOut) {
        if ((mapModel.indexOfCountry(attackCountryName)!=-1)&&(mapModel.indexOfCountry(defendCountryName)!=-1)) {
            CountryModel defendCountry= mapModel.getCountryList().get(mapModel.indexOfCountry(defendCountryName));
            CountryModel attackCountry= mapModel.getCountryList().get(mapModel.indexOfCountry(attackCountryName));
            PlayerModel defender=defendCountry.getOwner();
            PlayerModel attacker=attackCountry.getOwner();
            //make sure only the winner move command will be run after player conquered one country
            if (ifAttackerWin){
                System.out.println("Please move the army first");
                return false;
            }
            //check if the attack country belong to the current player
            if (!currentPlayer.equals(attacker)){
                System.out.println("The attack Country is not belong to the attacker!");
                return false;
            }
            //check if the same owner
            if (defender.equals(attacker)){
                System.out.println("The defend Country is belong to the attacker!");
                return false;
            }
            //check if attack country more than one army
            if (attackCountry.getArmyNum()<2){
                System.out.println("The attack country must more than one army!");
                return false;
            }
            //check if defend country is an adjacent country
            if (!attackCountry.getNeighbours().contains(defendCountry)){
                System.out.println("The defend Country is not an adjacent country to the attacker!");
                return false;
            }
            if (diceNum<1 || diceNum>3 || diceNum>(attackCountry.getArmyNum()-1)){
                System.out.println("The dice number is invalid");
                return false;
            }

            attackerDice = generateDiceNum(diceNum);
            this.defenderCountry = defendCountry;
            this.attackerCountry = attackCountry;
            System.out.println("Attack declare Valid! "+defender.getPlayerName() + " please set up your dice number");
            if (fromAllOut){
                diceNum = 0;
                if (defenderCountry.getArmyNum()>1){
                    diceNum = 2;
                }else if (defenderCountry.getArmyNum()==1){
                    diceNum = 1;
                }
                defendDiceNum(diceNum);
            }
            return true;
        } else {
            System.out.println("Country name is not valid! please try again");
            return false;
        }
    }

    public void defendDiceNum(int diceNum) {

        //make sure only the winner move command will be run after player conquered one country
        if (ifAttackerWin){
            System.out.println("Please move the army first");
            return;
        }

        if (attackerDice.isEmpty()||this.attackerCountry==null){
            System.out.println("Please declare the attacker dice number first");
        }else if (defenderCountry.getArmyNum()>=diceNum && diceNum<3  && diceNum>0){
            attackProcess(generateDiceNum(diceNum));
        }else {
            System.out.println("The dice number is invalid");
        }

    }

    public void attackAllOut(String attackCountryName, String defendCountryName) {
        if ((mapModel.indexOfCountry(attackCountryName)!=-1)&&(mapModel.indexOfCountry(defendCountryName)!=-1)) {

            CountryModel attackCountry= mapModel.getCountryList().get(mapModel.indexOfCountry(attackCountryName));
            int diceNum=0;
            while (!ifAttackerWin){
                if (attackCountry.getArmyNum()>3){
                    diceNum=3;
                }else if (attackCountry.getArmyNum()==3){
                    diceNum=2;
                }else if (attackCountry.getArmyNum()==2){
                    diceNum=1;
                }else{
                    System.out.println("The attack country can't attack!");
                    return;
                }
                if (!attackDiceNum(attackCountryName,defendCountryName, diceNum, true))
                    break;
            }
        } else
        {
            System.out.println("Country name is not valid! please try again");
        }

    }

    public void attackProcess(ArrayList<Integer> defenderDice) {

        System.out.println("Attacker Dice: "+attackerDice);
        System.out.println("Defender Dice: "+defenderDice);
        //compare the two list
        if (attackerDice.get(0) - defenderDice.get(0) > 0) {
            //remove the certain amount army of the loser
            defenderCountry.reduceArmyNum();
            defenderCountry.getOwner().reduceArmyNum(1);
            System.out.println("Attacker won the first round dice competition, "
                    + defenderCountry.getCountryName() + " left "
                    + defenderCountry.getArmyNum() + " Armies");
        } else {
            attackerCountry.reduceArmyNum();
            attackerCountry.getOwner().reduceArmyNum(1);
            System.out.println("Defender won the first round dice competition, "
                    + attackerCountry.getCountryName()  + " left "
                    + attackerCountry.getArmyNum() + " Armies");
        }
        ifAttackerWin = attackResult();
        if (attackerDice.size()>1 && defenderDice.size()>1){

            if (attackerDice.get(1) - defenderDice.get(1) > 0) {
                //remove the certain amount army of the loser
                defenderCountry.reduceArmyNum();
                defenderCountry.getOwner().reduceArmyNum(1);
                System.out.println("Attacker won the second round dice competition, "
                        + defenderCountry.getCountryName() + " left "
                        + defenderCountry.getArmyNum() + " Armies");
            } else {
                attackerCountry.reduceArmyNum();
                attackerCountry.getOwner().reduceArmyNum(1);
                System.out.println("Defender won the second round dice competition, "
                        + attackerCountry.getCountryName()  + " left "
                        + attackerCountry.getArmyNum() + " Armies");
            }
            ifAttackerWin = attackResult();
        }

        if (ifAttackerWin){
            if(gameEnd)
                return;
            //if attacker wins, set to true and start the attackmove
            System.out.println("Attacker take over the country! Please start move army!");
            hasPlayerConquered = true;
        }else {
            System.out.println("Attack done");
            if (!checkAttackChance())
                stopAttack();
            this.defenderCountry = null;
            this.attackerCountry = null;
            attackerDice.isEmpty();

        }

    }

    public boolean attackResult() {
        boolean attackerWin = false;
        PlayerModel attacker = attackerCountry.getOwner();
        PlayerModel defender = defenderCountry.getOwner();

        //if the attacker conquer the country
        if (defenderCountry.getArmyNum() == 0) {
            attackerWin = true;
            //remove the country from defender's country list
            defender.getPlayerCountries().remove(defenderCountry);
            //change country's owner
            defenderCountry.setOwner(attacker);
            //add the country to attacker's country list
            attacker.getPlayerCountries().add(defenderCountry);
            //check if the attacker owns all countries,if yes, then game finished.
            if (attacker.getPlayerCountries().size() == mapModel.getCountryList().size()) {
                System.out.println("GAME END! " + attacker.getPlayerName()+ " WIN");
                if (gameMode.equals("Single")){
                    exit(0);
                }else {
                    this.gameEnd = true;
                    gameWinner = attacker.getStrategy().getName();
                    System.out.println("---------------------------------------GAME END");
                    return attackerWin;
                }
            }
        }
        if (attackerCountry.getArmyNum() == 1){
            System.out.println(attackerCountry.getCountryName()+" can't attack anymore!");
            //show the domin view in the end when attack done
            setChanged();
            notifyObservers("DominView");
        }
        return attackerWin;
    }

    public void winnerMove(int num) {

        if (!ifAttackerWin){
            System.out.println("Invalid command");
            return;
        }
        else if (num > attackerCountry.getArmyNum()-1 || num < attackerDice.size()) {
            System.out.println("the army number should between "+attackerDice.size()+
                    " to " + (attackerCountry.getArmyNum()-1));
            return;
        }
        defenderCountry.addArmyNum(num);
        attackerCountry.reduceArmyNum(num);
        System.out.println("Moved the "+ num +" Armies to conquered Country");
        if (!checkAttackChance())
            stopAttack();
        ifAttackerWin=false;
        this.defenderCountry = null;
        this.attackerCountry = null;
        //show the domin view in the end when a country conquered
        setChanged();
        notifyObservers("DominView");
    }


    public void stopAttack(){
        //make sure only the winner move command will be run after player conquered one country
        if (ifAttackerWin){
            System.out.println("Please move the army first");
            return;
        }
        //set the to the next phase
        System.out.println("Attack Phase Done! please start Fortification phase");
        this.setPhase("Fortification");
    }


    /**
     * Generate the random dice list of one player, can be either attacker or defender
     * And the dice list would be sorted in ascending order
     *
     * @param diceNum is the number of dices they choose
     * @return ArrayList<Integer>  a list of dices
     */
    public ArrayList<Integer> generateDiceNum(int diceNum) {
        ArrayList<Integer> diceList = new ArrayList<>();
        int randomNumber;
        //generate a random number for each dice in the dice list
        for (int i = 0; i < diceNum; i++) {
            randomNumber = new Random().nextInt(6) + 1;
            diceList.add(randomNumber);
        }
        //making it be in a descending order
        Collections.sort(diceList, Collections.reverseOrder());
        return diceList;
    }

    /**
     * This method allows a player to fortify a country with armies from another related country
     */
    public void fortify(String fromcountry, String tocountry, int number) {
        if ((mapModel.indexOfCountry(fromcountry)!=-1)&&(mapModel.indexOfCountry(tocountry)!=-1)&&(number>=0)) {

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
        }else {
            System.out.println("Country name/number is not valid! please try again");
        }
    }
    /**
     * This method checks if a path exist between the two countries
     */

    public boolean existPath (CountryModel country1, CountryModel country2, ArrayList<Boolean> visited) {
        ArrayList<CountryModel> neighbours = country1.getNeighbours();
        for (CountryModel neighbour : neighbours) {
            if (visited.get(neighbour.getCountryValue())) {
                continue;
            }
            visited.set(neighbour.getCountryValue(), true);
            CountryModel neighbourCountryModel = neighbour;
            if (neighbourCountryModel.getOwner().getPlayerName().equals(country1.getOwner().getPlayerName())) {
                if (neighbour.getCountryValue() == country2.getCountryValue()) {
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
     *
     * This method fortifies no countries for the player
     */
    public void fortifyNone() {

        if (gameEnd)
            return;

        if (hasPlayerConquered){
            System.out.println(getCurrentPlayer().getPlayerName()+" You have conquered at least one country!");
            Card card = new Card(currentPlayer);
            CardType cardType = card.getCardType();
            currentPlayer.addCard(card);
            System.out.println("You receives a "+cardType+"card");
            hasPlayerConquered = false;
        }

        System.out.println(getCurrentPlayer().getPlayerName()+" Your turn over!");

        if (this.currentPlayerNum+1==this.playerList.size()){
            this.currentPlayerNum = 0;
            NumofTurns++;
            if (NumofTurns >= maxNumberOfTurns&&gameMode.equals("Tournament")){
                gameWinner = "Draw";
                System.out.println("DRAW!!!!!!");
                return;
            }
        }else
            this.currentPlayerNum++;

        while (this.playerList.get(this.currentPlayerNum).playerCountries.size()==0){
            if (this.currentPlayerNum+1==this.playerList.size()){
                this.currentPlayerNum = 0;
                NumofTurns++;
                if ((NumofTurns >= maxNumberOfTurns)&&(gameMode.equals("Tournament"))){
                    gameWinner = "Draw";
                    System.out.println("DRAW!!!!!!");
                    return;
                }
            }else
                this.currentPlayerNum++;
        }

        setCurrentPlayer(this.playerList.get(this.currentPlayerNum));
        startReinforcement();
        System.out.println("Current is in " +NumofTurns+" Turns");
        gameStart();

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


    public void saveGame(String fileName) throws IOException {
        File file = new File(fileName);

        if (file.createNewFile())
        {
            System.out.println("File is created!");
        } else {
            System.out.println("File already exists.");
        }

        //Write Content
        FileWriter writer = new FileWriter(file);
        String content = "\n";
        content += "[Map]"+"\n";
        content += mapModel.getMapName()+"\n";
        content += "\n";
        content += "[PlayerList]"+"\n";
        for (PlayerModel player:this.playerList) {
            content += player.toString()+"\n";
        }
        content += "\n";
        content += "[CountryList]"+"\n";
        for (CountryModel country:mapModel.getCountryList()) {
            content += country.toString()+"\n";
        }
        content += "\n";
        content += "[GameState]"+"\n";
        content += "currentPlayer "+this.currentPlayer.getPlayerName()+"\n"+
                "currentPlayerNum "+this.currentPlayerNum+"\n"+
                "currentExchangeTry "+this.currentExchangeTry+"\n"+
                "phase "+this.phase+"\n"+
                "ifAttackerWin "+this.ifAttackerWin+"\n"+
                "hasPlayerConquered "+this.hasPlayerConquered+"\n"+
                "NumofTurns "+this.NumofTurns+"\n"+
                "gameMode "+this.gameMode+"\n";
        writer.write(content);
        writer.close();
    }

    public void loadGame(String fileName) throws IOException {
        GameLoad gameLoad = new GameLoad(this);
        gameLoad.Loading(fileName);
    }

    /**
     * print continent
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
     * print country
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
            CountryModel neighbour = country.getNeighbours().get(i);
            if (i==0)
                System.out.print("[");
            if (i==country.getNeighbours().size()-1){
                System.out.println(neighbour.getCountryName()+"]\n");
                break;
            }
            System.out.print(neighbour.getCountryName()+", ");
        }

    }


    private void printTournmentResult(){
        ArrayList<ArrayList<String>> tournamentResult= this.tournamentResult;
        ArrayList<String> tournamentMaps=this.tournamentMaps;
        int numberOfGames=tournamentResult.get(0).size();
        int numberOfMaps=tournamentMaps.size();
        String Format=" ";
        switch(numberOfGames) {
            case 1:
                Format = "| %-4s%-1d | %-10s |%n";
                System.out.format("+-------+------------+%n");
                System.out.format("| Map # |   Game 1   |%n");
                System.out.format("+-------+------------+%n");
                for (int i = 0; i < numberOfMaps; i++) {
                    System.out.format(Format, "Map ", i+1,
                            tournamentResult.get(i).get(0));
                }
                System.out.format("+-------+------------+%n");
                break;
            case 2:
                Format = "| %-4s%-1d | %-10s | %-10s |%n";
                System.out.format("+-------+------------+------------+%n");
                System.out.format("| Map # |   Game 1   |   Game 2   |%n");
                System.out.format("+-------+------------+------------+%n");
                for (int i = 0; i < numberOfMaps; i++) {
                    System.out.format(Format, "Map ", i+1,
                            tournamentResult.get(i).get(0),
                            tournamentResult.get(i).get(1));
                }
                System.out.format("+-------+------------+------------+%n");
                break;
            case 3:
                Format = "| %-4s%-1d | %-10s | %-10s | %-10s |%n";
                System.out.format("+-------+------------+------------+------------+%n");
                System.out.format("| Map # |   Game 1   |   Game 2   |   Game 3   |%n");
                System.out.format("+-------+------------+------------+------------+%n");
                for (int i = 0; i < numberOfMaps; i++) {
                    System.out.format(Format, "Map ", i+1,
                            tournamentResult.get(i).get(0),
                            tournamentResult.get(i).get(1),
                            tournamentResult.get(i).get(2));
                }
                System.out.format("+-------+------------+------------+------------+%n");
                break;
            case 4:
                Format = "| %-4s%-1d | %-10s | %-10s | %-10s | %-10s |%n";
                System.out.format("+-------+------------+------------+------------+------------+%n");
                System.out.format("| Map # |   Game 1   |   Game 2   |   Game 3   |   Game 4   |%n");
                System.out.format("+-------+------------+------------+------------+------------+%n");
                for (int i = 0; i < numberOfMaps; i++) {
                    System.out.format(Format, "Map " , i+1,
                            tournamentResult.get(i).get(0),
                            tournamentResult.get(i).get(1),
                            tournamentResult.get(i).get(2),
                            tournamentResult.get(i).get(3));
                }
                System.out.format("+-------+------------+------------+------------+------------+%n");
                break;
            case 5:
                Format = "| %-4s%-1d | %-10s | %-10s | %-10s | %-10s | %-10s |%n";
                System.out.format("+-------+------------+------------+------------+------------+------------+%n");
                System.out.format("| Map # |   Game 1   |   Game 2   |   Game 3   |   Game 4   |   Game 5   |%n");
                System.out.format("+-------+------------+------------+------------+------------+------------+%n");
                for (int i = 0; i < numberOfMaps; i++) {
                    System.out.format(Format, "Map " , i+1,
                            tournamentResult.get(i).get(0),
                            tournamentResult.get(i).get(1),
                            tournamentResult.get(i).get(2),
                            tournamentResult.get(i).get(3),
                            tournamentResult.get(i).get(4));
                }
                System.out.format("+-------+------------+------------+------------+------------+------------+%n");
                break;
            default:
        }
    }

    /**
     * This method sets the Current Exchange Try number
     */
    public void setCurrentExchangeTry (int currentExchangeTry){
        this.currentExchangeTry = currentExchangeTry;
    }

    /**
     * This method returns the Current Exchange Try number
     */
    public int getCurrentExchangeTry (){
        return currentExchangeTry;
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
     * @param phase
     */
    public void setPhase(String phase) {
        this.phase = phase;
        setChanged();
        notifyObservers("PhaseView");
    }

    public void setMaxNumberOfTurns(int maxNumberOfTurns) {
        this.maxNumberOfTurns = maxNumberOfTurns;
    }

    public void setGameMode(String gameMode) {
        this.gameMode = gameMode;
    }
    public String getGameMode() {
        return gameMode;
    }

    public void setGameEnd(boolean gameEnd){
        this.gameEnd = gameEnd;
    }

    public void setGameWinner(String gameWinner) {
        this.gameWinner=gameWinner;
    }
    public MapModel getMapModel() {
        return mapModel;
    }

    public ArrayList<PlayerModel> getPlayerList() {
        return playerList;
    }

    public boolean isIfAttackerWin() {
        return ifAttackerWin;
    }

    public boolean isGameStopFlag() {
        return gameStopFlag;
    }

    public boolean isHasPlayerConquered() {
        return hasPlayerConquered;
    }

    /**
     * The builder class.
     */
    public static class Builder {

        int currentPlayerNum;
        int currentExchangeTry;

        public Builder currentPlayerNum(int currentPlayerNum) {
            this.currentPlayerNum = currentPlayerNum;
            return this;
        }

        public Builder currentExchangeTry(int currentExchangeTry) {
            this.currentExchangeTry = currentExchangeTry;
            return this;
        }

        public GameModel build()
        {
            return new GameModel(this);
        }
    }

}

