package MapEditorController;

import MapEditorModel.MapModel;
import org.junit.*;

import static junit.framework.TestCase.assertTrue;

public class ValidateTest {

    private MapModel mapModel;
    private Validate validate;

    @Before
    public void setBeforeClass() {
        TestDataProvider dataProvider = new TestDataProvider();
        mapModel = dataProvider.setUpModel();
        validate = new Validate(mapModel);
    }

    @After
    public void setAfterClass() {

        mapModel = null;
    }


    @Test
    public void isValid() {
        assertTrue(validate.isValid());
    }
}