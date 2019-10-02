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

        if(isRemoveContinentValid(continentName) != -1){
            this.continentList.remove(isRemoveContinentValid(continentName));
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

        return true;
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
                System.out.println("Continent Name is repeat, please enter another one");
                return false;
            }
        }

        return true;

    }

    public int isRemoveContinentValid(String continentName) {

        if (this.continentList.isEmpty()){
            return -1;
        }

        for (int i = 0; i < this.continentList.size(); i++){
            if(this.continentList.get(i).getContinentName().equals(continentName)){
                return i;
            }
        }

        System.out.println("Continent Name is not exist, please enter another one");
        return -1;

    }


    public void setValid(boolean valid) {
        isValid = valid;
    }
}
