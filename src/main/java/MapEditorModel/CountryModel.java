package MapEditorModel;

import GamePlayModel.PlayerModel;

import java.util.ArrayList;

public class CountryModel {

    private int countryValue;

    private String countryName;

    private String continentName;

    private ArrayList<Integer> neighbours;

    private int armyNum;

    private PlayerModel owner;

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
        this.owner = new PlayerModel("");
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


    public int getArmyNum() {
        return armyNum;
    }

    public void setArmyNum(int armyNum) {
        this.armyNum = armyNum;
    }

    public void addArmyNum() {
        this.armyNum++;
    }

    public void addArmyNum(int num) {
        this.armyNum= this.armyNum + num;
    }

    public PlayerModel getOwner() {
        return owner;
    }

    public void setOwner(PlayerModel owner) {
        this.owner = owner;
    }

    public void reduceArmyNum() {
        this.armyNum--;
    }

    public void reduceArmyNum(int num) {
        this.armyNum= this.armyNum - num;
    }
}
