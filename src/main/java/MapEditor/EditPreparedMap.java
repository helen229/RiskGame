package MapEditor;
import MapEditorModel.ContinentModel;
import MapEditorModel.CountryModel;
import MapEditorModel.MapModel;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class EditPreparedMap {



    private  static final String MAP_TAG= "[Map]";
    private static final String MAP=" Map";
    private static final String CONTINENTS_TAG = "[Continents]";
        private static final String CONTINENT = " continent";
        private static final String COUNTRY_TAG = "[Territories]";
        private static final String COUNTRY = "country";
        private static final String BORDERS = "borders";
        private static final String BORDERS_TAG = "[Territories]";

    MapHandler m = new MapHandler();


//    private ArrayList<ContinentModel> continents = new ArrayList<ContinentModel>();
//    private ArrayList<CountryModel> countries = new ArrayList<CountryModel>();


        private final String fileDirectory;
        private ArrayList<ArrayList<String>> neighbourRelationList = new ArrayList<ArrayList<String>>();

        private ArrayList<ArrayList<Integer>> neighbourRelationNumberList = new ArrayList<ArrayList<Integer>>();
        private HashMap<Integer,ArrayList<Integer>> neighbourRelationNumberMap = new HashMap<>();


        public EditPreparedMap(String fileDirectory) {
            this.fileDirectory = fileDirectory;
        }

        /**
         * This method checks the content of the file and stores the continents and countries appropriately
         * including their association with each other.
         *
         * @throws IOException
         */
        MapModel mapModel = new MapModel();

    int continentValue = 0;
    int countryValue = 0;
        public MapModel checkPreparedFile() throws IOException {
            FileReader file = new FileReader(fileDirectory);
            BufferedReader br = new BufferedReader(file);
            String line = br.readLine();
            String[] splitLine = line.split(" ");
            String checker = " ";
//            MapModel mapModel = new MapModel();
            ArrayList<ContinentModel> continents = mapModel.getContinentList();
            ArrayList<CountryModel> countries = mapModel.getCountryList();

            if (line != null) {
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
                                    String[] continentData = line.split("=");
                                    mapModel.addContinent(continentData[0], continentValue++);
//                                ContinentModel c1 = new ContinentModel(continentData[0], Integer.parseInt(continentData[1]));
//                                continents.add(c1);
//                                CountryList = c1.getCountriesList();
//                                for (String country : CountryList) {
//                                    c1.addCountryToList(country);
//                                }

                                } else {
                                    continue;
                                }

                                break;


                            case COUNTRY:
                                ArrayList<Integer> neigbour = new ArrayList<>();
                                if (size > 0) {
                                    String[] countryData = line.split(",");
                                    String[] borderData = line.trim().split(",");

                                    mapModel.addCountry(countryData[0], countryData[3]);
                                    mapModel.addCountryNumberNamePair(countryData[0], countryValue++);
                                    int id = mapModel.getCountryNumberFromName(borderData[0]);
                                    String firstCountryNameID = borderData[0];
                                    ArrayList<String> adjacentNamesList = new ArrayList<>();
                                    for (int i = 0; i < borderData.length; i++) {
                                        if (i != 1&& i!= 2&& i != 3){
                                            adjacentNamesList.add(borderData[i]);
                                        }

                                    }
                                    neighbourRelationList.add(adjacentNamesList);
                                } else {
                                    continue;
                                }

                                break;


                        }

                    }
                }
            } else {
                System.out.println(" File is Empty");
            }

            for (int i=0; i<neighbourRelationList.size();i++){
                ArrayList<Integer> neighbourNumbers = new ArrayList<>();
                for (int j=0; j<neighbourRelationList.get(i).size();j++){

                    //System.out.println(neighbourRelationList.get(i).get(j));
                    int m = mapModel.getCountryNumberFromName(neighbourRelationList.get(i).get(j));
                    //System.out.println(m);

                    neighbourNumbers.add(m);
                    //System.out.println(neighbourNumbers.size());

                    //neighbourRelationNumberMap.put()


                }
                neighbourRelationNumberList.add(i,neighbourNumbers);
            }
            for (int i=0; i<neighbourRelationNumberList.size();i++){
                int index = neighbourRelationNumberList.get(i).get(0);

                ArrayList<CountryModel> adjacent = new ArrayList<>();
                for (int j=1; j<neighbourRelationNumberList.get(i).size();j++){
                    adjacent.add(mapModel.getCountryList().get(neighbourRelationNumberList.get(i).get(j)));
                }
                int number= mapModel.indexOfCountry(index);

                countries.get(number).setNeighbours(adjacent);
            }

            file.close();
            return mapModel;
        }


}
