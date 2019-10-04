import MapEditorModel.CountryModel;
import MapEditorModel.MapModel;

import java.io.IOException;
import java.io.PrintWriter;

/**
 * This class allows a user to create a new map file and write into a  map file
 */
public class EditMap {

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
    public EditMap(String FileName) throws IOException {

        PrintWriter writer = new PrintWriter(FileName);
        writer.print(parseMapModel(new MapModel()));

    }

    /**
     * This method reads the MapModel  that is parsed into it and creates  String of all the details of each country and
     * each continent..
     * @param mapModel
     * @return
     */

    public String parseMapModel(MapModel mapModel) {
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
    }
}
