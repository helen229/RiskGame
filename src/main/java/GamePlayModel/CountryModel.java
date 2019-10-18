package GamePlayModel;

import java.util.ArrayList;

/**
 * This class create object of Country. Each country has:
 * @param countryName name as string
 * @param continentName name of the belong continent as string
 * @param countryValue ID as int
 * @param neighbours list of the IDs of neighbours countries as arrayList int
 * @param armyNum number of armies inside the country as int
 * @param ownerValue ID of the owner of the country as int
 * @author Ehsan
 */

public class CountryModel {
    
    private int countryValue;
    private String countryName;
    private String continentName;
    private ArrayList<Integer> neighbours;
    private int armyNum;
    private int ownerValue;

    /** Constructor for Country class. */
    public CountryModel(int countryID, String countryName, String continentName, int armyNum, int ownerValue) {
        this.countryValue = countryID;
        this.countryName = countryName;
        this.continentName = continentName;
        this.neighbours = new ArrayList<Integer>();
        this.armyNum= armyNum;
        this.ownerValue= ownerValue;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getContinentName() {
        return continentName;
    }

    public int getCountryValue() {
        return countryValue;
    }

    public void setCountryValue(int countryValue) {
        this.countryValue = countryValue;
    }

    public int getOwnerValue() {
        return ownerValue;
    }

    public void setOwnerValue(int ownerValue) {
        this.ownerValue = ownerValue;
    }
    
    public int getArmyNum() {
        return armyNum;
    }

    public void setArmyNum(int armyNum) {
        this.armyNum = armyNum;
    }
    
    public void addNeighbour(int countryValue) {
        this.neighbours.add(countryValue);
    }

    public void removeNeighbour(int countryValue) {
        this.neighbours.remove(countryValue);
    }

    public ArrayList<Integer> getNeighbours() {
        return neighbours;
    }

    public void setNeighbours(ArrayList<Integer> neighbours) {
        this.neighbours = neighbours;
    }

}
