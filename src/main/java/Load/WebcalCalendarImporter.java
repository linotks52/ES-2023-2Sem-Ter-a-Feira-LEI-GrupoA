package Load;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import Timetable.CalendarEvent;
/**
 * Classe que importa informacao de um Uri cria os eventos
 * @author tcast
 *
 */
public abstract class WebcalCalendarImporter {
	
	/**
	 * Função que recebe um string com um webcal e retorna um URI
	 * @param uristring webcalendar a ser importado
	 * @return URI gerado a partir do webcal
	 */
	public static String WebcaltoURI(String uristring) {
		String uri = uristring;
		String uri2 = "https";
		String newString = uri2 + uri.substring(6);
		return newString;
	}
	
	/**
	 * Funcão que recebe um uri e cria uma lista de eventos no calendário desse URI, nota se o URI for webcal
	 * usar a função {@link #WebcaltoURI(String)} primeiro
	 * @param uriString URI de onde serão extraídos os eventos
	 * @return Lista de Eventos de um Calendário
	 * @throws URISyntaxException caso a sintaxe do URI esteja incorreta
	 * @throws IOException caso hajam erros de input/output
	 * @throws ParseException caso hajam erros de conversão
	 */
    public static List<CalendarEvent> importEventsFromWebcal(String uriString) throws URISyntaxException, IOException, ParseException {
        List<CalendarEvent> events = new ArrayList<>();
        URL url = new URL(uriString);
        URLConnection connection = url.openConnection();
        connection.connect();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String line;
        CalendarEvent currentEvent = null;
        
        while ((line = bufferedReader.readLine()) != null) {
            if (line.startsWith("BEGIN:VEVENT")) {
                currentEvent = new CalendarEvent(line, line, null, null);
            } else if (line.startsWith("END:VEVENT")) {
                events.add(currentEvent);
                currentEvent = null;
            } else if (currentEvent != null) {
                parseLine(line, currentEvent);
            }
        }
        
        bufferedReader.close();
        return events;
    }
    
    /**
     * Função que recebe uma linha de texto e divide-a de acordo com os parametros estabelecidos e cria um evento com
     * essas informações
     * @param line uma linha de texto contendo informação do evento
     * @param event um evento do calendário que será atualizado com informação da linha de texto
     * @throws ParseException caso exista um erro a converter a data
     */
    private static void parseLine(String line, CalendarEvent event) throws ParseException {
        if (line.startsWith("SUMMARY:")) {
            event.setTitle(line.substring(8));
        } else if (line.startsWith("DESCRIPTION:")) {
            event.addDescription(line.substring(12));
        } else if (line.startsWith("DTSTART:")) {
            String dateString = line.substring(8);
            Date date = new SimpleDateFormat("yyyyMMdd'T'HHmmss").parse(dateString);
            event.setStartDate(date);
        } else if (line.startsWith("DTEND:")) {
            String dateString = line.substring(6);
            Date date = new SimpleDateFormat("yyyyMMdd'T'HHmmss").parse(dateString);
            event.setEndDate(date);
        } else if (!(line.startsWith("DTSTAMP") || line.startsWith("UID"))){       	
        	event.addDescription(line);
        }
        
    }
}



