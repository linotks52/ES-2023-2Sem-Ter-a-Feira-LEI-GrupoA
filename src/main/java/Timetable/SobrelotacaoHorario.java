package Timetable;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class SobrelotacaoHorario {
    
    public static List<CalendarEvent> getSobrelotacoes(List<CalendarEvent> input){
        List<CalendarEvent> lista = new ArrayList<>();
        for (CalendarEvent e : input){
            String s = e.getDescription();
            System.out.println(s);
            int inscritos = 0;
            int salacap = 0;
            String[] stringarr = s.split(" ");
            for (String word : stringarr){
                if (inscritos == 0 && word.matches("\\b\\d+\\b")) {
                    inscritos = Integer.parseInt(word);
                    System.out.println(inscritos);
                } else if (salacap == 0 && inscritos > 0 && word.matches("\\b\\d+\\b")) {
                    salacap = Integer.parseInt(word);
                    System.out.println(salacap);
                }
            }
            if (inscritos > salacap && salacap != 0)
                lista.add(e);
        }
        return lista;
    }

    public static int getNrSobreLotacoes(List<CalendarEvent> input){
        return getSobrelotacoes(input).size();
    }


    public static void main(String[] args) throws ParseException, IOException{
        List<CalendarEvent> eventos = showCSV.showHorario(new File("output.csv"));
        List<CalendarEvent> xd = getSobrelotacoes(eventos);
        System.out.println(getNrSobreLotacoes(xd));
    }
}

