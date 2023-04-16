package pt.iscte_iul.ista.DIAM;

import java.io.IOException;
import java.net.URISyntaxException;
import java.text.ParseException;
import java.util.List;

/**
 * 
 * @author tcast
 * @version 1.0
 * Classe de testes
 */
public class WebcalCalendarTest {
    public static void main(String[] args) throws IOException {
        String webcalUriString = "https://fenix.iscte-iul.pt/publico/publicPersonICalendar.do?method=iCalendar&username=dbrlo@iscte.pt&password=V5vG3uiVsWNaNDkKYT3tdP1R392Ma9Gq9l8Fl4QTdlz2Q99UWamZ0rKkRVhac7CwnXxCOimdsXWebkf2aKtqdkU8gn4SrnYicTp45qxidxeaUARk2UVhRlRLtKHcNJ13";
        WebcalCalendarImporter importer = new WebcalCalendarImporter();
        
        try {
            List<CalendarEvent> events = importer.importEventsFromWebcal(webcalUriString);
            
            for (CalendarEvent event : events) {
                System.out.println("Title: " + event.getTitle());
                System.out.println("Description: " + event.getDescription());
                System.out.println("Start Date: " + event.getStartDate());
                System.out.println("End Date: " + event.getEndDate());
                System.out.println();
            }
        } catch (URISyntaxException | ParseException e) {
            e.printStackTrace();
        }
    }
}

