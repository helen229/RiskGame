package MapEditor;
import MapEditorModel.ContinentModel;
import MapEditorModel.CountryModel;
import MapEditorModel.MapModel;
import MapEditor.EditPreparedMap;
import MapEditor.EditPreparedMapInterface;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * This  class changes the interface of the Map
 */


public class MapAdapter extends EditPreparedMap implements EditMapInterface {
    private EditPreparedMap adaptee;
    private EditMap map;

    private MapModel mapModel;
    /**
     * This method change the interface of the class

     */
    public MapAdapter(String fileDirectory) {

        super(fileDirectory);
        adaptee = new EditPreparedMap(fileDirectory);
        mapModel = new MapModel();
    }



    @Override
    public MapModel checkFile() throws IOException{
        mapModel = this.adaptee.checkPreparedFile();


        return mapModel;


    }


}
