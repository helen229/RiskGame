package MapValidate;

import MapEditorController.MapController;
import MapEditorModel.ContinentModel;
import MapEditorModel.CountryModel;
import MapEditorModel.MapModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MapValidatorTest {
    public MapModel map;
    public MapController mapController;
    private ContinentModel Asia;
    public CountryModel China;
    public CountryModel Japan;
    public CountryModel Singapore;

    /**
     * Each time invoke a method, set up this context
     */
    @BeforeEach
    public void setUp() {
        mapController = new MapController();
    }

    @Test
    public void testLoadMap() {
        mapController.editMap("test.txt");
        int size = mapController.getMapModel().getCountryList().size();
        assertTrue(size>0);
    }


    @Test
    public void testValidMap() {
        mapController.editMap("test.txt");
        mapController.getMapModel().isValid();
        assertTrue(mapController.getMapModel().isValid());
    }

    @Test
    public void testInValidMap() {
        mapController.editMap("testInvalidate1.txt");
        assertFalse(mapController.getMapModel().getCountryList().size()>0);
    }

}
