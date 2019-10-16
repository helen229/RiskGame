package module.game_play.phases;

import module.map_editor.MapEditorController.ReadFile;
import module.game_play.models.PlayerModel;
import module.map_editor.models.CountryModel;
import module.map_editor.models.MapModel;

import java.util.ArrayList;

/**
 * This method handles the startUp phase of the Game
 */
public class StartUpPhase {
    ArrayList<PlayerModel> playerList = new ArrayList<>();

    int numOfPlayers;
    MapModel mapModel;








    public int getNumOfPlayers() {
        return numOfPlayers;
    }


    public void setNumOfPlayers(int numOfPlayers) {
        this.numOfPlayers = numOfPlayers;
    }

    public void populateCountries() {

        ArrayList<CountryModel> countries = new ArrayList<>();
        countries = mapModel.getCountryList();
        int countrySize = countries.size();
        int numberCountry = countrySize / numOfPlayers;
        for (int j = 0; j < playerList.size(); j++) {
            for (int i = j; i < (j + 1) * numberCountry; i++) {
                countries.get(i).setOwner(playerList.get(j));
                playerList.get(j).addPlayerCountries(countries.get(i));
            }
        }
        int checker = countrySize % numOfPlayers;
        if (checker != 0) {
            int remainderCountries = countrySize - (numberCountry * numOfPlayers);

            for (int i = 0; i < remainderCountries; i++) {
                playerList.get(i).addPlayerCountries(countries.get(i));

            }


        }

    }

    public void addPlayer(String name) {
        PlayerModel playerModel = new PlayerModel();
        playerModel.setPlayerName(name);
        playerList.add(playerModel);
    }


    public void removePlayer(String playername){
     //PlayerModel playerModel= new PlayerModel();
    // playerModel.getPlayerName()


    }

    public MapModel loadMap(String fileName) {
        ReadFile readFile = new ReadFile(fileName);
        return readFile.getMapModel();
    }

    public void placeArmy(String countryName) {

    }

    public void placeAll() {

    }


}
