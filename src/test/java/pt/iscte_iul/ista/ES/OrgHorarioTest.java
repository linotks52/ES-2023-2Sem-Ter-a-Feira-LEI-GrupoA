package pt.iscte_iul.ista.ES;

import org.junit.jupiter.api.Test;

import timetable.CalendarEvent;
import timetable.OrgHorario;
import timetable.showCSV;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.temporal.WeekFields;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OrgHorarioTest {
    @Test
    public void testCalFiltradoAno() throws ParseException, IOException {
        OrgHorario orgHorario = new OrgHorario();

        // criar eventos de calendário
        List<CalendarEvent> eventos = showCSV.showHorario(
                new File("C:/Users/Utilizador/Desktop/ES2.0/ES-2023-2Sem-Ter-a-Feira-LEI-GrupoA/UTestShowCSVFen.csv"));

        // filtrar eventos por ano
        List<CalendarEvent> eventosFiltrados = orgHorario.CalFiltradoAno(eventos, 2022);

        // verificar se os eventos filtrados ocorrem apenas no ano especificado

        for (CalendarEvent g : eventosFiltrados) {
            String s = "";
            s = (g.getStartDate().toString());
            assertEquals(s.split(" ")[5], "2022");
        }
        assertEquals(3, eventosFiltrados.size());
    }

    @Test
    public void testCalFiltradoSemana() throws ParseException, IOException {
        OrgHorario orgHorario = new OrgHorario();
        List<CalendarEvent> eventos = showCSV.showHorario(
                new File("C:/Users/Utilizador/Desktop/ES2.0/ES-2023-2Sem-Ter-a-Feira-LEI-GrupoA/UTestShowCSVFen.csv"));
        List<CalendarEvent> eventosFiltrados = orgHorario.CalFiltradoSemana(eventos, 20);

        // os 3 ultimos eventos do UTestShowCSVFen.csv sao da semana 20 do ano
        for (CalendarEvent g : eventosFiltrados) {
            assertEquals(OrgHorario.DateToLocalDateTime(g.getStartDate()).get(WeekFields.ISO.weekOfWeekBasedYear()),
                    20);
        }
    }

    @Test
    public void testCalFiltradoDia() throws ParseException, IOException {
        OrgHorario orgHorario = new OrgHorario();
        List<CalendarEvent> eventos = showCSV.showHorario(
                new File("C:/Users/Utilizador/Desktop/ES2.0/ES-2023-2Sem-Ter-a-Feira-LEI-GrupoA/UTestShowCSVFen.csv"));
        List<CalendarEvent> eventosFiltrados = orgHorario.CalFiltradoSemana(eventos, 11);

        for (CalendarEvent g : eventosFiltrados) {
            String s = "";
            s = (g.getStartDate().toString());
            assertEquals(s.split(" ")[2], "2022");
            assertEquals(s, "20");
        }
    }

    @Test
    public void testCalFiltradoMes() throws ParseException, IOException{
        OrgHorario orgHorario = new OrgHorario();
        List<CalendarEvent> eventos = showCSV.showHorario(
                new File("C:/Users/Utilizador/Desktop/ES2.0/ES-2023-2Sem-Ter-a-Feira-LEI-GrupoA/UTestShowCSVFen.csv"));
        List<CalendarEvent> eventosFiltrados = orgHorario.CalFiltradoSemana(eventos, 5);

        for (CalendarEvent g : eventosFiltrados) {
            String s = "";
            s = (g.getStartDate().toString());
            assertEquals(s.split(" ")[1], "May");
           
        }
    }
    
}
