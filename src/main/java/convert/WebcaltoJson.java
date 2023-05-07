package convert;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URISyntaxException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import net.fortuna.ical4j.data.ParserException;
import timetable.CalendarEvent;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import load.WebcalCalendarImporter;

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

  public static File webcalltoCSV(String stringuri) throws URISyntaxException, IOException, ParseException {
    List<CalendarEvent> CEvent = WebcalCalendarImporter.importEventsFromWebcal(stringuri);
    File file = new File("output6.csv");
    FileWriter fw = new FileWriter(file);
    CSVPrinter csvPrinter = new CSVPrinter(fw, CSVFormat.DEFAULT.withHeader(
        "Title", "Description", "Start Date", "End Date"));
    for (CalendarEvent a : CEvent) {
      List<String> list = new ArrayList<>();
      list.add(a.getTitle());
      list.add(a.getDescription());
      list.add(a.getStartDate().toString());
      list.add(a.getEndDate().toString());
      csvPrinter.printRecord(list);
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

  public static File webcalltoJSON1(String a) throws URISyntaxException, IOException, ParseException {
    File file = webcalltoCSV(a);
    return CsvToJson.convert(file);
  }
}
