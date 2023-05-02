package pt.iscte_iul.ista.DIAM;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

public class OrgHorario {

    public List<CalendarEvent> CalFiltradoAno(List<CalendarEvent> CEvent, int Ano){
        List<CalendarEvent> eventosFiltrados = new ArrayList<>();
        for ( CalendarEvent b : CEvent){
            String c = b.getEndDate().toString();
            String[] data = c.split(" ");
            if(Integer.parseInt(data[5]) == Ano){
                 eventosFiltrados.add(b);
            }
        }
        return eventosFiltrados;
    }
    public List<CalendarEvent> CalFiltradoMes(List<CalendarEvent> CEvent, int Ano, int Mes){
        List<CalendarEvent> eventosFiltrados = new ArrayList<>();
        for ( CalendarEvent b : CEvent){
            String c = b.getEndDate().toString();
            String[] data = c.split(" ");
            if((Integer.parseInt(data[5]) == Ano)&&(checkData(data[1]) == Mes)){
                 eventosFiltrados.add(b);
            }
        }
        return eventosFiltrados;
    }
    public List<CalendarEvent> CalFiltradoDia(List<CalendarEvent> CEvent, int Ano, int Mes, int Dia){
        List<CalendarEvent> eventosFiltrados = new ArrayList<>();
        for ( CalendarEvent b : CEvent){
            String c = b.getEndDate().toString();
            String[] data = c.split(" ");
            if((Integer.parseInt(data[5]) == Ano)&&(checkData(data[1]) == Mes)&&(Integer.parseInt(data[2]) == Dia)){
                 eventosFiltrados.add(b);
            }
        }
        return eventosFiltrados;
    }
    public static int checkData(String mes) {
        switch (mes) {
            case "Jan":
                return 1;
            case "Feb":
                return 2;
            case "Mar":
                return 3;
            case "Apr":
                return 4;
            case "May":
                return 5;
            case "Jun":
                return 6;
            case "Jul":
                return 7;
            case "Aug":
                return 8;
            case "Sep":
                return 9;
            case "Oct":
                return 10;
            case "Nov":
                return 11;
            case "Dec":
                return 12;
            default:
                throw new IllegalArgumentException("Mês inválido: " + mes);
        }
    }

    public static void main(String[] args) throws ParseException, IOException {
        showCSV a = new showCSV();
        List<CalendarEvent> eventos = a.showHorario("output4.csv");
        OrgHorario b = new OrgHorario();
        for (CalendarEvent d :  b.CalFiltradoDia(eventos,2023,2,27)){
            System.out.println(d.getStartDate());
        }
            
    }


    
}

