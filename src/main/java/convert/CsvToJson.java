package convert;

import org.json.CDL;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.stream.Collectors;


/**
 * Class que converte um fichero CSV num ficheiro Json
 */
public class CsvToJson {   
    /**
     *  A funçao converte de csv to json
     * 
     * @param f csv file that the user summits 
     * @return the file converted to json
     */
    public static File convert(File f){
        File output = new File("output.json"); 
        try {
            String csvAsString = new BufferedReader(new FileReader(f.getAbsolutePath())).lines().collect(Collectors.joining("\n"));
            String json = CDL.toJSONArray(csvAsString).toString();
            BufferedWriter bw = new BufferedWriter(new FileWriter(output));
            bw.write(json);
            bw.close();
            
        } catch (IOException e) {
            e.printStackTrace();
        }
        return output;
    }
}