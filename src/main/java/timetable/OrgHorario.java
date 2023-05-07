package timetable;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.WeekFields;


/**
 * Class Responsável por o filtrar uma lista de CalendarEvents
 */
public class OrgHorario {

    /**
     * @param CEvent
     * @param Ano
     * @return
     */

    /**
     * Retorna uma Lista com os CalendarEvents que decorrem duranto o ano
     * 
     * @param CEvent é a lista a ser filtrada
     * @param Ano    é o ano para qual os eventos vão ser filtrados
     * @return uma lista de CalendarEvents que ocorrem no ano específicado
     */
    public List<CalendarEvent> CalFiltradoAno(List<CalendarEvent> CEvent, int Ano) {
        List<CalendarEvent> eventosFiltrados = new ArrayList<>();
        for (CalendarEvent b : CEvent) {
            String c = b.getEndDate().toString();
            String[] data = c.split(" ");
            if (Integer.parseInt(data[5]) == Ano) {
                eventosFiltrados.add(b);
            }
        }
        return eventosFiltrados;
    }

    /**
     * Retorna uma lista de eventos do calendário que ocorrem durante o mês e ano
     * especificados
     * 
     * @param CEvent A lista de eventos do calendário a ser filtrada
     * @param Ano    O ano para o qual os eventos serão filtrados
     * @param Mes    O mês para o qual os eventos serão filtrados (1 a 12)
     * @return Uma lista de eventos do calendário que ocorrem no mês e ano
     *         especificados
     */

    public List<CalendarEvent> CalFiltradoMes(List<CalendarEvent> CEvent, int Ano, int Mes) {
        if (Mes >= 1 || Mes <= 12) {
        }
        List<CalendarEvent> eventosFiltrados = new ArrayList<>();
        for (CalendarEvent b : CEvent) {
            String c = b.getEndDate().toString();
            String[] data = c.split(" ");
            if ((Integer.parseInt(data[5]) == Ano) && (checkData(data[1]) == Mes)) {
                eventosFiltrados.add(b);
                Date a = new Date();

            }
        }
        return eventosFiltrados;

    }

    /**
     * Retorna uma lista de eventos do calendário que ocorrem durante o mês, ano e
     * dia específicados
     * 
     * @param CEvent A lista de eventos do calendário a ser filtrada
     * @param Ano    O ano para o qual os eventos serão filtrados
     * @param Mes    O mês para o qual os eventos serão filtrados (1 a 12)
     * @param Dia    O dia para o qual os eventos serão filtrados. Este parametro é
     *               verificado através da função diaValido
     * @return Uma lista de eventos do calendário que ocorrem no mês, no ano e no
     *         dia especificados
     */

    public List<CalendarEvent> CalFiltradoDia(List<CalendarEvent> CEvent, int Ano, int Mes, int Dia) {
        List<CalendarEvent> eventosFiltrados = new ArrayList<>();
        if (diaValido(Mes, Dia)) {
            for (CalendarEvent b : CEvent) {
                String c = b.getEndDate().toString();
                String[] data = c.split(" ");
                if ((Integer.parseInt(data[5]) == Ano) && (checkData(data[1]) == Mes)
                        && (Integer.parseInt(data[2]) == Dia)) {
                    eventosFiltrados.add(b);
                }
            }
        }
        return eventosFiltrados;
    }

   
   /**
     * Retorna uma lista de eventos do calendário que ocorrem durante uma semana especificada
     * 
     * @param CEvent A lista de eventos do calendário a ser filtrada
     * @param Semana A semana para a qual a CEvent vai ser filtrada
     * @return Uma lista de eventos do calendário que ocorrem na semana 
     */
    public List<CalendarEvent> CalFiltradoSemana(List<CalendarEvent> CEvent, int Semana) {
        List<CalendarEvent> ListaFiltradaporSemana = new ArrayList<>();
        for (CalendarEvent a : CEvent) {
            int semanaevent = DateToLocalDateTime(a.getStartDate()).get(WeekFields.ISO.weekOfWeekBasedYear());
            if (semanaevent == Semana) {
                ListaFiltradaporSemana.add(a);
            }
        }
        return ListaFiltradaporSemana;
    }
/**
     * Transforma um Date em um LocalDateTime
     * 
     * @param date  o Date que queremos transformar
     * @return Retorna o localdateTime criado através do date
     */
    public static LocalDateTime DateToLocalDateTime(Date date) {
        Instant instant = date.toInstant();
        LocalDateTime localDateTime = instant.atZone(ZoneId.systemDefault()).toLocalDateTime();
        return localDateTime;
    }

    /**
     * Retorna o número do mês correspondente à string de mês dada
     * 
     * @param mes A string de mês para ser verificada
     * @return O número do mês correspondente (1 a 12)
     * @throws IllegalArgumentException caso o mês pedido não seja válido
     */

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

    /**
     * Verifica se o mês é valido, entre 1 - 12, e se a para cada mes esse dia
     * existe
     * 
     * @param mes Número do mês que pretendemos verificar
     * @param dia Número do dia que pretendemos verificar
     * @return Retorna true se o mês é valido e se para esse mês o dia existe, e
     *         false caso ou o mês seja inválido ou se o dia não existir nesse mês
     */
    public boolean diaValido(int mes, int dia) {
        if (mes < 1 || mes > 12) {
            System.out.println("Mes inserido Inválido");
            return false;
        }
        switch (mes) {
            case 2:
                if (dia < 1 || dia > 28) {
                    System.out.println("Dia inválido para o mês de fevereiro");
                    return false;
                }
                break;
            case 4:
            case 6:
            case 9:
            case 11:
                if (dia < 1 || dia > 30) {
                    System.out.println("Dia " + dia + " inválido para o mes " + mes);
                    return false;
                }
                break;
            default:
                if (dia < 1 || dia > 31) {
                    System.out.println("Dia " + dia + " inválido para o mes " + mes);
                    return false;
                }
        }
        return true;
    }

    public static void main(String[] args) throws ParseException, IOException {
        showCSV a = new showCSV();
        List<CalendarEvent> eventos = showCSV.showHorario(new File("C:/Users/Utilizador/Desktop/ES2.0/ES-2023-2Sem-Ter-a-Feira-LEI-GrupoA/UTestShowCSVFen.csv"));
        OrgHorario b = new OrgHorario();
        for (CalendarEvent c : eventos){
           System.out.println( DateToLocalDateTime(c.getStartDate()).get(WeekFields.ISO.weekOfWeekBasedYear()));
        }
       // List<CalendarEvent> c = b.CalFiltradoAno(eventos, 2022);
        //
        //for (CalendarEvent g : c){
        //    System.out.println(g.getStartDate());
       // }

       // for (CalendarEvent d : eventos) {
       //     LocalDateTime e = b.DateToLocalDateTime(d.getStartDate());
       //     System.out.println(e.get(WeekFields.ISO.weekOfWeekBasedYear()) + " " + d.getStartDate().toString());
            // System.out.println(d.getStartDate().toString());
       // }

    }

}
