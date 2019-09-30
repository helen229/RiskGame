package MapModel;

import java.util.ArrayList;

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

    public ArrayList<CountryModel> getCountryList() {
        return countryList;
    }

    public void setCountryList(ArrayList<CountryModel> countryList) {
        this.countryList = countryList;
    }

    public boolean isValid() {
        return isValid;
    }

    public void setValid(boolean valid) {
        isValid = valid;
    }
}
