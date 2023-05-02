package Convert;

import org.json.CDL;

import java.io.BufferedReader;
import java.io.BufferedWriter;
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
     * @param f csv file that the user summits 
     * @return the file converted to json
     */
    public File convert(File f){
    InputStream inputStream = CsvToJson.class.getClassLoader().getResourceAsStream(f.getAbsolutePath());
        String csvAsString = new BufferedReader(new InputStreamReader(inputStream)).lines().collect(Collectors.joining("\n"));
        String json = CDL.toJSONArray(csvAsString).toString();
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter("output.json"));
            bw.write(json);
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new File("output.json");
    }
}
