package Convert;

import org.json.CDL;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

public class CsvToJson {
    

    public static void main(String[] args) {
        //convert(new File("testeinput.csv"));
        convert(new File("/home/pereira/aulas/ES/ES-2023-2Sem-Ter-a-Feira-LEI-GrupoA-3/output.csv"));
    }
    /**
     *  A funçao converte de csv to json
     * 
     * @param f csv file that the user summits 
     * @return the file converted to json
     */
    public static File convert(File f){
        try {
            String csvAsString = new BufferedReader(new FileReader(f.getAbsolutePath())).lines().collect(Collectors.joining("\n"));
            String json = CDL.toJSONArray(csvAsString).toString();
            BufferedWriter bw = new BufferedWriter(new FileWriter("default.json"));
            bw.write(json);
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new File("output.json");
    }
}
