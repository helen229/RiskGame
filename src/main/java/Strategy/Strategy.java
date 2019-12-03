package Strategy;

import MapEditorModel.CountryModel;

/**
 * the interface of player strategy
 */
public interface Strategy {

    void reinforcement( );

    /**
     * attack method
     */
    void attack();


    /**
     * fortification method
     */
    void fortification();

    /**
     * get the strategy name
     * @return name of strategy
     */
    String getName();

}
