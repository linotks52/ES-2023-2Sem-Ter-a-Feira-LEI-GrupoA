package pt.iscte_iul.ista.DIAM;

import org.json.CDL;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import com.opencsv.CSVWriter;
import java.io.ByteArrayInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.json.JSONObject;
public class JsonToCsv {

 public static void main(String[] args) {
        InputStream inputStream = JsonToCsv.class.getClassLoader().getResourceAsStream("input.json");
        JSONArray jsonArray = new JSONArray(new JSONTokener(inputStream));
        try {
            FileWriter fw = new FileWriter("output3.csv");
            fw.write(CDL.toString(jsonArray));
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

   


