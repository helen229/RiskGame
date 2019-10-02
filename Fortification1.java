package fortification_phase;

import java.util.*;


public class Fortification {
	

	static void decreaseArmy(int armyName) {

		army -= army;
	}

	static void increaseArmy(int armyName) {

		army += army;
	}

	String sourceCountryName;
	String destinationCountryName;
	static int army;
	boolean isMatch;
	Player currentPlayer;
	Player playerNumber;
	Country destinationCountry;
	Country sourceCountry;
	Board boardMap;

	public void fotrify(String sourceCountryName, String destinationCountryName) {

		sourceCountry = boardMap.getcountryName(sourceCountryName);
		destinationCountry = boardMap.getcountryName(destinationCountryName);

		int i, playerName;
		int countOfPlayer = 3;

		for (i = 1; i < countOfPlayer;) {

			playerName = currentPlayer.getPlayerName();

			if (playerName == i) {

				System.out.println("this is a turn of player with name" + i);

				// possibility of fortification phase
				
				String source= sourceCountry.getOccupant();
				String destination= destinationCountry.getOccupant();

				if (currentPlayer.equals(source)&& currentPlayer.equals(destination)) {
						

					if (isMatch == true) {

						// Check player owns sourceCountry and destinationCountry or not
						 

						if (mainBoard.adjacency(sourceCountryName, destinationCountryName) == true) {

							// Check sourceCountry and destinationCountry are connected or not

							army = sourceCountry.getarmy();

							if (army > 1) {
								
								// Check sourceCountry`s army 

								System.out.println(" player" + currentPlayer.getPlayerName() + " can fortify from " + sourceCountryName + " with " + army + " army to " + destinationCountryName);
										

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
				}

				else {
					System.out.println("player, you do not have both " + sourceCountryName + " and " + destinationCountryName);
				}

			}

			else {

				System.out.println("this is a turn for next player for fortication");
			}
			
			i = i++;

			// i increase so next player should be start

		} // end of for

	}// end of method

}// end of class
