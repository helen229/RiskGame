import java.io.IOException;
import java.io.PrintWriter;

public class CreateFile {
   private String FileName;
   private static final String CONTINENT= " continent";
   private static final String COUNTRY= " country";

     public CreateFile(String FileName) throws IOException {

         PrintWriter writer = new PrintWriter(FileName);
         writer.println("NAME " + FileName);
         writer.println(" [CONTINENTS]");
         writer.println(" [COUNTRY]");

     }

     public  void writeToFIle( String Name , String FileName){

              String Creator;
              String  toBecreated= "";

              Creator= Name;
              switch(Creator){
                  case CONTINENT :

                      break;
                  case COUNTRY :
                      break;




              }

     }


}
