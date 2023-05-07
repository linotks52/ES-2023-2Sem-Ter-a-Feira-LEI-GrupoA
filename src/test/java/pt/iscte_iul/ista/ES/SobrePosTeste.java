package pt.iscte_iul.ista.ES;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Test;
import Timetable.CalendarEvent;
import Timetable.SobreposicaoHorario;
import Timetable.showCSV;

public class SobrePosTeste {

    @Test
    public void testSobreposHorario() throws ParseException, IOException {

                List<CalendarEvent> eventos = showCSV.showHorario(
                        new File("C:/Users/Utilizador/Desktop/ES2.0/ES-2023-2Sem-Ter-a-Feira-LEI-GrupoA/UTestShowCSVFen.csv"));
                
                CalendarEvent a = eventos.get(0);
                CalendarEvent b = eventos.get(1);
                CalendarEvent c = eventos.get(2);
                Map<CalendarEvent, List<CalendarEvent>> mapa = SobreposicaoHorario.SobreposHorario(eventos);
          
                assertEquals(mapa.containsKey(a), false);
                assertEquals(mapa.containsKey(b), true);
                assertEquals(mapa.containsKey(c), true);
        
            }
}
