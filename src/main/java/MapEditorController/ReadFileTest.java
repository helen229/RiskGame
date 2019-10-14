package MapEditorController;

import MapEditorModel.ContinentModel;
import org.junit.*;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class ReadFileTest {

    ReadFile readfile;
    @Before

    public void runBefore() {
        readfile = new ReadFile("C:\\Users\\Adeola Adeniji\\Desktop\\Game\\src\\main\\java\\MapEditorController\\ameroki.map");
       // try {
         //   readfile.checkFile();
      //  } catch (IOException e) {
         //   e.printStackTrace();
      //  }

    }
    @After
     public void testAfter(){
readfile=null;
    }

    @Test
    public void checkFile() {

    }

    @Test
    public void getContinents() {
        //ArrayList<ContinentModel> checkContinents= readfile.getContinents();
      //  Assert.assertNotNull(checkContinents);
    }

    @Test
    public void getCountries() {
    }
}