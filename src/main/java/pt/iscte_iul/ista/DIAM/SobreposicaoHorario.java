package pt.iscte_iul.ista.DIAM;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SobreposicaoHorario {

    public Map<Date, Integer> SobreposicaoHorario(List<CalendarEvent> Cevents){
        Map<Date, Integer> mapa = new HashMap<>();
        Map<Date, Integer> mapaComSobreposicoes = new HashMap<>();
        for (CalendarEvent ce : Cevents){
            if(!mapa.containsKey(ce.getStartDate())){
                mapa.put(ce.getStartDate(), 1);
            }else{
                int a = mapa.get(ce.getStartDate());
                mapa.put(ce.getStartDate(), a+1);
            }
        }
        
        for ( Date a : mapa.keySet()){
            if(mapa.get(a) != 1){
            //  System.out.println(a);
             //   System.out.println(mapa.get(a));
                mapaComSobreposicoes.put(a,mapa.get(a));
            }
        }
        return mapaComSobreposicoes;
    }


    public static void main(String[] args) throws ParseException, IOException {
        showCSV a = new showCSV();
        List<CalendarEvent> eventos = a.showHorario("output4.csv");
        CalendarEvent b = new CalendarEvent("OLA","xd", new Date(2022, 5, 12, 15, 30, 0), new Date(2022, 5 , 12, 17, 0, 0));
        eventos.add(b);
        eventos.add(b);
        SobreposicaoHorario c = new SobreposicaoHorario();
        Map<Date, Integer> mapa = c.SobreposicaoHorario(eventos);
        for ( Date d : mapa.keySet()){
            System.out.println(d); 
            System.out.println(mapa.get(d));
        }
    }
}
