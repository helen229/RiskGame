/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GamePlayView;
import GamePlayModel.GameModel;
import GamePlayModel.PlayerModel;
import MapEditorModel.MapModel;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
/**
 *
 * @author Ehsan
 */
public class PlayerDominationView implements Observer {
    public void update(Observable obs, Object arg) {
        if (!"DominView".equals(arg))
            return;
        ArrayList<PlayerModel> playerList = ((GameModel)obs).getPlayerList();
        MapModel mapModel = ((GameModel)obs).getMapModel();

        String head = "\n*************** Domination View *******************";
        String end = "*************** Domination View *******************\n";
        System.out.println(head);
        for (PlayerModel player:playerList) {
            player.checkPlayerContinents(mapModel.getContinentList());
            System.out.println(player.getPlayerName());
            System.out.println("The percentage of map: "+player.percentageOfmap(mapModel.getTotalCountries())+ "%");
            System.out.println("The Countries controlled: " + player.getPlayerCountries());
            System.out.println("The Continents controlled: " + player.getPlayerContinents());
            System.out.println("The total army number: "+player.getTotalNumArmy());
            }
        System.out.println(end);
    };
}
