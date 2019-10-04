package MapEditorController;

import MapEditorModel.ContinentModel;
import MapEditorModel.CountryModel;
import MapEditorModel.MapModel;

import java.util.ArrayList;

public class MapController {

    private MapModel mapModel;

    private static final String CONTINENT = "continent";
    private static final String COUNTRY = "country";
    private static final String BORDER = "border";
    private static final String NEW_LINE_DELIMITER = "\n";

    public MapController() {
        this.mapModel= new MapModel();
    }

    //  TODO: Unit TEST, add javadoc, add comments


    public void commandHandler(String[] args) {

        try
        {
            switch (args[0]) {
                case "editcontinent":
                    parseCommandOption(args,args[0],args[1]);
                    break;
                case "editcountry":
                    parseCommandOption(args,args[0],args[1]);
                    break;
                case "editneighbor":
                    parseCommandOption(args,args[0],args[1]);
                    break;
                case "showmap":
                    mapModel.showMap();
                    break;
                case "validatemap":
                    System.out.println("valid map method");
                    break;
                case "editmap":
                    editMap(args[1]);
                    break;
                case "savemap":
                    saveMap(args[1]);
                    break;

                default:
                    System.out.println("Invalid Command");
                    break;

            }
        }catch(ArrayIndexOutOfBoundsException e){
            System.out.println("Arguments number invalid");
        }

    }


    private void saveMap(String fileName) {
        //TODO: first check if the file exist or not, if not create a new file otherwise write the
        //      string to the file (clear the file content first)
        System.out.println(parseMapModel(mapModel));

    }

    private void editMap(String fileName) {
        // TODO: Merge your Read file class here

    }

    public String parseMapModel(MapModel mapModel) {

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("["+CONTINENT+"]"+NEW_LINE_DELIMITER);

        for (ContinentModel continent:mapModel.getContinentList()) {
            String continentValue = String.format("%s %d %s"+NEW_LINE_DELIMITER, continent.getContinentName(), continent.getContinentValue(), null);
            stringBuilder.append(continentValue);
        }

        stringBuilder.append("["+COUNTRY+"]"+NEW_LINE_DELIMITER);

        for (CountryModel country:mapModel.getCountryList()) {
            String countryValue= String.format("%d %s %s" + NEW_LINE_DELIMITER, country.getCountryValue(),country.getCountryName(), country.getContinentName());
            stringBuilder.append(countryValue);
        }

        stringBuilder.append("["+BORDER+"]"+NEW_LINE_DELIMITER);
        for (CountryModel country:mapModel.getCountryList()) {
            String countryValue= String.format("%d ", country.getCountryValue());
            stringBuilder.append(countryValue);
            for (int neighbour:country.getNeighbours()) {
                String neighbourValue= String.format(" %d", neighbour);
                stringBuilder.append(neighbourValue);
            }
            stringBuilder.append(NEW_LINE_DELIMITER);
        }
        return stringBuilder.toString();

    }


    public void parseCommandOption(String[] args, String command, String operation){

        try
        {
            if (operation.equals("-add")){
                switch (command){
                    case "editcontinent":
                        addContinent(args[2],Integer.parseInt(args[3]));
                        break;
                    case "editcountry":
                        addCountry(args[2],args[3]);
                        break;
                    case "editneighbor":
                        addNeighbor(args[2],args[3]);
                        break;
                    default:
                        System.out.println("Invalid Command");
                        break;
                }
            }
            else if(operation.equals("-remove")){
                switch (command){
                    case "editcontinent":
                        removeContinent(args[2]);
                        break;
                    case "editcountry":
                        removeCountry(args[2]);
                        break;
                    case "editneighbor":
                        removeNeighbor(args[2],args[3]);
                        break;
                    default:
                        System.out.println("Invalid Command");
                        break;
                }

            }
            else{
                System.out.println("Invalid Option");
            }

        }catch(ArrayIndexOutOfBoundsException e){
            System.out.println("Arguments number invalid");
        }

    }


    private void addNeighbor(String countryName, String neighborCountryName) {

        if (this.mapModel.addNeighbor(countryName, neighborCountryName)){
            System.out.println("Add Neighbor succeed");
        }
        else{
            System.out.println("Add Neighbor failed");
        }

    }


    private void removeNeighbor(String countryName, String neighborCountryName) {

        if (this.mapModel.removeNeighbor(countryName, neighborCountryName)){
            System.out.println("Remove Neighbor succeed");
        }
        else{
            System.out.println("Remove Neighbor failed");
        }

    }


    private void addCountry(String countryName, String continentName) {

        if (this.mapModel.addCountry(countryName, continentName)){
            System.out.println("Add Country succeed");
        }
        else{
            System.out.println("Add Country failed");
        }

    }


    private void removeCountry(String countryName) {

        if (this.mapModel.removeCountry(countryName)){
            System.out.println("Remove Country succeed");
        }
        else{
            System.out.println("Remove Country failed");
        }

    }


    private void addContinent(String continentName , int continentValue) {

        if (this.mapModel.addContinent(continentName, continentValue)){
            System.out.println("Add Continent succeed");
        }
        else{
            System.out.println("Add Continent failed");
        }

    }


    private void removeContinent(String continentName) {

        if (this.mapModel.removeContinent(continentName)){
            System.out.println("Remove succeed");
        }
        else{
            System.out.println("Remove failed");
        }

    }


    public MapModel getMapModel() {
        return mapModel;
    }

    public void setMapModel(MapModel mapModel) {
        this.mapModel = mapModel;
    }
}
