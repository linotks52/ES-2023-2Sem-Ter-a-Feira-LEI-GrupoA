package timetable;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javafx.scene.layout.HBox;

import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;
import jfxtras.scene.control.agenda.Agenda;
import jfxtras.scene.control.agenda.Agenda.Appointment;
import jfxtras.scene.control.agenda.Agenda.LocalDateTimeRange;
import javafx.scene.control.Button;
import jfxtras.scene.control.agenda.Agenda.AppointmentGroup;
import jfxtras.scene.control.agenda.AgendaSkinSwitcher;
import jfxtras.scene.layout.GridPane;
import jfxtras.scene.control.LocalDateTimeTextField;

public class SimpleAgenda extends Application {
	private LocalDateTime currentdate;

    @Override
    public void start(Stage primaryStage) {
        // create agenda
        Agenda agenda = new Agenda();
        int numerosobrelotacoes = 0;
        int numerosobreposicoes = 0;
        
        currentdate = LocalDateTime.now();
        
        Callback<Agenda.LocalDateTimeRange, Void> callback = new Callback<Agenda.LocalDateTimeRange, Void>() {
            @Override
            public Void call(Agenda.LocalDateTimeRange range) {
                LocalDateTime start = range.getStartLocalDateTime();
                LocalDateTime end = range.getEndLocalDateTime();
                // Perform some action with the start and end times...
                return null;
            }
        };

        // set start and end times for the agenda
        LocalDateTimeRange range = new LocalDateTimeRange(LocalDateTime.now().minusDays(1), LocalDateTime.now().plusDays(1));
        callback.call(range);
        agenda.setDisplayedLocalDateTime(LocalDateTime.now());
        agenda.setLocalDateTimeRangeCallback(callback);
     // skin
        GridPane lGridPane = new GridPane();
        int lRowIdx = 0;
        lGridPane.add(new Label("Skin"), new GridPane.C().row(lRowIdx).col(0).halignment(HPos.RIGHT));
        AgendaSkinSwitcher lAgendaSkinSwitcher = new AgendaSkinSwitcher(agenda);
        lGridPane.add(lAgendaSkinSwitcher, new GridPane.C().row(lRowIdx).col(1));
        lRowIdx++;

        // displayed calendar
        
            lGridPane.add(new Label("Display"), new GridPane.C().row(lRowIdx).col(0).halignment(HPos.RIGHT));
            LocalDateTimeTextField lLocalDateTimeTextField = new LocalDateTimeTextField();
            lGridPane.add(lLocalDateTimeTextField, new GridPane.C().row(lRowIdx).col(1));
            lLocalDateTimeTextField.localDateTimeProperty().bindBidirectional(agenda.displayedLocalDateTime());
        
        
       

        // create appointments for the agenda
        List<Appointment> appointments = new ArrayList<>();
        
        //vai buscar lista de eventos ao current file uploaded na gui
        
        //List<CalendarEvent> lista = funcao.getlista(currentfile.json);
        String s = "ola";
        String d = "ola";
        List<CalendarEvent> lista = new ArrayList<CalendarEvent>();
        lista.add(new CalendarEvent(s, d, new Date(), new Date()));
        
        AppointmentGroup stylefornormal = new Agenda.AppointmentGroupImpl();
        stylefornormal.setStyleClass("appointmentnormal");
        AppointmentGroup styleforlotado = new Agenda.AppointmentGroupImpl();
        styleforlotado.setStyleClass("appointmentlotado");
        AppointmentGroup styleforsobreposto = new Agenda.AppointmentGroupImpl();
        styleforsobreposto.setStyleClass("appointmentsobreposto");
        
        for(CalendarEvent e : lista) {
        	Appointment exampleappointment = new Agenda.AppointmentImpl();
        	exampleappointment.setStartLocalDateTime(toLocalDateTime(e.getStartDate()));
        	exampleappointment.setEndLocalDateTime(toLocalDateTime(e.getStartDate()));
        	exampleappointment.setDescription(e.getDescription());
        	exampleappointment.setSummary(e.getTitle());
        	
        	//if(e.issobrepostohorario) muda a cor e adiciona ao numero de sobreposicoes
        	exampleappointment.setAppointmentGroup(stylefornormal);
        }

        Appointment appointment1 = new Agenda.AppointmentImpl();
        appointment1.setStartLocalDateTime(LocalDateTime.now().minusHours(2));
        appointment1.setEndLocalDateTime(LocalDateTime.now().plusMinutes(10));
        appointment1.setAppointmentGroup(stylefornormal);
        appointments.add(appointment1);


        // add appointments to agenda
        agenda.appointments().addAll(appointments);

        // create label to display agenda date
        Label dateLabel = new Label();
        dateLabel.textProperty().bind(agenda.displayedLocalDateTime().asString(""));
        
        Label sobreposicoes = new Label("Número de sobreposições: " + Integer.toString(numerosobreposicoes));
        
        Label sobrelotacoes = new Label("Número de sobrelotações: " + Integer.toString(numerosobrelotacoes));
        
        Button prevWeekButton = new Button("Previous Week");
        prevWeekButton.setOnAction(event -> {
        	agenda.setDisplayedLocalDateTime(currentdate.minusWeeks(1));
        	currentdate = currentdate.minusWeeks(1);
            primaryStage.show();
            // Update your UI with the new date...
        });
        
        Button nextWeekButton = new Button("Next Week");
        nextWeekButton.setOnAction(event -> {
        	agenda.setDisplayedLocalDateTime(currentdate.plusWeeks(1));
        	currentdate = currentdate.plusWeeks(1);
            primaryStage.show();
            // Update your UI with the new date...
        });
        
        HBox buttonBox = new HBox(10, prevWeekButton, nextWeekButton);
        
        

        // create vbox to hold agenda and label
        VBox vbox = new VBox(dateLabel, sobreposicoes, sobrelotacoes, agenda);

        // create borderpane and add vbox to center
        BorderPane root = new BorderPane();
        root.setCenter(vbox);
        root.setBottom(buttonBox);
        root.setRight(lGridPane);

        // set scene and show stage
        Scene scene = new Scene(root, 800, 600);
        scene.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    public static LocalDateTime toLocalDateTime(Date date) {
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
    }
    
    

    public static void main(String[] args) {
        launch(args);
    }

}
