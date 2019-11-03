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

        String head = "*************** Domination View *******************";
        System.out.println(head);
        for (PlayerModel player:playerList) {
            player.checkPlayerContinents(mapModel.getContinentList());
            System.out.println(player.getPlayerName());
            System.out.println("The percentage of map: "+player.percentageOfmap(mapModel.getTotalCountries())+ "%");
            System.out.println("The Continents controlled: " + player.getPlayerContinents());
            System.out.println("The total army number: "+player.getTotalNumArmy()+"\n");
        }
        System.out.println(head);
    };
}
