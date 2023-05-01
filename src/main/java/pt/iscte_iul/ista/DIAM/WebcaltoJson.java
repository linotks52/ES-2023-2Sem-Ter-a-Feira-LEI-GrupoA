package pt.iscte_iul.ista.DIAM;

import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import net.fortuna.ical4j.data.CalendarBuilder;
import net.fortuna.ical4j.data.ParserException;
import net.fortuna.ical4j.model.Calendar;
import net.fortuna.ical4j.model.Component;
import net.fortuna.ical4j.model.component.VEvent;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;

import net.fortuna.ical4j.data.CalendarBuilder;
import net.fortuna.ical4j.data.ParserException;

public class WebcaltoJson {

  public static void WebcaltoJson1(String stringuri) throws URISyntaxException, IOException, ParseException {
    List<CalendarEvent> CEvent = WebcalCalendarImporter.importEventsFromWebcal(stringuri);
    FileWriter fw = new FileWriter("output4.csv");
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
  }


  public static void main(String[] args) throws IOException, ParserException, URISyntaxException, ParseException {
    WebcaltoJson1(
        "https://fenix.iscte-iul.pt/publico/publicPersonICalendar.do?method=iCalendar&username=dbrlo@iscte.pt&password=V5vG3uiVsWNaNDkKYT3tdP1R392Ma9Gq9l8Fl4QTdlz2Q99UWamZ0rKkRVhac7CwnXxCOimdsXWebkf2aKtqdkU8gn4SrnYicTp45qxidxeaUARk2UVhRlRLtKHcNJ13");

  }

}
