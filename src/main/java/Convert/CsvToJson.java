package Convert;

import org.json.CDL;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

public class CsvToJson {
    /**
     *  A funçao converte de csv to json
     * 
     * @param  csv file that the user summits 
     * @return the file converted to json
     */
    public FileWriter convert(File f){
    InputStream inputStream = CsvToJson.class.getClassLoader().getResourceAsStream(f.getAbsolutePath());
        String csvAsString = new BufferedReader(new InputStreamReader(inputStream)).lines().collect(Collectors.joining("\n"));
        String json = CDL.toJSONArray(csvAsString).toString();
        FileWriter fw;
        try {
            fw = new FileWriter("output.json");
            fw.write(json);
            fw.close();
            return fw;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
