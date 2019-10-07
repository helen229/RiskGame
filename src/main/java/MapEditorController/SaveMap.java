package MapEditorController;

import MapEditorModel.ContinentModel;
import MapEditorModel.CountryModel;
import MapEditorModel.MapModel;

import java.io.*;
import java.util.ArrayList;

/**
 * This class allows a user to create a new map file and write into a  map file
 */
public class SaveMap {


    private String FileName;
    private static final String CONTINENT = " continent";
    private static final String COUNTRY = " country";
    private static final String NEW_LINE_DELIMITER = "\n";

    /**
     * This  method allows a user to create  a new Map file and the method take a string as input and the input is used as
     * the name of the newly created file
     *
     * @param FileName the name of the File to be created
     * @throws IOException
     */
    public SaveMap(String FileName,MapModel mapModel) throws IOException {

       // PrintWriter writer = new PrintWriter(FileName);
        //writer.print(parseMapModel(new MapModel()));

writeFile(FileName,mapModel);

    }

    /**
     * This method reads the MapModel  that is parsed into it and creates  String of all the details of each country and
     * each continent..
     * @param mapModel
     * @return
     */

   /* public String parseMapModel(MapModel mapModel) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("[continents]"+NEW_LINE_DELIMITER);
        mapModel.getContinentList().forEach(continent -> {
            String continentValue = String.format("%s %d %s"+NEW_LINE_DELIMITER, continent.getContinentName(), continent.getContinentValue(), null);
            stringBuilder.append(continentValue);
        });
        stringBuilder.append(NEW_LINE_DELIMITER);
          int size= mapModel.getTotalCountries();
        for(int i=0;i< size;i++){
            CountryModel country=  mapModel.getCountryList().get(i);
            String countryValue= String.format(" %d %s %d" + NEW_LINE_DELIMITER, i,country.getCountryName(),country.getContinentName());
            stringBuilder.append(countryValue);


        }



        return stringBuilder.toString();
 }  */

    /**
     * This method takes Stores the string generated from a map in a file
     * @param FileName the name of the map.
     * @param mapModel the map from which the string is generated.
     * @throws IOException
     */

    public void writeFile(String FileName,MapModel mapModel) throws IOException{
        String Content= generateContent(mapModel);
        File file= new File(FileName);
        PrintWriter pw= new  PrintWriter(file);
        if(file.exists()){

            pw.print(" ");

        }
         FileWriter fw= new FileWriter(file);
        pw.print(Content);
        pw.close();



        }

    /**
     * This method takes in a map and Generate a string from the content of the map.
     * @param mapModel
     * @return
     */

    public String generateContent( MapModel mapModel) {
            final String NEW_LINE = " \n";
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(" [Continents" + NEW_LINE);
            for (ContinentModel continent : mapModel.getContinentList()) {

                String continentData = String.format(" %s %d", continent.getContinentName(), continent.getContinentValue());
                ArrayList<String> countrylist = continent.getCountriesList();
                stringBuilder.append(continentData);
                stringBuilder.append(" CountryList :");
                stringBuilder.append(ArrayCompiler(countrylist));
                stringBuilder.append(NEW_LINE);

            }
            stringBuilder.append("[COUNTRIES" + NEW_LINE);
            int length = mapModel.getTotalCountries();

            for (CountryModel country : mapModel.getCountryList()) {
                String countryData = String.format(" %d %s %s", country.getCountryValue(), country.getCountryName(), country.getContinentName());
                ArrayList<Integer> neighbour= country.getNeighbours();
                
                String neighbourString= ArrayCompiler(neighbour); 
                stringBuilder.append(countryData);
                stringBuilder.append(neighbour);
                stringBuilder.append(NEW_LINE);
                
                
        }
            return stringBuilder.toString();
    }
            

        

    /**
     * This method takes a list and merges them  correctly into a  Single String.
     *
     * @param list that stores the arraylist to be merged.
     * @return a string of all the elements in the arraylist.
     */

    public String ArrayCompiler(ArrayList list){
int Size= list.size();
        StringBuilder sb= new StringBuilder();
        for(int i=0; i<Size;i++ ){

             String Data=  (list.get(i)).toString();
             sb.append(Data);
             sb.append(" ");


        }
     String finalised= sb.toString();
        return finalised;
    }
}
