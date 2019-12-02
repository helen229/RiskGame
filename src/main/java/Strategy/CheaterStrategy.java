
package Strategy;
import GamePlayModel.GameModel;
import GamePlayModel.PlayerModel;
import MapEditorModel.CountryModel;
import java.util.ArrayList;
import static java.lang.System.exit;

/**
 * Cheater Strategy class
 */
public class CheaterStrategy implements Strategy{

    private String name;
    private PlayerModel player;
    private GameModel gameModel;

    /**
     * constructor
     * @param player player with this strategy
     */
    public CheaterStrategy(PlayerModel player, GameModel gameModel) {
        name = "Cheater";
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
        ArrayList<CountryModel> playerCountries=player.getPlayerCountries();
        int totalArmyLeft=0;
        int leftArmy=0;
        player.setTotalNumReinforceArmy(0);
//        if (player.getTotalNumReinforceArmy()<3) player.setTotalNumReinforceArmy(3);
        player.setNumReinforceArmyRemainPlace(0);
        
        //for (CountryModel country: playerCountries){
        //    leftArmy=country.getArmyNum();
        //    totalArmyLeft=totalArmyLeft+leftArmy;
        //}
        totalArmyLeft=player.getTotalNumArmy();
        
        for (CountryModel country: playerCountries){
            leftArmy=(country.getArmyNum()*2);
            country.setArmyNum(leftArmy);
            player.addArmyNum(leftArmy/2);
            System.out.println(player.getPlayerName()+" from "+totalArmyLeft+" total reinforcement army/armies, added "+
                        (leftArmy/2)+" army/armies to "+ 
                       country.getCountryName());
        }
        if (player.getCardList().size()>=5) {
            gameModel.exchangeCards(0,1,2);
        }
        System.out.println(player.getPlayerName()+" placed all Reinforcement armies successfully! ");
        player.setNumArmyRemainPlace(0);
    }



    /**
     * Attack method
     * always attack with the strongest country until it cannot attack anymore
     */
    @Override
    public void attack(){
        ArrayList<CountryModel> playerCountries=player.getPlayerCountries();
        ArrayList<CountryModel> newOwnedCountries=new ArrayList<CountryModel>();
        int leftArmy=0;
        System.out.println("======= " +player.getPlayerName()+ " Attack starts by cheating.=========");
        for (CountryModel country: playerCountries){
            ArrayList<CountryModel> neighbours = country.getNeighbours();
            for (CountryModel neighbour: neighbours) {
                if (!neighbour.getOwner().equals(country.getOwner())){
                    leftArmy=neighbour.getArmyNum();
                    neighbour.setArmyNum(1);
                    neighbour.getOwner().reduceArmyNum(leftArmy);
                    neighbour.getOwner().getPlayerCountries().remove(neighbour);
                    neighbour.setOwner(player);
                    player.addArmyNum(1);
                    newOwnedCountries.add(neighbour);
                    System.out.println("Attaks from "+country.getCountryName()+
                            " to "+neighbour.getCountryName()+
                            " and magically conquered it.");
                }
            }
        }
        for (CountryModel country: newOwnedCountries) {
            playerCountries.add(country);
        }
        if (player.getPlayerCountries().size() == gameModel.getMapModel().getCountryList().size()) {
                System.out.println("GAME END! " + player.getStrategy().getName()+ " WIN");
                if (gameModel.getGameMode().equals("Single")){
                    exit(0);
                }else {
                    gameModel.setGameEnd(true);
                    gameModel.setGameWinner(player.getStrategy().getName()); 
                    System.out.println("---------------------------------------GAME END");
                }
            }
    }



    /**
     * Fortification method
     * maximize aggregation of forces in one country
     * @param source from source
     * @param target to target
     * @param armyNumber move num of army
     */
    @Override
    public void fortification() {
        ArrayList<CountryModel> playerCountries=player.getPlayerCountries();
        ArrayList<CountryModel> candidateList = new ArrayList<CountryModel>();
        for (CountryModel country: playerCountries){
            ArrayList<CountryModel> neighbours = country.getNeighbours();
            for (CountryModel neighbour: neighbours) {
                if ((!neighbour.getOwner().equals(country.getOwner()))&&
                        !(candidateList.contains(country))){
                    System.out.println("The Magic path is valid, fortify succeed. Fortify "+
                            country.getArmyNum()+
                            " army/armies to "+country.getCountryName()+
                            ". It has now "+(country.getArmyNum()*2)+
                            " army/armies.");
                    country.setArmyNum(country.getArmyNum()*2);
                    candidateList.add(country);
                }
            }
        }
        if (candidateList.isEmpty()) {
            System.out.println("Fortification is not possible for of the "+
                    player.getPlayerName());
        }
        gameModel.fortifyNone();
    }
}
