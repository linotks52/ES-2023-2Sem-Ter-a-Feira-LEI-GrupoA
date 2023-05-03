package pt.iscte_iul.ista.DIAM;

import org.json.CDL;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import com.opencsv.CSVWriter;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.json.JSONObject;

public class JsonToCsv {

    public String JSONtoCSV(String file, String RetFile) {
        InputStream inputStream = JsonToCsv.class.getClassLoader().getResourceAsStream(file);
        JSONArray jsonArray = new JSONArray(new JSONTokener(inputStream));
        try {
            FileWriter fw = new FileWriter(RetFile);
            fw.write(CDL.toString(jsonArray));
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(RetFile);
        return RetFile;
    }

    public static void main(String[] args) {
        JsonToCsv a = new JsonToCsv();
        a.JSONtoCSV("csvjson.json", "test1.csv");
    }
}
