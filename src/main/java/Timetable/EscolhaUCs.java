package Timetable;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.Scanner;

import org.kohsuke.github.GHEventPayload.Public;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.util.Date;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;

import Convert.JsonToCsv;

/**
 * Esta classe contém métodos a escolha de UCS do ficheiro CSV do Moodle.
 */
public class EscolhaUCs {
    /**
     * 
     * Retorna uma lista de todas as disciplinas disponíveis em um ficheiro CSV.
     * 
     * @param f o ficheiro CSV contendo as disciplinas
     * @return uma lista de strings com o nome das disciplinas não repetidas
     * @throws IOException se ocorrer um erro de leitura de arquivo
     */
    public static List<String> UCsDisp(File f) throws IOException {
        if (f.getAbsolutePath().endsWith("csv")) {
            List<String> Ucs = new ArrayList<>();
            Reader leitor = new BufferedReader(
                    new InputStreamReader(new FileInputStream(f.getAbsolutePath()), StandardCharsets.UTF_8));
            CSVParser parser = new CSVParserBuilder().withSeparator(',').build();
            CSVReader csvReader = new CSVReaderBuilder(leitor).withCSVParser(parser).build();
            csvReader.readNext();

            String[] linha;
            while ((linha = csvReader.readNext()) != null) {
                if (!Ucs.contains(linha[6])) {
                    Ucs.add(linha[6]);
                }
            }
            return Ucs;
        } else {
            return UCsDisp(JsonToCsv.convert(f));
        }

    }

    /**
     * 
     * Reesceve no ficheiro CSV passado como input apenas as linhas correspondentes
     * às
     * disciplinas selecionadas.
     * 
     * @param ucsChecked um array de strings com o nome das disciplinas selecionadas
     * @param f          o arquivo CSV contendo todas as disciplinas
     * @throws IOException se ocorrer um erro de leitura ou escrita de arquivo
     */

    public static void RewriteCSV(String[] ucsChecked, File f) throws IOException {
        if (f.getAbsolutePath().endsWith("csv")) {
            Reader leitor = new BufferedReader(
                    new InputStreamReader(new FileInputStream(f), StandardCharsets.UTF_8));
            CSVParser parser = new CSVParserBuilder().withSeparator(',').build();
            CSVReader csvReader = new CSVReaderBuilder(leitor).withCSVParser(parser).build();
            String[] cabecalho = csvReader.readNext();
            ArrayList<String> listactudo = new ArrayList<>();
            String[] linha;

            while ((linha = csvReader.readNext()) != null) {
                if (checklinha(linha[0], ucsChecked)) {
                    listactudo.add(linha[0] + linha[1] + linha[2] + linha[3]);
                }
            }

            csvReader.close();
            BufferedWriter writer2 = new BufferedWriter(new FileWriter(f.getAbsolutePath()));
            writer2.write(cabecalho[0] + "," + cabecalho[1] + "," + cabecalho[2] + "," + cabecalho[3]);
            for (String linha1 : listactudo) {
                writer2.append(linha1);
            }
            writer2.close();
        } else {
            RewriteCSV(ucsChecked, JsonToCsv.convert(f));
        }
    }

   /**
    * Verifica se uma linha contém algumas das string do ucsChecked 
    * @param linha a linha que queremos verificar   
    * @param ucsChecked conjunto de strings que vamos correr e verificar se a linha contem
    * @return   verdade se contiver
    */
   
    private static boolean checklinha(String linha, String[] ucsChecked) {
        for (String s : ucsChecked) {
            if (linha.contains(s)) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) throws ParseException, IOException {

        ArrayList<String> arrayList = new ArrayList<>(Arrays.asList("Engenharia de Software", "Agentes Autónomos"));
        String[] a = arrayList.toArray(new String[0]);
        EscolhaUCs.RewriteCSV(a, new File("output4.csv"));
    }
}
