package module.map_editor.MapEditorController;

import module.map_editor.models.CountryModel;
import module.map_editor.models.MapModel;

import java.util.ArrayList;
import java.util.HashMap;

public class Validate {
    MapModel mapModel;
    ArrayList<CountryModel> countries;

    HashMap<CountryModel, Boolean> visited = new HashMap<>();
    HashMap<Integer, CountryModel> countryId = new HashMap<>();


    public  Validate(MapModel mapModel) {
        countries = mapModel.getCountryList();
        this.mapModel = mapModel;
        StoreData();
        validator();

    }

    public void StoreData() {
        for (CountryModel country : countries) {
            visited.put(country, false);
            countryId.put(country.getCountryValue(), country);
        }
    }

    public void validator() {

        for (CountryModel country : countries) {

            ArrayList<Integer> neighbours = country.getNeighbours();

            for (int i : neighbours
            ) {
                CountryModel neighbour = countryId.get(i);

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
