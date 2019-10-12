package MapEditorController;

import MapEditorModel.CountryModel;

import java.util.ArrayList;
import java.util.HashMap;

public class Validate {
    ArrayList<CountryModel> countryList;
    HashMap<CountryModel, Boolean> isValid=  new HashMap<>();

    public Validate( ArrayList <CountryModel>countryList){
        this.countryList= countryList;

        int size= countryList.size();
        for(int i=0;i< size;i++ ){
             isValid.put(countryList.get(i), false);
        }





    }
}
