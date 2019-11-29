package GamePlayModel;

import MapEditorModel.ContinentModel;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class PlayerModelTest {
    @BeforeAll
    public void setup() {




}
    @Test
    void checkPlayerContinents() {
        PlayerModel player = new PlayerModel("Test");
        ArrayList<ContinentModel> ContinentList= new ArrayList<>();
        player.checkPlayerContinents(ContinentList);

        assertNull(player.playerContinents);
    }



    @Test
    void percentageOfmap() {
        PlayerModel player = new PlayerModel("Test");
        ArrayList<ContinentModel> ContinentList= new ArrayList<>();
       int countryNum=3;
       assertNotNull(player.percentageOfmap(countryNum));
    }
}