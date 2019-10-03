package MapEditorModel;

import java.util.ArrayList;
import java.util.Iterator;

public class MapModel {

    /** The conquest map name. */
    private String mapName;

    /** The total countries. */
    public int totalCountries;

    /** The continents list. */
    private ArrayList<ContinentModel> continentList;

    /** The country list. */
    private ArrayList<CountryModel> countryList;


    private boolean isValid = true;

    public MapModel() {
        this.continentList = new ArrayList<ContinentModel>();
        this.countryList = new ArrayList<CountryModel>();
    }

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

    private void printCountry(CountryModel country){

        System.out.print(
                "Country Name:" +
                country.getCountryName() + "\n" +
                "Country Value:" +
                country.getCountryValue() + "\n" +
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

    public String getMapName() {
        return mapName;
    }

    public void setMapName(String mapName) {
        this.mapName = mapName;
    }

    public int getTotalCountries() {
        return totalCountries;
    }

    public void setTotalCountries(int totalCountries) {
        this.totalCountries = totalCountries;
    }

    public ArrayList<ContinentModel> getContinentList() {
        return continentList;
    }

    public void setContinentList(ArrayList<ContinentModel> continentList) {
        this.continentList = continentList;
    }

    public boolean addContinent(String continentName, int continentValue){

        ContinentModel continent= new ContinentModel(continentName, continentValue);
        if(isAddContinentValid(continentName, continentValue)){
            this.continentList.add(continent);
            return true;
        }

        return false;
    }

    public boolean removeContinent(String continentName){

        if(isRemoveContinentValid(continentName)){
            this.continentList.remove(indexOfContinent(continentName));
            return true;
        }

        return false;

    }

    public ArrayList<CountryModel> getCountryList() {
        return countryList;
    }

    public void setCountryList(ArrayList<CountryModel> countryList) {
        this.countryList = countryList;
    }

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

    public boolean removeCountry(String countryName){

        //to check if the continentName exist or not and the countryName, also return the continent index
        if (indexOfCountry(countryName)!=-1){
            String continentName = this.countryList.get(indexOfCountry(countryName)).getContinentName();
            CountryModel country = this.countryList.get(indexOfCountry(countryName));
            this.continentList.get(indexOfContinent(continentName)).removeCountryFromList(countryName);

            //remove the country from its neighbours list
            for (int i = 0; i < country.getNeighbours().size(); i++){
                int neighbourValue = country.getNeighbours().get(i);
                String neighbourName = this.countryList.get(indexOfCountry(neighbourValue)).getCountryName();
                removeNeighbor(countryName, neighbourName);
            }

            this.countryList.remove(indexOfCountry(countryName));

            return true;
        }

        return false;
    }


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
            country.addNeighbour(neighborCountry.getCountryValue());
            neighborCountry.addNeighbour(country.getCountryValue());
            return true;
        }
        System.out.println("The country or the neighbor country not exists ");
        return false;

    }


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

    private int indexOfCountry(String countryName) {
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

    private int indexOfCountry(int countryValue) {
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

    private int indexOfContinent(String continentName) {
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

    public boolean isMapValid() {
        return isValid;
    }

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

    public boolean isRemoveContinentValid(String continentName) {

        if (indexOfContinent(continentName)==-1){
            System.out.println("Continent Name is not exist, please enter another one");
            return false;
        }
        return true;

    }


    public void setValid(boolean valid) {
        isValid = valid;
    }
}
