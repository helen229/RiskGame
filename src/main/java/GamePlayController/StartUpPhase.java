package GamePlayController;

import GamePlayModel.PlayerModel;
import MapEditorModel.CountryModel;
import MapEditorModel.MapModel;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * This method handles the startUp phase of the Game
 *
 */
public class StartUpPhase {
    ArrayList<PlayerModel> playerList= new ArrayList<>();

    int numOfPlayers;
    MapModel mapModel;



    public int getNumOfPlayers() {
        return numOfPlayers;
    }


    public void setNumOfPlayers(int numOfPlayers) {
        this.numOfPlayers = numOfPlayers;
    }

    public void assignCountries(){

        ArrayList<CountryModel> countries= new ArrayList<>();
        countries= mapModel.getCountryList();
        int j=countries.size();
         while(j>0){
             j--;

             for (PlayerModel player:playerList) {
                 CountryModel country= countries.get(j);
                 player.addPlayerCountries(country);
             }
         }


    }

    public void assignArmies(){



    }
    public void loadMap(){


    }

    public void PlaceArmy( String countryName){

    }

    public void PlaceAll(){

    }
}
