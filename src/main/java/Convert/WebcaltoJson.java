package Convert;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URISyntaxException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import net.fortuna.ical4j.data.ParserException;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import Load.WebcalCalendarImporter;
import Timetable.CalendarEvent;

/**
 * Class que trata na conversão de um ficheiro Webcall para um ficheiro CSV ou JSON
 */

public class WebcaltoJson {

  /**
   * 
   * @param stringuri string que representa o URL do arquivo webcal a ser
   *                  importado.
   * @return o arquivo CSV gerado.
   * @throws URISyntaxException se ocorrer um erro de sintaxe ao analisar a URI.
   * @throws IOException        se ocorrer um erro ao ler ou gravar um arquivo.
   * @throws ParseException     se ocorrer um erro durante a análise de uma data
   *                            ou hora.
   */

  public static File WebcalltoCSV(String stringuri) throws URISyntaxException, IOException, ParseException {
    List<CalendarEvent> CEvent = WebcalCalendarImporter.importEventsFromWebcal(stringuri);
    File file = new File("output6.csv");
    FileWriter fw = new FileWriter(file);
    CSVPrinter csvPrinter = new CSVPrinter(fw, CSVFormat.DEFAULT.withHeader(
        "Title", "Description", "Start Date", "End Date"));
    for (CalendarEvent a : CEvent) {
      List<String> record = new ArrayList<>();
      record.add(a.getTitle());
      record.add(a.getDescription());
      record.add(a.getStartDate().toString());
      record.add(a.getEndDate().toString());
      csvPrinter.printRecord(record);
    }
    csvPrinter.close();
    fw.close();
    return file;
  }

  /**
   * 
   * Converte um arquivo webcal em formato CSV para um arquivo JSON.
   * 
   * @param a string que representa o URL do arquivo webcal a ser
   *          importado.
   * @return o arquivo JSON gerado.
   * @throws URISyntaxException se ocorrer um erro de sintaxe ao analisar a URI.
   * @throws IOException        se ocorrer um erro ao ler ou gravar um arquivo.
   * @throws ParseException     se ocorrer um erro durante a análise de uma data
   *                            ou hora.
   */

  public static File WebcalltoJSON1(String a) throws URISyntaxException, IOException, ParseException {
    File file = WebcalltoCSV(a);
    return CsvToJson.convert(file);
  }

  public static void main(String[] args) throws IOException, ParserException, URISyntaxException, ParseException {
    WebcalltoJSON1(
        "https://fenix.iscte-iul.pt/publico/publicPersonICalendar.do?method=iCalendar&username=dbrlo@iscte.pt&password=V5vG3uiVsWNaNDkKYT3tdP1R392Ma9Gq9l8Fl4QTdlz2Q99UWamZ0rKkRVhac7CwnXxCOimdsXWebkf2aKtqdkU8gn4SrnYicTp45qxidxeaUARk2UVhRlRLtKHcNJ13");

  }

}
