import static java.lang.System.exit;

import java.util.ArrayList;

import MapEditorModel.CountryModel;

	/**
	* This is a method on game model class
     * This method allows a player to fortify a country with armies from another related country
     */

    public void fortify(String fromcountry, String tocountry, int number) {
	    if ((mapModel.indexOfCountry(fromcountry)!=-1)&&(mapModel.indexOfCountry(tocountry)!=-1)&&(number>=0)) {

        CountryModel sourceCountry= mapModel.getCountryList().get(mapModel.indexOfCountry(fromcountry));
        CountryModel targetCountry= mapModel.getCountryList().get(mapModel.indexOfCountry(tocountry));
        ArrayList<Boolean> visitedCountryList=new ArrayList<>();
        for (int i = 0; i < mapModel.getCountryList().size(); i++) {
            visitedCountryList.add(false);
        }
        //can't less than one army
        if ((sourceCountry.getArmyNum()-number)<1){
            System.out.println("the Army number is greater than the fromcountry number! Please try again");
            return;
        }
        if (sourceCountry.getOwner().getPlayerName().equals(targetCountry.getOwner().PlayerName)){
           if (!currentPlayer.getPlayerName().equals(sourceCountry.getOwner().getPlayerName())){
               System.out.println("the two countries are not belong to current player! Please try again");
               return;
           }
        }else{
            System.out.println("the two countries are not belong to same player! Please try again");
            return;
        }

        if (existPath(sourceCountry,targetCountry,visitedCountryList)){
            sourceCountry.setArmyNum(sourceCountry.getArmyNum()-number);
            targetCountry.setArmyNum(targetCountry.getArmyNum()+number);
            System.out.println("this path is validate, fortify succeed");
            this.fortifyNone();
        }else {
            System.out.println("this path is not validate");
        }
        }else {
            System.out.println("Country name/number is not valid! please try again");
        }
    } 
    

} 
/**
 * This method checks if a path exist between the two countries
 */

public boolean existPath (CountryModel country1, CountryModel country2, ArrayList<Boolean> visited) {
    ArrayList<Integer> neighbours = country1.getNeighbours();
    for (Integer neighbour : neighbours) {
        if (visited.get(neighbour)) {
            continue;
        }
        visited.set(neighbour, true);
        CountryModel neighbourCountryModel = mapModel.getCountryList().get(neighbour);
        if (neighbourCountryModel.getOwner().getPlayerName().equals(country1.getOwner().getPlayerName())) {
            if (neighbour == country2.getCountryValue()) {
                return true;
            }
            boolean b = existPath(neighbourCountryModel, country2, visited);
            if (b) {
                return true;
            }
        }
    }
    return false;
} 
/**
 * This method fortifies no countries for the player
 */

public void fortifyNone() {
	
	if (hasPlayerConquered){
            System.out.println(getCurrentPlayer().getPlayerName()+" You have conquered at least one country!");
            Card card = new Card(currentPlayer);
            CardType cardType = card.getCardType();
            currentPlayer.addCard(card);
            System.out.println("You receives a "+cardType+"card");
            hasPlayerConquered = false;
        }

    System.out.println(getCurrentPlayer().getPlayerName()+" Your turn over!");
  if (this.currentPlayerNum+1==this.playerList.size()){
            this.currentPlayerNum = 0;
        }else
            this.currentPlayerNum++;
        setCurrentPlayer(this.playerList.get(this.currentPlayerNum));
        currentPlayer.setTotalNumArmy(this.playerList.size());
        currentPlayer.setNumArmyRemainPlace(currentPlayer.getTotalNumArmy());
        this.setPhase("Startup");
        System.out.println("Start Placing army, Current Player is "+ getCurrentPlayer().getPlayerName());
    }else{
        System.out.println("GAME END");
        exit(0);
    }

}
}
