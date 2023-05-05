package pt.iscte_iul.ista.DIAM;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.kohsuke.github.GHCheckRun.Output;

import java.util.Date;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;

//LerCSV de um CSV apenas funciona se o ficheiro vier de webcaltoJSON(), que foi alterado para webcaltoCSV
//Irei Criar um novo que pega um JSON, transforma num CSV através do JSONtoCSV, que funcionará para aquele formato
//LerJSON só funciona para o JSON vindo do CSVtoJSON do ficheiro do tipo específico que o professor pediu, o que tem no moodle, qualquer tipo de CSV que vier daquele formato aceita

//FORMATOS PEDIDOS PELO PROFESSOR NESTE PROJETO - MOODLE E DO WEBCALL DO FENIX

public class showCSV {
    public List<CalendarEvent> showHorario(String path) throws ParseException, IOException {
        List<CalendarEvent> horario = new ArrayList<>();
        if (path.endsWith("csv")) {
            // lerCSV(path, horario);
        } else if (path.endsWith("json")) {
            JsonToCsv a = new JsonToCsv();
            String json = a.JSONtoCSV(path, "ola2.csv");
            lerJson("ola2.csv", horario);
        }

        return horario;
    }

    public void lerCSV(String path, List<CalendarEvent> horario) throws ParseException, IOException {
        Reader leitor = Files.newBufferedReader(Paths.get(path));
        CSVParser parser = new CSVParserBuilder().withSeparator(',').build();
        CSVReader csvReader = new CSVReaderBuilder(leitor).withCSVParser(parser).build();

        String[] cabecalho = csvReader.readNext(); // Ler a primeira linha como cabeçalho para ser removido e n ler

        String[] linha;
        while ((linha = csvReader.readNext()) != null) {
            CalendarEvent evento = new CalendarEvent();
            evento.setTitle(linha[0]);
            evento.addDescription(linha[3]);
            String[] data = linha[2].split(" ");
            evento.setStartDate(ColocarCalCSV(data));
            String[] data2 = linha[3].split(" ");
            evento.setEndDate(ColocarCalCSV(data2));
            horario.add(evento);
        }
    }

    public static int checkData(String mes) {
        switch (mes) {
            case "Jan":
                return 1;
            case "Feb":
                return 2;
            case "Mar":
                return 3;
            case "Apr":
                return 4;
            case "May":
                return 5;
            case "Jun":
                return 6;
            case "Jul":
                return 7;
            case "Aug":
                return 8;
            case "Sep":
                return 9;
            case "Oct":
                return 10;
            case "Nov":
                return 11;
            case "Dec":
                return 12;
            default:
                throw new IllegalArgumentException("Mês inválido: " + mes);
        }
    }

    public Date ColocarCalCSV(String[] data) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, Integer.parseInt(data[5]));
        cal.set(Calendar.MONTH, Integer.parseInt(data[1]));
        cal.set(Calendar.DAY_OF_MONTH, 0);
        cal.set(Calendar.HOUR_OF_DAY, Integer.parseInt(data[3].split(":")[0]));
        cal.set(Calendar.MINUTE, Integer.parseInt(data[3].split(":")[1]));
        cal.set(Calendar.SECOND, Integer.parseInt(data[3].split(":")[2]));
        Date a = cal.getTime();
        return a;
    }

    // Recebe 2 inputs, pois o CSV do PROFESSOR tem a HORA e a DATA DA AULA
    // separado
    public Date ColocarCalJSON(String[] hora, String[] data) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, Integer.parseInt(data[2]));
        cal.set(Calendar.MONTH, Integer.parseInt(data[1]));
        cal.set(Calendar.DAY_OF_MONTH,  Integer.parseInt(data[0]));
        cal.set(Calendar.HOUR_OF_DAY, Integer.parseInt(hora[0]));
        cal.set(Calendar.MINUTE, Integer.parseInt(hora[1]));
        cal.set(Calendar.SECOND, Integer.parseInt(hora[2]));
        Date a = cal.getTime();
        return a;
    }

    public void lerJson(String path, List<CalendarEvent> horario) throws IOException {
        Reader leitor = new BufferedReader(new InputStreamReader(new FileInputStream(path), StandardCharsets.UTF_8));
        CSVParser parser = new CSVParserBuilder().withSeparator(',').build();
        CSVReader csvReader = new CSVReaderBuilder(leitor).withCSVParser(parser).build();

        String[] cabecalho = csvReader.readNext(); // Ler a primeira linha como cabeçalho para ser removido e n ler

        String[] linha;
        while ((linha = csvReader.readNext()) != null) {
            CalendarEvent evento = new CalendarEvent();
            evento.setTitle(linha[6]);
            evento.addDescription(linha[0] + " " + linha[1] + " " + linha[3] + " " + linha[5] + " " + linha[7] + " " + linha[8] + " " + linha[9]);
            String[] horaINI = linha[1].split(":");
            String[] data = linha[10].split("/");
           // System.out.println(data[2]);
            //System.out.println(ColocarCalJSON(horaINI, data));
           // evento.setStartDate(ColocarCalJSON(horaINI, data));
            String[] horaFim = linha[4].split(" ");
           // evento.setEndDate(ColocarCalJSON(horaFim, data));
            horario.add(evento);
        }
    }

    public static void main(String[] args) throws ParseException, IOException {
        showCSV a = new showCSV();
        List<CalendarEvent> eventos = a.showHorario("input.json");
        for (CalendarEvent evento : eventos) {
             System.out.println("Título: " + evento.getTitle());
             System.out.println("Descrição: " + evento.getDescription());
             System.out.println("Data de início: " + evento.getStartDate().toString());
             System.out.println("Data de fim: " + evento.getEndDate().toString());
             System.out.println();
        }

    }
}