package Convert;

import org.json.CDL;
import org.json.JSONArray;
import org.json.JSONTokener;

import com.opencsv.CSVWriter;

import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.File;

public class JsonToCsv {
 /**
     *  A funçao converte de json to csv
     * 
     * @param  json file that the user summits 
     * @return the file converted to csv
     */
    public static FileWriter convert(File f){
        InputStream inputStream = JsonToCsv.class.getClassLoader().getResourceAsStream(f.getAbsolutePath());
        JSONArray jsonArray = new JSONArray(new JSONTokener(inputStream));
        try {
            FileWriter fw = new FileWriter("output.csv");
            fw.write(CDL.toString(jsonArray));
            fw.close();
            return fw;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
