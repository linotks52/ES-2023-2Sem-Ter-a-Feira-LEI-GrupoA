package Convert;

import org.json.CDL;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONTokener;

import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;

/**
 * Classe que trata da conversão de um Ficheiro JSON para um ficheiro CSV
 */
public class JsonToCsv {

 /**
     *  A funçao converte de json to csv
     * 
     * @param  f file that the user summits 
     * @return the file converted to csv
     */
    static public File convert(File f){
    //	String[] name = f.getAbsolutePath().split("\\.");
       File output = new File("output.csv"); 
       // InputStream inputStream = JsonToCsv.class.getClassLoader().getResourceAsStream(f.getAbsolutePath());
        try { 
            BufferedReader br = new BufferedReader(new FileReader(f.getAbsolutePath()));
            JSONArray  jsonArray = new JSONArray(new JSONTokener(br.readLine()));
            BufferedWriter bw = new BufferedWriter(new FileWriter(output));
            bw.write(CDL.toString(jsonArray));
            bw.close();
            br.close();
        } catch (JSONException | IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return output;
    }
    public static void main(String[] args) {
        convert(new File("C:/Users/Utilizador/Desktop/ES/ES-2023-2Sem-Ter-a-Feira-LEI-GrupoA-3/src/main/resources/input.json"));
    }
}