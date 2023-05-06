package Timetable;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
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
 * EscolhaUCs
 */
public class EscolhaUCs {
    //CSV
    public static List<String> UCsDisp(File f) throws IOException {
        List<String> Ucs = new ArrayList<>();
        Reader leitor = new BufferedReader(
                new InputStreamReader(new FileInputStream(f.getAbsolutePath()), StandardCharsets.UTF_8));
        CSVParser parser = new CSVParserBuilder().withSeparator(',').build();
        CSVReader csvReader = new CSVReaderBuilder(leitor).withCSVParser(parser).build();
        
    
        String[] linha;
        while ((linha = csvReader.readNext()) != null) {
            if(!Ucs.contains(linha[6])){
                Ucs.add(linha[6]);
            }
            
        }

        return Ucs;

        // correr o csv do stor
        // pegar o nome de todas as cadeiras e se calhar a data - guardar numa lista
    }

    public static List<String> EscolhaUC(File f) throws IOException{
        List<String> UcsEscolhidas = new ArrayList<>();
        List<String> Ucs = UCsDisp(f);
        Scanner input = new Scanner(System.in);
        System.out.println("Disciplinas Disponiveis");
        for (String a : Ucs){
            System.out.println(a);
            System.out.print("Deseja selecionar esta disciplina? (S/N) ");
            String resposta = input.nextLine();
            if (resposta.equalsIgnoreCase("S")) {
                UcsEscolhidas.add(a);
            }
        }
        return UcsEscolhidas;
    }
    
    

    public static void main(String[] args) throws ParseException, IOException {

        
        List<String> UcsEsc = EscolhaUC(new File(
            "C:/Users/Utilizador/Desktop/ES2.0/ES-2023-2Sem-Ter-a-Feira-LEI-GrupoA/teste.csv"));
        for (String a : UcsEsc)
            System.out.println(a);
    }
}
