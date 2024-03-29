package MapEditor;

import MapEditorModel.ContinentModel;
import MapEditorModel.CountryModel;
import MapEditorModel.MapModel;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * The class EditMap loads the content of a file into a map and
 * allows the content to be retrieved by other classes.
 */
public class EditMap {


    private  static final String CONTINENTS_TAG= "[continents]";
    private static final String CONTINENT=" continent";
    private static final String COUNTRY_TAG= "[countries]";
    private static final String COUNTRY= "country";
    private static final String BORDERS = "borders";
    private static final String BORDERS_TAG = "[borders]";
    private final String fileDirectory;


    public EditMap (String fileDirectory) {
        this.fileDirectory = fileDirectory;
    }

    /**
     * This method checks the content of the file and stores the continents and countries appropriately
     * including their association with each other.
     * @throws IOException
     */
    public MapModel checkFile() throws IOException {
        MapModel mapModel= new MapModel();
        File checkExistence= new File(fileDirectory);
        if(checkExistence.exists()){
        FileReader file = new FileReader(fileDirectory);
        BufferedReader br = new BufferedReader(file);
        String line = br.readLine();
        String checker = " ";
        
        ArrayList<ContinentModel> continents=mapModel.getContinentList();
        ArrayList<CountryModel> countries=mapModel.getCountryList();

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
                                mapModel.addContinent(continentData[0], Integer.parseInt(continentData[1]));

                            } else {
                                continue;
                            }

                            break;


                        case COUNTRY:
                            ArrayList<Integer> neigbour = new ArrayList<>();
                            if (size > 0) {
                                String[] countryData = line.split(" ");
                                mapModel.addCountryFromFile(Integer.parseInt(countryData[0]), countryData[1], countryData[2]);
                            } else {
                                continue;
                            }

                            break;

                        case BORDERS:
                            if(size>0) {
                                String[] borderData = line.trim().split(" ");
                                int id = Integer.parseInt(borderData[0]);
                                ArrayList<CountryModel> adjacent = new ArrayList<>();
                                for (int i = 1; i < borderData.length; i++) {
                                    adjacent.add(mapModel.getCountryList().get(mapModel.indexOfCountry(Integer.valueOf(borderData[i]))));
                                }
                                int index= mapModel.indexOfCountry(id);
                                countries.get(index).setNeighbours(adjacent);
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
        file.close();
        } else{
           checkExistence.createNewFile();
           System.out.println(" Map file "+fileDirectory + " does not exist , thus new Map File was created" );
       }
        return mapModel;
    }



}