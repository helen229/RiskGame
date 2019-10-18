package MapEditorController;

import MapEditorModel.CountryModel;
import MapEditorModel.MapModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class Validate {
    MapModel mapModel;
    ArrayList<CountryModel> countryList;

    HashMap<CountryModel, Boolean> visited = new HashMap<>();
    HashMap<Integer, CountryModel> countryValue = new HashMap<>();


    public  Validate(MapModel mapModel) {
        countryList = mapModel.getCountryList();
        this.mapModel = mapModel;
        StoreData();
        validator();

    }

    public void StoreData() {
        for (CountryModel country : countryList) {
            visited.put(country, false);
            countryValue.put(country.getCountryValue(), country);
        }
    }

    public void validator() {

        for (CountryModel country : countryList) {

            ArrayList<Integer> neighbours = country.getNeighbours();

            for (int i : neighbours) {
                CountryModel neighbour = countryValue.get(i);

                visited.put(neighbour, true);

            }

        }
    }

    public boolean isValid() {



        for (boolean value : visited.values()) {
            if (value == false) {
                return false;
            }


        }
        return true;

    }
}
