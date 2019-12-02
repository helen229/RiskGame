package Strategy;
import GamePlayModel.GameModel;
import GamePlayModel.PlayerModel;
import MapEditorModel.CountryModel;
import java.util.ArrayList;
/**
 * Random strategy class
 */
public class RandomStrategy implements Strategy{

    private String name;
    private PlayerModel player;
    private GameModel gameModel;

    /**
     * constructor
     * @param player player with this strategy
     */
    public RandomStrategy(PlayerModel player, GameModel gameModel) {
        name = "Random";
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
        return attackableNeighbours;
    }

        public boolean checkAttackChance() {
        boolean ifAttackContinue = false;
        for (CountryModel playerCountry : player.getPlayerCountries()) {
            if (playerCountry.getArmyNum()<2){
                continue;
            }else if (!ifCountriesBelongPlayer(playerCountry.getNeighbours())){
                ifAttackContinue = true;
            }
        }
        return ifAttackContinue;
    }


    public boolean ifCountriesBelongPlayer(ArrayList<CountryModel> countryList) {
        for (CountryModel country:countryList) {
            if (!player.equals(country.getOwner())){
                return false;
            }
        }
        return true;
    }
    
    public ArrayList<CountryModel> getFortificationCountries(ArrayList<CountryModel> playerCountries) {
        ArrayList<CountryModel> candidateList = new ArrayList<CountryModel>();
        for (CountryModel country: playerCountries){
            if (country.getArmyNum()>=2){
                ArrayList<CountryModel> neighbours = country.getNeighbours();
                for (CountryModel neighbour: neighbours) {
                    if ((neighbour.getOwner().equals(country.getOwner()))&&
                            !(candidateList.contains(country)))
                        candidateList.add(country);
                }
            }
        }
        return candidateList;
    }
    
    public ArrayList<CountryModel> getAllNeighboursBelongPlayer(CountryModel country) {
        ArrayList<CountryModel> candidateList = new ArrayList<CountryModel>();
        ArrayList<CountryModel> neighbours = country.getNeighbours();
        for (CountryModel neighbour: neighbours) {
            if ((neighbour.getOwner().equals(country.getOwner()))&&
                    !(candidateList.contains(neighbour)))
                candidateList.add(neighbour);
        }
        return candidateList;
    }
    /**
     * Reinforcement method
     * reinforces randomly number of armies to the randomly owned country
     */
    @Override
    public void reinforcement() {
        player.setTotalNumReinforceArmy(player.getPlayerContinents().size()+(player.getPlayerCountries().size()/3));
        if (player.getTotalNumReinforceArmy()<3) player.setTotalNumReinforceArmy(3);
        player.setNumReinforceArmyRemainPlace(player.getTotalNumReinforceArmy());
        if (player.getCardList().size()>=5) {
            gameModel.exchangeCards(0,1,2);
        } 
        int armyLeft=player.getNumReinforceArmyRemainPlace();
        int totalArmyLeft=armyLeft;
        ArrayList<CountryModel> playerCountries=player.getPlayerCountries();
        if (armyLeft>0) {
            player.addArmyNum(armyLeft);
            while (armyLeft>0){
                int selectedCountry=(int)(Math.random() * (playerCountries.size()));
                int reinforcementNumber=(int)(Math.random() * (armyLeft));
                if (reinforcementNumber==0) reinforcementNumber=armyLeft; //To finish faster
                playerCountries.get(selectedCountry).setArmyNum(playerCountries.get(selectedCountry).getArmyNum()+reinforcementNumber);
                armyLeft=armyLeft-reinforcementNumber;
                System.out.println(player.getPlayerName()+" from "+totalArmyLeft+" total reinforcement army/armies, added "+
                        reinforcementNumber+" army/armies to "+ 
                        playerCountries.get(selectedCountry).getCountryName());
            }
            player.setNumArmyRemainPlace(0);
        } else {
            //System.out.println(player.getPlayerName()+" dosent have any countries or Reinforcement armies. Reinforcement skipped.");
        }
        System.out.println(player.getPlayerName()+" placed all Reinforcement armies successfully! ");
    }



    /**
     * Attack method
     * attack from a randomly selected country to another randomly selected neighbor (if attack possible) for randomly turns (or less)
     */
    @Override
    public void attack(){
        int selectedAttackCountry=0;
        int selectedDefenseCountry=0;
        int numberOfAttack=0;
        ArrayList<CountryModel> playerCountries=player.getPlayerCountries();
        CountryModel attackCountry=null;
        CountryModel defenseCountry = null;
        if (checkAttackChance()){
            do {
            selectedAttackCountry=(int)(Math.random() * playerCountries.size());
            }while ((getAttackableNeighbours(playerCountries.get(selectedAttackCountry)).size()==0)||
                    (playerCountries.get(selectedAttackCountry).getArmyNum()<2));
            
            attackCountry=playerCountries.get(selectedAttackCountry);
            System.out.println(attackCountry.getCountryName()+
                    " is Randomly selected as the attacker country of the "+
                    player.getPlayerName());
            
            selectedDefenseCountry=(int)(Math.random() * getAttackableNeighbours(attackCountry).size());
            defenseCountry=getAttackableNeighbours(attackCountry).get(selectedDefenseCountry);
            
            System.out.println(defenseCountry.getCountryName()+
                " is Randomly selected as the defense country of the "+
                defenseCountry.getOwner().getPlayerName());
            
            numberOfAttack=(int)(Math.random() * (attackCountry.getArmyNum()-1));
            System.out.println("======= " +attackCountry.getCountryName() + " Attack starts for randomly "+numberOfAttack+" turns (or less).=========");
            if (numberOfAttack==0) {
                //System.out.println("Attack none.");
                numberOfAttack=attackCountry.getArmyNum()-1; // To finish the game faster.
            }
            while (numberOfAttack>0){
                numberOfAttack=numberOfAttack-1;
                int diceNum=0;
                if (attackCountry.getArmyNum()>3){
                    diceNum=3;
                }else if (attackCountry.getArmyNum()==3){
                    diceNum=2;
                }else if (attackCountry.getArmyNum()==2){
                    diceNum=1;
                }else{
                    numberOfAttack=0;
                    System.out.println("The attack country can't attack!");
                    return;
                }
                if (!gameModel.attackDiceNum(attackCountry.getCountryName(),defenseCountry.getCountryName(), diceNum, true))
                    break;
                if (gameModel.isIfAttackerWin()){
                    numberOfAttack=0;
                    gameModel.winnerMove(gameModel.attackerDice.size());
                }
            }
        } else {
            System.out.println("NO Attack is availbe");
        }    
        
    }



    /**
     * Fortification method
     * Fortify randomly number of armies from the randomly selected country to randomly selected country
     */
    @Override
    public void fortification() {
        //gameModel.fortifyNone();

        int targetCountryValue=0;
        int sourceCountryValue=0;
        int fortificationArmyNum=0;
        ArrayList<CountryModel> playerCountries=player.getPlayerCountries();
        if (playerCountries.size()>1) {
            ArrayList<CountryModel> targetCountries = new ArrayList<CountryModel>();
            ArrayList<CountryModel> sourceCountries = getFortificationCountries(playerCountries);
            if (sourceCountries.size()>=1){
                sourceCountryValue=(int)(Math.random() * (sourceCountries.size()));
                System.out.println(sourceCountries.get(sourceCountryValue).getCountryName()+
                    " is Randomly selected as the fortification source country of the "+
                    player.getPlayerName());
                targetCountries=getAllNeighboursBelongPlayer(sourceCountries.get(sourceCountryValue));
                targetCountryValue=(int)(Math.random() * (targetCountries.size()));
                System.out.println(targetCountries.get(targetCountryValue).getCountryName()+
                    " is Randomly selected as the fortification destination country of the "+
                    player.getPlayerName());
                fortificationArmyNum=(int)(Math.random() * (sourceCountries.get(sourceCountryValue).getArmyNum()-1));
                System.out.println(fortificationArmyNum+
                    " is Randomly selected as the number of fortification artmy/armies.");
                gameModel.fortify(sourceCountries.get(sourceCountryValue).getCountryName(),targetCountries.get(targetCountryValue).getCountryName(),fortificationArmyNum);
            } else {
                System.out.println("Fortification is not possible for of the "+
                    player.getPlayerName());
                gameModel.fortifyNone();
            }
        } else {
            gameModel.fortifyNone();
        }
    }
}
