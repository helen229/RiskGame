import sun.awt.SunHints;
import java.util.ArrayList;

public class CountryModel {

    private int countryValue;

    private String countryName;

    private String continentName;

    private ArrayList<Integer> neighbours;

	 public int army;

    public CountryModel(int countryID, String countryName, String continentName) {
        this.countryValue = countryID;
        this.countryName = countryName;
        this.continentName = continentName;
        this.neighbours = new ArrayList<Integer>();
       
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }


    public String getContinentName() {
        return continentName;
    }

    public int getCountryValue() {
        return countryValue;
    }

    public void setCountryValue(int countryValue) {
        this.countryValue = countryValue;
    }

    public void addNeighbour(int countryValue) {

        this.neighbours.add(countryValue);

    }

    public void removeNeighbour(int countryValue) {

        this.neighbours.remove(countryValue);

    }

    public ArrayList<Integer> getNeighbours() {
        return neighbours;
    }

    public void setNeighbours(ArrayList<Integer> neighbours) {
        this.neighbours = neighbours;
    }


    public String getCountryFileFormat() {
        return null; // 1 siberia 1 329 152
    }

    public String getBorderFileFormat() {
        return null; // 1 20 32 2

    }//    private int armyNum;
//
//    private PlayerModel owner;
    
    /**
     * this method decrease a number of army from source class
     * @param armyNumber the number of source country`s army  
     */
   	public void decreaseArmy(int armyNumber) {
   		

   		army -= army;
   	}
   	
      /**
   	* this method increase a number of army in destination class
   	* @param armyNumber the number of destination country`s army  
   	*/

   	public void increaseArmy(int armyName) {

   		army += army;
   	}
}