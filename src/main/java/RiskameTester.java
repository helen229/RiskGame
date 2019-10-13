import MapEditorController.ReadFile;
import MapEditorController.SaveMap;
import MapEditorModel.ContinentModel;
import MapEditorModel.CountryModel;
import MapEditorModel.MapModel;
import org.junit.*;

import javax.security.sasl.SaslClient;
import java.io.IOException;
import java.util.ArrayList;

public class RiskameTester {
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


    @BeforeClass
     public  void SetBeforeClassO(){



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




    }
    @AfterClass
     public void runAfterClass(){
        mapModel=null;
    }
    @Before
    public void BeforeTestingMethods(){

    }
    @After
    public void AfterTestingMethod(){


    }

    @Test
    public void testSaveMAp(){
        try {
            SaveMap savemap = new SaveMap("test", mapModel);

            // Test the SaveMapMEthod class
            Assert.assertNotNull(savemap);


            // Test the GenerateCOntent method


            // Test the  ArrayCompiler method.
           String check=  savemap.ArrayCompiler(Canada.getNeighbours());
           String expectedString = " ";
           Assert.assertEquals(expectedString,check);


        }
catch (IOException e){
            e.printStackTrace();
        }

    }

}
