package module.map_editor.MapEditorController;

import module.map_editor.models.MapModel;
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