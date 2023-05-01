package Convert;

import org.json.CDL;
import org.json.JSONArray;
import org.json.JSONTokener;

import com.opencsv.CSVWriter;

import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;

public class JsonToCsv {

    public static void main(String[] args) {
        InputStream inputStream = JsonToCsv.class.getClassLoader().getResourceAsStream("input.json");
        JSONArray jsonArray = new JSONArray(new JSONTokener(inputStream));
        try {
            FileWriter fw = new FileWriter("output.csv");
            fw.write(CDL.toString(jsonArray));
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
