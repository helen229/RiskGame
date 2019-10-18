package GamePlayModel;

import java.util.ArrayList;

/**
 * This class create object of Continent. Each continent has:
 * @param continentName name as string
 * @param continentValue ID as int
 * @param countriesList list of countries as arrayList string
 * For the next build this class should also contain the owner ID.
 * @author Ehsan
 */

public class ContinentModel {

    private String continentName;
    private int continentValue;
    private ArrayList<String> countriesList;
    
    /** Constructor for Continent class. */
    public ContinentModel(String continentName, int continentValue) {
        this.setContinentName(continentName);
        this.setContinentValue(continentValue);
        this.countriesList = new ArrayList<String>();
    }

    public String getContinentName() {
        return continentName;
    }

    public void setContinentName(String continentName) {
        this.continentName = continentName;
    }

    public int getContinentValue() {
        return continentValue;
    }

    public void setContinentValue(int continentValue) {
        this.continentValue = continentValue;
    }

    public ArrayList<String> getCountriesList() {
        return countriesList;
    }

    public void addCountryToList(String countryName) {
        this.countriesList.add(countryName);
    }

    public void removeCountryFromList(String countryName) {
        this.countriesList.remove(countryName);
    }

    public void setCountriesList(ArrayList<String> countriesList) {
        this.countriesList = countriesList;
    }
}   
