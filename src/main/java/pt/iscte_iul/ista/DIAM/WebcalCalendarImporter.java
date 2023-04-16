package pt.iscte_iul.ista.DIAM;

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

/**
 * Classe que importa informacao de um Uri cria os eventos
 * @author tcast
 *
 */
public class WebcalCalendarImporter {
	
	/**
	 * Function that receives a String with webcal and returns it in a workable URI
	 * @param String
	 * @return String
	 */
	public String WebcaltoURI(String uristring) {
		String uri = uristring;
		String uri2 = "https";
		String newString = uri2 + uri.substring(6);
		return newString;
	}
	
	/**
	 * Funcão que recebe um uri e cria uma lista de eventos no calendário desse URI, nota se o URI for webcal
	 * usar a função {@link #WebcaltoURI(String)} primeiro
	 * @param uriString
	 * @return Lista de Eventos de um Calendário
	 * @throws URISyntaxException
	 * @throws IOException
	 * @throws ParseException
	 */
    public List<CalendarEvent> importEventsFromWebcal(String uriString) throws URISyntaxException, IOException, ParseException {
        List<CalendarEvent> events = new ArrayList<>();
        URL url = new URL(uriString);
        URLConnection connection = url.openConnection();
        connection.connect();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String line;
        CalendarEvent currentEvent = null;
        
        while ((line = bufferedReader.readLine()) != null) {
            if (line.startsWith("BEGIN:VEVENT")) {
                currentEvent = new CalendarEvent();
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
     * @param line
     * @param event
     * @throws ParseException
     */
    private void parseLine(String line, CalendarEvent event) throws ParseException {
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

/**
 * Classe de eventos de Calendario
 * @author tcast
 * @see #CalendarEvent()
 * @see #CalendarEvent(String title, String description, Date startDate, Date endDate)
 */
class CalendarEvent {
	
	/**
	 * Calendar event constructor (Title, Description, startDate, endDate)
	 * @param String
	 * @param String
	 * @param Date
	 * @param Date
	 */
    public CalendarEvent(String title, String description, Date startDate, Date endDate) {
		this.title = title;
		this.description = description;
		this.startDate = startDate;
		this.endDate = endDate;
	}
    
    /**
     * Calendar event Constructor
     */
    public CalendarEvent() {
    	this.description = "";
    }
    
    /**
     * Returs the title of the event
     * @return String
     */
	public String getTitle() {
		return title;
	}
	
	/**
	 * Sets the title of the event
	 * @param String
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	
	/**
	 * Returns the description of the event
	 * @return String
	 */
	public String getDescription() {
		return description;
	}
	
	/**
	 * Sets the description of the event
	 * @param String
	 */
	public void addDescription(String description) {
		String str1 = this.description;
		String str2 = description;
		String str3 = str1 + str2;
		this.description = str3;
	}
	
	/**
	 * Returns the date of the event
	 * @return Date
	 */
	public Date getStartDate() {
		return startDate;
	}
	
	/**
	 * Sets the start date of the event
	 * @param Date
	 */
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	
	/**
	 * Returns the end date of the event
	 * @return Date
	 */
	public Date getEndDate() {
		return endDate;
	}
	
	/**
	 * Sets the Date of the event
	 * @param Date
	 */
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	
	private String title;
    private String description;
    private Date startDate;
    private Date endDate;
    
 
}


