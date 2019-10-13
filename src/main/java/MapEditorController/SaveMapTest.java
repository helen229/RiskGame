//package MapEditorController;
//
//import MapEditorModel.MapModel;
//import org.junit.Assert;
//import org.junit.BeforeClass;
//import org.junit.Test;
//
//import java.io.IOException;
//
//public class SaveMapTest {
////    MapModel mapModel;
////    TestDataProvider dataProvider = new TestDataProvider();
////    mapModel=dataProvider.setUpModel();
//
//    @BeforeClass
//    public void runBeforeClass() {
//
//
//        try {
//            SaveMap savemap = new SaveMap("test", mapModel);
//
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    @Test
//    public void writeFile() {
//
//
//    }
//
//    @Test
//    public void generateContent() {
//        String message = " ";
//
//        String content = savemap.generateContent(mapModel);
//        Assert.assertEquals(message, savemap.generateContent(mapModel));
//    }
//
//    @Test
//    public void arrayCompiler() {
//    }
//}