package GamePlayView;

import GamePlayModel.GameModel;
import GamePlayModel.PlayerModel;
import MapEditorModel.MapModel;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public class PlayerDominationView implements Observer {
    public void update(Observable obs, Object arg) {
        if (!"DominView".equals(arg))
            return;
        ArrayList<PlayerModel> playerList = ((GameModel)obs).getPlayerList();
        MapModel mapModel = ((GameModel)obs).getMapModel();

        for (PlayerModel player:playerList) {

            System.out.println(player.percentageOfmap(mapModel.getTotalCountries()));

            System.out.println(player.getTotalNumArmy());
        }
    };
}
