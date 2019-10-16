package module.map_editor.MapEditorController;

import module.map_editor.models.ContinentModel;
import module.map_editor.models.CountryModel;
import module.map_editor.models.MapModel;

import java.util.ArrayList;

public class TestDataProvider {

    public MapModel setUpModel(){

        MapModel mapModel = new MapModel();
        ArrayList<CountryModel> testCountries= new ArrayList<>();
        ArrayList<ContinentModel> testContinents= new ArrayList<>();


        CountryModel  Nigeria= new CountryModel(1, "Nigeria","Africa");
        CountryModel  Canada= new CountryModel(2,"Canada", "NorthAmerica");
        CountryModel China= new CountryModel(3,"China","Asia");
        CountryModel Iran= new CountryModel(4,"Iran","MiddleEast");
        CountryModel Britain= new CountryModel(5,"Britain","Europe");


        ContinentModel Africa= new ContinentModel("Africa",1);
        ContinentModel Asia= new ContinentModel(" Asia", 2);
        ContinentModel MiddleEast= new ContinentModel("MiddleEast", 3);
        ContinentModel Europe= new ContinentModel("Europe", 4);
        ContinentModel NorthAmerica= new ContinentModel ("NorthAmerica",5);


        testCountries.add(Nigeria);
        testCountries.add(Canada);
        testCountries.add(China);
        testCountries.add(Iran);
        testCountries.add(Britain);


        mapModel.setCountryList(testCountries);
        mapModel.setContinentList(testContinents);
        Nigeria.addNeighbour(2);
        Nigeria.addNeighbour(3);
        Canada.addNeighbour(1);
        Canada.addNeighbour(4);
        China.addNeighbour(1);
        China.addNeighbour(4);
        Iran.addNeighbour(5);
        Iran.addNeighbour(3);
        Britain.addNeighbour(2);

        testContinents.add(Africa);
        testContinents.add(NorthAmerica);
        testContinents.add(MiddleEast);
        testContinents.add(Europe);
        testContinents.add(Asia);

 return mapModel;
    }
}
