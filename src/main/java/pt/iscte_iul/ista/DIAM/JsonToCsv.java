package pt.iscte_iul.ista.DIAM;

import org.json.CDL;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONTokener;

import com.opencsv.CSVWriter;

import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;

public class JsonToCsv {

    public static void main(String[] args) {
        convert(new File("C:/Users/Utilizador/Desktop/ES/ES-2023-2Sem-Ter-a-Feira-LEI-GrupoA-3/src/main/resources/input.json"));
    }
 /**
     *  A funçao converte de json to csv
     * 
     * @param  f file that the user summits 
     * @return the file converted to csv
     */
    static public File convert(File f){
       
       // InputStream inputStream = JsonToCsv.class.getClassLoader().getResourceAsStream(f.getAbsolutePath());
        try { 
            BufferedReader br = new BufferedReader(new FileReader(f.getAbsolutePath()));
            JSONArray  jsonArray = new JSONArray(new JSONTokener(br.readLine()));
            BufferedWriter bw = new BufferedWriter(new FileWriter("input.csv"));
            bw.write(CDL.toString(jsonArray));
            bw.close();
            br.close();
        } catch (JSONException | IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return new File("output.csv");
    }

}