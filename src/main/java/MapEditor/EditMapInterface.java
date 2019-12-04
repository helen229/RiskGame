package MapEditor;
import MapEditorModel.ContinentModel;
import MapEditorModel.CountryModel;
import MapEditorModel.MapModel;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * This is an EditMap Interface
 */

public interface EditMapInterface {
    MapModel checkFile() throws IOException;
}
