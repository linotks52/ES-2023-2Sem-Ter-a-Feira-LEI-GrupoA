package pt.iscte_iul.ista.ES;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import Timetable.CalendarEvent;
import Timetable.SobrelotacaoHorario;

class SobrelotacaoHorarioTest {

    @Test
    void testGetSobrelotacoes() {
        // Testar sem eventos
        List<CalendarEvent> input = new ArrayList<>();
        List<CalendarEvent> result = SobrelotacaoHorario.getSobrelotacoes(input);
        assertTrue(result.isEmpty());

        // Testar sem eventos sobrelotados
        CalendarEvent e1 = new CalendarEvent("Event 1", "10 20",new Date(), new Date());
        CalendarEvent e2 = new CalendarEvent("Event 2", "10 10",new Date(), new Date());
        input.add(e1);
        input.add(e2);
        result = SobrelotacaoHorario.getSobrelotacoes(input);
        assertTrue(result.isEmpty());

        // Testar com eventos sobrelotados
        CalendarEvent e3 = new CalendarEvent("Event 3", "20 10",new Date(), new Date());
        CalendarEvent e4 = new CalendarEvent("Event 4", "16 15",new Date(), new Date());
        input.add(e3);
        input.add(e4);
        result = SobrelotacaoHorario.getSobrelotacoes(input);
        assertEquals(2, result.size());
        assertTrue(result.contains(e3));
        assertTrue(result.contains(e4));
        assertTrue(e3.getIsSobrelotado());
        assertTrue(e4.getIsSobrelotado());
    }

    @Test
    void testGetNrSobreLotacoes() {
        // Testar sem eventos
        List<CalendarEvent> input = new ArrayList<>();
        int result = SobrelotacaoHorario.getNrSobreLotacoes(input);
        assertEquals(0, result);

        // Testar sem eventos sobrelotados
        CalendarEvent e1 = new CalendarEvent("Event 1", "10 20",new Date(), new Date());
        CalendarEvent e2 = new CalendarEvent("Event 2", "10 10",new Date(), new Date());
        input.add(e1);
        input.add(e2);
        result = SobrelotacaoHorario.getNrSobreLotacoes(input);
        assertEquals(0, result);

        // Testar com eventos sobrelotados
        CalendarEvent e3 = new CalendarEvent("Event 3", "20 10",new Date(), new Date());
        CalendarEvent e4 = new CalendarEvent("Event 4", "16 15",new Date(), new Date());
        input.add(e3);
        input.add(e4);
        result = SobrelotacaoHorario.getNrSobreLotacoes(input);
        assertEquals(2, result);
    }

}
