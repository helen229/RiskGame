package GamePlayModel;

import GamePlayModel.ContinentModel;
import GamePlayModel.CountryModel;

import java.util.ArrayList;
import static java.lang.Integer.parseInt;

/**
 * This class control the Game board. It has:
 * @param mapName name of the map as string
 * @param totalCountries total number of countries in the map
 * @param playersList array list of players objects created by PlayerModel class
 * @param continentList array list of countinents objects created by ContinentModel class
 * @param countryList array list of countries  objects created by CountryModel class
 * @author Ehsan
 */

public class GameModel {
    
    private String mapName;
    public int totalCountries;
    private boolean isValid = true;
    
    private ArrayList<PlayerModel> playersList;
    private ArrayList<ContinentModel> continentList;
    private ArrayList<CountryModel> countryList;

    /** Constructor for Game board (GameModel) class. */
    public GameModel() {
        this.playersList = new ArrayList<PlayerModel>();
        this.continentList = new ArrayList<ContinentModel>();
        this.countryList = new ArrayList<CountryModel>();
    }

    public String getMapName() {
        return mapName;
    }

    public void setMapName(String mapName) {
        this.mapName = mapName;
    }
    
    public int getTotalCountries() {
        return this.countryList.size();
    }

    public void setTotalCountries(int totalCountries) {
        this.totalCountries = totalCountries;
    }
    
    public ArrayList<PlayerModel> getPlayersList() {
        return playersList;
    }
    
    public void setPlayersList(ArrayList<PlayerModel> playersList) {
        this.playersList = playersList;
    }
    
    public ArrayList<ContinentModel> getContinentList() {
        return continentList;
    }
    
    public void setContinentList(ArrayList<ContinentModel> continentList) {
        this.continentList = continentList;
    }
    
    public ArrayList<CountryModel> getCountryList() {
        return countryList;
    }

    public void setCountryList(ArrayList<CountryModel> countryList) {
        this.countryList = countryList;
    }
    
    /** Add the new player name to the playersList array list. After checking the current list.*/
    public boolean addPlayer(String playerName){    
        if(isAddPlayerValid(playerName)){
            PlayerModel player= new PlayerModel(playerName, this.playersList.size()+1, 0);
            this.playersList.add(player);
            return true;
        }
        return false;
    }
    
    /**
     * @return false if the name is available in the playersList, and print related message.
     * @return true if the playersList is empty or dosn't have the new player name.
     */
    public boolean isAddPlayerValid(String playerName) {
        if (this.playersList.isEmpty()){
            return true;
        }
        for (int i = 0; i < this.playersList.size(); i++){
            if(this.playersList.get(i).getPlayerName().equals(playerName)){
                System.out.println("Player Name already exist, please enter another one");
                return false;
            }
        }
        return true;
    }
    
    /**
     * @return the player value (ID, index) if the name is available in the playersList.
     * @return -1 if the name is not available in the playersList, or the playersList is empty.
     */
    private int indexOfPlayer(String playerName) {
        if (this.playersList.isEmpty()){
            return -1;
        }
        for (int i = 0; i < this.playersList.size(); i++){
            if(this.playersList.get(i).getPlayerName().equals(playerName)){
                return i;
            }
        }
        return -1;
    }
    
    /**
     * @return false if the name is not available in the playersList, and print related message.
     * @return true if the name is available in the playersList.
     */
    public boolean isRemovePlayerValid(String playerName) {
        if (indexOfPlayer(playerName)==-1){
            System.out.println("Player Name is not exist, please enter another one");
            return false;
        }
        return true;
    }
   
    // Make the ownership of the country free.
    public boolean freeCountry(String countryName){
        //Free the ownership
            return true;
    } 
    
    /**
     * @return false if the name is not removed from the playersList.
     * @return true if the name is removed from the playersList.
     */
    public boolean removePlayer(String playerName){
        if(isRemovePlayerValid(playerName)){
            PlayerModel player = this.playersList.get(indexOfPlayer(playerName));
            //remove the ownership
            for (int i = 0; i < player.getPlayerCountriesList().size(); i++){
                String countryName= player.getPlayerCountriesList().get(i);
                freeCountry(countryName);
            }
            this.playersList.remove(indexOfPlayer(playerName));
            return true;
        }
        return false;
    }
    
    /**
     * This method print all continents, countries data plus the ownerships and number of armies in them.
     */
    public void showMap(){
        System.out.println("\nAll Continents: ");
        for (int i = 0; i < this.continentList.size(); i++){
            printContinent(this.continentList.get(i));
        }

        System.out.println("\n\nAll Countries: ");
        for (int i = 0; i < this.countryList.size(); i++){
            printCountry(this.countryList.get(i));
        }
    }
    
    /**
     * This method will print all related data to the current continents list.
     * @param continent 
     */
    private void printContinent(ContinentModel continent){
        System.out.print(
                "Continent Name:" +
                continent.getContinentName() + "    " +
                "Continent Value:" +
                continent.getContinentValue() + "\n" );

        for (int i = 0; i < continent.getCountriesList().size(); i++){
            if (i==0)
                System.out.print("[");
            if (i == continent.getCountriesList().size()-1){
                System.out.print(continent.getCountriesList().get(i)+"]");
                break;
            }
            System.out.print(continent.getCountriesList().get(i)+",");
        }

    }
    
    private void printCountry(CountryModel country){
        if (country.getOwnerValue()!=-1)
            System.out.print(
                "Country Name:" +
                country.getCountryName() + "     " +
                "Country Value:" +
                country.getCountryValue() + "\n" +
                "From Continent:"+
                country.getContinentName()+ "\n" +
                "Country Owner:" + 
                getOwnerName(this.playersList.get(country.getOwnerValue()))  +  "     " +
                "Number of Armies:" + 
                country.getArmyNum()  +  "\n" +
                "Neighbor Country List: ");
        else
            System.out.print(
                "Country Name:" +
                country.getCountryName() + "     " +
                "Country Value:" +
                country.getCountryValue() + "\n" +
                "From Continent:"+
                country.getContinentName()+ "\n" +
                "Country Owner:" + 
                "None"  +  "     " +
                "Number of Armies:" + 
                country.getArmyNum()  +  "\n" +
                "Neighbor Country List: ");

        for (int i = 0; i < country.getNeighbours().size(); i++){
            int neighbourValue= country.getNeighbours().get(i);
            if (i==0)
                System.out.print("[");
            if (i==country.getNeighbours().size()-1){
                System.out.println(this.countryList.get(indexOfCountry(neighbourValue)).getCountryName()+"]\n");
                break;
            }
            System.out.print(this.countryList.get(indexOfCountry(neighbourValue)).getCountryName()+", ");
        }

    }
    
    public int indexOfCountry(int countryValue) {
        if (this.countryList.isEmpty())
            return -1;
        for (int i = 0; i < this.countryList.size(); i++){
            if(this.countryList.get(i).getCountryValue()==(countryValue)){
                return i;
            }
        }
        return -1;
    }
    
    
   public int indexOfCountryByName(String countryName) {
        if (this.countryList.isEmpty())
            return -1;
        for (int i = 0; i < this.countryList.size(); i++){
            if(this.countryList.get(i).getCountryName().equals(countryName)){
                return i;
            }
        }
        return -1;
    }
   
   public void placeArmy(String countryName){
       int ownerValue;
       int countryValue;
       if (isNotPopulated()){
           System.out.println("Country without owner, first run> populatecountries");
       }else {
           countryValue=indexOfCountryByName(countryName);
           if (countryValue!= (-1)){
               ownerValue=this.countryList.get(countryValue).getOwnerValue();
               if (this.playersList.get(ownerValue).getArmyNum()>0){
                   this.playersList.get(ownerValue).setArmyNum( this.playersList.get(ownerValue).getArmyNum()-1 );
                   this.countryList.get(countryValue).setArmyNum(this.countryList.get(countryValue).getArmyNum()+1);
                   System.out.println(this.playersList.get(ownerValue).getPlayerName()+" added 1 army to "+countryName);
                   System.out.println(countryName+ 
                       " has now "+
                       this.countryList.get(countryValue).getArmyNum()+
                       " armies. Remained unassigned armies="+
                       this.playersList.get(ownerValue).getArmyNum());
               } else {
                   System.out.println(this.playersList.get(ownerValue).getPlayerName()+" dosen't have more unassigned armies.");
               }
           }
       }
   }
   
   public void placeAll(){
       int selectedPlayerCountry;
       String selectedCountryName;
       if (isNotPopulated()){
           System.out.println("Country without owner, first run> populatecountries");
       }else {
           for (int ownerValue=0;ownerValue<this.playersList.size();ownerValue++) {
               ArrayList<String> contries = new ArrayList<>();
               contries = this.playersList.get(ownerValue).getPlayerCountriesList();
               for (int armyNum=this.playersList.get(ownerValue).getArmyNum(); armyNum>0; armyNum--){
                   selectedPlayerCountry=(int) Math.floor((Math.random() * contries.size()));
                   selectedCountryName=contries.get(selectedPlayerCountry);
                   placeArmy(selectedCountryName);
               }
           }
           System.out.println("All initial armies are distributed to countries randomly/manually.");
           for (int ownerValue=0;ownerValue<this.playersList.size();ownerValue++) {
               System.out.println("Number of new Reinforcement armies assigned to "+
                       this.playersList.get(ownerValue).getPlayerName()+ " is : " +
                       ((this.playersList.get(ownerValue).getPlayerCountriesList().size()/3)+5));
               this.playersList.get(ownerValue).setArmyNum( ((this.playersList.get(ownerValue).getPlayerCountriesList().size()/3)+5));
           }
       }
   }
   
   public void reinforce(String countryName, int armyNum){
       int ownerValue;
       int countryValue;
       if (isNotPopulated()){
           System.out.println("Country without owner, first run> populatecountries");
       }else {
           countryValue=indexOfCountryByName(countryName);
           if (countryValue!= (-1)){
               ownerValue=this.countryList.get(countryValue).getOwnerValue();
               if ((this.playersList.get(ownerValue).getArmyNum()>0) && (this.playersList.get(ownerValue).getArmyNum()>=armyNum)){
                   for (int i=armyNum;i>0;i--){
                       this.playersList.get(ownerValue).setArmyNum( this.playersList.get(ownerValue).getArmyNum()-1 );
                       this.countryList.get(countryValue).setArmyNum(this.countryList.get(countryValue).getArmyNum()+1);
                   }
                   System.out.println(this.playersList.get(ownerValue).getPlayerName()+" added "+
                           armyNum+
                           " army/armies to "+countryName);
                   System.out.println(countryName+ 
                       " has now "+
                       this.countryList.get(countryValue).getArmyNum()+
                       " armies. Remained unassigned armies="+
                       this.playersList.get(ownerValue).getArmyNum());
               } else {
                   System.out.println(this.playersList.get(ownerValue).getPlayerName()+" dosen't have more unassigned armies.");
               }
           }
       }
   }
   
   public void populateCountries(){
       System.out.println("Populate Countries:");
       if (isNotPopulated()){
           int initCountriesNum = this.countryList.size()/this.playersList.size();
           if (initCountriesNum==0){
               System.out.println("Error: Number of Players > Number of Countries"); 
           } else {
               int totalCountriesAssigned=0;
               int selectedCountry=0;
               int selectedOwner=-1;
               for (int j=0; j<initCountriesNum; j++)
                   for (int i=0; i<this.playersList.size();i++){
                       do{
                           selectedCountry=(int) Math.floor((Math.random() * this.countryList.size()));
                       }
                       while ((returnOwner(this.countryList.get(selectedCountry)))!=-1);
                       setOwner(this.countryList.get(selectedCountry),i);
                       setPlayerCountryOwned(this.playersList.get(i),this.countryList.get(selectedCountry));
                       totalCountriesAssigned++;
                   }
               while(totalCountriesAssigned<this.countryList.size()){
                   selectedOwner++;
                   do{
                       selectedCountry=(int) Math.floor((Math.random() * this.countryList.size()));
                   }
                   while ((returnOwner(this.countryList.get(selectedCountry)))!=-1);
                   setOwner(this.countryList.get(selectedCountry),selectedOwner);
                   setPlayerCountryOwned(this.playersList.get(selectedOwner),this.countryList.get(selectedCountry));
                   totalCountriesAssigned++;
               }
               for (int i=0; i<this.playersList.size();i++){
                   assigneArmy(this.playersList.get(i),(int) this.countryList.size()/3);
               }
               if (selectedOwner<0) selectedOwner=0;
               System.out.println("Assigned initial armies (total countries/3):"+(int) this.countryList.size()/3);
               System.out.println("Assigned initial countries (one army included)");
               System.out.println("Total number of countries: "+this.countryList.size());
               System.out.println("Total number of players: "+this.playersList.size());
               System.out.println("Minimum number of owned countries: " +initCountriesNum);
               System.out.println("Maximum number of owned countries: " +(initCountriesNum+selectedOwner));
           }
       } else {
           System.out.println("Map has been populated before.");
       }
   }
   
   private boolean isNotPopulated(){
       if (this.countryList.isEmpty())
            return false;

        for (int i = 0; i < this.countryList.size(); i++){
            if(this.countryList.get(i).getOwnerValue() == (-1)){
                return true;
            }
        }

       return false;
   }
   
   private void assigneArmy(PlayerModel player, int armyNum){
       player.setArmyNum(player.getArmyNum()+armyNum);
   }
   
   private int returnOwner(CountryModel country){
       return country.getOwnerValue();
   }
   
   private void setOwner(CountryModel country, int ownerValue){
       country.setOwnerValue(ownerValue);
       country.setArmyNum(country.getArmyNum()+1);
   }
   
   private void setPlayerCountryOwned(PlayerModel player, CountryModel country){
       player.addCountryToListPlayer(country.getCountryName());
   }
   
   private String getOwnerName(PlayerModel player){
       return player.getPlayerName();
   }
}
