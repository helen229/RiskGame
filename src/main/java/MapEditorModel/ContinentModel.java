package MapEditorModel;

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

    /**
     * Get Continent name
     * @return the name of the Continent
     */
    public String getContinentName() {

        return continentName;
    }

    /**
     * Set Continent name
     * @param continentName
     */
    public void setContinentName(String continentName) {

        this.continentName = continentName;
    }

    /**
     * Get Continent value
     * @return
     */
    public int getContinentValue() {
        return continentValue;
    }

    /**
     * Set Continent value
     * @param continentValue
     */
    public void setContinentValue(int continentValue) {

        this.continentValue = continentValue;
    }

    /**
     * Get Continent's country list
     * @return country list
     */
    public ArrayList<String> getCountriesList() {

        return countriesList;
    }

    /**
     *
     * @param countryName
     */
    public void addCountryToList(String countryName) {

        this.countriesList.add(countryName);
    }

    /**
     *
     * @param countryName
     */
    public void removeCountryFromList(String countryName) {

        if (countriesList.size()==1 && countriesList.contains(countryName))
            countriesList.clear();
        else
            this.countriesList.remove(countryName);
    }

    /**
     *
     * @param countriesList
     */
    public void setCountriesList(ArrayList<String> countriesList) {

        this.countriesList = countriesList;
    }

    public int getCountriesSize() {
        return this.countriesList.size();
    }

    @Override
    public String toString() {
        return (this.getContinentName());
    }
}
