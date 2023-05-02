package Convert;

import org.json.CDL;
import org.json.JSONArray;
import org.json.JSONTokener;

import com.opencsv.CSVWriter;

import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.BufferedWriter;
import java.io.File;

public class JsonToCsv {
 /**
     *  A funçao converte de json to csv
     * 
     * @param  f file that the user summits 
     * @return the file converted to csv
     */
    public File convert(File f){
        InputStream inputStream = JsonToCsv.class.getClassLoader().getResourceAsStream(f.getAbsolutePath());
        JSONArray jsonArray = new JSONArray(new JSONTokener(inputStream));
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter("output.csv"));
            bw.write(CDL.toString(jsonArray));
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new File("output.csv");
    }
}
