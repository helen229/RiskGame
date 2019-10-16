package module.map_editor.MapEditorController;

import org.junit.*;

public class ReadFileTest {

    ReadFile readfile;
    @Before

    public void runBefore() {
        readfile = new ReadFile("C:\\Users\\Adeola Adeniji\\Desktop\\Game\\src\\main\\java\\module.map_editor.MapEditorController\\ameroki.map");
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