package GamePlayView;

import GamePlayModel.GameModel;

import java.util.Observable;
import java.util.Observer;

public class GamePhaseView implements Observer {
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

        String mapeditor = "The actions of this phase are: \nLoad map file \nEdit the game player " +
                "\nAssign the countries \nPlace the armies\n*************** PhaseView *******************\n";
        String startup = "The actions of this phase are: \nLoad map file \nEdit the game player " +
                "\nAssign the countries \nPlace the armies\n*************** PhaseView *******************\n";
        String reinforcement = "The actions of this phase are: \nReinforce the country \nExchange the cards" +
                "\n*************** PhaseView *******************\n";
        String attack = "The actions of this phase are: \nLoad map file \nEdit the game player " +
                "\nAssign the countries \nPlace the armies\n*************** PhaseView *******************\n";
        String fortification = "The actions of this phase are: \nLoad map file \nEdit the game player " +
                "\nAssign the countries \nPlace the armies\n*************** PhaseView *******************\n";

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
