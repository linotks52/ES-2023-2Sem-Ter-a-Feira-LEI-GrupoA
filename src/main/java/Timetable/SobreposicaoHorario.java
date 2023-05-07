package Timetable;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * Classe responsável por identificar a sobreposição de horários entre eventos
 * do calendário.
 */
public class SobreposicaoHorario {

    /**
     * 
     * Identifica as datas e horas com sobreposição de eventos a partir de uma lista
     * de eventos do calendário.
     * 
     * @param Cevents Lista de eventos do calendário.
     * 
     * @return Mapa contendo um evento e os eventos que estão sobrepostos
     * 
     */
    public static Map<CalendarEvent, List<CalendarEvent>> SobreposHorario(List<CalendarEvent> Cevents) {
        Map<CalendarEvent, List<CalendarEvent>> mapa = new HashMap<>();
        for (CalendarEvent a : Cevents) {
            for (CalendarEvent b : Cevents) {
                if (a != b && a != null && b != null && isBetween(a, b)) {
                    putmapa(mapa, a, b);
                    a.setIsSobreposto(true);
                    b.setIsSobreposto(true);


                }

            }

        }
        return mapa;
    }

    /**
     * 
     * Adiciona um evento a um mapa
     * 
     * @param mapa Mapa a qual vamos colocar o CalendarEvent
     * @param a    Calendar que vai estar no mapa
     * @param b    CalendarEvent que vai ser adicionado ao mapa, pelo a
     */
    private static void putmapa(Map<CalendarEvent, List<CalendarEvent>> mapa, CalendarEvent a, CalendarEvent b) {
        if (!mapa.containsKey(a)) {
            mapa.put(a, new ArrayList<CalendarEvent>());
        }

        List<CalendarEvent> eventosNoDia = mapa.get(a);
        eventosNoDia.add(b);
        mapa.put(a, eventosNoDia);
    }

    /**
     * 
     * Verifica se 2 CalendarEvents estão sobrepostos, ou seja, se um começa ou
     * acaba enquanto outro decorre ou
     * 
     * @param a CalendarEvent que vamos verificar
     * @param b CalendarEvent que já está no mapa
     * @return verdade se estão sobrepostos
     */

    public static boolean isBetween(CalendarEvent a, CalendarEvent b) {
        return ((a.getStartDate().before(b.getEndDate()) &&
                a.getStartDate().after(b.getStartDate()))
                || (a.getEndDate().before(b.getEndDate()) && a.getEndDate().after(b.getStartDate()))
                || a.getStartDate().equals(b.getStartDate()) ||
                a.getEndDate().equals(b.getEndDate()));

    }

    public static void main(String[] args) throws ParseException, IOException {

        List<CalendarEvent> eventos = showCSV.showHorario(
                new File("C:/Users/Utilizador/Desktop/ES2.0/ES-2023-2Sem-Ter-a-Feira-LEI-GrupoA/UTestShowCSVFen.csv"));

        CalendarEvent b = new CalendarEvent("OLA", "xd", new Date(2022, 5, 12, 15, 30, 0),
                new Date(2022, 5, 12, 17, 0, 0));
        eventos.add(b);
        eventos.add(b);

        Map<CalendarEvent, List<CalendarEvent>> mapa = SobreposicaoHorario.SobreposHorario(eventos);
        System.out.println(mapa.size());

        for (CalendarEvent ce : mapa.keySet()) {
            for (CalendarEvent z : mapa.get(ce)) {
                System.out.println(mapa.get(ce).size());

                System.out.println(ce.getTitle() + "has" + z.getTitle());
            }
        }

    }
}