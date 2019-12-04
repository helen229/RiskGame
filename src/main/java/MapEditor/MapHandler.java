package MapEditor;

import MapEditorModel.MapModel;

import java.io.IOException;

/**
 * This Map saves the Map
 */
public class MapHandler {

    private MapModel mapModel;
    private  static final String MAP_TAG= "[Map]";
    private static final String MAP=" Map";

    /**
     * Constructor of the MapController Class
     */
    public MapHandler() {
        this.mapModel= new MapModel();
    }

    //  TODO: Unit TEST, add javadoc, add comments

    /**
     * method to parse the command
     * @param args command from user
     */
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
                    mapModel.isValid();
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
        }catch(Exception e){
            System.out.println(e);
        }

    }

    /**
     * saveMap method is to run the command "savemap", first check if the file exist or not,
     * if not create a new file otherwise write the string to the file (clear the file content first)
     * @param fileName
     */
    private void saveMap(String fileName) {

        if (!mapModel.isValid()){
            return;
        }

        try {
            SaveMap saveMap = new SaveMap(fileName, mapModel);
        } catch (IOException e) {
            System.out.println("Save Map fail");
            return;
//            e.printStackTrace();
        }
        System.out.println("Save Map Succeed");

    }

    public boolean selfMap(){
        return true;
    }

    public boolean schoolMap(){
        return true;
    }

    /**
     * Load map from file
     * @param fileName
     */
    public void editMap(String fileName) {
        ChooseAdapter adp = new ChooseAdapter(fileName);

//        EditMap readFile = new EditMap(fileName);
        try{
            if (adp.isSchoolFile()){

                MapAdapter readFile = new MapAdapter(fileName);
                try {
                    this.mapModel = readFile.checkFile();
                } catch (IOException e) {
                    System.out.println("Edit Map fail, this file not exist");
                    return;
//            e.printStackTrace();
                }
                if (mapModel.isValid()){
                    System.out.println("Edit Map Succeed");
                }else {
                    /** not validate so clear all the content */
                    mapModel.getCountryList().clear();
                    mapModel.getContinentList().clear();
                }

            }else {
                EditMap readFile = new EditMap(fileName);
                try {
                    this.mapModel = readFile.checkFile();
                } catch (IOException e) {
                    System.out.println("Edit Map fail, this file not exist");
                    return;
//            e.printStackTrace();
                }
                if (mapModel.isValid()){
                    System.out.println("Edit Map Succeed");
                }else {
                    /** not validate so clear all the content */
                    mapModel.getCountryList().clear();
                    mapModel.getContinentList().clear();
                }
            }
        }catch(IOException ex){
            System.out.println("invalid");

        }


    }

    /**
     * parse the edit command option add or remove
     * @param args
     * @param command
     * @param operation
     */
    public void parseCommandOption(String[] args, String command, String operation){

        try
        {
            if (operation.equals("add")){
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
            else if(operation.equals("remove")){
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

        }catch(Exception e){
            System.out.println(e);
        }

    }

    /**
     * add the neighbor country and print out the result
     * @param countryName
     * @param neighborCountryName
     */
    private void addNeighbor(String countryName, String neighborCountryName) {

        if (this.mapModel.addNeighbor(countryName, neighborCountryName)){
            System.out.println("Add Neighbor succeed");
        }
        else{
            System.out.println("Add Neighbor failed");
        }

    }

    /**
     * remove the neighbor country and print out the result
     * @param countryName
     * @param neighborCountryName
     */
    private void removeNeighbor(String countryName, String neighborCountryName) {

        if (this.mapModel.removeNeighbor(countryName, neighborCountryName)){
            System.out.println("Remove Neighbor succeed");
        }
        else{
            System.out.println("Remove Neighbor failed");
        }

    }

    /**
     * add the country and print out the result
     * @param countryName
     * @param continentName
     */
    private void addCountry(String countryName, String continentName) {

        if (this.mapModel.addCountry(countryName, continentName)){
            System.out.println("Add Country succeed");
        }
        else{
            System.out.println("Add Country failed");
        }

    }

    /**
     * remove the country and print out the result
     * @param countryName
     */
    private void removeCountry(String countryName) {

        if (this.mapModel.removeCountry(countryName)){
            System.out.println("Remove Country succeed");
        }
        else{
            System.out.println("Remove Country failed");
        }

    }

    /**
     * add the continent and print out the result
     * @param continentName
     * @param continentValue
     */
    private void addContinent(String continentName , int continentValue) {

        if (this.mapModel.addContinent(continentName, continentValue)){
            System.out.println("Add Continent succeed");
        }
        else{
            System.out.println("Add Continent failed");
        }

    }

    /**
     * remove the continent and print out the result
     * @param continentName
     */
    private void removeContinent(String continentName) {

        if (this.mapModel.removeContinent(continentName)){
            System.out.println("Remove succeed");
        }
        else{
            System.out.println("Remove failed");
        }

    }

    /**
     * get the map model
     * @return MapModel
     */
    public MapModel getMapModel() {
        return mapModel;
    }

    /**
     * set the map model
     * @param mapModel
     */
    public void setMapModel(MapModel mapModel) {
        this.mapModel = mapModel;
    }
}
