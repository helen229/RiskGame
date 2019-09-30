package MapModel;

import java.util.ArrayList;

public class ContinentModel {

    /** The continent name. */
    private String continentName;

    /** The countries list. */
    private ArrayList<CountryModel> countriesList;


    /**
     * Constructor for Continent class.
     *
     * @param continentName name of the new continent
     */
    public ContinentModel(String continentName) {
        this.setContinentName(continentName);
        this.countriesList = new ArrayList<CountryModel>();
    }

    public String getContinentName() {
        return continentName;
    }

    public void setContinentName(String continentName) {
        this.continentName = continentName;
    }

    public ArrayList<CountryModel> getCountriesList() {
        return countriesList;
    }

    public void setCountriesList(ArrayList<CountryModel> countriesList) {
        this.countriesList = countriesList;
    }
}
