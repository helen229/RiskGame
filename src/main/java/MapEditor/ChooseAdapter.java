package MapEditor;
import MapEditorModel.ContinentModel;
import MapEditorModel.CountryModel;
import MapEditorModel.MapModel;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;


public class ChooseAdapter {
    private boolean isSchoolFile = false;
    private  static final String MAP_TAG= "[Map]";
    private static final String MAP="Map";



    private final String fileDirectory;


    public ChooseAdapter (String fileDirectory) {
        this.fileDirectory = fileDirectory;
    }

    public boolean isSchoolFile()throws IOException
    {
        FileReader file = new FileReader(fileDirectory);
        BufferedReader br = new BufferedReader(file);
        String line = br.readLine();
        String[] splitLine = line.split(" ");
        String checker = " ";


        if(line!=null) {
            while (line != null) {
                line = br.readLine();
                if (line != null) {
                    if (line.equals(MAP_TAG)) {
                        checker = MAP;
                        continue;
                    }


                    line.trim();
                    int size = line.length();
                    switch (checker) {
                        case MAP:
                            isSchoolFile = true;
                    }

                }
            } }
        else{
            System.out.println(" File is Empty");
        }
        file.close();
        return isSchoolFile;
    }
}
