package pt.iscte_iul.ista.ES;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;



import Timetable.CalendarEvent;
import Timetable.showCSV;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ShowCSVTeste {

    /**
     * @throws IOException
     * @throws ParseException
     */
    @Test
    public void ShowCSV() throws IOException, ParseException {
        File a = new File("UtestShowCSVFen.csv");
        a.createNewFile();
        FileWriter writer = new FileWriter(a);
        writer.write("Title,Description,StartDate,EndDate\n");
        writer.write("Event 1,Description 1,Wed Apr 11 12:00:00 BST 2023,Wed Apr 11 12:00:00 BST 2023\n");
        writer.write("Event 2,Description 2,Wed May 20 15:00:00 BST 2022,Wed May 20 17:00:00 BST 2022\n");
        writer.close();

        List<CalendarEvent> eventos = showCSV.showHorario(a);

        // Call the method being tested
        List<CalendarEvent> events = showCSV.showHorario(a);

        // Verify the results
        assertEquals(2, events.size());
        assertEquals("Event 1", events.get(0).getTitle());
        assertEquals("Description 1", events.get(0).getDescription());
        assertEquals("Tue Apr 11 12:00:00 BST 2023", events.get(0).getStartDate().toString());
        assertEquals("Tue Apr 11 12:00:00 BST 2023", events.get(0).getEndDate().toString());
        assertEquals("Event 2", events.get(1).getTitle());
        assertEquals("Description 2", events.get(1).getDescription());
        assertEquals("Fri May 20 15:00:00 BST 2022", events.get(1).getStartDate().toString());
        assertEquals("Fri May 20 17:00:00 BST 2022", events.get(1).getEndDate().toString());
    }

}