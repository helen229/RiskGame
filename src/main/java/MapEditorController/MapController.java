package MapEditorController;

import MapEditorModel.MapModel;

import java.util.ArrayList;

public class MapController {

    private MapModel mapModel;

    public MapController() {
        this.mapModel= new MapModel();
    }
//        editcontinent -add continentname continentvalue -remove continentname
//        editcountry -add countryname continentname -remove countryname
//        editneighbor -add countryname neighborcountryname -remove countryname neighborcountryname
//        showmap (show all continents and countries and their neighbors)
//        editmap filename
//        savemap filename
//        validatemap

//TO DO: args array out of bounds exception
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
                    System.out.println("show map method");
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
    }

    private void editMap(String fileName) {
    }

    //TO DO: args array out of bounds exception
    public void parseCommandOption(String[] args, String command, String operation){
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
                    addContinent(args[2],Integer.parseInt(args[3]));
                    break;
                case "editneighbor":
                   // addContinent(args[2],args[3]);
                    break;
                default:
                    System.out.println("Invalid Command");
                    break;
            }

        }
        else{
            System.out.println("Invalid Option");
        }


    }

    private void addNeighbor(String arg, String arg1) {
    }

    private void addCountry(String countrytName, String continentName) {

        if (this.mapModel.addCountry(countrytName, continentName)){
            System.out.println("Add succeed");
        }
        else{
            System.out.println("Add failed");
        }

    }

    private void removeCountry(String countrytName) {

    }



    private void addContinent(String continentName , int continentValue) {

        if (this.mapModel.addContinent(continentName, continentValue)){
            System.out.println("Add succeed");
        }
        else{
            System.out.println("Add failed");
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
