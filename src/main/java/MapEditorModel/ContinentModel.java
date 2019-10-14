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
     *
     * @return
     */
    public String getContinentName() {

        return continentName;
    }

    /**
     *
     * @param continentName
     */
    public void setContinentName(String continentName) {

        this.continentName = continentName;
    }

    /**
     *
     * @return
     */
    public int getContinentValue() {
        return continentValue;
    }

    /**
     *
     * @param continentValue
     */
    public void setContinentValue(int continentValue) {

        this.continentValue = continentValue;
    }

    /**
     *
     * @return
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

        this.countriesList.remove(countryName);
    }

    /**
     *
     * @param countriesList
     */
    public void setCountriesList(ArrayList<String> countriesList) {

        this.countriesList = countriesList;
    }
}
