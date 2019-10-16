package module.map_editor.models;

import java.util.ArrayList;

public class ContinentModel {

    /** The continent name. */
    private String continentName;

    /** The continent name. */
    private int continentValue;

    /** The countries list. */
    private ArrayList<String> countriesList;


    /**
     * Constructor for Continent class.
     *
     * @param continentName name of the new continent
     */
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
