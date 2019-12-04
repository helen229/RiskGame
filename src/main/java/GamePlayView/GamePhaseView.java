package GamePlayView;

import GamePlayModel.GameModel;

import java.util.Observable;
import java.util.Observer;

/**
 * This method updates the Game Phase View using the Observer design pattern
 */
public class GamePhaseView implements Observer {
    /**
     * This method updates the GamePhase View
     * @param obs
     * @param arg
     */
    public void update(Observable obs, Object arg) {
        if (!"PhaseView".equals(arg))
            return;
        String phase = ((GameModel)obs).getPhase();
        String player = "None Player Added yet";
        try {
            player = ((GameModel)obs).getCurrentPlayer().getPlayerName();
        }catch (NullPointerException e){
            //No player added yet
        }

        String mapeditor = "The actions of this phase are: \n"
                + "Edit the continent (editcontinent -add continentname continentvalue -remove continentname)\n"
                + "Edit the country (editcountry -add countryname continentname -remove countryname)\n"
                + "Edit the neighbor (editneighbor -add countryname neighborcountryname -remove countryname neighborcountryname)\n"
                + "Show the current map (showmap) \n"
                + "Save the map to file (savemap filename) \n"
                + "Edit the map from file (editmap filename) \n"
                + "Validate the current map (validatemap) \n"
                + "*************** PhaseView *******************\n";
        String startup = "The actions of this phase are: \n"
                + "Load map file (loadmap filename)\n"
                + "Edit the game player (gameplayer -add playername -remove playername)\n"
                + "Assign the countries (populatecountries)\n"
                + "Place the armies (placearmy countryname) (placeall)\n"
                + "*************** PhaseView *******************\n";
        String reinforcement = "The actions of this phase are: \n"
                + "Reinforce the country (reinforce countryname num)\n"
                + "Exchange the cards (exchangecards num num num –none)\n"
                + "*************** PhaseView *******************\n";
        String attack = "The actions of this phase are: \n"
                + "Attack to a country (attack countrynamefrom countynameto numdice –allout –noattack)\n"
                + "Defender defend his/her country (defend numdice)\n"
                + "Move armies to the new country (attackmove num)\n"
                + "*************** PhaseView *******************\n";
        String fortification = "The actions of this phase are: \n"
                + "Fortify armies (fortify fromcountry tocountry num –none)\n"
                + "*************** PhaseView *******************\n";

        System.out.println("\n*************** PhaseView *******************\nCurrent Phase is: "+phase);
        System.out.println("Current Player is: "+player);
        switch (phase) {
            case "MapEditor":
                System.out.println(mapeditor);
                break;
            case "Startup":
                System.out.println(startup);
                break;
            case "Reinforcement":
                System.out.println(reinforcement);
                break;
            case "Attack":
                System.out.println(attack);
                break;
            case "Fortification":
                System.out.println(fortification);
                break;
        }

    };
}
