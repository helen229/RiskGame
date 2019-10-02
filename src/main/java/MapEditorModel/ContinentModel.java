package MapEditorModel;

import java.util.ArrayList;

public class ContinentModel {

    /** The continent name. */
    private String continentName;

    /** The continent name. */
    private int continentValue;


    /** The countries list. */
    private ArrayList<CountryModel> countriesList;


    /**
     * Constructor for Continent class.
     *
     * @param continentName name of the new continent
     */
    public ContinentModel(String continentName, int continentValue) {
        this.setContinentName(continentName);
        this.setContinentValue(continentValue);
        this.countriesList = new ArrayList<CountryModel>();
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

    public ArrayList<CountryModel> getCountriesList() {
        return countriesList;
    }

    public void setCountriesList(ArrayList<CountryModel> countriesList) {
        this.countriesList = countriesList;
    }
}
