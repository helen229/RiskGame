package GamePlayController;

public class GameController {
    StartUpPhase startUpPhase;
    ReinforcementPhase reinforce;
    FortificationPhase fortify;

    public GameController(){
        StartUpPhase startUpPhase= new StartUpPhase();
        ReinforcementPhase reinforce= new ReinforcementPhase();
        FortificationPhase fortify= new FortificationPhase();
    }


    public void showMap(String phase){
        if (phase.equals("")){

        }else if (phase.equals("")){

        }else if (phase.equals("")){

        }

    }

    public void commandHandler(String[] args, String phase) {

        if (args[0].equals("showmap"))
            showMap(phase);
        try
        {
            if (phase.equals("Startup")){
                switch (args[0]) {
                    case "loadmap":
                        startUpPhase.loadMap(args[1]);
                        break;
                    case "populatecountries":
                        startUpPhase.assignCountries();
                        break;
//                case "editneighbor":
//                    parseCommandOption(args,args[0],args[1]);
//                    break;
//                case "validatemap":
//                    mapModel.isValid();
//                    break;
//                case "editmap":
//                    editMap(args[1]);
//                    break;
//                case "savemap":
//                    saveMap(args[1]);
//                    break;

                    default:
                        System.out.println("Invalid Command");
                        break;
                }

            }else if (phase.equals("")){
                //reinforce countryname num
            }else if (phase.equals("")){
                //fortify fromcountry tocountry num
                //fortify none (choose to not do a move)
            }
        }catch(ArrayIndexOutOfBoundsException e){
            System.out.println("Arguments number invalid");
        }    }
}
