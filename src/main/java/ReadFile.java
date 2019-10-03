import MapEditorModel.ContinentModel;
import MapEditorModel.CountryModel;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class ReadFile {


    private  static final String CONTINENTS_TAG= "[continents]";
    private static final String CONTINENT=" continent";
    private static final String COUNTRY_TAG= "[countries]";
    private static final String COUNTRY= "country";


    private ArrayList<ContinentModel> continents = new ArrayList<ContinentModel>();
    private ArrayList<CountryModel> countries = new ArrayList<CountryModel>();

    private final String fileDirectory;

    /**
     *  This class read the name of the mapFile (File Directory) to be loaded.
     *
     * @param fileDirectory
     */
    public ReadFile (String fileDirectory) {
        this. fileDirectory = fileDirectory;
    }

    /**
     * This class  checks the details of the read file into an array  and stores them correctly based on countries and continents.
     *
     * @throws IOException
     */
    public void checkFile() throws IOException {
        FileReader file = new FileReader(fileDirectory);
        BufferedReader br = new BufferedReader(file);
        String line = br.readLine();
        String[] splitLine = line.split(" ");
        String checker = " ";
        while (line != null) {
            line = br.readLine();
            if(line != null){
                if (line.equals(CONTINENTS_TAG)) {
                    checker = CONTINENT;
                    continue;
                } else if (line.equals(COUNTRY_TAG) ){
                    checker = COUNTRY;
                    continue;
                }
                }

                line.trim();
            int size= line.length();
            switch (checker) {
                case CONTINENT:


                    if (size >0){
                        String[] continentData = line.split(" ");
                        ContinentModel c1 = new ContinentModel(continentData[0], Integer.parseInt(continentData[1]));
                        continents.add(c1);}
                    else{
                        continue;
                    }

                    break;


                case COUNTRY:

                    if ( size>0){
                        String [] countryData= line.split(" ");
                        CountryModel country = new CountryModel( Integer.parseInt( countryData[0] ), countryData[1],countryData[2]);
                        //  Country c2= new Country(Integer.parseInt(countryData[0]),)
                        countries.add(country);}
                    else{
                        continue;
                    }

                    break;

            }

        }


        file.close();
    }



    /**
     *  This class returns an arraylist containing the list of country and continents
     * @return  Arraylists of continents and countries repectively.
     */

    public ArrayList<ContinentModel> getContinents() {
        return continents;
    }



    public ArrayList<CountryModel> getCountries() {
        return countries;
    }




}





