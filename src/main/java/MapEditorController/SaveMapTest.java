package MapEditorController;

 import MapEditorModel.MapModel;
 import org.junit.*;

 import java.io.File;
 import java.io.IOException;
 import static junit.framework.TestCase.assertTrue;

public class SaveMapTest {
  MapModel mapModel;
  String fileName;

    @Before()
    public void SetBeforeClass(){
        TestDataProvider dataProvider = new TestDataProvider();
        mapModel= dataProvider.setUpModel();
        fileName= "test";

    }
    @After
    public void setAfterClass(){
        mapModel=null;
        fileName=null;
    }



   @Test
   public void writeFile() {
        try{
       SaveMap saveMap= new SaveMap( fileName,mapModel);
        File file= saveMap.writeFile(fileName, mapModel);
        boolean check= file.exists();
      assertTrue(check);
       Assert.assertNotNull(saveMap.writeFile(fileName,mapModel));



   }
        catch(IOException e){
          e.printStackTrace();
        }
   }


}