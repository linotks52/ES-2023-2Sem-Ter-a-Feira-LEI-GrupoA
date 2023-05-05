package pt.iscte_iul.ista.DIAM;

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
     * @return Mapa contendo as datas e horas com sobreposição de eventos e uma
     *         lista com os CalendarEvents que ocorrem nessa data
     */
    public Map<Date, List<CalendarEvent>> SobreposicaoHorario(List<CalendarEvent> Cevents) {
        Map<Date, List<CalendarEvent>> mapa = new HashMap<>();
        Map<Date, List<CalendarEvent>> mapaComSobreposicoes = new HashMap<>();

        for (CalendarEvent ce : Cevents) {
            if (!mapa.containsKey(ce.getStartDate())) {
                List<CalendarEvent> a = new ArrayList<>();
                mapa.put(ce.getStartDate(), a);
            }

            List<CalendarEvent> eventosNoDia = mapa.get(ce.getStartDate());
            eventosNoDia.add(ce);
        }

        for (Date b : mapa.keySet()) {
            if (mapa.get(b).size() > 1) {
                // System.out.println(a);
                // System.out.println(mapa.get(b));
                mapaComSobreposicoes.put(b, mapa.get(b));
            }
        }
        return mapaComSobreposicoes;
    }

    public static void main(String[] args) throws ParseException, IOException {
        showCSV a = new showCSV();
        List<CalendarEvent> eventos = showCSV.showHorario(new File("output4.csv"));
        CalendarEvent b = new CalendarEvent("OLA", "xd", new Date(2022, 5, 12, 15, 30, 0),
                new Date(2022, 5, 12, 17, 0, 0));
        eventos.add(b);
        eventos.add(b);
        SobreposicaoHorario c = new SobreposicaoHorario();
        Map<Date, List<CalendarEvent>> mapa = c.SobreposicaoHorario(eventos);

        for (Date d : mapa.keySet()) {
            int count = 0;

            for (CalendarEvent e : mapa.get(d)) {
                count = count + 1;
                System.out.println(e.getTitle());
            }
            System.out.println(d + " " + count);

        }
    }
}
