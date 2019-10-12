package fortification_phase;

import java.util.*;

/**
 * This class is a fortification phase of risk game that each player can fortify during this phase
 * @author
 * @ version 4.5.2
 */


public class Fortification {
	
	
  /**
  * this method decrease a number of army from source class
  * @param armyNumber the number of source country`s army  
  */
	public static void decreaseArmy(int armyNumber) {

		army -= army;
	}
	
   /**
	* this method increase a number of army in destination class
	* @param armyNumber the number of destination country`s army  
	*/

	public static void increaseArmy(int armyName) {

		army += army;
	}

	String sourceCountryName;
	String destinationCountryName;
	private ArrayList countOfPlayer; 
	static int army;
	int playerNumber;
        Player currentPlayer;
	CountryModel destinationCountry;
	CountryModel sourceCountry;
	MapModel boardMap;
	
	/**
	 * this method is main method for fortification phase that check several condition for 
	 * eligibility of fortification phase like ownership,connection between countries and 
	 * the number of armies
	 * 
	 * @param sourceCountryName  this is a name of source country want to fortify
	 * @param destinationCountryName  this is a name of destination country want to get army from source country
	 */

	public String fortify(String sourceCountryName, String destinationCountryName) {

		
		  for(int i = 0; i < countOfPlayer.size(); i++) {
			  
			    sourceCountry = boardMap.getcountryName(sourceCountryName);
				destinationCountry = boardMap.getcountryName(destinationCountryName);
				playerNumber = currentPlayer.getplayerNumber();
				System.out.println("this is a turn of player" + playerNumber);

				// possibility of fortification phase
				
				
				String source= sourceCountry.isOwner();
				String destination= destinationCountry.isOwner();

				if (currentPlayer.equals(source)&& currentPlayer.equals(destination)) {
						
						// Check player owns sourceCountry and destinationCountry or not
					
						 

						if (mainBoard.adjacency(sourceCountryName, destinationCountryName) == true) {

							// Check sourceCountry and destinationCountry are connected or not
							
							

							army = sourceCountry.getarmy();

							if (army > 1) {
								
								// Check sourceCountry`s army 
								

								System.out.println(" player" + currentPlayer.getplayerNumber() + " can fortify from " + sourceCountryName + " with " + army + " army to " + destinationCountryName);
										
								sourceCountry.decreaseArmy(army);
								destinationCountry.increaseArmy(army);

								System.out.println("the number of army for" + sourceCountryName + "is" + army);
								System.out.println("the number of army for" + destinationCountryName + "is" + army);

							}

							else {
								System.out.println("player, you do not have enough army in " + sourceCountryName + " to send " + destinationCountryName);
										
							}

							
						}

						else {
							System.out.println("player, " + sourceCountryName + " is not Connected to " + destinationCountryName);
						}

					
				}

				else {
					System.out.println("player, you do not have both " + sourceCountryName + " and " + destinationCountryName);
				}
				

			}
	}

}
