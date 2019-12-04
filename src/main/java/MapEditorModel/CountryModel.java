package MapEditorModel;

import GamePlayModel.PlayerModel;

import java.util.ArrayList;

public class CountryModel {

    private int countryValue;

    private String countryName;

    private String continentName;

    private ArrayList<CountryModel> neighbours;

    private ArrayList<String> neighboursNames;

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
        this.neighbours = new ArrayList<CountryModel>();
        this.neighboursNames = new ArrayList<String>();
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
     * @param country
     */
    public void addNeighbour(CountryModel country) {

        this.neighbours.add(country);

    }

    /**
     *
     * @param country
     */
    public void removeNeighbour(CountryModel country) {

        if (neighbours.size()==1 && neighbours.contains(country))
            neighbours.clear();
        else
            this.neighbours.remove(neighbours.indexOf(country));
    }

    /**
     *
     * @return
     */
    public ArrayList<CountryModel> getNeighbours() {
        return neighbours;
    }


    public void setNeighbours(ArrayList<CountryModel> neighbours) {
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

    @Override
    public String toString() {
        return this.getCountryName()+" "+this.getArmyNum()+" "+this.getOwner().getPlayerName();
    }
}
