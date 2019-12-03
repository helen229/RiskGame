package MapEditor;

import MapEditorModel.ContinentModel;
import MapEditorModel.CountryModel;
import MapEditorModel.MapModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MapValidatorTest {
    public MapModel map;
    public MapHandler mapHandler;


    /**
     * Each time invoke a method, set up this context
     */
    @BeforeEach
    public void setUp() {
        mapHandler = new MapHandler();
    }

    @Test
    public void testLoadMap() {
        mapHandler.editMap("test.txt");
        int size = mapHandler.getMapModel().getCountryList().size();
        assertTrue(size>0);
    }


    @Test
    public void testValidMap() {
        mapHandler.editMap("test.txt");
        mapHandler.getMapModel().isValid();
        assertTrue(mapHandler.getMapModel().isValid());
    }

    @Test
    public void testInValidMap() {
        mapHandler.editMap("testInvalidate1.txt");
        assertFalse(mapHandler.getMapModel().getCountryList().size()>0);
    }

}
