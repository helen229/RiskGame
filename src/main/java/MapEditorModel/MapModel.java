package MapEditorModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class MapModel {

    /** The conquest map name. */
    private String mapName;

    /** The total countries. */
    public int totalCountries;

    /** The continent list. */
    private ArrayList<ContinentModel> continentList;

    /** The country list. */
    private ArrayList<CountryModel> countryList;

//    /** The map valid or not */
//    private boolean isValid = true;

    /**
     * Constructor for the MapModel
     */
    public MapModel() {
        this.continentList = new ArrayList<ContinentModel>();
        this.countryList = new ArrayList<CountryModel>();
    }

    /**
     * Method for the command show map
     */
    public void showMap(){
        System.out.println("Continents: ");

        for (int i = 0; i < this.continentList.size(); i++){
            printContinent(this.continentList.get(i));
            System.out.println("\n");
        }

        System.out.println("\nAll Countries: ");
        for (int i = 0; i < this.countryList.size(); i++){
            printCountry(this.countryList.get(i));
        }
    }

    /**
     * Check if the map is valid, if it is then return true
     * @return is valid or not
     */
    public boolean isValid(){
        boolean isValid = true;
        String invalidContinent ="";
        if (continentList.isEmpty()||countryList.isEmpty()||countryList.size()==1){
            System.out.println("Map not validate");
            isValid = false;
            return isValid;
        }
        //validate the continent
        for (ContinentModel continent:this.continentList) {

            if (continent.getCountriesList().size()>0){
                String countryName = continent.getCountriesList().get(0);
                CountryModel country= countryList.get(indexOfCountry(countryName));
                ArrayList<Boolean> visitedCountryList=new ArrayList<Boolean>();
                for (String temp: continent.getCountriesList()) {
                    visitedCountryList.add(false);
                }
                dfs(country,visitedCountryList, continent.getCountriesList());
                for (Boolean res:visitedCountryList) {
                    if (!res){
                        isValid=false;
                        invalidContinent = continent.getContinentName();
                        break;
                    }
                }

            }else{
                System.out.println("Continent can't be empty");
                isValid=false;
            }

        }
        //validate the map
        ArrayList<CountryModel> visited=new ArrayList<>();
        ifAllCountryConnected(this.countryList.get(0),visited);
        if (visited.size() != this.countryList.size()){
            isValid = false;
            invalidContinent = "not all countries connected,";
        }

        if (isValid)
            System.out.println("Map is valid");
        else
            System.out.println(invalidContinent+" is not valid");

       return isValid;
    }

    /**
     * A dfs method to help validate the map if Continent Country Connected
     * @param country
     * @param visitedCountryList
     */
    public void dfs(CountryModel country, ArrayList<Boolean> visitedCountryList, ArrayList<String> countryList){

        if (!countryList.contains(country.getCountryName()))
            return;

        int index = countryList.indexOf(country.getCountryName());

        if (visitedCountryList.get(index)){
            return;
        }else {
            visitedCountryList.set(index,true);
        }

        if (country.getNeighbours().size()==0)
            return;

        for (CountryModel neighbourCountry : country.getNeighbours()){
            dfs(neighbourCountry, visitedCountryList, countryList);
        }

        return;
    }

    /**
     * A dfs method to help validate the map
     * @param country
     * @param visitedCountryList
     */
    public void ifAllCountryConnected(CountryModel country, ArrayList<CountryModel> visitedCountryList){

        if (visitedCountryList.contains(country)){
            return;
        }else {
            visitedCountryList.add(country);
        }

        if (country.getNeighbours().size()==0)
            return;

        for (CountryModel neighbourCountry : country.getNeighbours()){
            ifAllCountryConnected(neighbourCountry, visitedCountryList);
        }

        return;
    }

    /**
     * the method for printout the Continent content
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
                System.out.print(continent.getCountriesList().get(i)+"]");
                break;
            }
            System.out.print(continent.getCountriesList().get(i)+",");
        }

    }

    /**
     * the method for printout the Country content
     * @param country
     */
    private void printCountry(CountryModel country){

        System.out.print(
                "Country Name:" +
                country.getCountryName() + "\n" +
                "Country Value:" +
                country.getCountryValue() + "\n" +
                "Neighbor Country List: ");
        if (country.getNeighbours().isEmpty()){
            System.out.print("[]\n\n");
        }
        for (int i = 0; i < country.getNeighbours().size(); i++){
            CountryModel neighbour= country.getNeighbours().get(i);
            if (i==0)
                System.out.print("[");
            if (i==country.getNeighbours().size()-1){
                System.out.println(neighbour.getCountryName()+"]\n");
                break;
            }
            System.out.print(neighbour.getCountryName()+", ");
        }

    }

    /**
     * get the map name
     * @return the map Name
     */
    public String getMapName() {
        return mapName;
    }

    /**
     *  set up the map name
     * @param mapName
     */
    public void setMapName(String mapName) {
        this.mapName = mapName;
    }

    /**
     * get totalCountries size
     * @return size
     */
    public int getTotalCountries() {
        return this.countryList.size();
    }

    /**
     * set totalCountries number
     * @param totalCountries
     */
    public void setTotalCountries(int totalCountries) {
        this.totalCountries = totalCountries;
    }

    /**
     *
     * @return
     */
    public ArrayList<ContinentModel> getContinentList() {
        return continentList;
    }

    /**
     *
     * @param continentList
     */
    public void setContinentList(ArrayList<ContinentModel> continentList) {
        this.continentList = continentList;
    }

    /**
     *
     * @param continentName
     * @param continentValue
     * @return
     */
    public boolean addContinent(String continentName, int continentValue){

        ContinentModel continent= new ContinentModel(continentName, continentValue);
        if(isAddContinentValid(continentName, continentValue)){
            this.continentList.add(continent);
            return true;
        }

        return false;
    }

    /**
     * remove continent valid or not
     * @param continentName
     * @return result
     */
    public boolean removeContinent(String continentName){

        if(isRemoveContinentValid(continentName)){
            ContinentModel continent = this.continentList.get(indexOfContinent(continentName));
            ArrayList<String> temp = new  ArrayList<String>();
            //copy the arraylist otherwise the list is changing
            for (String countryName:continent.getCountriesList()) {
               temp.add(countryName);
            }
            //remove the country of the continent first
            for (String countryName:temp) {
                removeCountry(countryName);
            }
            this.continentList.remove(indexOfContinent(continentName));
            return true;
        }
        return false;

    }

    /**
     *
     * @return
     */
    public ArrayList<CountryModel> getCountryList() {
        return countryList;
    }

    /**
     *
     * @param countryList
     */
    public void setCountryList(ArrayList<CountryModel> countryList) {
        this.countryList = countryList;
    }

    /**
     * add country to the countryList
     * @param countryName
     * @param continentName
     * @return add succeed or not
     */
    public boolean addCountry(String countryName, String continentName){

        //to check if the continentName exist or not and the countryName, also return the continent index
        if (indexOfContinent(continentName)!=-1 && indexOfCountry(countryName)==-1){
            CountryModel country = new CountryModel(this.countryList.size(), countryName, continentName);
            this.continentList.get(indexOfContinent(continentName)).addCountryToList(countryName);
            this.countryList.add(country);
            return true;
        }

        return false;
    }

    HashMap<String,Integer> countryNumberNamePair = new HashMap<>();
    public HashMap addCountryNumberNamePair(String countryName, int countryNumber){
        countryNumberNamePair.put(countryName,countryNumber);
        return countryNumberNamePair;
    }

    public int getCountryNumberFromName(String countryName){
        int countryNumber;
        if (countryNumberNamePair.containsKey(countryName)){
            countryNumber = countryNumberNamePair.get(countryName);

        }else {
            //System.out.println(countryNumberNamePair.size());
            countryNumberNamePair.put(countryName,countryNumberNamePair.size());
            //System.out.println(countryNumberNamePair.size());
            countryNumber = countryNumberNamePair.get(countryName);
        }
        return countryNumber;


    }

    /**
     * remove country to the countryList
     * @param countryName
     * @return succeed or not
     */
    public boolean removeCountry(String countryName){

        //to check if the continentName exist or not and the countryName, also return the continent index
        if (indexOfCountry(countryName)!=-1){
            String continentName = this.countryList.get(indexOfCountry(countryName)).getContinentName();
            CountryModel country = this.countryList.get(indexOfCountry(countryName));

            //remove the country from its neighbours list
            for (int i = 0; i < country.getNeighbours().size(); i++){
                CountryModel neighbour = country.getNeighbours().get(i);
                String neighbourName = neighbour.getCountryName();
                removeNeighbor(countryName, neighbourName);
            }
            this.countryList.remove(indexOfCountry(countryName));
            this.continentList.get(indexOfContinent(continentName)).removeCountryFromList(countryName);
            return true;
        }

        return false;
    }

    /**
     * Add the neighbor to the list failed or not
     * @param countryName
     * @param neighborCountryName
     * @return result
     */
    public boolean addNeighbor(String countryName, String neighborCountryName){

        //to check if the neighborCountryName exist or not and the countryName, also return the index of the country
        if (indexOfCountry(countryName)!=-1 && indexOfCountry(neighborCountryName)!=-1
            && !countryName.equals(neighborCountryName)){
            CountryModel country = this.countryList.get(indexOfCountry(countryName));
            CountryModel neighborCountry = this.countryList.get(indexOfCountry(neighborCountryName));
            if (country.getNeighbours().contains(neighborCountry.getCountryValue())){
                System.out.println("This Neighbor Country already exist");
                return false;
            }
            country.addNeighbour(neighborCountry);
            neighborCountry.addNeighbour(country);
            return true;
        }
        System.out.println("The country or the neighbor country not exists ");
        return false;

    }

    /**
     * Remove the neighbor to the list failed or not
     * @param countryName
     * @param neighborCountryName
     * @return result
     */
    public boolean removeNeighbor(String countryName, String neighborCountryName){

        //to check if the continentName exist or not and the countryName, also return the continent index
        if (indexOfCountry(countryName)!=-1 && indexOfCountry(neighborCountryName)!=-1
            && !countryName.equals(neighborCountryName)){
            CountryModel country = this.countryList.get(indexOfCountry(countryName));
            CountryModel neighborCountry = this.countryList.get(indexOfCountry(neighborCountryName));
            country.removeNeighbour(neighborCountry.getCountryValue());
            neighborCountry.removeNeighbour(country.getCountryValue());
            return true;
        }
        System.out.println("The country or the neighbor country not exists ");
        return false;

    }

    /**
     * get the index of the CountryList base on the country name
     * @param countryName
     * @return index
     */
    public int indexOfCountry(String countryName) {
        if (this.countryList.isEmpty())
            return -1;

        for (int i = 0; i < this.countryList.size(); i++){
            if(this.countryList.get(i).getCountryName().equals(countryName)){
//                System.out.println("Country Name already exist, please enter another one");
                return i;
            }
        }

        return -1;

    }

    /**
     * get the index of the CountryList base on the country value
     * @param countryValue
     * @return index
     */
    public int indexOfCountry(int countryValue) {
        if (this.countryList.isEmpty())
            return -1;

        for (int i = 0; i < this.countryList.size(); i++){
            if(this.countryList.get(i).getCountryValue()==(countryValue)){
//                System.out.println("Country Name already exist, please enter another one");
                return i;
            }
        }

        return -1;

    }

    /**
     * get the index of the ContinentList base on the Continent name
     * @param continentName
     * @return index
     */
    public int indexOfContinent(String continentName) {
        if (this.continentList.isEmpty()){
            return -1;
        }

        for (int i = 0; i < this.continentList.size(); i++){
            if(this.continentList.get(i).getContinentName().equals(continentName)){
                return i;
            }
        }

//        System.out.println("Continent Name is not exist, please enter another one");
        return -1;

    }

    /**
     * is the add validate
     * @param continentName
     * @param continentValue
     * @return result
     */
    public boolean isAddContinentValid(String continentName, int continentValue) {

        if (this.continentList.isEmpty()){
            return true;
        }

        for (int i = 0; i < this.continentList.size(); i++){
            if(this.continentList.get(i).getContinentName().equals(continentName) ||
                    this.continentList.get(i).getContinentValue()==continentValue){
                System.out.println("Continent Name already exist, please enter another one");
                return false;
            }
        }

        return true;

    }

    /**
     * is the remove validate
     * @param continentName
     * @return result
     */
    public boolean isRemoveContinentValid(String continentName) {

        if (indexOfContinent(continentName)==-1){
            System.out.println("Continent Name is not exist, please enter another one");
            return false;
        }
        return true;

    }




}
