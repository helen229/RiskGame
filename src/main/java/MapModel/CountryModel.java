package MapModel;

import java.util.ArrayList;

public class CountryModel {

    private int countryID;

    private String countryName;

    private ContinentModel continent;

    private ArrayList<CountryModel> neighbours;

    public CountryModel(int countryID, String countryName, ContinentModel continent) {
        this.countryID = countryID;
        this.countryName = countryName;
        this.continent = continent;
        this.neighbours = new ArrayList<CountryModel>();
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public int getCountryID() {
        return countryID;
    }

    public void setCountryID(int countryID) {
        this.countryID = countryID;
    }

    public ContinentModel getContinent() {
        return continent;
    }

    public void setContinent(ContinentModel continent) {
        this.continent = continent;
    }

    public ArrayList<CountryModel> getNeighbours() {
        return neighbours;
    }

    public void setNeighbours(ArrayList<CountryModel> neighbours) {
        this.neighbours = neighbours;
    }

//    private int armyNum;
//
//    private PlayerModel owner;
}
