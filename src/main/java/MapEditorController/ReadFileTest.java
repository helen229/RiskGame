package MapEditorController;

import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

public class ReadFileTest {
    @BeforeClass
    public void runBeforeClass() {

    }

    @Test
    public void checkFile() {
        ReadFile readfile = new ReadFile(" C:\\Users\\Adeola Adeniji\\Desktop\\Game\\src\\main\\java\\MapEditorController\\ameroki.map");
        try {
            readfile.checkFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void getContinents() {
    }

    @Test
    public void getCountries() {
    }
}