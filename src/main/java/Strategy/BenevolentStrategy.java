package Strategy;
import GamePlayModel.GameModel;
import GamePlayModel.PlayerModel;
import MapEditorModel.CountryModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Benevolent Strategy class
 */
public class BenevolentStrategy implements Strategy{


    private String name;
    private PlayerModel player;
    private GameModel gameModel;

    /**
     * constructor
     * @param player player with this strategy
     */
    public BenevolentStrategy(PlayerModel player, GameModel gameModel) {
        name = "Benevolent";
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
    /**
     * Reinforcement method
     * reinforces its strongest country
     */
    @Override
    public void reinforcement() {

        CountryModel destination = null;
        destination = getWeakestCountry(player.getPlayerCountries());

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


    public CountryModel getWeakestCountry(ArrayList<CountryModel> countryList) {
        CountryModel weakestCountry = null;
        int min = 1000000;
        for (CountryModel country: countryList) {

            if(min > country.getArmyNum()){
                min = country.getArmyNum();
                weakestCountry = country;
            }

        }
        return weakestCountry;
    }
    /**
     * Attack method
     * always attack with the strongest country until it cannot attack anymore
     */
    @Override
    public void attack(){

    }


    /**
     * Fortification method
     * maximize aggregation of forces in one country
     */
    @Override
    public void fortification() {

        CountryModel targetCountry = getWeakestCountry(player.getPlayerCountries());
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
