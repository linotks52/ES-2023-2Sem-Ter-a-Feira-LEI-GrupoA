package timetable;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;

import convert.JsonToCsv;

//FORMATOS PEDIDOS PELO PROFESSOR NESTE PROJETO - MOODLE E DO WEBCALL DO FENIX

/**
 * Class que trata em criar uma Lista de CalendarEvents de um ficheiro - CSV ou
 * JSON - com os formatos do exemplo do Moodle, ou do import do Fénix.
 * Esta lista vai ser usada para depois ser percorrida e mostrada na GUI
 * 
 */
public class showCSV {
    /**
     * 
     * @param path o ficheiro a que queremos converter numa lista de eventos para
     *             depois mostar o horário
     * @return Lista de CalendarEvents do path que foi introduzido
     * @throws ParseException
     * @throws IOException
     */
    public static List<CalendarEvent> showHorario(File path) throws ParseException, IOException {
        List<CalendarEvent> horario = new ArrayList<>();

        if (path.getAbsolutePath().endsWith("csv")) {

            Reader leitor = new BufferedReader(
                    new InputStreamReader(new FileInputStream(path.getAbsolutePath()), StandardCharsets.UTF_8));
            CSVParser parser = new CSVParserBuilder().withSeparator(',').build();
            CSVReader csvReader = new CSVReaderBuilder(leitor).withCSVParser(parser).build();
            String[] cabecalho = csvReader.readNext();
            String[] Primlinha = csvReader.readNext();
            char a = Primlinha[0].charAt(0);
            if (Character.isDigit(a)) {
                lerCSVMoodle(path, horario);
            } else {
                lerCSVFenix(path, horario);
            }
        } else if (path.getAbsolutePath().endsWith("json")) {
            lerJsonMoodle(JsonToCsv.convert(path), horario);
        }

        return horario;
    }

    /**
     * Lê o ficheiro CSV no formato usado pelo sistema do Fénix, e adiciona cada
     * Evento desse ficheiro numa Lista
     *
     * @param a       Ficheiro CSV a ser lido
     * @param horario A lista de CalendarEvents a qual queremos adicionar os
     *                CalendarEvents do ficheiro
     * @throws ParseException Caso exista algum erro no parsing da data
     * @throws IOException    Caso exista algum erro a ler o ficheiro
     */

    public static void lerCSVFenix(File a, List<CalendarEvent> horario) throws ParseException, IOException {
        Reader leitor = new BufferedReader(
                new InputStreamReader(new FileInputStream(a), StandardCharsets.UTF_8));
        CSVParser parser = new CSVParserBuilder().withSeparator(',').build();
        CSVReader csvReader = new CSVReaderBuilder(leitor).withCSVParser(parser).build();

        String[] cabecalho = csvReader.readNext(); // Ler a primeira linha como cabeçalho para ser removido e n ler

        String[] linha;
        while ((linha = csvReader.readNext()) != null) {
      
            CalendarEvent evento = new CalendarEvent();
            evento.setTitle(linha[0]);
            evento.addDescription(linha[1]);
            String[] data = linha[2].split(" ");
           // ArrayList<String> datahelp=new ArrayList<String>();;
           // datahelp.add(data[1]);
            //datahelp.add(data[2]);
            //datahelp.add(data[3]);
            //datahelp.add(data[5]);
            //System.out.println(datahelp.get(0)+ " " +datahelp.get(1)+ " " + datahelp.get(2)+ " " +datahelp.get(3));
            
        

            evento.setStartDate(ColocarCalCSV(data));
            //Date aa = ColocarCalCSV(data);
            //System.out.println(aa.getTime());
            String[] data2 = linha[3].split(" ");
            evento.setEndDate(ColocarCalCSV(data2));
            horario.add(evento);
        }
    }

 
    /**
     * Lê o ficheiro CSV com o formato enviado pelo professor, e adiciona a uma
     * Lista de CalendarEvents os seus Eventos
     *
     * @param a       O ficheiro CSV a ser lido
     * @param horario A lista de CalendarEvents a qual queremos adicionar os
     *                CalendarEvents do ficheiro
     * @throws ParseException Caso exista algum erro no parsing da data
     * @throws IOException    Caso exista algum erro a ler o ficheiro
     */

    public static void lerCSVMoodle(File a, List<CalendarEvent> horario) throws ParseException, IOException {
        Reader leitor = new BufferedReader(
                new InputStreamReader(new FileInputStream(a), StandardCharsets.UTF_8));
        CSVParser parser = new CSVParserBuilder().withSeparator(',').build();
        CSVReader csvReader = new CSVReaderBuilder(leitor).withCSVParser(parser).build();
        String[] cabecalho = csvReader.readNext(); // Ler a primeira linha como cabeçalho para ser removido e n ler

        String[] linha;

        while ((linha = csvReader.readNext()) != null) {
            CalendarEvent evento = new CalendarEvent();
            evento.setTitle(linha[6]);
            evento.addDescription(linha[0] + " " + linha[3] + " " + linha[5] + " " + linha[7] + " "
                    + linha[8] + " " + linha[9]);
            String[] horaINI = linha[1].split(":");
            String[] horaFim = linha[4].split(":");
            String[] data = linha[10].split("/");
            if (data.length > 1) {
                evento.setStartDate(ColocarCalJSON(horaINI, data));
                evento.setEndDate(ColocarCalJSON(horaFim, data));
            }
            horario.add(evento);
        }
    }

    /**
     * Lê um ficheiro JSON obtido pela conversão do programa CsvToJson.java, do
     * ficheiro CSV com o formato do ficheiro disponibilizado no Moodle
     *
     * @param a       Ficheiro JSON a ser lido - (Convertido para CSV antes de
     *                entrar no programa)
     * @param horario A lista de CalendarEvents a qual queremos adicionar os
     *                CalendarEvents do ficheiro
     * @throws IOException Caso exista algum erro a ler o ficheiro
     */

    public static void lerJsonMoodle(File a, List<CalendarEvent> horario) throws IOException {
        Reader leitor = new BufferedReader(
                new InputStreamReader(new FileInputStream(a.getAbsolutePath()), StandardCharsets.UTF_8));
        CSVParser parser = new CSVParserBuilder().withSeparator(',').build();
        CSVReader csvReader = new CSVReaderBuilder(leitor).withCSVParser(parser).build();

        String[] cabecalho = csvReader.readNext(); // Ler a primeira linha como cabeçalho para ser removido e n ler

        String[] linha;
        while ((linha = csvReader.readNext()) != null) {
            CalendarEvent evento = new CalendarEvent();
            evento.setTitle(linha[6]);
            evento.addDescription(linha[0] + " " + linha[3] + " " + linha[5] + " " + linha[7] + " "
                    + linha[8] + " " + linha[9]);
            String[] horaINI = linha[1].split(":");
            String[] horaFim = linha[4].split(":");
            String[] data = linha[10].split("/");
            if (data.length > 1) {
                evento.setStartDate(ColocarCalJSON(horaINI, data));
                evento.setEndDate(ColocarCalJSON(horaFim, data));
            }
            horario.add(evento);
        }
    }
    

    /**
     * Devolve o numero correspondente ao mês inserido
     * 
     * @param mes Abreviação do mês em ingles em 3 letras
     * @return o número correspondente ao mês inserido
     * @throws IllegalArgumentException Caso a abreviação não seja reconhecida
     */

    public static int checkData(String mes) {
        switch (mes) {
            case "Jan":
                return Calendar.JANUARY;
            case "Feb":
                return Calendar.FEBRUARY;
            case "Mar":
                return Calendar.MARCH;
            case "Apr":
                return Calendar.APRIL;
            case "May":
                return Calendar.MAY;
            case "Jun":
                return Calendar.JUNE;
            case "Jul":
                return Calendar.JULY;
            case "Aug":
                return Calendar.AUGUST;
            case "Sep":
                return Calendar.SEPTEMBER;
            case "Oct":
                return Calendar.OCTOBER;
            case "Nov":
                return Calendar.NOVEMBER;
            case "Dec":
                return Calendar.DECEMBER;
            default:
                throw new IllegalArgumentException("Mês inválido: " + mes);
        }
    }


    
    public static Date ColocarCalCSV(String[] data) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, Integer.parseInt(data[5]));
        cal.set(Calendar.MONTH, checkData(data[1]));
        cal.set(Calendar.DAY_OF_MONTH, Integer.parseInt(data[2]));
        cal.set(Calendar.HOUR_OF_DAY, Integer.parseInt(data[3].split(":")[0]));
        cal.set(Calendar.MINUTE, Integer.parseInt(data[3].split(":")[1]));
        cal.set(Calendar.SECOND, Integer.parseInt(data[3].split(":")[2]));
        Date a = cal.getTime();
        return a;
    }
      
        
    
    /**
     * 
     * Cria um objeto do tipo Date a partir de duas strings representando a hora e a
     * data da aula.
     * Recebe 2 inputs, pois o CSV do PROFESSOR tem a HORA e a DATA DA AULA separado
     * 
     * @param hora um array de três strings representando a hora da aula no formato
     *             HH:MM:SS.
     * @param data um array de três strings representando a data da aula no formato
     *             DD/MM/YYYY.
     * @return um objeto do tipo Date representando a data e hora da aula.
     * @throws NumberFormatException se as strings fornecidas não puderem ser
     *                               convertidas em números inteiros.
     */

    public static Date ColocarCalJSON(String[] hora, String[] data) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, Integer.parseInt(data[2]));
        cal.set(Calendar.MONTH, Integer.parseInt(data[1]));
        cal.set(Calendar.DAY_OF_MONTH, Integer.parseInt(data[0]));
        cal.set(Calendar.HOUR_OF_DAY, Integer.parseInt(hora[0]));
        cal.set(Calendar.MINUTE, Integer.parseInt(hora[1]));
        cal.set(Calendar.SECOND, Integer.parseInt(hora[2]));
        Date a = cal.getTime();
        return a;
    }

    public static void main(String[] args) throws ParseException, IOException {
        File a = new File("UtestShowCSVFen.csv");
        a.createNewFile();
        FileWriter writer = new FileWriter(a);
        writer.write("Title,Description,StartDate,EndDate\n");
        writer.write("Event 1,Description 1,Wed Apr 11 12:00:00 BST 2023,Wed Apr 11 13:30:00 BST 2023\n");
        writer.write("Event 2,Description 2,Wed May 20 15:00:00 BST 2022,Mon May 20 17:00:00 BST 2022\n");
        writer.close();

        File b = new File("C:/Users/Utilizador/Desktop/ES2.0/ES-2023-2Sem-Ter-a-Feira-LEI-GrupoA/output4.csv");
        List<CalendarEvent> eventos = showHorario(b);
        for (CalendarEvent evento : eventos) {

            System.out.println("Título: " + evento.getTitle());
            System.out.println("Descrição: " + evento.getDescription());
            if (evento.getStartDate() != null) {
                System.out.println("Data de início: " + evento.getStartDate().toString());
              //  System.out.println("Data de fim: " + evento.getEndDate().toString());
            }
            System.out.println();
        }

    }
}

