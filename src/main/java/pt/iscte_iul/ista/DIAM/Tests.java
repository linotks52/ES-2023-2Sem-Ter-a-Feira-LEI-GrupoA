package pt.iscte_iul.ista.DIAM;

import java.io.IOException;
import java.net.URISyntaxException;
import java.text.ParseException;
import java.util.List;
import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * 
 * @author tcast
 * @version 1.0
 * Classe de testes
 */
public class Tests {
    public static void main(String[] args) throws IOException {
        String webcalUriString = "https://fenix.iscte-iul.pt/publico/publicPersonICalendar.do?method=iCalendar&username=dbrlo@iscte.pt&password=V5vG3uiVsWNaNDkKYT3tdP1R392Ma9Gq9l8Fl4QTdlz2Q99UWamZ0rKkRVhac7CwnXxCOimdsXWebkf2aKtqdkU8gn4SrnYicTp45qxidxeaUARk2UVhRlRLtKHcNJ13";
        
        
        try {
            List<CalendarEvent> events = WebcalCalendarImporter.importEventsFromWebcal(webcalUriString);
            
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
        
       /*  String url1 = "https://github.com/linotks52/ES-2023-2Sem-Ter-a-Feira-LEI-GrupoA/blob/Webcal_Import/README.md";
        File File = URLFileDownloader.downloadFileFromURL(url1);
        BufferedReader reader = new BufferedReader(new FileReader(File));
        String line;
        while ((line = reader.readLine()) != null) {
            System.out.println(line);
        }
        reader.close();
    }*/
}
}
    

    
    
//}

