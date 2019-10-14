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
 */
public class SaveMap {


    private String FileName;
    private static final String CONTINENT = "continents";
    private static final String COUNTRY = "countries";
    private static final String BORDER = "borders";
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
        String Content = parseMapModel(mapModel);
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

    public String parseMapModel(MapModel mapModel) {

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(NEW_LINE_DELIMITER);
        stringBuilder.append("["+CONTINENT+"]"+NEW_LINE_DELIMITER);

        for (ContinentModel continent:mapModel.getContinentList()) {
            String continentValue = String.format("%s %d"+NEW_LINE_DELIMITER, continent.getContinentName(), continent.getContinentValue());
            stringBuilder.append(continentValue);
        }
        stringBuilder.append(NEW_LINE_DELIMITER);

        stringBuilder.append("["+COUNTRY+"]"+NEW_LINE_DELIMITER);

        for (CountryModel country:mapModel.getCountryList()) {
            String countryValue= String.format("%d %s %s" + NEW_LINE_DELIMITER, country.getCountryValue(),country.getCountryName(), country.getContinentName());
            stringBuilder.append(countryValue);
        }
        stringBuilder.append(NEW_LINE_DELIMITER);

        stringBuilder.append("["+BORDER+"]"+NEW_LINE_DELIMITER);
        for (CountryModel country:mapModel.getCountryList()) {
            String countryValue= String.format("%d", country.getCountryValue());
            stringBuilder.append(countryValue);
            for (int neighbour:country.getNeighbours()) {
                String neighbourValue= String.format(" %d", neighbour);
                stringBuilder.append(neighbourValue);
            }
            stringBuilder.append(NEW_LINE_DELIMITER);
        }
        return stringBuilder.toString();
    }



    /**
     * This method takes a list and merges them  correctly into a  Single String.
     *
     * @param list that stores the arraylist to be merged.
     * @return a string of all the elements in the arraylist.
     */

    public String ArrayCompiler(ArrayList list) {
        int Size = list.size();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < Size; i++) {

            String Data = (list.get(i)).toString();
            sb.append(i + " ");
            sb.append(Data);
            sb.append(" ");


        }
        String finalised = sb.toString();
        return finalised;
    }
}