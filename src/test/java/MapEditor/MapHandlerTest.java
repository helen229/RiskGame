package MapEditor;

import MapEditorModel.ContinentModel;
import MapEditorModel.CountryModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MapHandlerTest {
    private ContinentModel Asia;
    public CountryModel China;
    public CountryModel Japan;
    public CountryModel Singapore;

    /**
     * Each time invoke a method, set up this context
     */
    @BeforeEach
    public void setUp() {

        Asia = new ContinentModel("Asia", 1);
        China = new CountryModel(0,"china", "Asia");
        Japan = new CountryModel(1,"thailand", "Asia");
        Singapore = new CountryModel(2,"singapore", "Asia");
        ArrayList<String> countryList = new ArrayList<String>();
        countryList.add(China.getCountryName());
        countryList.add(Japan.getCountryName());
        countryList.add(Singapore.getCountryName());
        Asia.setCountriesList(countryList);
    }

    @Test
    public void testSetCountryName() {
        Japan.setCountryName("NewName");
        assertTrue(Japan.getCountryName().equals("NewName"));
    }


    @Test
    public void testGetCountriesList() {
        assertFalse(Asia.getCountriesList().isEmpty());
    }

    @Test
    public void testAddCountriesList() {
        Asia.addCountryToList("India");
        assertTrue(Asia.getCountriesList().contains("India"));
    }

    @Test
    public void testRemoveCountriesList() {
        Asia.removeCountryFromList("japan");
        assertFalse(Asia.getCountriesList().contains("japan"));
    }
}
