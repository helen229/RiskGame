package MapEditorController;

import MapEditorModel.ContinentModel;
import MapEditorModel.CountryModel;
import MapEditorModel.MapModel;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * The class ReadFile loads the content of a file into a map and
 * allows the content to be retrieved by other classes.
 */
public class ReadFile {


    private  static final String CONTINENTS_TAG= "[continents]";
    private static final String CONTINENT=" continent";
    private static final String COUNTRY_TAG= "[countries]";
    private static final String COUNTRY= "country";
    private static final String BORDERS = "borders";
    private static final String BORDERS_TAG = "[borders]";


    private ArrayList<ContinentModel> continents = new ArrayList<ContinentModel>();
    private ArrayList<CountryModel> countries = new ArrayList<CountryModel>();


    private final String fileDirectory;


    public ReadFile (String fileDirectory) {
        this. fileDirectory = fileDirectory;
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
        MapModel mapModel= new MapModel();
        mapModel.setContinentList(continents);
        mapModel.setCountryList(countries);

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
                        ArrayList<String> CountryList = new ArrayList<>();

                        if (size > 0) {
                            String[] continentData = line.split(" ");
                            ContinentModel c1 = new ContinentModel(continentData[0], Integer.parseInt(continentData[1]));
                            continents.add(c1);
                            CountryList = c1.getCountriesList();
                            for (String country : CountryList) {
                                c1.addCountryToList(country);
                            }

                        } else {
                            continue;
                        }

                        break;


                    case COUNTRY:
                        ArrayList<Integer> neigbour = new ArrayList<>();
                        if (size > 0) {
                            String[] countryData = line.split(" ");

                            CountryModel country = new CountryModel(Integer.parseInt(countryData[0]), countryData[1], countryData[2]);

                            countries.add(country);

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

                        int Index=   mapModel.indexOfCountry(id);

                            countries.get(Index).setNeighbours(adjacent);
                        }else{
                            continue;
                        }
                        break;


                }

            }
        } }
        else{
            System.out.println(" File is Empty");
        }
        mapModel.showMap();
        file.close();
    }



    /**
     *  This class returns an arraylist containing the list of  continents
     * @return  Arraylists of continents
     */

    public ArrayList<ContinentModel> getContinents() {
        return continents;
    }

    /**
     * This class returns an arraylist of the countries
     * @return Arraylist of countries.
     */


    public ArrayList<CountryModel> getCountries() {
        return countries;
    }




}





