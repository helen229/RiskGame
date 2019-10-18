package GamePlayController;

import GamePlayModel.ContinentModel;
import GamePlayModel.CountryModel;
import GamePlayModel.PlayerModel;
import GamePlayModel.GameModel;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * This class reads the map file and add none owner ship and 0 army to countries.
 * @author Ehsan
 */
public class ReadFile {
    
    private static final String CONTINENTS_TAG= "[continents]";
    private static final String CONTINENT="continent";
    private static final String COUNTRY_TAG= "[countries]";
    private static final String COUNTRY= "country";
    private static final String BORDERS = "borders";
    private static final String BORDERS_TAG = "[borders]";

    private ArrayList<ContinentModel> continentList = new ArrayList<ContinentModel>();
    private ArrayList<CountryModel> countryList = new ArrayList<CountryModel>();

    private final String fileDirectory;

    public ReadFile (String fileDirectory) {
        this. fileDirectory = fileDirectory;
        try{
        checkFile();
        }
        catch (IOException e){
            e.printStackTrace();
        }
        getContinents();
        getCountries();
    }

    /**
     * This method checks the content of the file and stores the continents and countries appropriately
     * including their association with each other.
     * @throws IOException
     */
    public void checkFile() throws IOException {
        FileReader file = new FileReader(fileDirectory);
        BufferedReader br = new BufferedReader(file);
        String line = br.readLine();
        String[] splitLine = line.split(" ");
        String checker = " ";
        GameModel gameModel= new GameModel();
        gameModel.setContinentList(continentList);
        gameModel.setCountryList(countryList);

        if(line!=null) {
            while (line != null) {
                line = br.readLine();
                if (line != null) {
                    if (line.equals(CONTINENTS_TAG)) {
                        checker = CONTINENT;
                        continue;
                    } else if (line.equals(COUNTRY_TAG)) {
                        checker = COUNTRY;
                        continue;
                    } else if (line.equals(BORDERS_TAG)) {
                        checker = BORDERS;
                        continue;
                    }
                    
                    
                    line.trim();
                    int size = line.length();
                    switch (checker) {
                        case CONTINENT:
                            if (size > 0) {
                                String[] continentData = line.split(" ");
                                ContinentModel c1 = new ContinentModel(continentData[0], Integer.parseInt(continentData[1]));
                                continentList.add(c1);
                            } else {
                                continue;
                            }
                            break;
                        
                        
                        case COUNTRY:  // Add 0 army and -1 as the value of the country owner to the new object contry at CountryModel class.
                            ArrayList<Integer> neigbour = new ArrayList<>();
                            if (size > 0) {
                                String[] countryData = line.split(" ");
                                CountryModel country = new CountryModel(Integer.parseInt(countryData[0]), countryData[1], countryData[2],0 ,-1);
                                countryList.add(country);
                                neigbour = country.getNeighbours();
                                for (int neighbourCountry : neigbour) {
                                    country.addNeighbour(neighbourCountry);
                                }
                            } else {
                                continue;
                            }
                            break;
                        
                        
                        case BORDERS:
                            if(size>0) {
                                String[] borderData = line.trim().split(" ");
                                int id = Integer.parseInt(borderData[0]);
                                ArrayList<Integer> adjacent = new ArrayList<>();
                                for (int i = 1; i < borderData.length; i++) {
                                    adjacent.add(Integer.valueOf(borderData[i]));
                                }
                                int Index=gameModel.indexOfCountry(id);
                                countryList.get(Index).setNeighbours(adjacent);
                            }else{
                                continue;
                            }
                            break;
                    }
                }
            }
        }
        else{
            System.out.println(" File is Empty");
        }
        gameModel.showMap();
        file.close();
    }


    /**
     *  This class returns an arrayList containing the list of continents
     * @return  continentList
     */
    public ArrayList<ContinentModel> getContinents() {
        return continentList;
    }

    /**
     * This class returns an arrayList of the countries
     * @return countryList
     */
    public ArrayList<CountryModel> getCountries() {
        return countryList;
    } 
}
