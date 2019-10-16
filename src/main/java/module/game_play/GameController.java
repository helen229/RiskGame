package module.game_play;

import module.game_play.phases.FortificationPhase;
import module.game_play.phases.ReinforcementPhase;
import module.game_play.phases.StartUpPhase;
import module.map_editor.models.MapModel;

public class GameController {

    private MapModel mapModel;

    public void gameController(){




    StartUpPhase startUpPhase= new StartUpPhase();
    startUpPhase.loadMap("map.map");



    startUpPhase.populateCountries();

    ReinforcementPhase reinforce= new ReinforcementPhase();
    FortificationPhase fortify= new FortificationPhase();



    }


    public void showMap(){

    }

}
