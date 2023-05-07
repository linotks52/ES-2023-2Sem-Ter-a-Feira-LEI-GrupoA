package pt.iscte_iul.ista.ES;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Test;
import Timetable.CalendarEvent;
import Timetable.SobreposicaoHorario;

public class SobrePosTeste {

    @Test
    public void testSobreposHorario() {
        List<CalendarEvent> eventos = new ArrayList<>();
        CalendarEvent a = new CalendarEvent("Evento 1", "Descrição do evento 1",
                new Date(2023, 5, 10, 14, 0), new Date(2023, 5, 10, 16, 0));
        eventos.add(a);
        CalendarEvent b = new CalendarEvent("Evento 2", "Descrição do evento 2",
                new Date(2023, 5, 10, 15, 0), new Date(2023, 5, 10, 17, 0));
        eventos.add(b);
        CalendarEvent c = new CalendarEvent("Evento 3", "Descrição do evento 3",
                new Date(2023, 5, 10, 16, 0), new Date(2023, 5, 10, 18, 0));
        eventos.add(c);

        Map<Date, List<CalendarEvent>> mapa = SobreposicaoHorario.SobreposHorario(eventos);

        assertEquals(1, mapa.size());

        Date dataSobreposicao = new Date(2023, 5, 10);
        List<CalendarEvent> eventosSobrepostos = mapa.get(dataSobreposicao);
        assertEquals(3, eventosSobrepostos.size());
        assertEquals(true, eventosSobrepostos.get(0).getIsSobreposto());
        assertEquals(true, eventosSobrepostos.get(1).getIsSobreposto());
        assertEquals(true, eventosSobrepostos.get(2).getIsSobreposto());
    }
}
