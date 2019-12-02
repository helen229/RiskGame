package Strategy;
import GamePlayModel.PlayerModel;
import MapEditorModel.CountryModel;
/**
 * Human strategy class
 */
public class HumanStrategy implements Strategy {

    private String name;
    private PlayerModel player;

    /**
     * constructor
     * @param player player with this strategy
     */
    public HumanStrategy(PlayerModel player) {
        name = "Human";
        this.player = player;
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

    }



    /**
     * Attack method
     * always attack with the strongest country until it cannot attack anymore
     * @param attacker null
     * @param attackerNum 0
     * @param defender null
     * @param defenderNum 0
     * @param isAllOut true
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

    }
}
