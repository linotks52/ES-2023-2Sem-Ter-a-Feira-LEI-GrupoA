package Timetable;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe SobrelotacaoHorario com métodos para identificar eventos sobrelotados e contá-los
 */
public class SobrelotacaoHorario {

    /**
     * Devolve uma lista de CalendarEvents que estão sobrelotados, ou seja o número de inscritos é maior que a capacidade da sala
     * Também altera o boolean isSobrelotado de todos os eventos que estão sobrelotados
     *
     * @param input lista de CalendarEvents
     * @return lista de CalendarEvents sobrelotados
     */
    public static List<CalendarEvent> getSobrelotacoes(List<CalendarEvent> input) {
        List<CalendarEvent> lista = new ArrayList<>();
        for (CalendarEvent e : input) {
            String s = e.getDescription();
            //System.out.println(s);
            int inscritos = 0;
            int salacap = 0;
            String[] stringarr = s.split(" ");
            for (String word : stringarr) {
                if (inscritos == 0 && word.matches("\\b\\d+\\b")) {
                    inscritos = Integer.parseInt(word);
                    //System.out.println(inscritos);
                } else if (salacap == 0 && inscritos > 0 && word.matches("\\b\\d+\\b")) {
                    salacap = Integer.parseInt(word);
                    //System.out.println(salacap);
                }
            }
            if (inscritos > salacap && salacap != 0){
                lista.add(e);
                isSobrelotado(e);
            }
        }
        return lista;
    }

    /**
     * Devolve o número de eventos sobrelotados 
     *
     * @param input lista de CalendarEvents 
     * @return o total de eventos sobrelotados
     */
    public static int getNrSobreLotacoes(List<CalendarEvent> input) {
        return getSobrelotacoes(input).size();
    }

    /**
     * Mete o atributo boolean isSobrelotado de um CalendarEvent a true
     *
     * @param input um CalendarEvent
     * @return 
     */

    public static void isSobrelotado(CalendarEvent e){
        e.setIsSobrelotado(true);
    }

    public static void main(String[] args) throws ParseException, IOException {
        List<CalendarEvent> eventos = showCSV.showHorario(new File("output.csv"));
        getSobrelotacoes(eventos); 
        //List<CalendarEvent> sobreLot = getSobrelotacoes(eventos); se quiseres a lista de eventos sobrelotados 
        int nr = getNrSobreLotacoes(eventos);
        System.out.println(nr);
    }
}
