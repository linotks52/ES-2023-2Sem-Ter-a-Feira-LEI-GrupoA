package Timetable;

import java.util.Date;


/**
 * Classe de eventos de Calendario
 * @author tcast
 * @see #CalendarEvent()
 * @see #CalendarEvent(String title, String description, Date startDate, Date endDate)
 */

public class CalendarEvent {
	
	/**
	 * Calendar event constructor (Title, Description, startDate, endDate)
	 * @param title Título do evento
	 * @param description Descrição do evento
	 * @param startDate Data de início do evento
	 * @param endDate Data de fim do enveot
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
	 * @param title Título do evento
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
	 * @param description Descrição do evento
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
	 * @param startDate Data de início do evento
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
	 * @param endDate Data de fim do evento
	 */
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	
	public Boolean getIsSobreposto(){
		return isSobreposto;
	}

	public void setIsSobreposto(Boolean b){
		isSobreposto = b;
	}

	public Boolean getIsSobrelotado(){
		return isSobrelotado;
	}

	public void setIsSobrelotado(Boolean b){
		isSobrelotado = b;
	}
	
	private String title;
    private String description;
    private Date startDate;
    private Date endDate;
    private boolean isSobreposto;
	private boolean isSobrelotado;

	

}