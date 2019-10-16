package module.game_play;

import module.game_play.phases.FortificationPhase;
import module.game_play.phases.ReinforcementPhase;
import module.game_play.phases.StartUpPhase;
import module.map_editor.models.ContinentModel;
import module.map_editor.models.CountryModel;
import module.map_editor.models.MapModel;

import java.util.ArrayList;

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
              ArrayList<CountryModel> countries= mapModel.getCountryList();

              for(int i=0;i<countries.size();i++){
                  System.out.println( countries.get(i).getCountryName());
              }
        for(int i=0;i<countries.size();i++){
            //System.out.println( countries.get(i).getowner());
        }
              ArrayList<ContinentModel> continents= mapModel.getContinentList();
        for(int i=0;i<continents.size();i++){
            System.out.println( countries.get(i).getCountryName());
        }






    }

}
