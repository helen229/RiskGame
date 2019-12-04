package Strategy;


import GamePlayModel.GameModel;
import GamePlayModel.PlayerModel;
import MapEditorModel.CountryModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Aggressive strategy class
 */
public class AggressiveStrategy implements Strategy {

    private String name;
    private PlayerModel player;
    private GameModel gameModel;

    /**
     * constructor
     * @param player player with this strategy
     */
    public AggressiveStrategy(PlayerModel player, GameModel gameModel) {
        name = "Aggressive";
        this.player = player;
        this.gameModel = gameModel;
    }


    /**
     * Get name
     * @return name
     */
    @Override
    public String getName() {
        return name;
    }

    public ArrayList<CountryModel> getAttackableNeighbours(CountryModel country) {
        ArrayList<CountryModel> neighbours = country.getNeighbours();
        ArrayList<CountryModel> attackableNeighbours = new ArrayList<CountryModel>();
        for (CountryModel neighbour: neighbours) {
            if (!neighbour.getOwner().equals(country.getOwner()))
                attackableNeighbours.add(neighbour);
        }
        //sort it to make sure the weakest country is attacked first
        Collections.sort(attackableNeighbours, new Comparator<CountryModel>() {
            @Override
            public int compare(CountryModel o1, CountryModel o2) {
                return (o1.getArmyNum()>=o2.getArmyNum())?(o1.getArmyNum()>o2.getArmyNum()?1:0):-1;
            }
        });
        return attackableNeighbours;
    }
    /**
     * Reinforcement method
     * reinforces its strongest country
     */
    @Override
    public void reinforcement() {

        CountryModel destination = null;
        destination = getStrongestCountry(player.getPlayerCountries());

        if (destination != null) {
            int armyLeft = player.getNumReinforceArmyRemainPlace();
            player.setNumReinforceArmyRemainPlace(0);
            player.setTotalNumReinforceArmy(0);
            player.addArmyNum(armyLeft);
            destination.addArmyNum(armyLeft);
            System.out.println("Place Reinforcement Army Succeed! "+ player.getPlayerName()
                    + " added all the armies to " + destination.getCountryName());
            System.out.println(destination.getCountryName()+" has "+destination.getArmyNum() +" armies");

            if (player.getCardList().size()>=5) {
                gameModel.exchangeCards(0,1,2);
            }
        }else{
            System.out.println("Assign to a random country since there are no country attackable");
        }
    }

    /**
     * This metohd returns the Strongest Country from the countryList
     * @param countryList
     * @return
     */

    public CountryModel getStrongestCountry(ArrayList<CountryModel> countryList) {
        CountryModel strongestCountry = null;
        int max = 0;
        for (CountryModel country: countryList) {

            if (getAttackableNeighbours(country).isEmpty()){
               continue;
            }
            if(max<country.getArmyNum()){
                max = country.getArmyNum();
                strongestCountry = country;
            }

        }
        return strongestCountry;
    }
    /**
     * Attack method
     * always attack with the strongest country until it cannot attack anymore
     */
    @Override
    public void attack(){

        CountryModel strongestCountry = getStrongestCountry(player.getPlayerCountries());
        if (strongestCountry != null) {
            System.out.println(strongestCountry.getCountryName()+" is the strongest country of "+player.getPlayerName());
            for (CountryModel defendCountry : getAttackableNeighbours(strongestCountry)) {
                if (strongestCountry.getArmyNum()<2)
                    return;
                System.out.println("============="+strongestCountry.getCountryName()+" Attack starts=============");
                gameModel.attackAllOut(strongestCountry.getCountryName(),defendCountry.getCountryName());
                if (gameModel.isIfAttackerWin()){
                    gameModel.winnerMove(gameModel.attackerDice.size());
                }
            }

        }else
        {
            System.out.println("NO Attack");
        }
    }


    /**
     * Fortification method
     * maximize aggregation of forces in one country
     */
    @Override
    public void fortification() {

        CountryModel targetCountry = getStrongestCountry(player.getPlayerCountries());
        CountryModel sourceCountry = null;
        ArrayList<CountryModel> candidateList = new ArrayList<>();
        for (CountryModel country: player.getPlayerCountries()) {
            candidateList.add(country);
        }
        candidateList.remove(targetCountry);

        int max = 1;

        if (targetCountry != null) {
            for (CountryModel country : candidateList) {
                ArrayList<Boolean> visitedCountryList=new ArrayList<>();
                for (int i = 0; i < gameModel.getMapModel().getCountryList().size(); i++) {
                    visitedCountryList.add(false);
                }
                if (country.getArmyNum()>max && gameModel.existPath(country, targetCountry,visitedCountryList)){
                    max = country.getArmyNum();
                    sourceCountry = country;
                }
            }
            if (sourceCountry != null)
                gameModel.fortify(sourceCountry.getCountryName(),targetCountry.getCountryName(),sourceCountry.getArmyNum()-1);
            else
                gameModel.fortifyNone();
        }else{
            gameModel.fortifyNone();
        }

    }
}
