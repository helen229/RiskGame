package module.map_editor.models;


import module.game_play.models.PlayerModel;

import java.util.ArrayList;

public class CountryModel {

    private int countryValue;

    private String countryName;

    private String continentName;

    private ArrayList<Integer> neighbours;
    //    private int armyNum;
//
    public PlayerModel owner;

    public CountryModel(int countryID, String countryName, String continentName) {
        this.countryValue = countryID;
        this.countryName = countryName;
        this.continentName = continentName;
        this.neighbours = new ArrayList<Integer>();
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
    public void setOwner(PlayerModel owner) {
        this.owner = owner;
    }


}
