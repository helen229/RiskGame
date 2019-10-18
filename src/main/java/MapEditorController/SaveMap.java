package MapEditorController;


import MapEditorModel.ContinentModel;
import MapEditorModel.CountryModel;
import MapEditorModel.MapModel;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 * This class allows a user to create a new map file and write into a  map file
 *
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
    public SaveMap(String FileName, MapModel mapModel) throws IOException {

        writeFile(FileName, mapModel);
    }


    /**
     * This method takes Stores the string generated from a map in a file
     *
     * @param FileName the name of the map.
     * @param mapModel the map from which the string is generated.
     * @throws IOException
     */

    public File writeFile(String FileName, MapModel mapModel) throws IOException {
        String Content = generateContent(mapModel);
        File file = new File(FileName);
        PrintWriter pw = new PrintWriter(file);
        if (file.exists()) {

            pw.print(" ");

        }
        FileWriter fw = new FileWriter(file);
        pw.print(Content);
        pw.close();

 return file;
    }

    /**
     * This method takes in a map and Generate a string from the content of the map.
     *
     * @param mapModel
     * @return
     */

    public String generateContent(MapModel mapModel) {
        final String NEW_LINE = "\n";
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(" " + NEW_LINE +"[continents]" + NEW_LINE);
        for (ContinentModel continent : mapModel.getContinentList()) {

            String continentData = String.format("%s %d", continent.getContinentName(), continent.getContinentValue());
            //ArrayList<String> countrieslist = continent.getCountriesList();
            stringBuilder.append(continentData);
            //stringBuilder.append(" ");
            //stringBuilder.append(arrayCompiler1(countrieslist));
            stringBuilder.append(NEW_LINE);

        }
        stringBuilder.append("[countries]" + NEW_LINE);
        int length = mapModel.getTotalCountries();
        ArrayList<Integer> neighbour;


        for (CountryModel country : mapModel.getCountryList()) {
            String countryData = String.format("%d %s %s", country.getCountryValue(), country.getCountryName(), country.getContinentName());
            //neighbour = country.getNeighbours();


            stringBuilder.append(countryData);

            stringBuilder.append(NEW_LINE);


        }
        stringBuilder.append("[borders]" + NEW_LINE);
        for (CountryModel country : mapModel.getCountryList()) {
           neighbour=  country.getNeighbours();
            String neighbourString =String.format("%d ",country.getCountryValue());
            
            stringBuilder .append(neighbourString);
            stringBuilder .append(arrayCompiler2(neighbour));
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

    public String arrayCompiler1(ArrayList list) {
        int Size = list.size();
        StringBuilder sb = new StringBuilder();
        sb.append("[ ");
        for (int i = 0; i < Size; i++) {

            String Data = String.format("%s ",(list.get(i)).toString());
            sb.append(Data);
        }
        sb.append("]");
        String finalised = sb.toString();
        return finalised;
    }
    public String arrayCompiler2(ArrayList list) {
        int Size = list.size();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < Size; i++) {

            String Data = String.format("%s ",(list.get(i)).toString());
            sb.append(Data);


        }
        String finalised = sb.toString();
        return finalised;
    }
}
