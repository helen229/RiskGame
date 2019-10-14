package MapEditorModel;

import sun.awt.SunHints;

import java.util.ArrayList;

public class CountryModel {

    private int countryValue;

    private String countryName;

    private String continentName;

    private ArrayList<Integer> neighbours;

    /**
     * Constructor for the CountryModel
     * @param countryID
     * @param countryName
     * @param continentName
     */
    public CountryModel(int countryID, String countryName, String continentName) {
        this.countryValue = countryID;
        this.countryName = countryName;
        this.continentName = continentName;
        this.neighbours = new ArrayList<Integer>();
    }

    /**
     *
     * @return
     */
    public String getCountryName() {
        return countryName;
    }

    /**
     *
     * @param countryName
     */
    public void setCountryName(String countryName) {
        this.countryName = countryName;
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
     * @return
     */
    public int getCountryValue() {
        return countryValue;
    }

    /**
     *
     * @param countryValue
     */
    public void setCountryValue(int countryValue) {
        this.countryValue = countryValue;
    }

    /**
     *
     * @param countryValue
     */
    public void addNeighbour(int countryValue) {

        this.neighbours.add(countryValue);

    }

    /**
     *
     * @param countryValue
     */
    public void removeNeighbour(int countryValue) {

        this.neighbours.remove(countryValue);

    }

    /**
     *
     * @return
     */
    public ArrayList<Integer> getNeighbours() {
        return neighbours;
    }

    public void setNeighbours(ArrayList<Integer> neighbours) {
        this.neighbours = neighbours;
    }

//    private int armyNum;
//
//    private PlayerModel owner;
}
